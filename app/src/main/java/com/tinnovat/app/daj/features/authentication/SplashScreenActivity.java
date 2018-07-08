package com.tinnovat.app.daj.features.authentication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

public class SplashScreenActivity extends BaseActivity {

    private static final int SPLASH_TIME_OUT = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        int timeOut = SPLASH_TIME_OUT;

        if (getIntent().getBooleanExtra("IS_FROM_LOGOUT",false))
            timeOut = 0;


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, LoginFragment.getInstance());
                transaction.setCustomAnimations(FragmentTransaction.TRANSIT_ENTER_MASK,0);
                transaction.commit();
            }
        }, timeOut);
    }

    @Override
    public void onClick(View v) {
    }
}