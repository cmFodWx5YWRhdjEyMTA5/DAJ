package com.tinnovat.app.daj.features.foodAndTaxi;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.FoodResponseModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderFoodActivity extends BaseActivity {

    private AppPreferanceStore appPreferanceStore;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        //Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.order_food));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getText(R.string.order_food));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(8).setChecked(true);
        navigationView.setItemIconTintList(null);

        appPreferanceStore = new AppPreferanceStore(this);

        fetchFoodApps();
    }

    private void fetchFoodApps() {

        startLoading();
        ImageView banner = findViewById(R.id.bannerText);
        banner.setImageResource(R.drawable.order_food);

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<FoodResponseModel> call = apiInterface.getFoodApps(appPreferanceStore.getLanguage() ? "en" : "ar","android");
        call.enqueue(new Callback<FoodResponseModel>() {
            @Override
            public void onResponse(Call<FoodResponseModel> call, Response<FoodResponseModel> response) {

                //showMessage("FoodApps Successfully");
                setData(response);
            }

            @Override
            public void onFailure(Call<FoodResponseModel> call, Throwable t) {

                endLoading();
                //showMessage("FoodApps Failed");
            }
        });
    }

    private void setData(Response<FoodResponseModel> response){
        GridView gridview =  findViewById(R.id.gridview);
        gridview.setAdapter(new OrderAdapter(this,response));
        endLoading();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
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
