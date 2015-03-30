package com.androidproject.organizasyonum;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Firat on 23.3.2015.
 */
public class login extends Activity implements View.OnClickListener  {
    Helper helper = null;
    private EditText user, pass;
    private Button mSubmit, mRegister;
    // Progress Dialog
    private ProgressDialog pDialog;

    //testing on Emulator:
    private static final String LOGIN_URL = "http://192.168.1.101:80/webservis/android_organization/login.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_USERID="user_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        helper = Helper.INSTANCE;

        //setup input fields
        user = helper.getirView(this, R.id.username, EditText.class);
        pass = helper.getirView(this, R.id.password, EditText.class);

        //setup buttons
        mSubmit = helper.getirView(this, R.id.login, Button.class);
        mRegister = helper.getirView(this, R.id.register, Button.class);

        //register listeners
        mSubmit.setOnClickListener(this);
        mRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.login:
                new AttemptLogin().execute();
                break;
            case R.id.register:
                helper.goster(this, register.class);
                break;
            default:
                break;
        }
    }

    class AttemptLogin extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = helper.showLoadingDialogBox(login.this, AlertDialog.THEME_HOLO_LIGHT, "Giriş Yapılıyor...", true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag

            String username = user.getText().toString();
            String password = pass.getText().toString();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", password));

            helper.yazLog(Helper.logTypes.debug, "HttpRequest", "start " + LOGIN_URL);
            // getting product details by making HTTP request
            String json = JSONParser.INSTANCE.makeHttpRequestReturnJSONString(LOGIN_URL, JSONParser.requestMethod.Post, params);

            helper.yazLog(Helper.logTypes.debug, "HttpRequest", "finish " + LOGIN_URL);

            return json;
        }

        /**
         * After completing background task Dismiss the progress dialog
         *
         * @param file_url
         */
        protected void onPostExecute(String file_url) {
            helper.yazLog(Helper.logTypes.debug, file_url);
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            JSONObject jObj = JSONParser.INSTANCE.convertJSONStringToJSONObject(file_url);
            if (jObj == null) {
                helper.gosterBilgiMesaji(login.this, "Profil bilgisi okunamadı...");
                return;
            }

            int success = 0;
            String message = null;
            int userid=0;
            try {
                success = jObj.getInt(TAG_SUCCESS);
                message = jObj.getString(TAG_MESSAGE);
                userid = jObj.getInt(TAG_USERID);
            } catch (JSONException e) {
                e.printStackTrace();
                helper.yazLog(Helper.logTypes.error, e.toString());
                helper.gosterBilgiMesaji(login.this, "Giriş denemesi başarısız...");
            }

            if (success == 1) {
                String username = user.getText().toString();
                //String password = pass.getText().toString();
                // save user data
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(login.this);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("username", username);
                //edit.putString("password", password);
                edit.putInt("user_id",userid);
                edit.commit();

                helper.cikis(login.this);
                helper.goster(login.this, menu.class);
            }
            helper.gosterBilgiMesaji(login.this, message);
        }
    }
}
