package com.tinnovat.app.daj.features.bookings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GuestRegistrationActivityMain extends BaseActivity implements GuestTimeSlotAdapter.DateAdapterListener {

    int purposeId = 0;
    TextView monthTitle;
    MaterialCalendarView cal;

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getText(R.string.guest_reg));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setItemIconTintList(null);

        buttonAdd = findViewById(R.id.add);
        buttonSubmit = findViewById(R.id.submit);
        table = findViewById(R.id.tab_layout);


        List<String> listItems = new ArrayList<String>();

        listItems.add(getResources().getString(R.string.choose_purpose));
        listItems.add(getResources().getString(R.string.family));
        listItems.add(getResources().getString(R.string.friend));
        listItems.add(getResources().getString(R.string.maintenance));
        listItems.add(getResources().getString(R.string.taxi));
        listItems.add(getResources().getString(R.string.delivery));
        purposeList = listItems.toArray(new CharSequence[listItems.size()]);

        createNewRow(table.getChildCount());

        buttonAdd.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);
    }

    private void createNewRow(int rowPosition) {
        row = (TableRow) LayoutInflater.from(this).inflate(R.layout.content_form_guest_registration, null);
        cal = row.findViewById(R.id.calendarView);
        monthTitle = row.findViewById(R.id.monthTitle);
        //purpose = row.findViewById(R.id.purpose);
        EditText inputName = row.findViewById(R.id.input_name);
        EditText vehicleNumber = row.findViewById(R.id.vehicle_no);
        Spinner spinner = row.findViewById(R.id.spinner_purpose);

        calendarViewList.add(cal);
        tvListMonthTitle.add(monthTitle);
        //tvListPurpose.add(purpose);
        purposeSpinnerList.add(spinner);
        tvInputNameList.add(inputName);
        tvVehicleNumberList.add(vehicleNumber);
        spinner.setAdapter(new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,purposeList));

        setCalender(rowPosition);
//        setTimeSlot(recyclerView);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    showMessage("nothing");
                }else {
                    showMessage("selected");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        table.addView(row);

    }

    private void setCalender(final int position) {

        MaterialCalendarView cal = calendarViewList.get(position);
        final TextView monthTitle = tvListMonthTitle.get(position);

        cal.state().edit()
                .setMinimumDate(CalendarDay.from(CalendarDay.today().getYear(), CalendarDay.today().getMonth(), CalendarDay.today().getDay()))
                .commit();

        cal.setSelectedDate(CalendarDay.today());
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
        Form form = new Form.Builder(this)
                .showErrors(true)
                .build();

        // validate the form
        if (form.isValid()) {

            doRegistration(name, dates, time, purpose, vehicleNo);

/*  //todo change
            for (int i = 0; i < table.getChildCount(); i++) {

                // the form is valid
                EditText input_name = tvInputNameList.get(i);
                EditText vehicle_no = tvVehicleNumberList.get(i);
                String selectedDate = CommonUtils.getInstance().getDate(calendarViewList.get(i).getCurrentDate().getCalendar());

                showMessage("valid");
                if (selectedDate == null) {
                    selectedDate = CommonUtils.getInstance().getDate(CalendarDay.today().getCalendar());
                    doRegistration(input_name.getText().toString(), selectedDate, time, purposeId, vehicle_no.getText().toString());
                } else {
                    doRegistration(input_name.getText().toString(), selectedDate, time, purposeId, vehicle_no.getText().toString());
                }


            }*/


        } else {
            // the form is not valid
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
                        showMessage(response.body().getMessage());
                    } else {
                        showMessage(response.body().getMessage());
                    }
                    finish();
                } else {
                    showMessage(getResources().getString(R.string.network_problem));
                }
            }

            @Override
            public void onFailure(Call<GuestRegistrationResponseModel> call, Throwable t) {
                endLoading();

                showMessage(getResources().getString(R.string.network_problem));
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
                //purposeSpinnerList
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
                        if (timePicker.getHour()<10){
                            hr = "0"+Integer.toString(timePicker.getHour());
                            //time.add(""+hr+":"+timePicker.getMinute());
                        }else {
                           hr = Integer.toString(timePicker.getHour());
                        }

                        if (timePicker.getMinute()<10){
                            min = "0"+Integer.toString(timePicker.getMinute());
                        }else {
                            min = Integer.toString(timePicker.getMinute());
                        }
                        time.add(hr+":"+min);
                    }else {
                        time.add("10:00");
                    }

                    purpose.add(purposeSpinnerList.get(i).getSelectedItemPosition());
                    vehicleNo.add(vehicleno.getText().toString());
                }
                validation();
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
