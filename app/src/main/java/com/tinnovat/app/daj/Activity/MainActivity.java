package com.tinnovat.app.daj.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.Objects;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView facebook;
    ImageView twitter;
    ImageView instagram;
    RelativeLayout events;
    RelativeLayout services;
    RelativeLayout guest_registration;
    RelativeLayout view_camera;
    RelativeLayout navigation;
    RelativeLayout projects;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Objects.requireNonNull(getSupportActionBar()).setTitle("DASHBOARD");

        facebook = findViewById(R.id.facebook);
        twitter = findViewById(R.id.twitter);
        instagram = findViewById(R.id.instagram);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        events = findViewById(R.id.events);
        services = findViewById(R.id.services);
        guest_registration = findViewById(R.id.guest_registration);
        view_camera = findViewById(R.id.view_camera);
        navigation = findViewById(R.id.navigation);
        projects = findViewById(R.id.projects);


       // getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"facebook clicked",Toast.LENGTH_SHORT).show();
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"twitter clicked",Toast.LENGTH_SHORT).show();
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"instagram clicked",Toast.LENGTH_SHORT).show();
            }
        });

        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ServicesActivity.class);
                startActivity(i);
            }
        });
        guest_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        view_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        int a = 1;
        if (id == R.id.home) {
            // Handle the camera action
           // item.setIcon(R.layout.sample);
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

           /* if (a==1) {
                item.setIcon(R.drawable.icon_slection_arabic);
            }else {
                item.setIcon(R.drawable.icon_slection_english);
            }*/
        } else if (id == R.id.events) {
           // setLanguage(true);
           // getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            Intent i = new Intent(MainActivity.this, EventBookingActivity.class);
            startActivity(i);

        } else if (id == R.id.services) {
           // setLanguage(false);
           /* Intent i = new Intent(MainActivity.this, TestActivity.class);
            startActivity(i);*/
           // finish();
        } else if (id == R.id.guest_registration) {

        } else if (id == R.id.view_camera) {

        } else if (id == R.id.navigation) {

        }else if (id == R.id.projects) {

        }else if (id == R.id.my_bookings) {
            Intent i = new Intent(MainActivity.this, ServiceBookingActivity.class);
            startActivity(i);

        }else if (id == R.id.view_profile) {

        }else if (id == R.id.food) {
            Intent i = new Intent(MainActivity.this, OrderFoodActivity.class);
            startActivity(i);

        }else if (id == R.id.taxi) {
            Intent i = new Intent(MainActivity.this, OrderTaxiActivity.class);
            startActivity(i);

        }else if (id == R.id.complaint) {
            Intent i = new Intent(MainActivity.this, RegisterComplaintActivity.class);
            startActivity(i);
        }else if (id == R.id.emergency_contact) {

        }else if (id == R.id.change_password) {

            Toast.makeText(this,""+getLanguage(),Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
