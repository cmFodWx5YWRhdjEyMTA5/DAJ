package com.tinnovat.app.daj.features.Complaint;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.ComplaintListResponseModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyComplaintListActivity extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_complaint_list);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.my_complaint_list));

        fetchContactList();

        registerComplaint();


    }

    private void registerComplaint(){
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setColorFilter(Color.WHITE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent i = new Intent(MyComplaintListActivity.this, RegisterComplaintActivity.class);
                startActivity(i);
            }
        });
    }

    private void fetchContactList() {

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<ComplaintListResponseModel> call = apiInterface.getComplaintList("en");
        call.enqueue(new Callback<ComplaintListResponseModel>() {
            @Override
            public void onResponse(Call<ComplaintListResponseModel> call, Response<ComplaintListResponseModel> response) {
                showMessage("ContactList Successfully");
                setData(response);
            }

            @Override
            public void onFailure(Call<ComplaintListResponseModel> call, Throwable t) {

                showMessage("ContactList Failed");
            }
        });
    }

    public void setData(Response<ComplaintListResponseModel> response){
        RecyclerView recyclerView= findViewById(R.id.recycler_view);
        ComplaintListAdapter mAdapter = new ComplaintListAdapter(response);

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

        switch (v.getId()){
            case R.id.row1:
                Intent i = new Intent(MyComplaintListActivity.this, MyComplaintActivity.class);
                startActivity(i);
                break;
        }
    }
}
