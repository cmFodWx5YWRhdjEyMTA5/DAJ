package com.tinnovat.app.daj.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.Objects;

public class ServiceBookingActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_booking);
        Objects.requireNonNull(getSupportActionBar()).setTitle("SERVICE BOOKING");

    }
}
