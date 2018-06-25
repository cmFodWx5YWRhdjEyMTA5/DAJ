package com.tinnovat.app.daj;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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

    }

    public void changeOrientation(){
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }

    public void setLanguage(Boolean language){
        editor.putBoolean("Language", language);
        editor.apply();
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


}
