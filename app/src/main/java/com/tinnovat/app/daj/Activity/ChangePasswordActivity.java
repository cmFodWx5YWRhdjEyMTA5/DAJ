package com.tinnovat.app.daj.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.RequestParams;
import com.tinnovat.app.daj.data.network.model.SuccessResponseModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.change_password));

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);
       // initialiseEventListners();
    }

    @Override
    public void initialiseViews() {

    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.submit:

                checkValidation();

                break;


        }
    }

    private void checkValidation() {
        EditText currentPassword = findViewById(R.id.currentPassword);
        EditText newPassword = findViewById(R.id.newPassword);
        EditText cnfrmPassword = findViewById(R.id.cnfrmPassword);

        if (newPassword.getText().toString().matches("") || newPassword.getText().toString().matches("") ||newPassword.getText().toString().matches("")){
            showMessage(getResources().getString(R.string.fil_all_fields));
        }else {
            if (newPassword.getText().toString().equals(cnfrmPassword.getText().toString())){

                invokeChangePassword(currentPassword.getText().toString(),newPassword.getText().toString());
            }else {
                showMessage(getResources().getString(R.string.password_mismatch));
            }
        }

    }
    private void invokeChangePassword(String oldPassword, String newPassword) {

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        RequestParams.ResetPasswordRequest resetPasswordRequest = new RequestParams().new ResetPasswordRequest(oldPassword, newPassword);
        Call<SuccessResponseModel> call = apiInterface.postChangePassword(resetPasswordRequest);
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
