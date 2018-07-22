package com.tinnovat.app.daj.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.FuturePhasesResponseModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FuturePhaseInfoListActivity extends BaseActivity {

    RelativeLayout row1;
    RelativeLayout row2;
    RelativeLayout row3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_phase_info_list);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.future_phase_info_list));

        row1 = findViewById(R.id.row1);
        row2 = findViewById(R.id.row2);
        row3 = findViewById(R.id.row3);

        row1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( FuturePhaseInfoListActivity.this,FuturePhaseInfoDetailActivity.class);
                startActivity(i);
            }
        });

        fetchFuturePhasesInfo();
    }
    private void fetchFuturePhasesInfo() {

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<FuturePhasesResponseModel> call = apiInterface.getFuturePhasesInfo("en");
        call.enqueue(new Callback<FuturePhasesResponseModel>() {
            @Override
            public void onResponse(Call<FuturePhasesResponseModel> call, Response<FuturePhasesResponseModel> response) {
                showMessage("Future PhasesInfo Successfully");

            }

            @Override
            public void onFailure(Call<FuturePhasesResponseModel> call, Throwable t) {

                showMessage("FuturePhasesInfo Failed");
            }
        });
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
