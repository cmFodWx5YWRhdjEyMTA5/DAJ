package com.tinnovat.app.daj.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.Objects;

public class OrderTaxiActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_taxi);
        Objects.requireNonNull(getSupportActionBar()).setTitle("ORDER TAXI");
    }
}