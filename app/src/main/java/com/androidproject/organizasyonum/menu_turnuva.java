package com.androidproject.organizasyonum;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Firat on 25.3.2015.
 */
public class menu_turnuva extends Activity implements View.OnClickListener {
    Helper helper = null;
    private Button btnFutbol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_turnuva);
        helper = Helper.INSTANCE;

        //setup buttons
        btnFutbol = helper.getirView(this, R.id.btnFutbol, Button.class);
        //register listeners
        btnFutbol.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnFutbol:
                helper.goster(this, menu_turnuva_futbol.class);
                break;
            default:
                break;
        }
    }
}
