package com.tinnovat.app.daj.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.EventListModel;
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
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjU3NzNjNjJjMGVhMzMyMTcxNWVkN2U2YTg3ZGE1NWU2Zjg5NmE4NWE1NTdlMWM3NTJjNTQyYWQyZWZlMWM5ZjQ2MjQwNWQxNGZiMTczMjg1In0.eyJhdWQiOiIyIiwianRpIjoiNTc3M2M2MmMwZWEzMzIxNzE1ZWQ3ZTZhODdkYTU1ZTZmODk2YTg1YTU1N2UxYzc1MmM1NDJhZDJlZmUxYzlmNDYyNDA1ZDE0ZmIxNzMyODUiLCJpYXQiOjE1MzEzODcyOTksIm5iZiI6MTUzMTM4NzI5OSwiZXhwIjoxNTYyOTIzMjk4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.iybjrJeHEnAeRz974dayPopw1O7jOLLCaRfmViostLhtoNN2wWcTQUAp1JfuGDIqy5Vt_6q9IpdubOVSV--eP14-BlgH0zlRI4rCDvSOlvoVgl6sTPsRDqRNJSN2khefPIwokT2cCyHYDk8GefiZ7g2LeUYtbE5_hNdv8bsmJi3ATvwW8YeJjCDBK7fI0V2I5ZmPdSBGcn7jJc2XPlWjm2M2bukdXbSU1yXH67n7h-AdvPQhSSwneqD0gh2A2DgDaniTExfJ_iZHSyrmi7sYzYRPJuxYuhxTlKy31lDn3GW0pnwEGLrz5kUWzkDJqdZSGwE-FPTOckbJPKjiGgsN9Q-Q2jG5ZXr3MTRGNCRQh7DQjEJks7cjV_HfZDrA1y0rfDhLE0F4wigrlw70Dkrv0yVbXNEqRbGnPe2XojhqieacixWoKBmbn-uXMqSDDsCnsAsUASePkwk7tu72fxHPewjOwf1dsNoDXwEvXOLz8YygAfb4dyzqqufZu40DR7PR7-_1untYJzgqSwBo74Py4zyxZEZsRJ3X0oXSLqHwPXq8w_shobCJ7-QE5YEg0X0p5CThXNyznxyk7Q8I1Bgm29mcemLtKT_J8_twk8Qo6CXUCTxBbVT_XDx55J4SoFYV41_lp-LZGJG6OuEcAB2-Z7hY8VswOVZbNONslRGTnQ0";

        ApiInterface apiInterface = ApiClient.getAuthClient(token).create(ApiInterface.class);
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
