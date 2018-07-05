package com.tinnovat.app.daj.Activity;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.Locale;
import java.util.Objects;

public class MyBookingActivity extends BaseActivity {

    MaterialCalendarView cal;
    TextView todayDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.my_booking));
        cal = findViewById(R.id.calendarView);

        todayDate = findViewById(R.id.todayDate);
        cal.setSelectedDate(CalendarDay.today());
        cal.setSelected(true);
        if(getLanguage()){
            cal.setRightArrowMask(ContextCompat.getDrawable(this, R.drawable.arrow_right));
            cal.setLeftArrowMask(ContextCompat.getDrawable(this, R.drawable.arrow_left));
        }else {
            cal.setRightArrowMask(ContextCompat.getDrawable(this, R.drawable.arrow_left));
            cal.setLeftArrowMask(ContextCompat.getDrawable(this, R.drawable.arrow_right));
        }
        Locale locale = new Locale("en", "US");

        setDate(CalendarDay.today().getDay(),CalendarDay.today().getMonth());
        String[] ArrayWeekDayFormatter = { "Sun","Mon","Tue","Wed","Thu","Fri","Sat" };
        DayFormatter formatter =DayFormatter.DEFAULT;
        cal.setWeekDayLabels(ArrayWeekDayFormatter);
      //  SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd,yyyy hh:mm a");
        DayFormatter day = new DayFormatter() {
            @NonNull
            @Override
            public String format(@NonNull CalendarDay day) {
                return ""+day.getDay();
            }
        };
        cal.setDayFormatter(day);
       // cal.setDayFormatter(CalendarDay);

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
