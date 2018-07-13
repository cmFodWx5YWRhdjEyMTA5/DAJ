package com.tinnovat.app.daj.features.services;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.services));

        recyclerView = findViewById(R.id.recycler_view);

        fetchServiceList();

       // mAdapter = new ServicesAdapter(movieList);
       /* RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);*/





     /*   List<String> listItems = new ArrayList<String>();

        listItems.add(getResources().getString(R.string.hall1));
        listItems.add(getResources().getString(R.string.hall2));
        listItems.add(getResources().getString(R.string.hall3));
        listItems.add(getResources().getString(R.string.hall4));

        categoryList = listItems.toArray(new CharSequence[listItems.size()]);

        arcHall = findViewById(R.id.arcHall);
        arcHall1 = findViewById(R.id.arcHall1);
        arcCourt = findViewById(R.id.arcCourt);
        arcPool = findViewById(R.id.arcPool);

        if (!getLanguage()){
            arcHall.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_right));
            arcHall.setScaleType(ImageView.ScaleType.FIT_START);

            arcCourt.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_right));
            arcCourt.setScaleType(ImageView.ScaleType.FIT_START);

            arcPool.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_right));
            arcPool.setScaleType(ImageView.ScaleType.FIT_START);

            arcHall1.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_right));
            arcHall1.setScaleType(ImageView.ScaleType.FIT_START);
        }else {
            arcHall.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_left));
            arcHall.setScaleType(ImageView.ScaleType.FIT_END);

            arcCourt.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_left));
            arcCourt.setScaleType(ImageView.ScaleType.FIT_END);

            arcPool.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_left));
            arcPool.setScaleType(ImageView.ScaleType.FIT_END);

            arcHall1.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_left));
            arcHall1.setScaleType(ImageView.ScaleType.FIT_END);
        }

        hall = findViewById(R.id.hall);
        hall2 = findViewById(R.id.hall2);
        pool = findViewById(R.id.pool);
        court = findViewById(R.id.court);



        hall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        hall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        pool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        court.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });*/


    }

    private void fetchServiceList() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjU3NzNjNjJjMGVhMzMyMTcxNWVkN2U2YTg3ZGE1NWU2Zjg5NmE4NWE1NTdlMWM3NTJjNTQyYWQyZWZlMWM5ZjQ2MjQwNWQxNGZiMTczMjg1In0.eyJhdWQiOiIyIiwianRpIjoiNTc3M2M2MmMwZWEzMzIxNzE1ZWQ3ZTZhODdkYTU1ZTZmODk2YTg1YTU1N2UxYzc1MmM1NDJhZDJlZmUxYzlmNDYyNDA1ZDE0ZmIxNzMyODUiLCJpYXQiOjE1MzEzODcyOTksIm5iZiI6MTUzMTM4NzI5OSwiZXhwIjoxNTYyOTIzMjk4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.iybjrJeHEnAeRz974dayPopw1O7jOLLCaRfmViostLhtoNN2wWcTQUAp1JfuGDIqy5Vt_6q9IpdubOVSV--eP14-BlgH0zlRI4rCDvSOlvoVgl6sTPsRDqRNJSN2khefPIwokT2cCyHYDk8GefiZ7g2LeUYtbE5_hNdv8bsmJi3ATvwW8YeJjCDBK7fI0V2I5ZmPdSBGcn7jJc2XPlWjm2M2bukdXbSU1yXH67n7h-AdvPQhSSwneqD0gh2A2DgDaniTExfJ_iZHSyrmi7sYzYRPJuxYuhxTlKy31lDn3GW0pnwEGLrz5kUWzkDJqdZSGwE-FPTOckbJPKjiGgsN9Q-Q2jG5ZXr3MTRGNCRQh7DQjEJks7cjV_HfZDrA1y0rfDhLE0F4wigrlw70Dkrv0yVbXNEqRbGnPe2XojhqieacixWoKBmbn-uXMqSDDsCnsAsUASePkwk7tu72fxHPewjOwf1dsNoDXwEvXOLz8YygAfb4dyzqqufZu40DR7PR7-_1untYJzgqSwBo74Py4zyxZEZsRJ3X0oXSLqHwPXq8w_shobCJ7-QE5YEg0X0p5CThXNyznxyk7Q8I1Bgm29mcemLtKT_J8_twk8Qo6CXUCTxBbVT_XDx55J4SoFYV41_lp-LZGJG6OuEcAB2-Z7hY8VswOVZbNONslRGTnQ0";

        ApiInterface apiInterface = ApiClient.getAuthClient(token).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<ServicesResponseModel> call = apiInterface.getServiceList("en");
        call.enqueue(new Callback<ServicesResponseModel>() {
            @Override
            public void onResponse(Call<ServicesResponseModel> call, Response<ServicesResponseModel> response) {
                showMessage("Data Fetched Successfully");
                if (response.body() != null && response.body().getServiceCategory() != null) {


                    setData(response);
                    showMessage("ServiceList success");
                }
            }

            @Override
            public void onFailure(Call<ServicesResponseModel> call, Throwable t) {

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
