package com.tinnovat.app.daj.features.authentication;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

public class LoginActivity extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initialiseViews() {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, LoginFragment.getInstance()).addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public void onClick(View view) {

    }
}
