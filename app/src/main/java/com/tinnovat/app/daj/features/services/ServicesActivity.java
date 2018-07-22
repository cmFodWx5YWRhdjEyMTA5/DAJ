package com.tinnovat.app.daj.features.services;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
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
