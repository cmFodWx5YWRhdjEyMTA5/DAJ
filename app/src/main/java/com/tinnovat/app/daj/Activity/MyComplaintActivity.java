package com.tinnovat.app.daj.Activity;

import android.graphics.Paint;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import org.w3c.dom.Text;

import java.util.Objects;

public class MyComplaintActivity extends BaseActivity {

    TextView locationText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_complaint);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.my_complaint));
        locationText = findViewById(R.id.locationText);

        locationText.setPaintFlags(locationText.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
       // description.setText(Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID));
    }
}
