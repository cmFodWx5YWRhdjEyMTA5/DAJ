package com.tinnovat.app.daj.features.services;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import com.squareup.picasso.Picasso;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.RequestParams;
import com.tinnovat.app.daj.data.network.model.Service;
import com.tinnovat.app.daj.data.network.model.ServiceAvailableDate;
import com.tinnovat.app.daj.data.network.model.ServiceSlots;
import com.tinnovat.app.daj.data.network.model.SuccessResponseModel;
import com.tinnovat.app.daj.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceBookingActivity extends BaseActivity implements ChooseDateAdapter.DateAdapterListener {

    MaterialCalendarView cal;
    TextView monthTitle;
    TextView locationText;
    ImageView servicesImage;
    Button buttonBook;

    int category_id;
    String selectedDate = null;

    private List<Integer> mSelectedTimeSlots = new ArrayList<>();

    private Service res;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_booking);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.service_booking));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cal = findViewById(R.id.calendarView);
        monthTitle = findViewById(R.id.monthTitle);
        locationText = findViewById(R.id.locationText);
        servicesImage = findViewById(R.id.servicesImage);
        buttonBook = findViewById(R.id.button_book);
        buttonBook.setOnClickListener(this);


        Intent i = getIntent();
        category_id = i.getIntExtra("category_id",0);
        res = new Gson().fromJson( i.getStringExtra("response") ,Service.class );


        setData(res);

        setCalender();



    }

    private void fetchServiceAvailableSlots(String selectedDate) {
        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<ServiceSlots> call = apiInterface.getAvailableTimeSlots(category_id,res.getId(),selectedDate);
        call.enqueue(new Callback<ServiceSlots>() {
            @Override
            public void onResponse(Call<ServiceSlots> call, Response<ServiceSlots> response) {
                endLoading();
                showMessage("Data Fetched Successfully");

                setDateCategory(response.body().getServiceAvailableDates());
                /*if (response.body() != null && response.body().getServiceCategory() != null) {


                   // setData(response);
                    showMessage("ServiceList success");
                }*/
            }

            @Override
            public void onFailure(Call<ServiceSlots> call, Throwable t) {
                endLoading();

                showMessage("ServiceAvailableSlots Failed");
            }
        });
    }

    private void setDateCategory(List<ServiceAvailableDate> serviceAvailableDate){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        ChooseDateAdapter mAdapter = new ChooseDateAdapter(this, this, serviceAvailableDate);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);
    }

    private void setData(Service res){
        locationText.setText(res.getName());
        Picasso.get().load(res.getServiceImages()).into(servicesImage);

        setDateCategory(res.getServiceAvailableDates());
    }

    private void setCalender(){

        cal.state().edit()
                .setMinimumDate(CalendarDay.from(CalendarDay.today().getYear(), CalendarDay.today().getMonth(), CalendarDay.today().getDay()))
                .commit();

        cal.setSelectedDate(CalendarDay.today());
        cal.setSelected(true);

        if(getLanguage()){
            cal.setRightArrowMask(ContextCompat.getDrawable(this, R.drawable.arrow_right));
            cal.setLeftArrowMask(ContextCompat.getDrawable(this, R.drawable.arrow_left));
        }else {
            cal.setRightArrowMask(ContextCompat.getDrawable(this, R.drawable.arrow_left));
            cal.setLeftArrowMask(ContextCompat.getDrawable(this, R.drawable.arrow_right));
        }

        String[] weekDays ={ "Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
        String[] months = { "January","February","March","April","May","June","July","August","September","October","November","December" };

        cal.setWeekDayLabels(weekDays);

        DayFormatter day = new DayFormatter() {
            @NonNull
            @Override
            public String format(@NonNull CalendarDay day) {
                return ""+day.getDay();
            }
        };
        cal.setTitleMonths(months);
        cal.setDayFormatter(day);
        cal.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                monthTitle.setVisibility(View.INVISIBLE);
                cal.setHeaderTextAppearance(R.style.CalenderViewCustomWeekHeading);
            }
        });

        cal.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                monthTitle.setVisibility(View.VISIBLE);
                cal.setHeaderTextAppearance(R.style.CalenderViewCustomWeekHeading1);
                //  monthTitle.setText(""+getCurrentMonthWeek(date.getMonth())+" "+date.getYear());
                monthTitle.setText(CommonUtils.getInstance().getMonthWithYear(date.getCalendar()));
                selectedDate = CommonUtils.getInstance().getDate(date.getCalendar());

                fetchServiceAvailableSlots(selectedDate);

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
        switch (v.getId()) {
            case R.id.button_book :

                if (mSelectedTimeSlots.isEmpty()){
                    showMessage(getResources().getString(R.string.select_time));
                }else {
                    fetchData();
                }

            break;
        }

    }

    private void fetchData(){
        EditText noOfGuest = findViewById(R.id.noOfGuest);
        EditText commentBox = findViewById(R.id.commentBox);
        if (selectedDate == null){
            selectedDate = CommonUtils.getInstance().getDate( CalendarDay.today().getCalendar());
        }

        if ((noOfGuest.getText().toString().isEmpty())){
            doBooking(category_id,res.getId(), selectedDate ,mSelectedTimeSlots,0,commentBox.getText().toString());
        }else if (commentBox.getText().toString().isEmpty()){
            doBooking(category_id,res.getId(), selectedDate ,mSelectedTimeSlots,Integer.parseInt(noOfGuest.getText().toString())," ");
        }else if ((noOfGuest.getText().toString().isEmpty()) && commentBox.getText().toString().isEmpty()){
            doBooking(category_id,res.getId(), selectedDate ,mSelectedTimeSlots,0," ");

        }else {
            doBooking(category_id,res.getId(), selectedDate ,mSelectedTimeSlots,Integer.parseInt(noOfGuest.getText().toString()),commentBox.getText().toString());
        }

    }


    private void doBooking(int category_id , int service_id, String date, List<Integer> timeSlots, int guest_no, String comments){

        startLoading();


        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        RequestParams.ServiceBookingRequest serviceBookingRequest = new RequestParams().new ServiceBookingRequest(category_id , service_id, date, timeSlots, guest_no, comments);

        Call<SuccessResponseModel> call = apiInterface.serviceBooking(serviceBookingRequest);
            call.enqueue(new Callback<SuccessResponseModel>() {
                @Override
                public void onResponse(Call<SuccessResponseModel> call, Response<SuccessResponseModel> response) {
                    endLoading();
                    if (response.body() != null) {
                        if (response.body().getSuccess()) {
                            showMessage(response.body().getMessage());
                        } else {
                            showMessage(response.body().getMessage());
                        }
                        finish();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDateSelected(List<Integer> selectedTimeSlots) {
        mSelectedTimeSlots = selectedTimeSlots;
    }
}
