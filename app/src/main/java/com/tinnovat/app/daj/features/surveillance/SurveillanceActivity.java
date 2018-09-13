package com.tinnovat.app.daj.features.surveillance;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

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
        navigationView.getMenu().getItem(4).setChecked(true);
        navigationView.setItemIconTintList(null);

        ImageView facebook = findViewById(R.id.facebook);
        ImageView twitter = findViewById(R.id.twitter);
        ImageView instagram = findViewById(R.id.instagram);


        if (!getLanguage()) {
            //for arabic
            navigationView.getMenu().getItem(0).setIcon(R.drawable.home_ar_nav);
            navigationView.getMenu().getItem(1).setIcon(R.drawable.event_ar_nav);
            navigationView.getMenu().getItem(2).setIcon(R.drawable.service_ar_nav);
            navigationView.getMenu().getItem(3).setIcon(R.drawable.guest_ar_nav);
            //navigationView.getMenu().getItem(4).setIcon(R.drawable.camera_ar_nav);
            navigationView.getMenu().getItem(6).setIcon(R.drawable.navigation_ar_nav);
            navigationView.getMenu().getItem(7).setIcon(R.drawable.project_ar_nav);
            navigationView.getMenu().getItem(8).setIcon(R.drawable.booking_ar_nav);
            navigationView.getMenu().getItem(9).setIcon(R.drawable.profile_ar_nav);
            navigationView.getMenu().getItem(10).setIcon(R.drawable.food_ar_nav);
            navigationView.getMenu().getItem(11).setIcon(R.drawable.taxi_ar_nav);
            navigationView.getMenu().getItem(12).setIcon(R.drawable.complaint_ar_nav);
            navigationView.getMenu().getItem(13).setIcon(R.drawable.contact_ar_nav);
            navigationView.getMenu().getItem(14).setIcon(R.drawable.password_ar_nav);
            navigationView.getMenu().getItem(15).setIcon(R.drawable.language_ar_nav);
            navigationView.getMenu().getItem(16).setIcon(R.drawable.logout_ar_nav);

        } else {
            //for Eng

            navigationView.getMenu().getItem(0).setIcon(R.drawable.home_nav);
            navigationView.getMenu().getItem(1).setIcon(R.drawable.event_nav);
            navigationView.getMenu().getItem(2).setIcon(R.drawable.service_nav);
            navigationView.getMenu().getItem(3).setIcon(R.drawable.guest_nav);
            // navigationView.getMenu().getItem(4).setIcon(R.drawable.camera_nav);
            navigationView.getMenu().getItem(4).setIcon(R.drawable.navigation_nav);
            navigationView.getMenu().getItem(5).setIcon(R.drawable.project_nav);
            navigationView.getMenu().getItem(6).setIcon(R.drawable.booking_nav);
            navigationView.getMenu().getItem(7).setIcon(R.drawable.profile_nav);
            navigationView.getMenu().getItem(8).setIcon(R.drawable.food_nav);
            navigationView.getMenu().getItem(9).setIcon(R.drawable.taxi_nav);
            navigationView.getMenu().getItem(10).setIcon(R.drawable.complaint_nav);
            navigationView.getMenu().getItem(11).setIcon(R.drawable.contact_nav);
            navigationView.getMenu().getItem(12).setIcon(R.drawable.password_nav);
            navigationView.getMenu().getItem(13).setIcon(R.drawable.language_nav);
            navigationView.getMenu().getItem(14).setIcon(R.drawable.logout_nav);

        }


        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(MainActivity.this,"facebook clicked",Toast.LENGTH_SHORT).show();
                Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/DarJewar/?ref=br_rs"));
                startActivity(followIntent);
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/darjewar?lang=en"));
                startActivity(followIntent);
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/darjewar/"));
                startActivity(followIntent);
            }
        });

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
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
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
