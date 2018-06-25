package com.tinnovat.app.daj.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.Objects;

public class SurveillanceActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveillance);
        Objects.requireNonNull(getSupportActionBar()).setTitle("DAR Al JEWAR SURVEILLANCE");
    }
}
