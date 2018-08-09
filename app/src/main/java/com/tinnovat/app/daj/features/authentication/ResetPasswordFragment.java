package com.tinnovat.app.daj.features.authentication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pchmn.androidverify.Form;
import com.tinnovat.app.daj.BaseFragment;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.RequestParams;
import com.tinnovat.app.daj.data.network.model.SuccessResponseModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordFragment extends BaseFragment {

    EditText userName;
    EditText email;
    Form form;


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
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getText(R.string.reset_password));
        }
        toolbar.setNavigationOnClickListener(this);
        return view;
    }


    @Override
    public void initialiseViews(View view) {
        form = new Form.Builder(getActivity(), view)
                .showErrors(true)
                .build();

        Button submit = view.findViewById(R.id.submit);
        userName = view.findViewById(R.id.userName);
        email = view.findViewById(R.id.email);

        //submit.setOnClickListener(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (form.isValid()) {
                    //  invokeResetPassword(userName.getText().toString(),email.getText().toString());
                    startLoading();

                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    RequestParams.ResetPasswordRequest resetPasswordRequest = new RequestParams().new ResetPasswordRequest(userName.getText().toString(), email.getText().toString());

                    Call<SuccessResponseModel> call = apiInterface.resetPassword(resetPasswordRequest);
                    call.enqueue(new Callback<SuccessResponseModel>() {
                        @Override
                        public void onResponse(Call<SuccessResponseModel> call, Response<SuccessResponseModel> response) {
                            endLoading();

                            //todo change
                            if (response.body() != null) {
                                if (response.body().getSuccess()) {

                                    showMessage(response.body().getMessage());
                                    Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
                                } else {
                                    showMessage(response.body().getMessage());
                                }
                            } else {
                                //showMessage(getResources().getString(R.string.network_problem));
                            }
                        }

                        @Override
                        public void onFailure(Call<SuccessResponseModel> call, Throwable t) {

                            endLoading();
                            //showMessage(getResources().getString(R.string.network_problem));
                        }
                    });

                }
            }
        });

    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public void onClick(View view) {

        if (getActivity() != null)
            getActivity().onBackPressed();

        switch (view.getId()) {

            case R.id.submit:
                //fetchData(view);
                if (form.isValid()) {
                    //  invokeResetPassword(userName.getText().toString(),email.getText().toString());
                    startLoading();

                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    RequestParams.ResetPasswordRequest resetPasswordRequest = new RequestParams().new ResetPasswordRequest(userName.getText().toString(), email.getText().toString());

                    Call<SuccessResponseModel> call = apiInterface.resetPassword(resetPasswordRequest);
                    call.enqueue(new Callback<SuccessResponseModel>() {
                        @Override
                        public void onResponse(Call<SuccessResponseModel> call, Response<SuccessResponseModel> response) {
                            endLoading();
                            //showMessage("Successful");
                            getActivity().getFragmentManager().popBackStack();

                            //todo change
               /* if (response.body() != null) {
                    if (response.body().getSuccess()) {

                        showMessage(response.body().getMessage());
                    } else {
                        showMessage(response.body().getMessage());
                    }
                }else {
                    showMessage(getResources().getString(R.string.network_problem));
                }*/
                        }

                        @Override
                        public void onFailure(Call<SuccessResponseModel> call, Throwable t) {

                            endLoading();
                            //  showMessage(getResources().getString(R.string.network_problem));
                        }
                    });

                }
                break;
        }


    }

    private void fetchData(View view) {
       /* EditText userName = view.findViewById(R.id.userName);
        EditText email = view.findViewById(R.id.email);*/


        // validate the form
        if (form.isValid()) {
            invokeResetPassword(userName.getText().toString(), email.getText().toString());

        } else {
            // showMessage(view,"testtt");
            // showMessage("testtt");
        }


    }

    public void showMessage(View view, String message) {
        Snackbar mySnackbar = Snackbar.make(view,
                message, Snackbar.LENGTH_SHORT);
        mySnackbar.show();
//        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void invokeResetPassword(String userName, String email) {
        startLoading();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        RequestParams.ResetPasswordRequest resetPasswordRequest = new RequestParams().new ResetPasswordRequest(userName, email);

        Call<SuccessResponseModel> call = apiInterface.resetPassword(resetPasswordRequest);
        call.enqueue(new Callback<SuccessResponseModel>() {
            @Override
            public void onResponse(Call<SuccessResponseModel> call, Response<SuccessResponseModel> response) {
                endLoading();
                //   showMessage("Login Successful");
                getActivity().getFragmentManager().popBackStack();

                //todo change
               /* if (response.body() != null) {
                    if (response.body().getSuccess()) {

                        showMessage(response.body().getMessage());
                    } else {
                        showMessage(response.body().getMessage());
                    }
                }else {
                    showMessage(getResources().getString(R.string.network_problem));
                }*/
            }

            @Override
            public void onFailure(Call<SuccessResponseModel> call, Throwable t) {

                endLoading();
                //  showMessage(getResources().getString(R.string.network_problem));
            }
        });
    }

}
