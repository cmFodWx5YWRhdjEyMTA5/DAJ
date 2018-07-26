package com.tinnovat.app.daj.features.bookings;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GuestRegistrationActivityMain extends BaseActivity implements GuestTimeSlotAdapter.DateAdapterListener {

    TextView purpose;
    int purposeId = 0;
    TextView monthTitle;
    MaterialCalendarView cal;

    CharSequence purposeList[];

    private RecyclerView recyclerView;
    private GuestTimeSlotAdapter mAdapter;

    private int timeSlot = 0;
    private String selectedDate = null;
    private String time = null;
    private Button buttonAdd;
    private TableLayout table;
    private TableRow row;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_registration_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.guest_reg));

        buttonAdd = findViewById(R.id.add);

        table = findViewById(R.id.tab_layout);
        row = (TableRow) LayoutInflater.from(this).inflate(R.layout.content_form_guest_registration, null);

        table.addView(row);

        buttonAdd.setOnClickListener(this);
    }


    private void setCalender() {

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
        cal.setSelectedDate(CalendarDay.today().getCalendar());
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
                selectedDate = CommonUtils.getInstance().getDate(date.getCalendar());


            }
        });

    }

    private void setTimeSlot() {
        mAdapter = new GuestTimeSlotAdapter(this, this);
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

            // the form is valid
            EditText input_name = findViewById(R.id.input_name);
            EditText vehicle_no = findViewById(R.id.vehicle_no);

            showMessage("valid");
            if (timeSlot == 0) {
                showMessage(getResources().getString(R.string.choose_time));
            } else if (timeSlot < 10) {
                time = "0" + timeSlot + ":00";
            } else {
                time = timeSlot + ":00";
            }

            if (purposeId == 0) {
                showMessage(getResources().getString(R.string.choose_purpose));
            }
            if (timeSlot != 0 && purposeId != 0) {
                if (selectedDate == null) {
                    selectedDate = CommonUtils.getInstance().getDate(CalendarDay.today().getCalendar());
                    doRegistration(input_name.getText().toString(), selectedDate, time, purposeId, vehicle_no.getText().toString());
                } else {
                    doRegistration(input_name.getText().toString(), selectedDate, time, purposeId, vehicle_no.getText().toString());
                }


            }
        } else {
            // the form is not valid
        }
    }


    private void doRegistration(String name, String date, String time, int purpose, String vehicleNo) {

        startLoading();


        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        RequestParams.GuestRegistrationRequest guestRegistrationRequest = new RequestParams().new GuestRegistrationRequest(name, date, time, purpose, vehicleNo);

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
                for (int i = 0; i < table.getChildCount(); i++) {
                    EditText editText = table.getChildAt(i).findViewById(R.id.input_name);
                }
                validation();
                break;

            case R.id.purpose:
                ViewDialog alert = new ViewDialog();
                alert.showDialog(GuestRegistrationActivityMain.this, "");
                break;

            case R.id.add:
                if (table != null) {
                    row = (TableRow) LayoutInflater.from(this).inflate(R.layout.content_form_guest_registration, null);
                    table.addView(row);
                }
                break;
        }

    }

    @Override
    public void onDateSelected(int selectedTimeSlots) {
        timeSlot = selectedTimeSlots;
    }


    public class ViewDialog {

        public void showDialog(Activity activity, String msg) {
            final Dialog dialog = new Dialog(GuestRegistrationActivityMain.this);
            dialog.setContentView(R.layout.dialog_guest);
            final TextView family = dialog.findViewById(R.id.family);
            final TextView friend = dialog.findViewById(R.id.friend);
            final TextView maintenance = dialog.findViewById(R.id.maintenance);
            final TextView taxi = dialog.findViewById(R.id.taxi);
            final TextView delivery = dialog.findViewById(R.id.delivery);


            family.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    purposeId = 1;
                    purpose.setText(family.getText());
                    dialog.dismiss();
                }
            });

            friend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    purposeId = 2;
                    purpose.setText(friend.getText());
                    dialog.dismiss();
                }
            });

            maintenance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    purposeId = 3;
                    purpose.setText(maintenance.getText());
                    dialog.dismiss();
                }
            });
            taxi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    purposeId = 4;
                    purpose.setText(taxi.getText());
                    dialog.dismiss();
                }
            });
            delivery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    purposeId = 5;
                    purpose.setText(delivery.getText());
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    }
}
