package com.tinnovat.app.daj.features.Complaint;

import android.content.Intent;
import android.graphics.Paint;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.ComplaintList;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MyComplaintActivity extends BaseActivity {

    TextView locationText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_complaint);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.my_complaint));
        locationText = findViewById(R.id.locationText);

        locationText.setPaintFlags(locationText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Intent i = getIntent();
        ComplaintList complaintList = new Gson().fromJson(i.getStringExtra("response"), ComplaintList.class);
        setData(complaintList);
        // description.setText(Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID));
    }

    public void setData(ComplaintList complaintList) {
        TextView complaintTitle = findViewById(R.id.complaintTitle);
        TextView complaintDate = findViewById(R.id.complaintDate);
        TextView status = findViewById(R.id.status);
        TextView description = findViewById(R.id.description);


        complaintTitle.setText(complaintList.getCategoryName());
        complaintDate.setText(complaintList.getSubmittedDate());
        description.setText(complaintList.getDescription());

        switch (complaintList.getComplaintStatus()) {
            case 0:
                status.setText(R.string.submitted);
                status.setBackgroundResource(R.drawable.curve_small_bg_green);
                break;

            case 1:
                status.setText(R.string.in_progress);
                status.setBackgroundResource(R.drawable.curve_small_bg_orange);
                break;

            case 2:
                status.setText(R.string.completed);
                status.setBackgroundResource(R.drawable.curve_small_bg_red);
                break;
        }

        if (complaintList.getImages().size() != 0) {
            ImageView image1 = findViewById(R.id.image1);
            ImageView image2 = findViewById(R.id.image2);
            ImageView image3 = findViewById(R.id.image3);
            ImageView image4 = findViewById(R.id.image4);
            LinearLayout secondRow = findViewById(R.id.secondRow);
            RelativeLayout imageLayout = findViewById(R.id.photoLayout);

            TextView locationText = findViewById(R.id.locationText);

            LinearLayout banner = findViewById(R.id.banner);


            if (getAddress(complaintList.getLocation().getLat(), complaintList.getLocation().getLong()) != null) {
                banner.setVisibility(View.VISIBLE);
                locationText.setText(getAddress(complaintList.getLocation().getLat(), complaintList.getLocation().getLong()));
            } else
                banner.setVisibility(View.GONE);

            switch (complaintList.getImages().size()) {
                case 0:
                    imageLayout.setVisibility(View.GONE);
                    break;
                case 1:
                    imageLayout.setVisibility(View.VISIBLE);
                    secondRow.setVisibility(View.GONE);
                    Picasso.get().load(complaintList.getImages().get(0).getPath()).into(image1);


                    break;

                case 2:
                    imageLayout.setVisibility(View.VISIBLE);
                    secondRow.setVisibility(View.VISIBLE);
                    image4.setVisibility(View.VISIBLE);
                    Picasso.get().load(complaintList.getImages().get(0).getPath()).into(image1);
                    Picasso.get().load(complaintList.getImages().get(1).getPath()).into(image4);

                    break;

                case 3:
                    Picasso.get().load(complaintList.getImages().get(0).getPath()).into(image1);
                    Picasso.get().load(complaintList.getImages().get(1).getPath()).into(image2);
                    Picasso.get().load(complaintList.getImages().get(3).getPath()).into(image3);
                    image4.setVisibility(View.GONE);
                    break;

                default:
                    break;


            }


        } else {
            RelativeLayout imageLayout = findViewById(R.id.photoLayout);
            imageLayout.setVisibility(View.GONE);
        }

        setStatus(complaintList);
    }

    private String getAddress(double lat, double lng) {

        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            if (addresses.size() != 0)
                return addresses.get(0).getAdminArea();
            else
                return null;
        } catch (IOException e) {
            //e.printStackTrace();
            Log.e("Error : Geocoder", "Impossible to connect to Geocoder", e);
        }
        return null;
    }

    public void setStatus(ComplaintList response) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        StatusListAdapter mAdapter = new StatusListAdapter(response);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
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
