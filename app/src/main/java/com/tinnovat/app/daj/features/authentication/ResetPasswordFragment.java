package com.tinnovat.app.daj.features.authentication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.pchmn.androidverify.Form;
import com.tinnovat.app.daj.BaseFragment;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.RequestParams;
import com.tinnovat.app.daj.data.network.model.SuccessResponseModel;

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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (form.isValid()) {
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

                                    showDilog(response.body().getMessage(),true);
                                } else {
                                    showDilog(response.body().getMessage(),false);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<SuccessResponseModel> call, Throwable t) {

                            endLoading();
                            showDilog(getResources().getString(R.string.try_again_later),true);
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

    }

    private void showDilog(String message, final boolean success){
        final AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        if (success){
                            getActivity().onBackPressed();
                        }

                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

}
