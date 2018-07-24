package com.tinnovat.app.daj;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.tinnovat.app.daj.data.AppPreferanceStore;

import java.util.Objects;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private AppPreferanceStore appPreferenceStore;
     private Dialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActionBar() != null)
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        appPreferenceStore = new AppPreferanceStore(getApplicationContext());
        appPreferenceStore.changeLocaleLanguage(appPreferenceStore.getLanguage());

        initialiseViews();
        initialiseEventListners();

        appPreferenceStore = new AppPreferanceStore(this);
        appPreferenceStore.changeLocaleLanguage(appPreferenceStore.getLanguage());
        progressDialog = new Dialog(Objects.requireNonNull(this));
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.dialog_progress);
        Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setCancelable(false);
    }
    public void startLoading(){
        progressDialog.show();
    }

    public void endLoading() {
        if (progressDialog != null) {
            progressDialog.cancel();
            progressDialog.hide();
        }
    }

    public abstract void initialiseViews();

    public abstract void initialiseEventListners();


    public void changeOrientation() {
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }

    public void setLanguage(Boolean language) {
        appPreferenceStore.setLanguage(language);
    }

    public String getToken() {
        return appPreferenceStore.getToken();
    }

    public boolean getLanguage() {
        return appPreferenceStore.getLanguage();
    }

    public String getName() {
        return appPreferenceStore.getName();
    }
    public String getUserName(){
        return appPreferenceStore.getUserName();
    }

    public int getGender(){
        return appPreferenceStore.getGender();
    }

    public String getDob(){
        return appPreferenceStore.getDob();
    }

    public String getJod(){
        return appPreferenceStore.getJod();
    }

    public String getEmail(){
        return appPreferenceStore.getEmail();
    }

    public String getAddress(){
        return appPreferenceStore.getAddress();
    }

    public String getOccupation(){
        return appPreferenceStore.getOccupation();
    }

    public String getMobNo(){
        return appPreferenceStore.getMobNo();
    }

    public int getMaritalStatus(){
        return appPreferenceStore.getMaritalStatus();
    }

    public String getNationality(){
        return appPreferenceStore.getNationality();
    }

    public int getStatusBoolean(){
        return appPreferenceStore.getStatusBoolean();
    }

    public String getVillaNo(){
        return appPreferenceStore.getVillaNo();
    }

    public void showMessage( String message) {
      /*  Snackbar mySnackbar = Snackbar.make(view,
                message, Snackbar.LENGTH_SHORT);
        mySnackbar.show();*/
//        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void showMessage(View view, String message) {
        Snackbar mySnackbar = Snackbar.make(view,
                message, Snackbar.LENGTH_SHORT);
        mySnackbar.show();
//        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
