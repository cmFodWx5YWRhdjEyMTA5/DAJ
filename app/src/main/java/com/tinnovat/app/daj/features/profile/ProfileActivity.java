package com.tinnovat.app.daj.features.profile;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.MyServiceBookingResponseModel;
import com.tinnovat.app.daj.data.network.model.ProfileData;
import com.tinnovat.app.daj.data.network.model.ProfileResponseModel;
import com.tinnovat.app.daj.utils.CommonUtils;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends BaseActivity {

    private AppPreferanceStore appPreferanceStore;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getText(R.string.my_profile));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(8).setChecked(true);
        navigationView.setItemIconTintList(null);

        appPreferanceStore = new AppPreferanceStore(this);

        fetchProfileData();

    }

    @Override
    public void initialiseViews() {


    }


    public void fetchProfileData() {
        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<ProfileResponseModel> call = apiInterface.getProfileData(appPreferanceStore.getLanguage() ? "en" : "ar");
        call.enqueue(new Callback<ProfileResponseModel>() {
            @Override
            public void onResponse(Call<ProfileResponseModel> call, Response<ProfileResponseModel> response) {
                endLoading();
              //  showMessage("Profile list Successfully");
                if (response.body() != null){
                    setData(response.body().getData());
                }



            }

            @Override
            public void onFailure(Call<ProfileResponseModel> call, Throwable t) {
                //showMessage("Profile failed");

            }
        });
    }

   private void setData(ProfileData data){
       TextView name = findViewById(R.id.name);
       TextView gender = findViewById(R.id.gender);
       TextView userName = findViewById(R.id.userName);
       TextView villaNo = findViewById(R.id.villaNo);
       TextView dob = findViewById(R.id.dob);
       TextView joiningDate = findViewById(R.id.joiningDate);
       TextView email = findViewById(R.id.email);
       TextView address = findViewById(R.id.address);
       TextView occupation = findViewById(R.id.occupation);
       TextView maritalStatus = findViewById(R.id.maritalStatus);
       TextView nationality = findViewById(R.id.nationality);
       TextView mobNo = findViewById(R.id.mobNo);

       name.setText(String.format(getResources().getString(R.string.name_formatter),data.getFirstName(),data.getMiddleName(),data.getLastName()));
       userName.setText(data.getUsername());
       if (data.getGender()==0){
           gender.setText(getString(R.string.female));
       }else {
           gender.setText(getString(R.string.male));
       }
       villaNo.setText(String.format(getResources().getString(R.string.villa_no_045),data.getVillaNo()));
       dob.setText(data.getDateOfBirth());
       joiningDate.setText(data.getJoiningDate());
       email.setText(data.getEmail());
       address.setText(data.getPermanentAddress());
       occupation.setText(data.getOccupation());
       if (data.getMaritalStatus()==0){
           maritalStatus.setText(getString(R.string.single));
       }else {
           maritalStatus.setText(getString(R.string.married));
       }

       nationality.setText(data.getNationality());
       mobNo.setText(data.getMobileNo());
   }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public void onClick(View v) {

    }
}
