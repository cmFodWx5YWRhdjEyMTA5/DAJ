package com.tinnovat.app.daj.features.bookings;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;

import com.pchmn.androidverify.Form;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.GuestRegistrationResponseModel;
import com.tinnovat.app.daj.data.network.model.RequestParams;
import com.tinnovat.app.daj.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GuestRegistrationActivityMain extends BaseActivity implements GuestTimeSlotAdapter.DateAdapterListener {

    int purposeId = 0;
    TextView monthTitle;
    MaterialCalendarView cal;
    ImageView facebook;
    ImageView twitter;
    ImageView instagram;

    CharSequence purposeList[];

    private RecyclerView recyclerView;

    private int timeSlot = 0;
    private String selectedDate = null;
    //private String time = null;
    private Button buttonAdd;
    private Button buttonSubmit;
    private TableLayout table;
    private TableRow row;

    List<String> selectedDatesList = new ArrayList<>();
    //List<TextView> tvListPurpose = new ArrayList<>();
    List<TextView> tvListMonthTitle = new ArrayList<>();
    List<MaterialCalendarView> calendarViewList = new ArrayList<>();
    List<Spinner> purposeSpinnerList = new ArrayList<>();
    List<EditText> tvInputNameList = new ArrayList<>();
    List<EditText> tvVehicleNumberList = new ArrayList<>();


    private List<String> name = new ArrayList<>();
    private List<String> dates = new ArrayList<>();
    private List<String> time = new ArrayList<>();
    private List<Integer> purpose = new ArrayList<>();
    private List<String> vehicleNo = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_registration);
//        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.guest_reg));

        Locale.setDefault(Locale.US);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getText(R.string.guest_reg));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(3).setChecked(true);
        navigationView.setItemIconTintList(null);

        facebook = findViewById(R.id.facebook);
        twitter = findViewById(R.id.twitter);
        instagram = findViewById(R.id.instagram);


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

        buttonAdd = findViewById(R.id.add);
        buttonSubmit = findViewById(R.id.submit);
        table = findViewById(R.id.tab_layout);


        List<String> listItems = new ArrayList<String>();

        listItems.add(getResources().getString(R.string.choose_purpose));
        listItems.add(getResources().getString(R.string.family));
        listItems.add(getResources().getString(R.string.friend));
        listItems.add(getResources().getString(R.string.maintenance));
        listItems.add(getResources().getString(R.string.taxi1));
        listItems.add(getResources().getString(R.string.delivery));
        purposeList = listItems.toArray(new CharSequence[listItems.size()]);

        createNewRow(table.getChildCount());

        buttonAdd.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
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

    private void createNewRow(final int rowPosition) {

        row = (TableRow) LayoutInflater.from(this).inflate(R.layout.content_form_guest_registration, null);
        cal = row.findViewById(R.id.calendarView);
        monthTitle = row.findViewById(R.id.monthTitle);
        EditText inputName = row.findViewById(R.id.input_name);
        EditText vehicleNumber = row.findViewById(R.id.vehicle_no);
        Spinner spinner = row.findViewById(R.id.spinner_purpose);

//        inputName.setText(""+rowPosition);

        ImageView delete = row.findViewById(R.id.delete);
        if (rowPosition == 0){
            delete.setVisibility(View.INVISIBLE);
        }else {
            delete.setVisibility(View.VISIBLE);
        }

        calendarViewList.add(cal);
        tvListMonthTitle.add(monthTitle);
        purposeSpinnerList.add(spinner);
        tvInputNameList.add(inputName);
        tvVehicleNumberList.add(vehicleNumber);
        spinner.setAdapter(new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,purposeList));

        setCalender(rowPosition);
//        setTimeSlot(recyclerView);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        table.addView(row);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //table.removeView(row);
                //table.removeView(row);

                int pos = table.getChildCount()-1;

                if (rowPosition > pos){
                    calendarViewList.remove(pos);
                    tvListMonthTitle.remove(pos);
                    purposeSpinnerList.remove(pos);
                    tvInputNameList.remove(pos);
                    tvVehicleNumberList.remove(pos);
                    deleteGuest(pos);
                }else {
                    calendarViewList.remove(rowPosition);
                    tvListMonthTitle.remove(rowPosition);
                    purposeSpinnerList.remove(rowPosition);
                    tvInputNameList.remove(rowPosition);
                    tvVehicleNumberList.remove(rowPosition);
                    deleteGuest(rowPosition);
                }
            }
        });

