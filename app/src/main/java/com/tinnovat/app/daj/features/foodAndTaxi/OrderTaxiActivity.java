package com.tinnovat.app.daj.features.foodAndTaxi;

import android.os.Bundle;
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

public class OrderTaxiActivity extends BaseActivity {

    private AppPreferanceStore appPreferanceStore;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.order_taxi));

        appPreferanceStore = new AppPreferanceStore(this);

        fetchTaxiApps();
    }

    private void fetchTaxiApps() {

        startLoading();
        ImageView banner = findViewById(R.id.bannerText);
        banner.setImageResource(R.drawable.order_car);

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<FoodResponseModel> call = apiInterface.getTaxiApps(appPreferanceStore.getLanguage() ? "en" : "ar","android");
        call.enqueue(new Callback<FoodResponseModel>() {
            @Override
            public void onResponse(Call<FoodResponseModel> call, Response<FoodResponseModel> response) {
                endLoading();
                showMessage("TaxiApps Successfully");
                setData(response);
            }

            @Override
            public void onFailure(Call<FoodResponseModel> call, Throwable t) {

                endLoading();
                showMessage("TaxiApps Failed");
            }
        });
    }

    private void setData(Response<FoodResponseModel> response){
        GridView gridview =  findViewById(R.id.gridview);
        gridview.setAdapter(new OrderAdapter(this,response));
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
