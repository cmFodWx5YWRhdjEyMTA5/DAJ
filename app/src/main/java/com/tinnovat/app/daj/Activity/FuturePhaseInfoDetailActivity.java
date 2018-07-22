package com.tinnovat.app.daj.Activity;

import android.os.Bundle;
import android.view.View;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.Objects;

public class FuturePhaseInfoDetailActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_phase_info_detail);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getText(R.string.future_phase_info_details));
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
}
