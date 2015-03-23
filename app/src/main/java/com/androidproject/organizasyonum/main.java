package com.androidproject.organizasyonum;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Created by Firat on 22.3.2015.
 */
public class main extends Activity {
    Helper helper;
    MediaPlayer splash_sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        helper = Helper.INSTANCE;
        helper.tamEkran(this);

        splash_sound = MediaPlayer.create(main.this, R.raw.splash_sound);
        splash_sound.start();

        helper.calistirParalel(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    helper.goster(main.this, login.class);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    helper.cikis(main.this);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        splash_sound.release();
    }
}
