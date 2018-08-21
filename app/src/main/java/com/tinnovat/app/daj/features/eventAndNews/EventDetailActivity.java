package com.tinnovat.app.daj.features.eventAndNews;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.EventDetails;
import com.tinnovat.app.daj.data.network.model.RequestParams;
import com.tinnovat.app.daj.data.network.model.SuccessResponseModel;
import com.tinnovat.app.daj.features.futurePhase.ImageListAdapter;

import java.util.List;
import java.util.Objects;

import me.biubiubiu.justifytext.library.JustifyTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailActivity extends BaseActivity {
    private EventDetails mEventDetailsList;
    private int mId = 0;
    private Button interested;
    private JustifyTextView description;
    private TextView readMore;
    private TextView readLess;
    private TextView bannerMore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        //Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.event_details));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getText(R.string.event_details));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(1).setChecked(true);
        navigationView.setItemIconTintList(null);


        setData();

    }

    private void setData() {
        Intent i = getIntent();
        mEventDetailsList = new Gson().fromJson(i.getStringExtra("response"), EventDetails.class);
        mId = mEventDetailsList.getId();

        interested = findViewById(R.id.interested);
        TextView startDate = findViewById(R.id.startDate);
        TextView endDate = findViewById(R.id.endDate);
        TextView startTime = findViewById(R.id.startTime);
        TextView endTime = findViewById(R.id.endTime);
        TextView venue = findViewById(R.id.venue);
        TextView tittle = findViewById(R.id.tittle);
        description = findViewById(R.id.discrptn);
        readMore = findViewById(R.id.readMore);
        readLess = findViewById(R.id.readLess);
        bannerMore = findViewById(R.id.bannerMore);
        ImageView backBg = findViewById(R.id.backBg);

        if (mEventDetailsList.getEventsImages().size() == 0){
            bannerMore.setVisibility(View.GONE);
        }else {
            bannerMore.setVisibility(View.VISIBLE);
        }


        if (mEventDetailsList.getInterest()) {
            if (mEventDetailsList.getUserInterested()) {
                doInterested(true);
            } else {
                doInterested(false);
            }
        } else {
            if (mEventDetailsList.getUserInterested()) {
                doInterested(true);
            } else {
                interested.setEnabled(false);
                interested.setText(getResources().getString(R.string.fully_booked));
                interested.setBackground(getResources().getDrawable(R.drawable.curve_small_bg_disable));
            }

        }

        if (mEventDetailsList.getEventsImages().size() != 0) {
            Picasso.get().load(mEventDetailsList.getEventsImages().get(0).getImgPath()).into(backBg);
        }

        String[] startDateTime = mEventDetailsList.getStartDatetime().split(" ");
        String[] endDateTime = mEventDetailsList.getEndDatetime().split(" ");

        startDate.setText(startDateTime[0]);
        endDate.setText(endDateTime[0]);
        startTime.setText(String.format(getResources().getString(R.string.time_formatter), startDateTime[1], startDateTime[2]));
        endTime.setText(String.format(getResources().getString(R.string.time_formatter), endDateTime[1], endDateTime[2]));

        venue.setText(mEventDetailsList.getEventsVenue());
        tittle.setText(mEventDetailsList.getEventsName());
        description.setText(String.format(getResources().getString(R.string.description_formatter),mEventDetailsList.getEventsDescription()));


        description.post(new Runnable() {
            @Override
            public void run() {
                int lineCount = description.getLineCount();
                if (lineCount > 5){
                    readLess.setVisibility(View.GONE);
                    readMore.setVisibility(View.VISIBLE);
                }else {
                    readLess.setVisibility(View.GONE);
                    readMore.setVisibility(View.GONE);
                }
            }
        });



        interested.setOnClickListener(this);
        readMore.setOnClickListener(this);
        readLess.setOnClickListener(this);
        setAdapter();
    }

    private void setAdapter() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        EventImageListAdapter mAdapter = new EventImageListAdapter(mEventDetailsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);
    }

    private void doInterested(boolean interest) {
        if (interest) {
            interested.setEnabled(false);
            interested.setText(getResources().getString(R.string.interested));
            interested.setBackground(getResources().getDrawable(R.drawable.curve_small_bg_disable));
        } else {
            interested.setEnabled(true);
            interested.setText(getResources().getString(R.string.interest_to_attend));
            interested.setBackground(getResources().getDrawable(R.drawable.curve_small_bg_green));

        }
    }

    private void actionInterested() {
        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        Call<SuccessResponseModel> call = apiInterface.postEventInterset(mId);
        call.enqueue(new Callback<SuccessResponseModel>() {
            @Override
            public void onResponse(Call<SuccessResponseModel> call, Response<SuccessResponseModel> response) {
                endLoading();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        showDilog(response.body().getMessage());
                        doInterested(true);
                    } else {
                        showDilog(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<SuccessResponseModel> call, Throwable t) {
                endLoading();
                showDilog("Please Try After SomeTime !");
            }
        });
    }

    private void showDilog(String message){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void doReadMore() {
        description.setMaxLines(50);
        readLess.setVisibility(View.VISIBLE);
        readMore.setVisibility(View.GONE);
    }

    private void doReadLess() {
        description.setMaxLines(8);
        readLess.setVisibility(View.GONE);
        readMore.setVisibility(View.VISIBLE);
    }

    @Override
    public void initialiseViews() {

    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.interested:
                actionInterested();
                break;

            case R.id.readMore:
                doReadMore();
                break;

            case R.id.readLess:
                doReadLess();
                break;
        }

    }
}
