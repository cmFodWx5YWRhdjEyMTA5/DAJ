package com.tinnovat.app.daj.Activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.Objects;

public class ServicesActivity extends BaseActivity {
    RelativeLayout hall;
    RelativeLayout hall2;
    RelativeLayout pool;
    RelativeLayout court;

    ImageView arcHall;
    ImageView arcHall1;
    ImageView arcCourt;
    ImageView arcPool;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        Objects.requireNonNull(getSupportActionBar()).setTitle("SERVICES");


        arcHall = findViewById(R.id.arcHall);
        arcHall1 = findViewById(R.id.arcHall1);
        arcCourt = findViewById(R.id.arcCourt);
        arcPool = findViewById(R.id.arcPool);

        if (!getLanguage()){
            arcHall.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_right));
            arcHall.setScaleType(ImageView.ScaleType.FIT_START);

            arcCourt.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_right));
            arcCourt.setScaleType(ImageView.ScaleType.FIT_START);

            arcPool.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_right));
            arcPool.setScaleType(ImageView.ScaleType.FIT_START);

            arcHall1.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_right));
            arcHall1.setScaleType(ImageView.ScaleType.FIT_START);
        }else {
            arcHall.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_left));
            arcHall.setScaleType(ImageView.ScaleType.FIT_END);

            arcCourt.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_left));
            arcCourt.setScaleType(ImageView.ScaleType.FIT_END);

            arcPool.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_left));
            arcPool.setScaleType(ImageView.ScaleType.FIT_END);

            arcHall1.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_left));
            arcHall1.setScaleType(ImageView.ScaleType.FIT_END);
        }

        hall = findViewById(R.id.hall);
        hall2 = findViewById(R.id.hall2);
        pool = findViewById(R.id.pool);
        court = findViewById(R.id.court);

        hall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ServicesActivity.this, ServiceBookingActivity.class);
                startActivity(i);
            }
        });

        hall2.setOnClickListener(new View.OnClickListener() {
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
