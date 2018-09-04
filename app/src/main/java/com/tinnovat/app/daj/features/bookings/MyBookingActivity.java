package com.tinnovat.app.daj.features.bookings;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andrognito.flashbar.Flashbar;
import com.andrognito.flashbar.anim.FlashAnim;
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
import com.tinnovat.app.daj.data.network.model.ServiceBooking;
import com.tinnovat.app.daj.data.network.model.SuccessResponseModel;
import com.tinnovat.app.daj.features.services.ServicesMainActivity;
import com.tinnovat.app.daj.utils.CommonUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBookingActivity extends BaseActivity implements MyBookingsAdapter.SelectAdapterListener,
        UpcomingMyBookingsAdapter.DeleteEventListener{


    RelativeLayout relativeLayout;
    TextView upComingBanner;
    String anotherDate = null;
    List<Integer> mSelectedBookings = new ArrayList<>();
    List<Integer> mSelectedBookings1 = new ArrayList<>();
    UpcomingMyBookingsAdapter mAdapter2;
    MyBookingsAdapter mMyBookingAdapter;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    private AppPreferanceStore appPreferanceStore;
    RelativeLayout listToday;
    private int mCount;
    private int mCount2 = 0;
    MaterialCalendarView cal;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);
       // Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.my_booking));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getText(R.string.my_booking));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(6).setChecked(true);
        navigationView.setItemIconTintList(null);

        ImageView facebook = findViewById(R.id.facebook);
        ImageView twitter = findViewById(R.id.twitter);
        ImageView instagram = findViewById(R.id.instagram);


        if (!getLanguage()) {
            //for arabic
            navigationView.getMenu().getItem(0).setIcon(R.drawable.home_ar_nav);
            navigationView.getMenu().getItem(1).setIcon(R.drawable.event_ar_nav);
            navigationView.getMenu().getItem(2).setIcon(R.drawable.service_ar_nav);
            navigationView.getMenu().getItem(3).setIcon(R.drawable.guest_ar_nav);
            //navigationView.getMenu().getItem(4).setIcon(R.drawable.camera_ar_nav);
            navigationView.getMenu().getItem(4).setIcon(R.drawable.navigation_ar_nav);
            navigationView.getMenu().getItem(5).setIcon(R.drawable.project_ar_nav);
            navigationView.getMenu().getItem(6).setIcon(R.drawable.booking_ar_nav);
            navigationView.getMenu().getItem(7).setIcon(R.drawable.profile_ar_nav);
            navigationView.getMenu().getItem(8).setIcon(R.drawable.food_ar_nav);
            navigationView.getMenu().getItem(9).setIcon(R.drawable.taxi_ar_nav);
            navigationView.getMenu().getItem(10).setIcon(R.drawable.complaint_ar_nav);
            navigationView.getMenu().getItem(11).setIcon(R.drawable.contact_ar_nav);
            navigationView.getMenu().getItem(12).setIcon(R.drawable.password_ar_nav);
            navigationView.getMenu().getItem(13).setIcon(R.drawable.language_ar_nav);
            navigationView.getMenu().getItem(14).setIcon(R.drawable.logout_ar_nav);

        } else {
            //for Eng

            navigationView.getMenu().getItem(0).setIcon(R.drawable.home_nav);
            navigationView.getMenu().getItem(1).setIcon(R.drawable.event_nav);
            navigationView.getMenu().getItem(2).setIcon(R.drawable.service_nav);
            navigationView.getMenu().getItem(3).setIcon(R.drawable.guest_nav);
            // navigationView.getMenu().getItem(4).setIcon(R.drawable.camera_nav);
            navigationView.getMenu().getItem(4).setIcon(R.drawable.navigation_nav);
            navigationView.getMenu().getItem(5).setIcon(R.drawable.project_nav);
            navigationView.getMenu().getItem(6).setIcon(R.drawable.booking_nav);
            navigationView.getMenu().getItem(7).setIcon(R.drawable.profile_nav);
            navigationView.getMenu().getItem(8).setIcon(R.drawable.food_nav);
            navigationView.getMenu().getItem(9).setIcon(R.drawable.taxi_nav);
            navigationView.getMenu().getItem(10).setIcon(R.drawable.complaint_nav);
            navigationView.getMenu().getItem(11).setIcon(R.drawable.contact_nav);
            navigationView.getMenu().getItem(12).setIcon(R.drawable.password_nav);
            navigationView.getMenu().getItem(13).setIcon(R.drawable.language_nav);
            navigationView.getMenu().getItem(14).setIcon(R.drawable.logout_nav);

        }


        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(MainActivity.this,"facebook clicked",Toast.LENGTH_SHORT).show();
                Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/DarJewar/?ref=br_rs"));
                startActivity(followIntent);
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/darjewar?lang=en"));
                startActivity(followIntent);
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/darjewar/"));
                startActivity(followIntent);
            }
        });

        FloatingActionButton addService = findViewById(R.id.add);
        addService.setColorFilter(Color.WHITE);



        relativeLayout= findViewById(R.id.relativeLayout);
        upComingBanner= findViewById(R.id.upComingBanner);
        ImageView delete = findViewById(R.id.delete);

        listToday = findViewById(R.id.listToday);

        appPreferanceStore = new AppPreferanceStore(this);

        fetchMyBookingService();

        SetCalenderView();

        delete.setOnClickListener(this);
        addService.setOnClickListener(this);
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
                if(mSelectedBookings.size() !=0){
                    invokeDeleteBooking(mSelectedBookings);
                }
                break;

            case R.id.add:
                String selectedDay = CommonUtils.getInstance().getDate(cal.getSelectedDate().getCalendar());
                fetchServices(selectedDay);
                break;
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
    public void fetchServices(String selectedDay) {
        //Bundle bundle = new Bundle();

        Intent i = new Intent(getApplicationContext(), ServicesMainActivity.class);
        i.putExtra("selectedDay", selectedDay);
        startActivity(i);
    }

    private void SetCalenderView(){
        cal = findViewById(R.id.calendarView);

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
                   // relativeLayout.setVisibility(View.VISIBLE);
                   // upComingBanner.setVisibility(View.VISIBLE);
                    fetchMyBookingService();
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
                //todo changes

                if (response.body() != null){
                    setData(response.body().getServiceBooking());
                }

            }

            @Override
            public void onFailure(Call<MyServiceBookingResponseModel> call, Throwable t) {
                endLoading();
            }
        });
    }

    public void fetchMyBookingService() {
        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<MyServiceBookingResponseModel> call = apiInterface.getBookingServices(appPreferanceStore.getLanguage() ? "en" : "ar");
        call.enqueue(new Callback<MyServiceBookingResponseModel>() {
            @Override
            public void onResponse(Call<MyServiceBookingResponseModel> call, Response<MyServiceBookingResponseModel> response) {
                endLoading();

                todayListFiltration(response,CommonUtils.getInstance().getDate2(CalendarDay.today().getCalendar()));
                upcomingListFiltration(response,CommonUtils.getInstance().getDate2(CalendarDay.today().getCalendar()));
            }

            @Override
            public void onFailure(Call<MyServiceBookingResponseModel> call, Throwable t) {
                endLoading();

            }
        });
    }

    private void todayListFiltration(Response<MyServiceBookingResponseModel> response ,String date){
        List<ServiceBooking> serviceBookings =new ArrayList<>();
        for (int i = 0;i<response.body().getServiceBooking().size();i++){
            if(date.equals(response.body().getServiceBooking().get(i).getServiceBookingDate())){

                serviceBookings.add(response.body().getServiceBooking().get(i));

            }
        }
        if (serviceBookings.size() != 0){
            setData(serviceBookings);
        }else {
            listToday.setVisibility(View.GONE);
        }
        //if(date.equals(response.getServiceBooking().get(position).getServiceBookingDate())
    }

    private void upcomingListFiltration(Response<MyServiceBookingResponseModel> response ,String date){
        List<ServiceBooking> serviceBookings =new ArrayList<>();
        for (int i = 0;i<response.body().getServiceBooking().size();i++){
            if(!date.equals(response.body().getServiceBooking().get(i).getServiceBookingDate())){

                serviceBookings.add(response.body().getServiceBooking().get(i));

            }
        }
        if (serviceBookings.size() != 0){
            setUpComingData(serviceBookings);
        }else {
            relativeLayout.setVisibility(View.GONE);
            upComingBanner.setVisibility(View.GONE);
        }
        //if(date.equals(response.getServiceBooking().get(position).getServiceBookingDate())
    }

    private void setData(List<ServiceBooking> serviceBookings){

        recyclerView = findViewById(R.id.recycler_view);

        mMyBookingAdapter = new MyBookingsAdapter(serviceBookings,this,getLanguage());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mMyBookingAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mMyBookingAdapter);

        if (mMyBookingAdapter.getItemCount() == 0){
            listToday.setVisibility(View.GONE);
        }else {
            listToday.setVisibility(View.VISIBLE);
        }

    }

    private void setUpComingData(List<ServiceBooking> serviceBookings){

        recyclerView2 = findViewById(R.id.recycler_view2);
        mAdapter2 = new UpcomingMyBookingsAdapter(serviceBookings,this,getLanguage());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(mLayoutManager);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        mAdapter2.notifyDataSetChanged();
        recyclerView2.setAdapter(mAdapter2);
        

         if (mAdapter2.getItemCount() == 0){
             //listToday.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.GONE);
            upComingBanner.setVisibility(View.GONE);
        }else {
             //listToday.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.VISIBLE);
            upComingBanner.setVisibility(View.VISIBLE);
        }
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
                        //showMessage(response.body().getMessage());

                        showMessageSnakBar(response.body().getMessage());
                        //finish();
                      //  mMyBookingAdapter.notifyDataSetChanged();
                       // recyclerView.setAdapter(mMyBookingAdapter);
                        fetchMyBookingService();
                    } else {
                        showMessageSnakBar(response.body().getMessage());
                        //showMessage(response.body().getMessage());
                        fetchMyBookingService();
                       // finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<SuccessResponseModel> call, Throwable t) {
                endLoading();
               // showMessage("2 "+getResources().getString(R.string.network_problem));
            }
        });
    }

    @Override
    public void onBookingSelected(List<Integer> selectedBookings) {
        //mSelectedBookings = selectedBookings;
        invokeDeleteBooking(selectedBookings);


    }

    @Override
    public void onDeleteItemSelected(List<Integer> selectedItems) {
        invokeDeleteBooking(selectedItems);


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void showMessageSnakBar(String message) {
       /* Snackbar mySnackbar = Snackbar.make(view,message, Snackbar.LENGTH_SHORT);
        mySnackbar.show();*/

        final Flashbar flashbar = new Flashbar.Builder(this)
                .gravity(Flashbar.Gravity.BOTTOM)
                .title(message)
                .message("  ")
                .backgroundColorRes(R.color.colorPrimaryDark)
                .enterAnimation(FlashAnim.with(this)
                        .animateBar()
                        .duration(400)
                        .slideFromLeft()
                        .overshoot())
                .exitAnimation(FlashAnim.with(this)
                        .animateBar()
                        .duration(250)
                        .slideFromLeft()
                        .accelerate())
                .build();

        flashbar.show();


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                flashbar.dismiss();
            }
        }, 4000);
      /*  Snackbar mySnackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout),
                R.string.email_archived, Snackbar.LENGTH_SHORT);
        mySnackbar.setAction(R.string.undo_string, new MyUndoListener());
        mySnackbar.show();*/
    }
}
