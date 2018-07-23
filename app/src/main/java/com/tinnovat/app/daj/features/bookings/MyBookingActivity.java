package com.tinnovat.app.daj.features.bookings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.MyServiceBookingResponseModel;
import com.tinnovat.app.daj.utils.CommonUtils;

import java.util.Calendar;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBookingActivity extends BaseActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.my_booking));


        fetchMyBooking();

        SetCalenderView();

    }

    @Override
    public void initialiseViews() {

    }

    @Override
    public void initialiseEventListners() {

    }

    private void setDate(Calendar calendar) {
        TextView todayDate = findViewById(R.id.todayDate);
        todayDate.setText(CommonUtils.getInstance().getDateMonth(calendar));
    }

    @Override
    public void onClick(View v) {

    }

    private void SetCalenderView(){
        MaterialCalendarView cal = findViewById(R.id.calendarView);

        cal.setSelectedDate(CalendarDay.today());
        cal.setSelected(true);
        if(getLanguage()){
            cal.setRightArrowMask(ContextCompat.getDrawable(this, R.drawable.arrow_right));
            cal.setLeftArrowMask(ContextCompat.getDrawable(this, R.drawable.arrow_left));
        }else {
            cal.setRightArrowMask(ContextCompat.getDrawable(this, R.drawable.arrow_left));
            cal.setLeftArrowMask(ContextCompat.getDrawable(this, R.drawable.arrow_right));
        }

        setDate(CalendarDay.today().getCalendar());

//        setDate(CalendarDay.today().getDay(),CalendarDay.today().getMonth());
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

        cal.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

//                setDate(date.getDay(),date.getMonth());
                setDate(date.getCalendar());
            }
        });
    }

    private void fetchMyBooking() {
        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<MyServiceBookingResponseModel> call = apiInterface.getBookingServices("en");
        call.enqueue(new Callback<MyServiceBookingResponseModel>() {
            @Override
            public void onResponse(Call<MyServiceBookingResponseModel> call, Response<MyServiceBookingResponseModel> response) {
                endLoading();
                showMessage("Category list Successfully");
                //setData(response);

                setData(response);
            }

            @Override
            public void onFailure(Call<MyServiceBookingResponseModel> call, Throwable t) {
                endLoading();

                showMessage("Category list Failed");
            }
        });
    }

    private void setData(Response<MyServiceBookingResponseModel> response){
        RecyclerView recyclerView;
        MyBookingsAdapter mAdapter;
        recyclerView = findViewById(R.id.recycler_view);

        mAdapter = new MyBookingsAdapter(response.body());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
}
