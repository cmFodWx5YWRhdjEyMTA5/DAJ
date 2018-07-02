package com.tinnovat.app.daj.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.Objects;

public class EmergencyContactActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.emergency_contact));
    }
}
