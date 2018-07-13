package com.tinnovat.app.daj.features.services;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.tinnovat.app.daj.Activity.ServiceBookingActivity;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.ServicesResponseModel;
import com.tinnovat.app.daj.testing.Movie;
import com.tinnovat.app.daj.testing.MoviesAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class ServiceListActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ServicesListAdapter mAdapter;
    private ServicesResponseModel res;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        recyclerView = findViewById(R.id.recycler_view);


        Intent i = getIntent();
        //String test = i.getStringExtra("response");
        res = new Gson().fromJson( i.getStringExtra("response") ,ServicesResponseModel.class );

        mAdapter = new ServicesListAdapter();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.row1:
                Intent i = new Intent(ServiceListActivity.this, ServiceBookingActivity.class);
                startActivity(i);
                break;


        }
    }
}
