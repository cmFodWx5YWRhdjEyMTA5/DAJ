package com.tinnovat.app.daj.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.Objects;

public class ServicesActivity extends BaseActivity {
    RelativeLayout hall;
    RelativeLayout pool;
    RelativeLayout court;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        Objects.requireNonNull(getSupportActionBar()).setTitle("SERVICES");

        hall = findViewById(R.id.hall);
        pool = findViewById(R.id.pool);
        court = findViewById(R.id.court);

        hall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ServicesActivity.this, ServiceBookingActivity.class);
                startActivity(i);
            }
        });

        pool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ServicesActivity.this, ServiceBookingActivity.class);
                startActivity(i);
            }
        });

        court.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ServicesActivity.this, ServiceBookingActivity.class);
                startActivity(i);
            }
        });


    }
}
