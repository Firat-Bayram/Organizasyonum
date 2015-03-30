package com.androidproject.organizasyonum;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Firat on 25.3.2015.
 */
public class menu_turnuva_futbol extends Activity implements View.OnClickListener {
    Helper helper = null;
    private Button btnEkleGrup,btnEkleTek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_turnuva_futbol);
        helper = Helper.INSTANCE;

        //setup buttons
        btnEkleGrup = helper.getirView(this, R.id.btnEkleGrup, Button.class);
        btnEkleTek = helper.getirView(this, R.id.btnEkleTek, Button.class);
        //register listeners
        btnEkleGrup.setOnClickListener(this);
        btnEkleTek.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnEkleGrup:
                helper.goster(this, menu_turnuva_futbol_grupmac.class);
                break;
            case R.id.btnEkleTek:
                //helper.goster(this, register.class);
                break;
            default:
                break;
        }
    }
}
