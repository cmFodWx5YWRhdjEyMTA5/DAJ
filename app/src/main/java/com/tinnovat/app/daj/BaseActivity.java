package com.tinnovat.app.daj;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Locale;
import java.util.Objects;

public class BaseActivity extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActionBar() != null)
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();
        //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }

    public void changeOrientation(){
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }

    public void setLanguage(Boolean language){
        //true : eng  false = arabic
        editor.putBoolean("Language", language);
        editor.apply();
        String languageToLoad;

        if (language){
            languageToLoad  = "en"; // your language
        }else {
            languageToLoad  = "ar"; // your language
        }

        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

    public boolean getLanguage(){
        return pref.getBoolean("Language", true);
    }

    public String getCurrentMonth(int month){
        String mnt = "JAN";
        if (month == 0){
            mnt = "JAN";
        }else if (month == 1){
            mnt = "FEB";
        }else if (month == 2){
            mnt = "MAR";
        }else if (month == 3){
            mnt = "APR";
        }else if (month == 4){
            mnt = "MAY";
        }else if (month == 5){
            mnt = "JUN";
        }else if (month == 6){
            mnt = "JLY";
        }else if (month == 7){
            mnt = "AUG";
        }else if (month == 8){
            mnt = "SEP";
        }else if (month == 9){
            mnt = "OCT";
        }else if (month == 10){
            mnt = "NOV";
        }else if (month == 11){
            mnt = "DEC";
        }

      return   mnt;
    }

    public String getCurrentMonthWeek(int month){

        String mnt = "January";
        if (month == 0){
            mnt = "January";
        }else if (month == 1){
            mnt = "February";
        }else if (month == 2){
            mnt = "March";
        }else if (month == 3){
            mnt = "April";
        }else if (month == 4){
            mnt = "May";
        }else if (month == 5){
            mnt = "June";
        }else if (month == 6){
            mnt = "July";
        }else if (month == 7){
            mnt = "August";
        }else if (month == 8){
            mnt = "September";
        }else if (month == 9){
            mnt = "October";
        }else if (month == 10){
            mnt = "November";
        }else if (month == 11){
            mnt = "December";
        }

      return   mnt;
    }




}
