package com.tinnovat.app.daj.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.Locale;

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

    ImageView arc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLanguage(true);


            String languageToLoad  = "ar"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        /*String languageToLoad  = "ar"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());*/
        this.setContentView(R.layout.activity_main);

        Toast.makeText(this, ""+getText(R.string.hello_world), Toast.LENGTH_SHORT).show();
        //Objects.requireNonNull(getSupportActionBar()).setTitle("DASHBOARD");

        facebook = findViewById(R.id.facebook);
        twitter = findViewById(R.id.twitter);
        instagram = findViewById(R.id.instagram);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        events = findViewById(R.id.events);
        services = findViewById(R.id.services);
        guest_registration = findViewById(R.id.guest_registration);
        view_camera = findViewById(R.id.view_camera);
        navigation = findViewById(R.id.navigation);
        projects = findViewById(R.id.projects);


        arc = findViewById(R.id.leftRc);

        if (getLanguage()){
            arc.setScaleType(ImageView.ScaleType.FIT_START);
            arc.setImageDrawable(getResources().getDrawable(R.drawable.arc_right));
        }

        //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
       // getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setItemIconTintList(null);

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
                Intent i = new Intent(MainActivity.this, EventAndNewsActivity.class);
                startActivity(i);

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
                Intent i = new Intent(MainActivity.this, GuestRegistrationActivity.class);
                startActivity(i);
            }
        });
        view_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SurveillanceActivity.class);
                startActivity(i);
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
                Intent i = new Intent(MainActivity.this, FuturePhaseInfoListActivity.class);
                startActivity(i);
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


           // getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            // Handle the camera action
           // item.setIcon(R.layout.sample);
           // getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

           /* Intent i = new Intent(MainActivity.this, MainActivity.class);
            startActivity(i);*/

           /* if (a==1) {
                item.setIcon(R.drawable.icon_slection_arabic);
            }else {
                item.setIcon(R.drawable.icon_slection_english);
            }*/
        } else if (id == R.id.events) {
           // setLanguage(true);
           // getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            Intent i = new Intent(MainActivity.this, EventAndNewsActivity.class);
            startActivity(i);

        } else if (id == R.id.services) {
            Intent i = new Intent(MainActivity.this, ServicesActivity.class);
            startActivity(i);
           // setLanguage(false);
           /* Intent i = new Intent(MainActivity.this, TestActivity.class);
            startActivity(i);*/
           // finish();
        } else if (id == R.id.guest_registration) {
            Intent i = new Intent(MainActivity.this, GuestRegistrationActivity.class);
            startActivity(i);

        } else if (id == R.id.view_camera) {
            Intent i = new Intent(MainActivity.this, SurveillanceActivity.class);
            startActivity(i);

        } else if (id == R.id.navigation) {

        }else if (id == R.id.projects) {
            Intent i = new Intent(MainActivity.this, FuturePhaseInfoListActivity.class);
            startActivity(i);

        }else if (id == R.id.my_bookings) {
            Intent i = new Intent(MainActivity.this, MyBookingActivity.class);
            startActivity(i);

        }else if (id == R.id.view_profile) {
            Intent i = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(i);

        }else if (id == R.id.food) {
            Intent i = new Intent(MainActivity.this, OrderFoodActivity.class);
            startActivity(i);

        }else if (id == R.id.taxi) {
            Intent i = new Intent(MainActivity.this, OrderTaxiActivity.class);
            startActivity(i);

        }else if (id == R.id.complaint) {
            Intent i = new Intent(MainActivity.this, MyComplaintListActivity.class);
            startActivity(i);
        }else if (id == R.id.emergency_contact) {
            Intent i = new Intent(MainActivity.this, EmergencyContactActivity.class);
            startActivity(i);

        }else if (id == R.id.change_password) {
            Intent i = new Intent(MainActivity.this, ChangePasswordActivity.class);
            startActivity(i);
           // Toast.makeText(this,""+getLanguage(),Toast.LENGTH_SHORT).show();
        }else if (id == R.id.logout) {
           // Toast.makeText(this,""+getLanguage(),Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
