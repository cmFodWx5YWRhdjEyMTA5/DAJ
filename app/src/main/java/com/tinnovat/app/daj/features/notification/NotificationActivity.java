package com.tinnovat.app.daj.features.notification;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.ContactResponseModel;
import com.tinnovat.app.daj.data.network.model.Event;
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
    private RelativeLayout noData;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);

        noData = findViewById(R.id.noData);

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
                    setData(response.body().getEvents());
                }
            }

            @Override
            public void onFailure(Call<NotificationResponseModel> call, Throwable t) {
                endLoading();
            }
        });
    }

    private void setData(List<Event> event){
        if (event.size() == 0){
            noData.setVisibility(View.VISIBLE);
        }else {
            noData.setVisibility(View.GONE);
        }
        RecyclerView recyclerView= findViewById(R.id.recycler_view);
        NotificationAdapter mAdapter = new NotificationAdapter(event);

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
