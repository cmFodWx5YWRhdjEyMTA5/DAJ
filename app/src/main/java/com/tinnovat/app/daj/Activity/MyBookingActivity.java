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

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;

public class MyBookingActivity extends BaseActivity {

    MaterialCalendarView cal;
    TextView todayDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_booking);
        Objects.requireNonNull(getSupportActionBar()).setTitle("MY BOOKINGS");
        cal = findViewById(R.id.calendarView);

        todayDate = findViewById(R.id.todayDate);
        cal.setSelectedDate(CalendarDay.today());
        cal.setSelected(true);

        setDate(CalendarDay.today().getDay(),CalendarDay.today().getMonth());


        cal.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                setDate(date.getDay(),date.getMonth());
            }
        });



    }

    private void setDate(int day ,int month){
        todayDate.setText(""+day+"\n"+""+ getCurrentMonth(month));
    }
}
