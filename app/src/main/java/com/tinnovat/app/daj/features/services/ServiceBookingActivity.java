package com.tinnovat.app.daj.features.services;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.tinnovat.app.daj.data.network.model.Service;
import com.tinnovat.app.daj.data.network.model.ServiceAvailableDate;
import com.tinnovat.app.daj.data.network.model.ServiceSlots;
import com.tinnovat.app.daj.data.network.model.ServicesResponseModel;
import com.tinnovat.app.daj.testing.MoviesAdapter;
import com.tinnovat.app.daj.utils.CommonUtils;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceBookingActivity extends BaseActivity {

    MaterialCalendarView cal;
    TextView monthTitle;
    TextView locationText;
    ImageView servicesImage;

    private RecyclerView recyclerView;
    private ChooseDateAdapter mAdapter;

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

        recyclerView = findViewById(R.id.recycler_view);

        Intent i = getIntent();
        setData(new Gson().fromJson( i.getStringExtra("response") ,Service.class ));

        setCalender();

        fetchServiceAvailableSlots();

    }

    private void fetchServiceAvailableSlots() {

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<ServiceSlots> call = apiInterface.getAvailableTimeSlots("1","2","2018-06-28");
        call.enqueue(new Callback<ServiceSlots>() {
            @Override
            public void onResponse(Call<ServiceSlots> call, Response<ServiceSlots> response) {
                showMessage("Data Fetched Successfully");
                /*if (response.body() != null && response.body().getServiceCategory() != null) {


                   // setData(response);
                    showMessage("ServiceList success");
                }*/
            }

            @Override
            public void onFailure(Call<ServiceSlots> call, Throwable t) {

                showMessage("ServiceAvailableSlots Failed");
            }
        });
    }

    private void setDateCategory(List<ServiceAvailableDate> serviceAvailableDate){
        mAdapter = new ChooseDateAdapter(serviceAvailableDate);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private void setData(Service res){
        locationText.setText(res.getName());
        Picasso.get().load(res.getServiceImages()).into(servicesImage);

        setDateCategory(res.getServiceAvailableDates());
    }

    private void setCalender(){

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
        cal.setSelectedDate(CalendarDay.today());
        cal.setSelected(true);
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
}
