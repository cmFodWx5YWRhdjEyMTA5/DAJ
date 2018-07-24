package com.tinnovat.app.daj.features.dashboard;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tinnovat.app.daj.features.authentication.ChangePasswordActivity;
import com.tinnovat.app.daj.features.futurePhase.FuturePhaseInfoListActivity;
import com.tinnovat.app.daj.Activity.ProfileActivity;
import com.tinnovat.app.daj.Activity.SurveillanceActivity;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.features.authentication.LoginActivity;
import com.tinnovat.app.daj.features.bookings.GuestRegistrationActivity;
import com.tinnovat.app.daj.features.bookings.MyBookingActivity;
import com.tinnovat.app.daj.features.complaint.MyComplaintListActivity;
import com.tinnovat.app.daj.features.emergencyContact.EmergencyContactActivity;
import com.tinnovat.app.daj.features.eventAndNews.circle.EventAndNewsActivity;
import com.tinnovat.app.daj.features.foodAndTaxi.OrderFoodActivity;
import com.tinnovat.app.daj.features.foodAndTaxi.OrderTaxiActivity;
import com.tinnovat.app.daj.features.services.ServicesActivity;
import com.tinnovat.app.daj.map.MapsActivity;

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

    ImageView arcEventAr;
    ImageView arcEventEn;
    ImageView arcServicesAr;
    ImageView arcServicesEn;

    ImageView arcGuestAr;
    ImageView arcGuestEn;
    ImageView arcCameraAr;
    ImageView arcCameraEn;

    ImageView arcNavAr;
    ImageView arcNavEn;
    ImageView arcProjectAr;
    ImageView arcProjectEn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String languageToLoad;
        if (getLanguage()){
            languageToLoad  = "en"; // english
        }else {
            languageToLoad  = "ar"; // arabic
        }

        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());


        this.setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getText(R.string.dashboard));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setItemIconTintList(null);

        facebook = findViewById(R.id.facebook);
        twitter = findViewById(R.id.twitter);
        instagram = findViewById(R.id.instagram);

        events = findViewById(R.id.events);
        services = findViewById(R.id.services);
        guest_registration = findViewById(R.id.guest_registration);
        view_camera = findViewById(R.id.view_camera);
        navigation = findViewById(R.id.navigation);
        projects = findViewById(R.id.projects);



        arcEventAr = findViewById(R.id.arcEventAr);
        arcEventEn = findViewById(R.id.arcEventEn);

        arcServicesAr = findViewById(R.id.arcServicesAr);
        arcServicesEn = findViewById(R.id.arcServicesEn);

        arcGuestAr = findViewById(R.id.arcGuestAr);
        arcGuestEn = findViewById(R.id.arcGuestEn);

        arcCameraAr = findViewById(R.id.arcCameraAr);
        arcCameraEn = findViewById(R.id.arcCameraEn);

        arcNavAr = findViewById(R.id.arcNavAr);
        arcNavEn = findViewById(R.id.arcNavEn);

        arcProjectAr = findViewById(R.id.arcProjectAr);
        arcProjectEn = findViewById(R.id.arcProjectEn);

        if (!getLanguage()){
            //for arabic
            navigationView.getMenu().getItem(0).setIcon(R.drawable.home_ar_nav);
            navigationView.getMenu().getItem(1).setIcon(R.drawable.event_ar_nav);
            navigationView.getMenu().getItem(2).setIcon(R.drawable.service_ar_nav);
            navigationView.getMenu().getItem(3).setIcon(R.drawable.guest_ar_nav);
            navigationView.getMenu().getItem(4).setIcon(R.drawable.camera_ar_nav);
            navigationView.getMenu().getItem(5).setIcon(R.drawable.navigation_ar_nav);
            navigationView.getMenu().getItem(6).setIcon(R.drawable.project_ar_nav);
            navigationView.getMenu().getItem(7).setIcon(R.drawable.booking_ar_nav);
            navigationView.getMenu().getItem(8).setIcon(R.drawable.profile_ar_nav);
            navigationView.getMenu().getItem(9).setIcon(R.drawable.food_ar_nav);
            navigationView.getMenu().getItem(10).setIcon(R.drawable.taxi_ar_nav);
            navigationView.getMenu().getItem(11).setIcon(R.drawable.complaint_ar_nav);
            navigationView.getMenu().getItem(12).setIcon(R.drawable.contact_ar_nav);

            arcEventAr.setVisibility(View.VISIBLE);
            arcEventEn.setVisibility(View.INVISIBLE);

            arcServicesAr.setVisibility(View.VISIBLE);
            arcServicesEn.setVisibility(View.INVISIBLE);

            arcGuestAr.setVisibility(View.VISIBLE);
            arcGuestEn.setVisibility(View.INVISIBLE);

            arcCameraAr.setVisibility(View.VISIBLE);
            arcCameraEn.setVisibility(View.INVISIBLE);

            arcNavAr.setVisibility(View.VISIBLE);
            arcNavEn.setVisibility(View.INVISIBLE);

            arcProjectAr.setVisibility(View.VISIBLE);
            arcProjectEn.setVisibility(View.INVISIBLE);
        }else {
            //for Eng

            navigationView.getMenu().getItem(0).setIcon(R.drawable.home_nav);
            navigationView.getMenu().getItem(1).setIcon(R.drawable.event_nav);
            navigationView.getMenu().getItem(2).setIcon(R.drawable.service_nav);
            navigationView.getMenu().getItem(3).setIcon(R.drawable.guest_nav);
            navigationView.getMenu().getItem(4).setIcon(R.drawable.camera_nav);
            navigationView.getMenu().getItem(5).setIcon(R.drawable.navigation_nav);
            navigationView.getMenu().getItem(6).setIcon(R.drawable.project_nav);
            navigationView.getMenu().getItem(7).setIcon(R.drawable.booking_nav);
            navigationView.getMenu().getItem(8).setIcon(R.drawable.profile_nav);
            navigationView.getMenu().getItem(9).setIcon(R.drawable.food_nav);
            navigationView.getMenu().getItem(10).setIcon(R.drawable.taxi_nav);
            navigationView.getMenu().getItem(11).setIcon(R.drawable.complaint_nav);
            navigationView.getMenu().getItem(12).setIcon(R.drawable.contact_nav);

            arcEventAr.setVisibility(View.INVISIBLE);
            arcEventEn.setVisibility(View.VISIBLE);

            arcServicesAr.setVisibility(View.INVISIBLE);
            arcServicesEn.setVisibility(View.VISIBLE);

            arcGuestAr.setVisibility(View.INVISIBLE);
            arcGuestEn.setVisibility(View.VISIBLE);

            arcCameraAr.setVisibility(View.INVISIBLE);
            arcCameraEn.setVisibility(View.VISIBLE);

            arcNavAr.setVisibility(View.INVISIBLE);
            arcNavEn.setVisibility(View.VISIBLE);

            arcProjectAr.setVisibility(View.INVISIBLE);
            arcProjectEn.setVisibility(View.VISIBLE);
        }





       facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this,"facebook clicked",Toast.LENGTH_SHORT).show();
                Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/MadinahKEC/"));
                startActivity(followIntent);
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/MadinahKEC"));
                startActivity(followIntent);
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/madinahkec/"));
                startActivity(followIntent);
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
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
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
    public void initialiseViews() {

    }

    @Override
    public void initialiseEventListners() {

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

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

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

        if (id == R.id.home) {




           // getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            // Handle the camera action
           // item.setIcon(R.layout.dialog_guest);
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
            Intent i = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(i);

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
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

    }
}
