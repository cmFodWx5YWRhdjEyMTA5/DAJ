package com.tinnovat.app.daj.features.futurePhase;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

    private RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.future_phase_info_list));

        recyclerView = findViewById(R.id.recycler_view);

        fetchFuturePhasesInfo();
    }
    private void fetchFuturePhasesInfo() {
        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<FuturePhasesResponseModel> call = apiInterface.getFuturePhasesInfo("en");
        call.enqueue(new Callback<FuturePhasesResponseModel>() {
            @Override
            public void onResponse(Call<FuturePhasesResponseModel> call, Response<FuturePhasesResponseModel> response) {
                endLoading();

                if (response.body() != null && response.body().getFuturephases() != null) {
                    showMessage("Future PhasesInfo Successfully");
                    setData(response);
                }

            }

            @Override
            public void onFailure(Call<FuturePhasesResponseModel> call, Throwable t) {
                endLoading();

                showMessage("FuturePhasesInfo Failed");
            }
        });
    }

    public void setData(Response<FuturePhasesResponseModel> response){
        FuturePhaseListAdapter mAdapter = new FuturePhaseListAdapter(response);
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
