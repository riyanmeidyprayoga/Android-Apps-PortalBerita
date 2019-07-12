package com.riyanmeidyprayoga.www.uas2019v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // loading splas srean berapa lama
        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent a = new Intent(SplashScreen.this, HomeActivity.class);
                    startActivity(a);
                    finish();
                }
            }
        };
        thread.start();

        //progresbar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}

