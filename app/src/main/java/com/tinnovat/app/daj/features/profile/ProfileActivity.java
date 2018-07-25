package com.tinnovat.app.daj.features.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.Objects;

public class ProfileActivity extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.my_profile));

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

        name.setText(getName());
        userName.setText(getUserName());
        if (getGender()==0){
            gender.setText(getString(R.string.female));
        }else {
            gender.setText(getString(R.string.male));
        }
        villaNo.setText(getResources().getString(R.string.villa_no_045)+getVillaNo());
        dob.setText(getDob());
        joiningDate.setText(getJod());
        email.setText(getEmail());
        address.setText(getAddress());
        occupation.setText(getOccupation());
        if (getMaritalStatus()==0){
            maritalStatus.setText(getString(R.string.single));
        }else {
            maritalStatus.setText(getString(R.string.married));
        }

        nationality.setText(getNationality());
        mobNo.setText(getMobNo());
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
