package com.tinnovat.app.daj.Activity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.Objects;

public class EventBookingActivity extends BaseActivity {

    CalendarView cal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_booking);
        Objects.requireNonNull(getSupportActionBar()).setTitle("EVENT BOOKING");
        cal = findViewById(R.id.simpleCalendarView);
    }
}
