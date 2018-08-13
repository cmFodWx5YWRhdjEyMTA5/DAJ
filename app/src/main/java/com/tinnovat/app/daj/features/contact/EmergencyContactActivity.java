package com.tinnovat.app.daj.features.contact;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.ContactResponseModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmergencyContactActivity extends BaseActivity {

    private AppPreferanceStore appPreferanceStore;
    private TextView noData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
       // Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.emergency_contact));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getText(R.string.emergency_contact));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(12).setChecked(true);
        navigationView.setItemIconTintList(null);

        appPreferanceStore = new AppPreferanceStore(this);
        noData = findViewById(R.id.nodata);
        fetchContactList();
    }

    private void fetchContactList() {
        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<ContactResponseModel> call = apiInterface.getContactList(appPreferanceStore.getLanguage() ? "en" : "ar");
        call.enqueue(new Callback<ContactResponseModel>() {
            @Override
            public void onResponse(Call<ContactResponseModel> call, Response<ContactResponseModel> response) {
                endLoading();
               // showMessage("ContactList Successfully");
                setData(response);
            }

            @Override
            public void onFailure(Call<ContactResponseModel> call, Throwable t) {

                endLoading();
                //showMessage("ContactList Failed");
            }
        });
    }

    public void setData(Response<ContactResponseModel> response){
        RecyclerView recyclerView= findViewById(R.id.recycler_view);
        ContactListAdapter mAdapter = new ContactListAdapter(response);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        if (mAdapter.getItemCount() == 0){
            noData.setVisibility(View.VISIBLE);
        }else {
            noData.setVisibility(View.GONE);
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
