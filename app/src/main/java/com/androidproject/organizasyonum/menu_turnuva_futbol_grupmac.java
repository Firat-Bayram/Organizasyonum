package com.androidproject.organizasyonum;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Firat on 25.3.2015.
 */
public class menu_turnuva_futbol_grupmac extends ListActivity implements View.OnClickListener {
    // Progress Dialog
    private ProgressDialog pDialog;
    private Helper helper = null;
    private static final String READ_GROUPS_URL = "http://192.168.1.101:80/webservis/futbolgroups.php";

    private static final String TAG_GROUPS = "groups";

    private static final String TAG_GROUPNAME = "groupname";
    private static final String TAG_GROUP_ID = "group_id";
    private static final String TAG_USERNAME = "username";

    //private Button btnEkleGrup,btnEkleTek;
    //An array of all of our comments
    private JSONArray mComments = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_turnuva_futbol_grupmac);
        helper = Helper.INSTANCE;

      /*  //setup buttons
        btnEkleGrup = helper.getirView(this, R.id.btnEkleGrup, Button.class);
        btnEkleTek = helper.getirView(this, R.id.btnEkleTek, Button.class);
        //register listeners
        btnEkleGrup.setOnClickListener(this);
        btnEkleTek.setOnClickListener(this);*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        //loading the comments via AsyncTask
        new LoadFootballGroups().execute();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
       /* switch (v.getId()) {
            case R.id.btnEkleGrup:
                //helper.goster(this, menu_turnuva.class);
                break;
            case R.id.btnEkleTek:
                //helper.goster(this, register.class);
                break;
            default:
                break;
        }*/
    }

    /**
     * Inserts the parsed data into the listview.
     */
    private void updateList(ArrayList<HashMap<String, String>> mCommentList) {
        // For a ListActivity we need to set the List Adapter, and in order to do
        //that, we need to create a ListAdapter.  This SimpleAdapter,
        //will utilize our updated Hashmapped ArrayList,
        //use our single_post xml template for each item in our list,
        //and place the appropriate info from the list to the
        //correct GUI id.  Order is important here.
        ListAdapter adapter = new SimpleAdapter(this, mCommentList,
                R.layout.menu_turnuva_futbol_grupmac_item, new String[]{TAG_GROUPNAME,
                TAG_USERNAME}, new int[]{R.id.groupname,
                R.id.username});

        // I shouldn't have to comment on this one:
        setListAdapter(adapter);

        // Optional: when the user clicks a list item we
        //could do something.  However, we will choose
        //to do nothing...
        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> params = (HashMap<String, String>) parent.getAdapter().getItem(position);
                /*try{
                    helper.goster(menu_turnuva_futbol_grupmac.this,CommentDetail.class,params);
                }catch (Exception e){
                    e.printStackTrace();
                }*/

                // This method is triggered if an item is click within our
                // list. For our example we won't be using this, but
                // it is useful to know in real life applications.

            }
        });
    }

    public class LoadFootballGroups extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = helper.showLoadingDialogBox(menu_turnuva_futbol_grupmac.this, AlertDialog.THEME_HOLO_LIGHT, "Gruplar Yükleniyor...", true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... arg0) {
            String json = JSONParser.INSTANCE.makeHttpRequestReturnJSONString(READ_GROUPS_URL, JSONParser.requestMethod.Post, null);
            helper.yazLog(Helper.logTypes.debug, "HttpRequest", "finish " + READ_GROUPS_URL);
            return json;
        }

        @Override
        protected void onPostExecute(String file_url) {
            //super.onPostExecute(result);
            helper.yazLog(Helper.logTypes.debug, file_url);
            pDialog.dismiss();
            //we will develop this method in version 2
            //updateList();

            JSONObject jObj = JSONParser.INSTANCE.convertJSONStringToJSONObject(file_url);
            if (jObj == null) {
                helper.gosterBilgiMesaji(menu_turnuva_futbol_grupmac.this, "Grup bilgisi okunamadı...");
                return;
            }
            ArrayList<HashMap<String, String>> mCommentList = new ArrayList<HashMap<String, String>>();
            //when parsing JSON stuff, we should probably
            //try to catch any exceptions:
            try {
                //I know I said we would check if "Posts were Avail." (success==1)
                //before we tried to read the individual posts, but I lied...
                //mComments will tell us how many "posts" or comments are
                //available
                mComments = jObj.getJSONArray(TAG_GROUPS);

                // looping through all posts according to the json object returned
                for (int i = 0; i < mComments.length(); i++) {
                    JSONObject c = mComments.getJSONObject(i);

                    //gets the content of each tag
                    String groupId = c.getString(TAG_GROUP_ID);
                    String groupName = c.getString(TAG_GROUPNAME);
                    String username = c.getString(TAG_USERNAME);

                    // creating new HashMap
                    HashMap<String, String> map = new HashMap<String, String>();

                    map.put(TAG_GROUP_ID, groupId);
                    map.put(TAG_GROUPNAME, groupName);
                    map.put(TAG_USERNAME, username);

                    // adding HashList to ArrayList
                    mCommentList.add(map);
                    //annndddd, our JSON data is up to date same with our array list
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            updateList(mCommentList);
        }
    }
}