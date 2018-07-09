package com.tinnovat.app.daj.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.utils.CommonUtils;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;


public class GuestRegistrationActivity extends BaseActivity {

    TextView purpose;
    TextView monthTitle;
    MaterialCalendarView cal;

    CharSequence purposeList[];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_registration);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.guest_reg));

        List<String> listItems = new ArrayList<String>();

        listItems.add(getResources().getString(R.string.family));
        listItems.add(getResources().getString(R.string.friend));
        listItems.add(getResources().getString(R.string.maintenance));
        listItems.add(getResources().getString(R.string.taxi));
        listItems.add(getResources().getString(R.string.delivery));

        purposeList = listItems.toArray(new CharSequence[listItems.size()]);

        cal = findViewById(R.id.calendarView);
        monthTitle = findViewById(R.id.monthTitle);
        purpose = findViewById(R.id.purpose);

        purpose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewDialog alert = new ViewDialog();
                alert.showDialog(GuestRegistrationActivity.this, "");
            }
        });

        if(getLanguage()){
            cal.setRightArrowMask(ContextCompat.getDrawable(this, R.drawable.arrow_right));
            cal.setLeftArrowMask(ContextCompat.getDrawable(this, R.drawable.arrow_left));
        }else {
            cal.setRightArrowMask(ContextCompat.getDrawable(this, R.drawable.arrow_left));
            cal.setLeftArrowMask(ContextCompat.getDrawable(this, R.drawable.arrow_right));
        }


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
        cal.setSelectedDate(CalendarDay.today());
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
//                monthTitle.setText(""+getCurrentMonthWeek(date.getMonth())+" "+date.getYear());
                monthTitle.setText(CommonUtils.getInstance().getMonthWithYear(date.getCalendar()));
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

    }

    public class ViewDialog {

        public void showDialog(Activity activity, String msg){
            final Dialog dialog = new Dialog(GuestRegistrationActivity.this);
            dialog.setContentView(R.layout.sample);
            final TextView family =  dialog.findViewById(R.id.family);
            final TextView friend =  dialog.findViewById(R.id.friend);
            final TextView maintenance =  dialog.findViewById(R.id.maintenance);
            final TextView taxi =  dialog.findViewById(R.id.taxi);
            final TextView delivery =  dialog.findViewById(R.id.delivery);


            family.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    purpose.setText(family.getText());
                    dialog.dismiss();
                }
            });

            friend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    purpose.setText(friend.getText());
                    dialog.dismiss();
                }
            });

            maintenance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    purpose.setText(maintenance.getText());
                    dialog.dismiss();
                }
            });taxi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    purpose.setText(taxi.getText());
                    dialog.dismiss();
                }
            });delivery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    purpose.setText(delivery.getText());
                    dialog.dismiss();
                }
            });

            dialog.show();

        }

    }
}
