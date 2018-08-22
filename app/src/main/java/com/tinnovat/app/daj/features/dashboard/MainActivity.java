package com.tinnovat.app.daj.features.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.kapil.circularlayoutmanager.CircularLayoutManager;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.features.authentication.ChangePasswordActivity;
import com.tinnovat.app.daj.features.foodAndTaxi.OrderFoodActivity;
import com.tinnovat.app.daj.features.notification.NotificationActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends BaseActivity {
//        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView facebook;
    ImageView twitter;
    ImageView instagram;


    private List<String> eventName;
    private AppPreferanceStore appPreferanceStore;

    RecyclerView recyclerView;

    private RecyclerViewAdapter mAdapter;

    ImageView logoEn;
    ImageView logoAr;
    boolean status;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        endLoading();
        Intent intent = getIntent();
        status = intent.getBooleanExtra("status",false);
        if (status){
            status = false;
            intent = new Intent(MainActivity.this, ChangePasswordActivity.class);
            startActivity(intent);

        }

        String languageToLoad;
        if (getLanguage()) {
            languageToLoad = "en"; // english
        } else {
            languageToLoad = "ar"; // arabic
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

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setItemIconTintList(null);

        facebook = findViewById(R.id.facebook);
        twitter = findViewById(R.id.twitter);
        instagram = findViewById(R.id.instagram);


        if (!getLanguage()) {
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

        } else {
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


        appPreferanceStore = new AppPreferanceStore(this);


        logoEn = findViewById(R.id.logo_en);
        logoAr = findViewById(R.id.logo_ar);
        if (getLanguage()) {
            logoEn.setVisibility(View.VISIBLE);
            logoAr.setVisibility(View.INVISIBLE);
        } else {
            logoEn.setVisibility(View.INVISIBLE);
            logoAr.setVisibility(View.VISIBLE);
        }

        initializeList();


        // fetchEventList();//for api
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    public boolean onPrepareOptionsMenu(Menu menu) {
        //  preparation code here
        return super.onPrepareOptionsMenu(menu);
    }

    private static int getScreenResolution(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        return height ;
    }

    private void setViews(List<String> eventName) {
        int height = getScreenResolution(this);

        Log.e("hhhhhhh",""+height);
        recyclerView = findViewById(R.id.recycler_view);
        // recyclerView.setAdapter(new RecyclerViewAdapter(getApplicationContext(), list));
        mAdapter = new RecyclerViewAdapter(getApplicationContext(), eventName, getLanguage());
        recyclerView.addItemDecoration(new RecyclerItemDecoration());
        /*RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);*/
        recyclerView.setLayoutManager(new CircularLayoutManager(getApplicationContext(), 250, -80));//280

        //  recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);



        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(getApplicationContext(),
                new OnRecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void OnItemClick(RecyclerView parent, int childIndex) {

                        if (parent.getChildAt(childIndex).getX() > parent.getChildAt(childIndex-1).getX()){
                            if (parent.getChildAt(childIndex).getX() > parent.getChildAt(childIndex+1).getX()){
                                performClick(((TextView) parent.getChildAt(childIndex).findViewById(R.id.item)).getText().toString());
                            }/*else {
                                showMessage("222");
                            }
                        }else {
                            showMessage("111");*/
                        }

                        /*if (childIndex == 3) {
                            performClick(((TextView) parent.getChildAt(childIndex).findViewById(R.id.item)).getText().toString());

                            //parent.removeItemDecorationAt(0);
                        }*/
                    }
                }));
    }


    private void performClick(String option) {
        switch (option) {

            case "3":
                fetchEvents();
                break;
            case "4":
                fetchServices();
                break;
            case "5":
                fetchGuestRegistration();
                break;
            case "6":
                fetchCamera();
                break;
            case "7":
                fetchMaps();
                break;
            case "8":
                fetchFuturePhase();
                break;
            case "9":
                fetchMyBooking();
                break;
            case "10":
                fetchProfile();
                break;
            case "11":
                fetchOrderFood();
                break;
            case "12":
                fetchOrderTaxi();
                break;
            case "13":
                fetchMyComplaintList();
                break;
            case "14":
                fetchEmergencyContact();
                break;
            case "15":
                fetchChangePassword();
                break;
            case "16":
                changeLanguage();
                break;
            case "17":
                fetchLogout();
                break;


        }
    }

    private void initializeList() {
        eventName = new ArrayList<>();


        eventName.add(" ");
        eventName.add(" ");
        eventName.add(" ");
        eventName.add(getResources().getString(R.string.events));
        eventName.add(getResources().getString(R.string.services));
        eventName.add(getResources().getString(R.string.guest_reg));
        eventName.add(getResources().getString(R.string.view_camera));
        eventName.add(getResources().getString(R.string.navigation));
        eventName.add(getResources().getString(R.string.projects));
        eventName.add(getResources().getString(R.string.my_bookings));
        eventName.add(getResources().getString(R.string.my_profile));
        eventName.add(getResources().getString(R.string.order_food));
        eventName.add(getResources().getString(R.string.order_taxi));
        eventName.add(getResources().getString(R.string.complaint));
        eventName.add(getResources().getString(R.string.emergency_contact));
        eventName.add(getResources().getString(R.string.change_password));
        eventName.add(getResources().getString(R.string.change_language));
        eventName.add(getResources().getString(R.string.logout));
        eventName.add(" ");
        eventName.add(" ");
        eventName.add(" ");


        setViews(eventName);
    }

    @Override
    public void initialiseViews() {

    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
            Intent i = new Intent(MainActivity.this, NotificationActivity.class);
            startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*  @SuppressWarnings("StatementWithEmptyBody")
      @Override
      public boolean onNavigationItemSelected(MenuItem item) {
          // Handle navigation view item clicks here.
          int id = item.getItemId();

          if (id == R.id.home) {


              // getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
              // Handle the camera action
              // item.setIcon(R.layout.dialog_guest);
              // getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

             *//* Intent i = new Intent(MainActivity.this, MainActivity.class);
            startActivity(i);*//*

     *//* if (a==1) {
                item.setIcon(R.drawable.icon_slection_arabic);
            }else {
                item.setIcon(R.drawable.icon_slection_english);
            }*//*
        } else if (id == R.id.events) {
            fetchEvents();

        } else if (id == R.id.services) {
            fetchServices();
        } else if (id == R.id.guest_registration) {
            fetchGuestRegistration();

        } else if (id == R.id.view_camera) {
            fetchCamera();

        } else if (id == R.id.navigation) {
            fetchMaps();

        } else if (id == R.id.projects) {
            fetchFuturePhase();

        } else if (id == R.id.my_bookings) {
            fetchMyBooking();

        } else if (id == R.id.view_profile) {
            fetchProfile();

        } else if (id == R.id.food) {
            fetchOrderFood();

        } else if (id == R.id.taxi) {
            fetchOrderTaxi();

        } else if (id == R.id.complaint) {
            fetchMyComplaintList();
        } else if (id == R.id.emergency_contact) {
            fetchEmergencyContact();

        } else if (id == R.id.change_password) {
            fetchChangePassword();
        } else if (id == R.id.logout) {
            fetchLogout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void fetchHome() {
        Intent i = new Intent(MainActivity.this, MainActivity.class);
        startActivity(i);
    }

    private void fetchEvents() {
        Intent i = new Intent(MainActivity.this, EventNewsActivity.class);
        startActivity(i);
    }

    private void fetchServices() {
        Intent i = new Intent(MainActivity.this, ServicesMainActivity.class);
        startActivity(i);
    }

    private void fetchGuestRegistration() {
        Intent i = new Intent(MainActivity.this, GuestRegistrationActivityMain.class);
        startActivity(i);
    }

    private void fetchCamera() {
        Intent i = new Intent(MainActivity.this, SurveillanceActivity.class);
        startActivity(i);
    }

    private void fetchMaps() {
        Intent i = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(i);
    }

    private void fetchFuturePhase() {
        Intent i = new Intent(MainActivity.this, FuturePhaseMainActivity.class);
        startActivity(i);
    }

    private void fetchMyBooking() {
        Intent i = new Intent(MainActivity.this, MyBookingActivity.class);
        startActivity(i);
    }

    private void fetchProfile() {
        Intent i = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(i);
    }

    private void fetchOrderFood() {
        Intent i = new Intent(MainActivity.this, OrderFoodActivity.class);
        startActivity(i);
    }

    private void fetchOrderTaxi() {
        Intent i = new Intent(MainActivity.this, OrderTaxiActivity.class);
        startActivity(i);
    }

    private void fetchMyComplaintList() {
        Intent i = new Intent(MainActivity.this, ComplaintMainActivity.class);
        startActivity(i);
    }

    private void fetchEmergencyContact() {
        Intent i = new Intent(MainActivity.this, EmergencyContactActivity.class);
        startActivity(i);
    }

    private void fetchChangePassword() {
        Intent i = new Intent(MainActivity.this, ChangePasswordActivity.class);
        startActivity(i);
    }

    private void fetchLogout() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
*/
    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onResume() {

        //initializeList();

        if ( mAdapter != null){
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
           // showMessage("resume");
        }else {
           // showMessage("list null");
        }
        super.onResume();
    }
}
