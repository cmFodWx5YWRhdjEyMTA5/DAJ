package com.tinnovat.app.daj.features.authentication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pchmn.androidverify.Form;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.RequestParams;
import com.tinnovat.app.daj.data.network.model.SuccessResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        //Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.change_password));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getText(R.string.change_password));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(12).setChecked(true);
        navigationView.setItemIconTintList(null);

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);
       // initialiseEventListners();
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


        Form form = new Form.Builder(this)
                .showErrors(true)
                .build();

        // validate the form
        if (form.isValid()) {
            if (newPassword.getText().toString().equals(cnfrmPassword.getText().toString())){

                invokeChangePassword(currentPassword.getText().toString(),newPassword.getText().toString());
            }else {
                showDilog(getResources().getString(R.string.password_mismatch),false);
            }
        }

    }
    private void showDilog(String message, final Boolean error){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        if (error){
                            finish();
                        }
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void invokeChangePassword(String oldPassword, String newPassword) {
        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        RequestParams.ChangePasswordRequest changePasswordRequest = new RequestParams().new ChangePasswordRequest(oldPassword, newPassword);
        Call<SuccessResponseModel> call = apiInterface.postChangePassword(changePasswordRequest);
        call.enqueue(new Callback<SuccessResponseModel>() {
            @Override
            public void onResponse(Call<SuccessResponseModel> call, Response<SuccessResponseModel> response) {
                endLoading();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        showDilog(response.body().getMessage(),true);
                    } else {
                        showDilog(response.body().getMessage(),true);
                    }
                }
            }

            @Override
            public void onFailure(Call<SuccessResponseModel> call, Throwable t) {
                endLoading();
                showDilog("Please Try Again Later!",true);
            }
        });
    }
}
