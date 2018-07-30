package com.tinnovat.app.daj.features.eventAndNews;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailActivity extends BaseActivity {
    private EventDetails mEventDetailsList;
    private int mId = 0;
    private Button interested;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.event_details));


        setData();

    }

    private void setData(){
        Intent i = getIntent();
        mEventDetailsList = new Gson().fromJson( i.getStringExtra("response") , EventDetails.class );
        showMessage("done");
        mId = mEventDetailsList.getId();

        interested = findViewById(R.id.interested);
        TextView startDate = findViewById(R.id.startDate);
        TextView endDate = findViewById(R.id.endDate);
        TextView startTime = findViewById(R.id.startTime);
        TextView endTime = findViewById(R.id.endTime);
        TextView venue = findViewById(R.id.venue);
        TextView tittle = findViewById(R.id.tittle);
        TextView description = findViewById(R.id.discrptn);
        ImageView backBg = findViewById(R.id.backBg);

        if (mEventDetailsList.getInterest()){
            if (mEventDetailsList.getUserInterested()){
                doInterested(true);
            }else {
                doInterested(false);
            }
        }else {
            doInterested(true);
        }

        if (mEventDetailsList.getEventsImages().size() != 0){
            Picasso.get().load(mEventDetailsList.getEventsImages().get(0).getImgPath()).into(backBg);
        }

        String[] startDateTime = mEventDetailsList.getStartDatetime().split(" ");
        String[] endDateTime = mEventDetailsList.getEndDatetime().split(" ");

        startDate.setText(startDateTime[0]);
        endDate.setText(endDateTime[0]);
        startTime.setText(startDateTime[1]+" "+endDateTime[2]);
        endTime.setText(endDateTime[1]+" "+endDateTime[2]);
        venue.setText(mEventDetailsList.getEventsVenue());
        tittle.setText(mEventDetailsList.getEventsName());
        description.setText(mEventDetailsList.getEventsDescription());

        interested.setOnClickListener(this);
    }

    private void doInterested(boolean interest){
       if (interest){
           interested.setEnabled(false);
           interested.setText(getResources().getString(R.string.interested));
           interested.setBackground(getResources().getDrawable(R.drawable.curve_small_bg_disable));
       }else {
           interested.setEnabled(true);
           interested.setText(getResources().getString(R.string.interest_to_attend));
           interested.setBackground(getResources().getDrawable(R.drawable.curve_small_bg_green));
       }
    }

    private void actionInterested(){
        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
      //  RequestParams.DeleteServiceBookingRequest deleteServiceBookingRequest = new RequestParams().new DeleteServiceBookingRequest(booking_id);
        Call<SuccessResponseModel> call = apiInterface.postEventInterset(mId);
        call.enqueue(new Callback<SuccessResponseModel>() {
            @Override
            public void onResponse(Call<SuccessResponseModel> call, Response<SuccessResponseModel> response) {
                endLoading();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        showMessage(response.body().getMessage());
                        doInterested(true);
                        //finish();
                        //  mMyBookingAdapter.notifyDataSetChanged();
                        // recyclerView.setAdapter(mMyBookingAdapter);
                       // fetchMyBookingService();
                    } else {
                        showMessage(response.body().getMessage());
                        // finish();
                    }
                }else {
                    showMessage("1 "+getResources().getString(R.string.network_problem));
                }
            }

            @Override
            public void onFailure(Call<SuccessResponseModel> call, Throwable t) {
                endLoading();
                showMessage("2 "+getResources().getString(R.string.network_problem));
            }
        });
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
            case R.id.interested:
                actionInterested();
                break;
        }

    }
}
