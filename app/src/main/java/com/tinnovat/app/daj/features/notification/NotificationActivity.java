package com.tinnovat.app.daj.features.notification;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.ContactResponseModel;
import com.tinnovat.app.daj.data.network.model.Event;
import com.tinnovat.app.daj.data.network.model.Guest;
import com.tinnovat.app.daj.data.network.model.NotificationResponseModel;
import com.tinnovat.app.daj.data.network.model.ProfileData;
import com.tinnovat.app.daj.data.network.model.ProfileResponseModel;
import com.tinnovat.app.daj.features.contact.ContactListAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends BaseActivity {

    private AppPreferanceStore appPreferanceStore;
    private TextView noData;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);

        noData = findViewById(R.id.nodata);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getText(R.string.notification));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
            navigationView.getMenu().getItem(4).setIcon(R.drawable.camera_ar_nav);
            navigationView.getMenu().getItem(5).setIcon(R.drawable.navigation_ar_nav);
            navigationView.getMenu().getItem(6).setIcon(R.drawable.project_ar_nav);
            navigationView.getMenu().getItem(7).setIcon(R.drawable.booking_ar_nav);
            navigationView.getMenu().getItem(8).setIcon(R.drawable.profile_ar_nav);
            navigationView.getMenu().getItem(9).setIcon(R.drawable.food_ar_nav);
            navigationView.getMenu().getItem(10).setIcon(R.drawable.taxi_ar_nav);
            navigationView.getMenu().getItem(11).setIcon(R.drawable.complaint_ar_nav);
            navigationView.getMenu().getItem(12).setIcon(R.drawable.contact_ar_nav);
            navigationView.getMenu().getItem(13).setIcon(R.drawable.password_ar_nav);
            navigationView.getMenu().getItem(14).setIcon(R.drawable.language_ar_nav);
            navigationView.getMenu().getItem(15).setIcon(R.drawable.logout_ar_nav);

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
            navigationView.getMenu().getItem(13).setIcon(R.drawable.password_nav);
            navigationView.getMenu().getItem(14).setIcon(R.drawable.language_nav);
            navigationView.getMenu().getItem(15).setIcon(R.drawable.logout_nav);

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

        fetchProfileData();

    }

    @Override
    public void initialiseViews() {


    }


    public void fetchProfileData() {
        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<NotificationResponseModel> call = apiInterface.getNotificationData(appPreferanceStore.getLanguage() ? "en" : "ar");
        call.enqueue(new Callback<NotificationResponseModel>() {
            @Override
            public void onResponse(Call<NotificationResponseModel> call, Response<NotificationResponseModel> response) {
                endLoading();
                if (response.body() != null){
                    setDataGuest(response.body().getGuest());
                    setData(response.body().getEvents());

                    if (response.body().getGuest().size() == 0 && response.body().getEvents().size() == 0){
                        setNoData(true);
                    }else {
                        setNoData(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<NotificationResponseModel> call, Throwable t) {
                endLoading();
            }
        });
    }
    private void setNoData(Boolean emptyList){
        if (emptyList){
            noData.setVisibility(View.VISIBLE);
        }else {
            noData.setVisibility(View.GONE);
        }
    }

    private void setData(List<Event> event){

        RecyclerView recyclerView= findViewById(R.id.recycler_view);
        NotificationAdapter mAdapter = new NotificationAdapter(event);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private void setDataGuest(List<Guest> guest){

        RecyclerView recyclerView= findViewById(R.id.recycler_view_guest);
        NotificationGuestAdapter mAdapter = new NotificationGuestAdapter(guest);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
    @Override
    public void initialiseEventListners() {

    }

    @Override
    public void onClick(View v) {

    }
}
