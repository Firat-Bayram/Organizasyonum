package com.androidproject.organizasyonum;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Firat on 25.3.2015.
 */
public class menu_turnuva extends Activity implements View.OnClickListener {
    Helper helper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_turnuva);
        helper = Helper.INSTANCE;
    }

    @Override
    public void onClick(View v) {
       /* // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnTurnuva:
                helper.goster(this, menu_turnuva.class);
                break;
            case R.id.register:
                helper.goster(this, register.class);
                break;
            default:
                break;
        }*/
    }
}
