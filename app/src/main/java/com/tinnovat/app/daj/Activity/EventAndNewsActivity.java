package com.tinnovat.app.daj.Activity;

import android.os.Bundle;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.Objects;

public class EventAndNewsActivity extends BaseActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_and_news_eng);
        Objects.requireNonNull(getSupportActionBar()).setTitle("EVENT AND NEWS");

    }
}
