package com.tinnovat.app.daj.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.Objects;

public class MyComplaintListActivity extends BaseActivity {

    ImageView addComplaints;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_complaint_list);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.my_complaint_list));
        addComplaints = findViewById(R.id.addComplaints);

        RelativeLayout row1 = findViewById(R.id.row1);
        RelativeLayout row2 = findViewById(R.id.row2);
        RelativeLayout row3 = findViewById(R.id.row3);
        RelativeLayout row4 = findViewById(R.id.row4);
        RelativeLayout row5 = findViewById(R.id.row5);

        row1.setOnClickListener(this);
        row2.setOnClickListener(this);
        row3.setOnClickListener(this);
        row4.setOnClickListener(this);
        row5.setOnClickListener(this);

        addComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyComplaintListActivity.this, RegisterComplaintActivity.class);
                startActivity(i);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setColorFilter(Color.WHITE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent i = new Intent(MyComplaintListActivity.this, RegisterComplaintActivity.class);
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

        switch (v.getId()){
            case R.id.row1:
                Intent i = new Intent(MyComplaintListActivity.this, MyComplaintActivity.class);
                startActivity(i);
                break;

                case R.id.row2:
                i = new Intent(MyComplaintListActivity.this, MyComplaintActivity.class);
                startActivity(i);
                break;

                case R.id.row3:
                i = new Intent(MyComplaintListActivity.this, MyComplaintActivity.class);
                startActivity(i);
                break;

                case R.id.row4:
                i = new Intent(MyComplaintListActivity.this, MyComplaintActivity.class);
                startActivity(i);
                break;

                case R.id.row5:
                i = new Intent(MyComplaintListActivity.this, MyComplaintActivity.class);
                startActivity(i);
                break;
        }
    }
}
