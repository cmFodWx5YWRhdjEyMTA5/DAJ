package com.tinnovat.app.daj.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.Objects;

public class FuturePhaseInfoListActivity extends BaseActivity {

    RelativeLayout row1;
    RelativeLayout row2;
    RelativeLayout row3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_phase_info_list);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.future_phase_info_list));

        row1 = findViewById(R.id.row1);
        row2 = findViewById(R.id.row2);
        row3 = findViewById(R.id.row3);

        row1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( FuturePhaseInfoListActivity.this,FuturePhaseInfoDetailActivity.class);
                startActivity(i);
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
}
