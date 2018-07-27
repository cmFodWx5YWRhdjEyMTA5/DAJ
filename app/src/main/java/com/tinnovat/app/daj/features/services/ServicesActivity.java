package com.tinnovat.app.daj.features.services;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.ServicesResponseModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicesActivity extends BaseActivity {


    private RecyclerView recyclerView;
    private ServicesAdapter mAdapter;
    private AppPreferanceStore appPreferanceStore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.services));

        recyclerView = findViewById(R.id.recycler_view);

        appPreferanceStore = new AppPreferanceStore(this);
        fetchServiceList();

    }

    private void fetchServiceList() {

        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<ServicesResponseModel> call = apiInterface.getServiceList(appPreferanceStore.getLanguage() ? "en" : "ar");
        call.enqueue(new Callback<ServicesResponseModel>() {
            @Override
            public void onResponse(Call<ServicesResponseModel> call, Response<ServicesResponseModel> response) {
                endLoading();
                showMessage("Data Fetched Successfully");
                if (response.body() != null && response.body().getServiceCategory() != null) {


                    setData(response);
                    showMessage("ServiceList success");
                }
            }

            @Override
            public void onFailure(Call<ServicesResponseModel> call, Throwable t) {
                endLoading();

                showMessage("Login Failed");
            }
        });
    }

    public void setData(Response<ServicesResponseModel> response){
        mAdapter = new ServicesAdapter(response,getLanguage());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
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
