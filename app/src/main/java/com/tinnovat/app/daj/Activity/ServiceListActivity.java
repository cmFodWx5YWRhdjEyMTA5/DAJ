package com.tinnovat.app.daj.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

public class ServiceListActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(getText(R.string.services));
        }

       RelativeLayout row1 = findViewById(R.id.row1);
       RelativeLayout row2 = findViewById(R.id.row2);
       RelativeLayout  row3 = findViewById(R.id.row3);

        row1.setOnClickListener(this);
        row2.setOnClickListener(this);
        row3.setOnClickListener(this);
    }

    @Override
    public void initialiseViews() {

    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.row1:
                Intent i = new Intent(ServiceListActivity.this, ServiceBookingActivity.class);
                startActivity(i);
                break;

                case R.id.row2:
                    i = new Intent(ServiceListActivity.this, ServiceBookingActivity.class);
                    startActivity(i);
                break;

                case R.id.row3:
                    i = new Intent(ServiceListActivity.this, ServiceBookingActivity.class);
                    startActivity(i);
                break;
        }
    }
}
