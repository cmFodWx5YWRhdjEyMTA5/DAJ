package com.tinnovat.app.daj.Activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Calendar;
import java.util.Objects;


public class GuestRegistrationActivity extends BaseActivity {
    TextView purpose;
    MaterialCalendarView cal;

    CharSequence purposeList[] =new CharSequence[]{"family","friend","maintenance","taxi","delivery"};
    /*CharSequence purposeList[] = new CharSequence[] {GuestRegistrationActivity.this.getResources().getString(R.string.family),
            GuestRegistrationActivity.this.getResources().getString(R.string.friend),
            GuestRegistrationActivity.this.getResources().getString(R.string.maintenance),
            GuestRegistrationActivity.this.getResources().getString(R.string.taxi),
            GuestRegistrationActivity.this.getResources().getString(R.string.delivery)};*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_registration);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.guest_reg));

        cal = findViewById(R.id.calendarView);

        purpose = findViewById(R.id.purpose);
        purpose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

    }

    private void showDialog(){
        int cate = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //  builder.setTitle("Pick a color");
        builder.setItems(purposeList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // the user clicked on colors[which]
                Toast.makeText(GuestRegistrationActivity.this, "test :"+which, Toast.LENGTH_SHORT).show();
                purpose.setText(purposeList[which]);
            }
        });
        builder.show();
    }
}
