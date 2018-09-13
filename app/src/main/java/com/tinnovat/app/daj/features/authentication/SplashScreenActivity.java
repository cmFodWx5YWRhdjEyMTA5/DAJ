package com.tinnovat.app.daj.features.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.features.dashboard.MainActivity;

public class SplashScreenActivity extends BaseActivity {

    private static final int SPLASH_TIME_OUT = 3000;
    private AppPreferanceStore appPreferanceStore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        appPreferanceStore = new AppPreferanceStore(this);
    }

    @Override
    public void initialiseViews() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                appPreferanceStore.setDeviceToken(newToken);
                Log.e("newToken",newToken);

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (appPreferanceStore.getToken() == null){
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    intent.putExtra("status",true);
                    startActivity(intent);
                }


            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public void onClick(View v) {
    }
}