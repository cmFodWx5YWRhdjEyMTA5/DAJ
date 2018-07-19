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
import android.widget.EditText;

import com.tinnovat.app.daj.BaseFragment;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.ComplaintListResponseModel;
import com.tinnovat.app.daj.data.network.model.LoginResponseModel;
import com.tinnovat.app.daj.data.network.model.RequestParams;
import com.tinnovat.app.daj.data.network.model.SuccessResponseModel;
import com.tinnovat.app.daj.features.dashboard.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordFragment extends BaseFragment {

    EditText userName;
    EditText email;


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



    @Override
    public void initialiseViews(View view) {

        Button submit = view.findViewById(R.id.submit);
        userName = view.findViewById(R.id.userName);
        email = view.findViewById(R.id.email);

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
                fetchData();
                break;
        }


    }

    private void fetchData(){
       /* EditText userName = view.findViewById(R.id.userName);
        EditText email = view.findViewById(R.id.email);*/

        if ( userName.getText().toString().matches("") ||email.getText().toString().matches("")){
            showMessage(getResources().getString(R.string.fil_all_fields));
        }else {
            invokeResetPassword(userName.getText().toString(),email.getText().toString());
        }

    }

    private void invokeResetPassword(String userName, String email) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<SuccessResponseModel> call = apiInterface.resetPassword(userName,email);
        call.enqueue(new Callback<SuccessResponseModel>() {
            @Override
            public void onResponse(Call<SuccessResponseModel> call, Response<SuccessResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        showMessage(response.body().getMessage());
                    } else {
                        showMessage(response.body().getMessage());
                    }
                }else {
                    showMessage(getResources().getString(R.string.network_problem));
                }
            }

            @Override
            public void onFailure(Call<SuccessResponseModel> call, Throwable t) {

                showMessage(getResources().getString(R.string.network_problem));
            }
        });
    }
}
