package com.androidproject.organizasyonum;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Firat on 22.3.2015.
 */
public class main extends Activity {
    private ProgressBar progressBar;
    private TextView textView;
    Helper helper;
    MediaPlayer splash_sound;
    private Handler handler = new Handler();
    private int progressStatus = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        helper = Helper.INSTANCE;
        helper.tamEkran(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        textView = (TextView) findViewById(R.id.textView1);
        splash_sound = MediaPlayer.create(main.this, R.raw.splash_sound);
        splash_sound.start();

        helper.calistirParalel(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 4;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            textView.setText(progressStatus+"/"+progressBar.getMax());
                        }
                    });

                    try {
                        // Sleep for 200 milliseconds.
                        //Just to display the progress slowly
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                helper.goster(main.this, login.class);
                helper.cikis(main.this);
               /* try {
                    Thread.sleep(5000);
                    helper.goster(main.this, login.class);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    helper.cikis(main.this);
                }*/
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        splash_sound.release();
    }
}
