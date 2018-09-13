package com.tinnovat.app.daj;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.LogoutResponseModel;
import com.tinnovat.app.daj.features.authentication.ChangePasswordActivity;
import com.tinnovat.app.daj.features.authentication.LoginActivity;
import com.tinnovat.app.daj.features.bookings.GuestRegistrationActivityMain;
import com.tinnovat.app.daj.features.bookings.MyBookingActivity;
import com.tinnovat.app.daj.features.complaint.ComplaintMainActivity;
import com.tinnovat.app.daj.features.contact.EmergencyContactActivity;
import com.tinnovat.app.daj.features.dashboard.MainActivity;
import com.tinnovat.app.daj.features.eventAndNews.EventNewsActivity;
import com.tinnovat.app.daj.features.foodAndTaxi.OrderFoodActivity;
import com.tinnovat.app.daj.features.foodAndTaxi.OrderTaxiActivity;
import com.tinnovat.app.daj.features.futurePhase.FuturePhaseMainActivity;
import com.tinnovat.app.daj.features.profile.ProfileActivity;
import com.tinnovat.app.daj.features.services.ServicesMainActivity;
import com.tinnovat.app.daj.features.surveillance.SurveillanceActivity;
import com.tinnovat.app.daj.map.MapsActivity;

import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

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
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void showMessageSnakBar(View view, String message) {
        Snackbar mySnackbar = Snackbar.make(view,
                message, Snackbar.LENGTH_SHORT);
        mySnackbar.show();
//        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {


            fetchHome();
            // getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            // Handle the camera action
            // item.setIcon(R.layout.dialog_guest);
            // getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

           /* Intent i = new Intent(MainActivity.this, MainActivity.class);
            startActivity(i);*/

           /* if (a==1) {
                item.setIcon(R.drawable.icon_slection_arabic);
            }else {
                item.setIcon(R.drawable.icon_slection_english);
            }*/
        } else if (id == R.id.events) {
            fetchEvents();

        } else if (id == R.id.services) {
            fetchServices();
        } else if (id == R.id.guest_registration) {
            fetchGuestRegistration();

        } /*else if (id == R.id.view_camera) {
            fetchCamera();

        }*/ else if (id == R.id.navigation) {
            fetchMaps();

        } else if (id == R.id.projects) {
            fetchFuturePhase();

        } else if (id == R.id.my_bookings) {
            fetchMyBooking();

        } else if (id == R.id.view_profile) {
            fetchProfile();

        } else if (id == R.id.food) {
            fetchOrderFood();

        } else if (id == R.id.taxi) {
            fetchOrderTaxi();

        } else if (id == R.id.complaint) {
            fetchMyComplaintList();
        } else if (id == R.id.emergency_contact) {
            fetchEmergencyContact();

        } else if (id == R.id.change_password) {
            fetchChangePassword();
        } else if (id == R.id.change_language) {
            changeLanguage();
        } else if (id == R.id.logout) {
            fetchLogout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void fetchHome() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }


    public void fetchEvents() {
        Intent i = new Intent(getApplicationContext(), EventNewsActivity.class);
        startActivity(i);
    }

    public void fetchServices() {
        Intent i = new Intent(getApplicationContext(), ServicesMainActivity.class);
        startActivity(i);
    }

    public void fetchGuestRegistration() {
        Intent i = new Intent(getApplicationContext(), GuestRegistrationActivityMain.class);
        startActivity(i);
    }

    public void fetchCamera() {
        Intent i = new Intent(getApplicationContext(), SurveillanceActivity.class);
        startActivity(i);
    }

    public void fetchMaps() {
        Intent i = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(i);
    }

    public void fetchFuturePhase() {
        Intent i = new Intent(getApplicationContext(), FuturePhaseMainActivity.class);
        startActivity(i);
    }

    public void fetchMyBooking() {
        Intent i = new Intent(getApplicationContext(), MyBookingActivity.class);
        startActivity(i);
    }

    public void fetchProfile() {
        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(i);
    }

    public void fetchOrderFood() {
        Intent i = new Intent(getApplicationContext(), OrderFoodActivity.class);
        startActivity(i);
    }

    public void fetchOrderTaxi() {
        Intent i = new Intent(getApplicationContext(), OrderTaxiActivity.class);
        startActivity(i);
    }

    public void fetchMyComplaintList() {
        Intent i = new Intent(getApplicationContext(), ComplaintMainActivity.class);
        startActivity(i);
    }

    public void fetchEmergencyContact() {
        Intent i = new Intent(getApplicationContext(), EmergencyContactActivity.class);
        startActivity(i);
    }

    public void fetchChangePassword() {
        Intent i = new Intent(getApplicationContext(), ChangePasswordActivity.class);
        startActivity(i);
    }

    public void fetchLogout() {
        doLogout();

    }

    public void doLogout() {
        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<LogoutResponseModel> call = apiInterface.getLogout();
        call.enqueue(new Callback<LogoutResponseModel>() {
            @Override
            public void onResponse(Call<LogoutResponseModel> call, Response<LogoutResponseModel> response) {
                endLoading();
                if (response.body() != null){
                    appPreferenceStore.deleteToken();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<LogoutResponseModel> call, Throwable t) {
                endLoading();
            }
        });
    }

    public void changeLanguage(){
        Boolean language = !getLanguage();
        appPreferenceStore.setLanguage(language);
        String languageToLoad;
        if (getLanguage()) {
            languageToLoad = "en"; // english
        } else {
            languageToLoad = "ar"; // arabic
        }

        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        if (getBaseContext() != null)
            finish();
    }
}
