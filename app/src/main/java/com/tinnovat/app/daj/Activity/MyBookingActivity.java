package com.tinnovat.app.daj.Activity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;

public class MyBookingActivity extends BaseActivity {

    CalendarView cal;
    TextView todayDate;
    String currentMonth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_booking);
        Objects.requireNonNull(getSupportActionBar()).setTitle("MY BOOKINGS");
        cal = findViewById(R.id.calendarView);
        todayDate = findViewById(R.id.todayDate);

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(MyBookingActivity.this, ""+year+" "+month+" "+dayOfMonth, Toast.LENGTH_SHORT).show();

                todayDate.setText(""+dayOfMonth+"\n"+""+ getCurrentMonth(month));

            }
        });


    }
}
