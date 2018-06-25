package com.tinnovat.app.daj.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
public class SplashScreenActivity extends BaseActivity {

        private static int SPLASH_TIME_OUT = 3000;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash_screen);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }

    }