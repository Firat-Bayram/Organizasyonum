package com.androidproject.organizasyonum;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Firat on 25.3.2015.
 */
public class register extends Activity {
    Helper helper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        helper = Helper.INSTANCE;
    }
}
