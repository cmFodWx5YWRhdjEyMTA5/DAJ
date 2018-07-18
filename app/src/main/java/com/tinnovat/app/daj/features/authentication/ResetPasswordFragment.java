package com.tinnovat.app.daj.features.authentication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tinnovat.app.daj.BaseFragment;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.LoginResponseModel;
import com.tinnovat.app.daj.data.network.model.RequestParams;
import com.tinnovat.app.daj.data.network.model.SuccessResponseModel;
import com.tinnovat.app.daj.features.dashboard.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordFragment extends BaseFragment {


    public static Fragment getInstance() {
        return new ResetPasswordFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_reset_password, container, false);
        initialiseViews(view);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            if (((AppCompatActivity) getActivity()).getSupportActionBar() != null)
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getText(R.string.reset_password));
        }
        toolbar.setNavigationOnClickListener(this);
        return view;
    }

    private void invokeChangePassword() {
        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        RequestParams.ResetPasswordRequest resetPasswordRequest = new RequestParams().new ResetPasswordRequest("123456", "12345");
        Call<SuccessResponseModel> call = apiInterface.postChangePassword(resetPasswordRequest);
        call.enqueue(new Callback<SuccessResponseModel>() {
            @Override
            public void onResponse(Call<SuccessResponseModel> call, Response<SuccessResponseModel> response) {
                showMessage("Login Successful");
                /*if (response.body() != null && response.body().getData() != null) {


                }*/
            }

            @Override
            public void onFailure(Call<SuccessResponseModel> call, Throwable t) {
                showMessage("Login Failed");
            }
        });
    }

    @Override
    public void initialiseViews(View view) {
        Button submit = view.findViewById(R.id.submit);

        submit.setOnClickListener(this);


    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public void onClick(View view) {

        if (getActivity() != null)
        getActivity().onBackPressed();

        switch (view.getId()){
            case R.id.submit:

                invokeChangePassword();

                break;

                default:
                    break;
        }
    }
}
