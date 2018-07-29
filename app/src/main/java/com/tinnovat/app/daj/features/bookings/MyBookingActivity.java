package com.tinnovat.app.daj.features.bookings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.MyServiceBookingResponseModel;
import com.tinnovat.app.daj.data.network.model.RequestParams;
import com.tinnovat.app.daj.data.network.model.SuccessResponseModel;
import com.tinnovat.app.daj.utils.CommonUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBookingActivity extends BaseActivity implements MyBookingsAdapter.SelectAdapterListener,UpcomingMyBookingsAdapter.DeleteEventListener{


    RelativeLayout relativeLayout;
    TextView upComingBanner;
    String anotherDate = null;
    List<Integer> mSelectedBookings = new ArrayList<>();
    List<Integer> mSelectedBookings1 = new ArrayList<>();
    UpcomingMyBookingsAdapter mAdapter;
    MyBookingsAdapter mMyBookingAdapter;
    RecyclerView recyclerView;
    private AppPreferanceStore appPreferanceStore;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.my_booking));

        relativeLayout= findViewById(R.id.relativeLayout);
        upComingBanner= findViewById(R.id.upComingBanner);
        ImageView delete = findViewById(R.id.delete);

        appPreferanceStore = new AppPreferanceStore(this);

        fetchMyBooking();

        SetCalenderView();

        delete.setOnClickListener(this);

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
        switch (v.getId()){
            case R.id.delete:
                mSelectedBookings1.add(51);
                invokeDeleteBooking(mSelectedBookings);
                break;
        }

    }

    private void SetCalenderView(){
        MaterialCalendarView cal = findViewById(R.id.calendarView);

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
                anotherDate =CommonUtils.getInstance().getDate2(date.getCalendar()) ;
                setDate(date.getCalendar());
                fetchMyBookingOnAnotherDate(CommonUtils.getInstance().getDate(date.getCalendar()));

                if (CommonUtils.getInstance().getDate2(CalendarDay.today().getCalendar()).equals(anotherDate) ){
                    relativeLayout.setVisibility(View.VISIBLE);
                    upComingBanner.setVisibility(View.VISIBLE);
                    fetchMyBooking();
                }else {
                    fetchMyBookingOnAnotherDate(CommonUtils.getInstance().getDate(date.getCalendar()));
                    relativeLayout.setVisibility(View.GONE);
                    upComingBanner.setVisibility(View.GONE);
                }

            }
        });
    }

    private void fetchMyBookingOnAnotherDate(String date) {

        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<MyServiceBookingResponseModel> call = apiInterface.getBookingAnotherDate(appPreferanceStore.getLanguage() ? "en" : "ar",date);
        call.enqueue(new Callback<MyServiceBookingResponseModel>() {
            @Override
            public void onResponse(Call<MyServiceBookingResponseModel> call, Response<MyServiceBookingResponseModel> response) {
                endLoading();
                showMessage("Category list Successfully");
                //setData(response);

                //todo changes

                setData(response,anotherDate);
              //  setUpComingData(response);
            }

            @Override
            public void onFailure(Call<MyServiceBookingResponseModel> call, Throwable t) {
                endLoading();

                showMessage("Category list Failed");
            }
        });
    }

    private void fetchMyBooking() {
        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<MyServiceBookingResponseModel> call = apiInterface.getBookingServices(appPreferanceStore.getLanguage() ? "en" : "ar");
        call.enqueue(new Callback<MyServiceBookingResponseModel>() {
            @Override
            public void onResponse(Call<MyServiceBookingResponseModel> call, Response<MyServiceBookingResponseModel> response) {
                endLoading();
                showMessage("Category list Successfully");

                setData(response,CommonUtils.getInstance().getDate2(CalendarDay.today().getCalendar()));
                setUpComingData(response);
            }

            @Override
            public void onFailure(Call<MyServiceBookingResponseModel> call, Throwable t) {
                endLoading();

                showMessage("Category list Failed");
            }
        });
    }

    private void setData(Response<MyServiceBookingResponseModel> response ,String date){
      //  RecyclerView recyclerView;

        recyclerView = findViewById(R.id.recycler_view);

        mMyBookingAdapter = new MyBookingsAdapter(response.body(),date,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mMyBookingAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mMyBookingAdapter);
    }

    private void setUpComingData(Response<MyServiceBookingResponseModel> response){

        //UpcomingMyBookingsAdapter mAdapter;
        recyclerView = findViewById(R.id.recycler_view2);

        mAdapter = new UpcomingMyBookingsAdapter(response.body(),this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);
    }


    private void invokeDeleteBooking(List<Integer> booking_id) {
        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        RequestParams.DeleteServiceBookingRequest deleteServiceBookingRequest = new RequestParams().new DeleteServiceBookingRequest(booking_id);
        Call<SuccessResponseModel> call = apiInterface.deleteServiceBooking(deleteServiceBookingRequest);
        call.enqueue(new Callback<SuccessResponseModel>() {
            @Override
            public void onResponse(Call<SuccessResponseModel> call, Response<SuccessResponseModel> response) {
                endLoading();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        showMessage(response.body().getMessage());
                        //finish();
                      //  mMyBookingAdapter.notifyDataSetChanged();
                       // recyclerView.setAdapter(mMyBookingAdapter);
                        fetchMyBooking();
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
    public void onBookingSelected(List<Integer> selectedBookings) {
        mSelectedBookings = selectedBookings;

    }

    @Override
    public void onDeleteItemSelected(List<Integer> selectedItems) {
        invokeDeleteBooking(selectedItems);

    }
}