//        table.addView(row);

    }

    public void deleteGuest(int rowPosition){

        table.removeViewAt(rowPosition);

    }

    public void setCalender(final int position) {

//        MaterialCalendarView cal = calendarViewList.get(position);
        cal = calendarViewList.get(position);
        final TextView monthTitle = tvListMonthTitle.get(position);

        cal.state().edit()
                .setMinimumDate(CalendarDay.from(CalendarDay.today().getYear(), CalendarDay.today().getMonth(), CalendarDay.today().getDay()))
                .commit();

        cal.setSelectedDate(CalendarDay.today());
        cal.setCurrentDate(CalendarDay.today());
        cal.setSelected(true);


        if (getLanguage()) {
            cal.setRightArrowMask(ContextCompat.getDrawable(this, R.drawable.arrow_right));
            cal.setLeftArrowMask(ContextCompat.getDrawable(this, R.drawable.arrow_left));
        } else {
            cal.setRightArrowMask(ContextCompat.getDrawable(this, R.drawable.arrow_left));
            cal.setLeftArrowMask(ContextCompat.getDrawable(this, R.drawable.arrow_right));
        }

        String[] weekDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        cal.setWeekDayLabels(weekDays);

        DayFormatter day = new DayFormatter() {
            @NonNull
            @Override
            public String format(@NonNull CalendarDay day) {
                return "" + day.getDay();
            }
        };
        cal.setTitleMonths(months);
        cal.setDayFormatter(day);
        cal.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                monthTitle.setVisibility(View.INVISIBLE);
                GuestRegistrationActivityMain.this.cal.setHeaderTextAppearance(R.style.CalenderViewCustomWeekHeading);
            }
        });

        cal.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                monthTitle.setVisibility(View.VISIBLE);
                GuestRegistrationActivityMain.this.cal.setHeaderTextAppearance(R.style.CalenderViewCustomWeekHeading1);
                //  monthTitle.setText(""+getCurrentMonthWeek(date.getMonth())+" "+date.getYear());
                monthTitle.setText(CommonUtils.getInstance().getMonthWithYear(date.getCalendar()));
                if (selectedDatesList.size() > position)
                    selectedDatesList.set(position, CommonUtils.getInstance().getDate(date.getCalendar()));
                else
                    selectedDatesList.add(CommonUtils.getInstance().getDate(date.getCalendar()));

            }
        });

    }

    private void setTimeSlot(RecyclerView recyclerView) {
        GuestTimeSlotAdapter mAdapter = new GuestTimeSlotAdapter(this, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private void validation() {
        if (isNetworkConnected()){
            doRegistration(name, dates, time, purpose, vehicleNo);
        }else {
            showErrorDilog(false);
        }

    }


    private void doRegistration(List<String> name, List<String> date, List<String> time, List<Integer> purpose, List<String> vehicleNo) {

        startLoading();


        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        RequestParams.GuestRegistrationRequest guestRegistrationRequest =
                new RequestParams().new GuestRegistrationRequest(name, date, time, purpose, vehicleNo);

        Call<GuestRegistrationResponseModel> call = apiInterface.guestRegistration(guestRegistrationRequest);
        call.enqueue(new Callback<GuestRegistrationResponseModel>() {
            @Override
            public void onResponse(Call<GuestRegistrationResponseModel> call, Response<GuestRegistrationResponseModel> response) {
                endLoading();
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        showDilog(response.body().getMessage());
                    } else {
                        showDilog(response.body().getMessage());
                    }
                } else {
                    showDilog(getResources().getString(R.string.try_again_later));
                }
            }

            @Override
            public void onFailure(Call<GuestRegistrationResponseModel> call, Throwable t) {
                endLoading();
                showDilog(getResources().getString(R.string.try_again_later));
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
            case R.id.submit:

                name.clear();
                dates.clear();
                time.clear();
                purpose.clear();
                vehicleNo.clear();

                Form form = new Form.Builder(this)
                        .showErrors(true)
                        .build();
                //purposeSpinnerList
                if (form.isValid()) {
                    for (int i = 0; i < table.getChildCount(); i++) {
                        EditText userName = table.getChildAt(i).findViewById(R.id.input_name);

                        TimePicker timePicker = table.getChildAt(i).findViewById(R.id.simpleTimePicker);
                        Spinner spinner_purpose = table.getChildAt(i).findViewById(R.id.spinner_purpose);
                        EditText vehicleno = table.getChildAt(i).findViewById(R.id.vehicle_no);

                        name.add(userName.getText().toString());
                        dates.add(CommonUtils.getInstance().getDate(calendarViewList.get(i).getSelectedDate().getCalendar()));
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

                            String hr;
                            String min;
                            if (timePicker.getHour() < 10) {
                                hr = "0" + Integer.toString(timePicker.getHour());

                            } else {
                                hr = Integer.toString(timePicker.getHour());
                            }

                            if (timePicker.getMinute() < 10) {
                                min = "0" + Integer.toString(timePicker.getMinute());
                            } else {
                                min = Integer.toString(timePicker.getMinute());
                            }
                            time.add(hr + ":" + min);
                        } else {
                            time.add("10:00");
                        }

                        if (purposeSpinnerList.get(i).getSelectedItemPosition() != 0){
                            purpose.add(purposeSpinnerList.get(i).getSelectedItemPosition());
                        }else {
                            showMessage(getResources().getString(R.string.choose_purpose));
                        }


                        vehicleNo.add(vehicleno.getText().toString());
                    }
                }
                if (name.size() != 0 && dates.size() != 0&& time.size() != 0&& purpose.size() !=0){
                    if (name.size() == dates.size() &&  dates.size()== purpose.size()){
                        validation();
                    }

                }

                break;

            case R.id.add:
                if (table != null) {
                    createNewRow(table.getChildCount());

                }
                break;
        }

    }

    @Override
    public void onDateSelected(int selectedTimeSlots) {
        timeSlot = selectedTimeSlots;
    }
}
