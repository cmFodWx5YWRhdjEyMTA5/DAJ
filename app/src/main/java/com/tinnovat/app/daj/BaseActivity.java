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


}
