package com.tinnovat.app.daj.features.eventAndNews;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.EventCategory;
import com.tinnovat.app.daj.data.network.model.EventDetails;
import com.tinnovat.app.daj.data.network.model.EventListModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventNewsActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    private ViewPager viewPager;
    private PagerAdapter adapter;
    private List<EventDetails> eventDetailsList;
    private List<EventCategory> eventCategories;
    private AppPreferanceStore appPreferanceStore;
    private EventAndNewsListener mEventAndNewsListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_event_news);
        super.onCreate(savedInstanceState);
        //Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.event_news));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getText(R.string.event_news));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(1).setChecked(true);
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

        if (isNetworkConnected()){
            fetchEventList();
        }else {
            showErrorDilog(true);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNetworkConnected()){
            fetchEventList();
        }else {
            showErrorDilog(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void initialiseViews() {

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.today1)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tomorrow)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.weekend)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = findViewById(R.id.pager);


        adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#2C7861"));
        tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#2C7861"));

        tabLayout.addOnTabSelectedListener(this);

    }


    @Override
    public void initialiseEventListners() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    public void fetchEventList() {
        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<EventListModel> call = apiInterface.getEventsAndNews(appPreferanceStore.getLanguage() ? "en" : "ar");
        call.enqueue(new Callback<EventListModel>() {
            @Override
            public void onResponse(Call<EventListModel> call, Response<EventListModel> response) {
                endLoading();
               // showMessage("Data Fetched Successfully");
                if (response.body() != null && response.body().getCategory() != null) {

                    List<EventDetails> eventDetails = new ArrayList<>();
                    for (EventCategory category : response.body().getCategory()) {
                        for (EventDetails details : category.getEvents()) {
                            eventDetails.add(details);
                        }
                    }

                    adapter.setData(eventDetails);
                }
            }

            @Override
            public void onFailure(Call<EventListModel> call, Throwable t) {

                endLoading();
                //showMessage("EventsAndNews Failed");
            }
        });
    }

    public interface EventAndNewsListener {
        void EventCategories(List<EventCategory> eventCategories);
    }
}