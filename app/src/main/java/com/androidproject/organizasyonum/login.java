package com.androidproject.organizasyonum;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Firat on 23.3.2015.
 */
public class login extends Activity {
    Helper helper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = Helper.INSTANCE;
    }
}
