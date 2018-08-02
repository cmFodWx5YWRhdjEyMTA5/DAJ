package com.tinnovat.app.daj.features.surveillance;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.Objects;

public class SurveillanceActivity extends BaseActivity {

    ImageView arc_gate;
    ImageView arc_community;
    ImageView arc_play;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveillance);
        //Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.surveillance));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getText(R.string.surveillance));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setItemIconTintList(null);

        arc_gate = findViewById(R.id.arc_gate);
        arc_community = findViewById(R.id.arc_community);
        arc_play = findViewById(R.id.arc_play);

        if (getLanguage()){
            arc_gate.setImageResource(R.drawable.arc_left);
            arc_gate.setScaleType(ImageView.ScaleType.FIT_END);

            arc_community.setImageResource(R.drawable.arc_left);
            arc_community.setScaleType(ImageView.ScaleType.FIT_END);

            arc_play.setImageResource(R.drawable.arc_left);
            arc_play.setScaleType(ImageView.ScaleType.FIT_END);
        }else {
            arc_gate.setImageResource(R.drawable.arc_right);
            arc_gate.setScaleType(ImageView.ScaleType.FIT_START);

            arc_community.setImageResource(R.drawable.arc_right);
            arc_community.setScaleType(ImageView.ScaleType.FIT_START);

            arc_play.setImageResource(R.drawable.arc_right);
            arc_play.setScaleType(ImageView.ScaleType.FIT_START);
        }
    }

    @Override
    public void initialiseViews() {

    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public void onClick(View v) {

    }
}
