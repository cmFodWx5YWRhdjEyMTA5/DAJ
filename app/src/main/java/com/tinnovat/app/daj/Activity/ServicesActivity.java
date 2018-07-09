package com.tinnovat.app.daj.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServicesActivity extends BaseActivity {

    RelativeLayout hall;
    RelativeLayout hall2;
    RelativeLayout pool;
    RelativeLayout court;

    ImageView arcHall;
    ImageView arcHall1;
    ImageView arcCourt;
    ImageView arcPool;

    CharSequence categoryList[];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.services));
        List<String> listItems = new ArrayList<String>();

        listItems.add(getResources().getString(R.string.hall1));
        listItems.add(getResources().getString(R.string.hall2));
        listItems.add(getResources().getString(R.string.hall3));
        listItems.add(getResources().getString(R.string.hall4));

        categoryList = listItems.toArray(new CharSequence[listItems.size()]);

        arcHall = findViewById(R.id.arcHall);
        arcHall1 = findViewById(R.id.arcHall1);
        arcCourt = findViewById(R.id.arcCourt);
        arcPool = findViewById(R.id.arcPool);

        if (!getLanguage()){
            arcHall.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_right));
            arcHall.setScaleType(ImageView.ScaleType.FIT_START);

            arcCourt.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_right));
            arcCourt.setScaleType(ImageView.ScaleType.FIT_START);

            arcPool.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_right));
            arcPool.setScaleType(ImageView.ScaleType.FIT_START);

            arcHall1.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_right));
            arcHall1.setScaleType(ImageView.ScaleType.FIT_START);
        }else {
            arcHall.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_left));
            arcHall.setScaleType(ImageView.ScaleType.FIT_END);

            arcCourt.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_left));
            arcCourt.setScaleType(ImageView.ScaleType.FIT_END);

            arcPool.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_left));
            arcPool.setScaleType(ImageView.ScaleType.FIT_END);

            arcHall1.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_left));
            arcHall1.setScaleType(ImageView.ScaleType.FIT_END);
        }

        hall = findViewById(R.id.hall);
        hall2 = findViewById(R.id.hall2);
        pool = findViewById(R.id.pool);
        court = findViewById(R.id.court);



        hall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        hall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        pool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        court.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });


    }

    @Override
    public void initialiseViews() {

    }

    @Override
    public void initialiseEventListners() {

    }

    private void showDialog(){

        Intent i = new Intent(ServicesActivity.this, ServiceListActivity.class);
        startActivity(i);
        int cate = 0;
       /* AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle("S a color");
        builder.setItems(categoryList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // the user clicked on colors[which]
                Intent i = new Intent(ServicesActivity.this, ServiceBookingActivity.class);
                startActivity(i);
            }
        });
        builder.show();*/
    }

    @Override
    public void onClick(View v) {

    }
}
