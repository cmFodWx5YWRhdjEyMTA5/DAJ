package com.tinnovat.app.daj.features.services;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.Service;

import java.util.Objects;

public class ServiceListActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ServicesListAdapter mAdapter;
    private Service[] res;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.services_list));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recycler_view);

        setData();

    }

    private void setData(){
        Intent i = getIntent();
        res = new Gson().fromJson( i.getStringExtra("response") ,Service[].class );

        mAdapter = new ServicesListAdapter(res, i.getIntExtra("category_id",0) );
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
        /*switch (v.getId()) {
            case R.id.row1:
                Intent i = new Intent(ServiceListActivity.this, ServiceBookingActivity.class);
                startActivity(i);
                break;
        }*/
    }
}
