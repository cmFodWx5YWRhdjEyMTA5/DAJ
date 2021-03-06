package com.tinnovat.app.daj.features.dashboard;

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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kapil.circularlayoutmanager.CircularLayoutManager;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.features.authentication.ChangePasswordActivity;
import com.tinnovat.app.daj.features.notification.NotificationActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends BaseActivity {
//        implements NavigationView.OnNavigationItemSelectedListener {




    private List<String> eventName;
    private AppPreferanceStore appPreferanceStore;

    RecyclerView recyclerView;

    private RecyclerViewAdapter mAdapter;

    ImageView logoEn;
    ImageView logoAr;
    boolean status;
    boolean isTablet;
    boolean isSmall;

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

        isTablet = getResources().getBoolean(R.bool.isTablet);
        isSmall = getResources().getBoolean(R.bool.small);

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
            navigationView.getMenu().getItem(4).setIcon(R.drawable.navigation_ar_nav);
            navigationView.getMenu().getItem(5).setIcon(R.drawable.project_ar_nav);
            navigationView.getMenu().getItem(6).setIcon(R.drawable.booking_ar_nav);
            navigationView.getMenu().getItem(7).setIcon(R.drawable.profile_ar_nav);
            navigationView.getMenu().getItem(8).setIcon(R.drawable.food_ar_nav);
            navigationView.getMenu().getItem(9).setIcon(R.drawable.taxi_ar_nav);
            navigationView.getMenu().getItem(10).setIcon(R.drawable.complaint_ar_nav);
            navigationView.getMenu().getItem(11).setIcon(R.drawable.contact_ar_nav);
            navigationView.getMenu().getItem(12).setIcon(R.drawable.password_ar_nav);
            navigationView.getMenu().getItem(13).setIcon(R.drawable.language_ar_nav);
            navigationView.getMenu().getItem(14).setIcon(R.drawable.logout_ar_nav);

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

    private void setViews(List<String> eventName) {

        recyclerView = findViewById(R.id.recycler_view);
        // recyclerView.setAdapter(new RecyclerViewAdapter(getApplicationContext(), list));
        mAdapter = new RecyclerViewAdapter(getApplicationContext(), eventName, getLanguage(),isTablet,isSmall);
        recyclerView.addItemDecoration(new RecyclerItemDecoration());
        /*RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);*/
//        recyclerView.setLayoutManager(new CircularLayoutManager(getApplicationContext(), 250, -80));//280

        //  recyclerView.setItemAnimator(new DefaultItemAnimator());


//Now check condition
        if(isTablet){
            if (isSmall){
                //Device is tablet
                recyclerView.setLayoutManager(new CircularLayoutManager(getApplicationContext(), 400, -80));//280
            }else {
                //Device is tablet
                recyclerView.setLayoutManager(new CircularLayoutManager(getApplicationContext(), 500, -80));//280
            }

        }else{
            //Device is mobile
            recyclerView.setLayoutManager(new CircularLayoutManager(getApplicationContext(), 250, -80));//250 80
        }
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

                    case "4":
                        fetchEvents();
                        break;
                    case "5":
                        fetchServices();
                        break;
                    case "6":
                        fetchGuestRegistration();
                        break;
                   /* case "6":
                        fetchCamera();
                        break;*/
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
                eventName.add(" ");
                eventName.add(getResources().getString(R.string.events));
                eventName.add(getResources().getString(R.string.services));
                eventName.add(getResources().getString(R.string.guest_reg));
                //eventName.add(getResources().getString(R.string.view_camera));
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

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onResume() {

        //initializeList();

        /*if ( mAdapter != null){
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
           // showMessage("resume");
        }else {
           // showMessage("list null");
        }*/
        super.onResume();
    }
}
