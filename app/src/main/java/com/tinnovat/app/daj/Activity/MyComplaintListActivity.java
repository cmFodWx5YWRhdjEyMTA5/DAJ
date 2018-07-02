package com.tinnovat.app.daj.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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

        addComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyComplaintListActivity.this, RegisterComplaintActivity.class);
                startActivity(i);
            }
        });
    }
}
