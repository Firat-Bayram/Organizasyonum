package com.androidproject.organizasyonum;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Firat on 25.3.2015.
 */
public class menu extends Activity implements View.OnClickListener {
    Helper helper = null;
    private Button btnTurnuva;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        helper = Helper.INSTANCE;

        //setup buttons
        btnTurnuva = helper.getirView(this, R.id.btnTurnuva, Button.class);
        //register listeners
        btnTurnuva.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnTurnuva:
                helper.goster(this, menu_turnuva.class);
                break;
            default:
                break;
        }
    }
}
