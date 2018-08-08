package com.tinnovat.app.daj.features.eventAndNews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.EventDetails;
import com.tinnovat.app.daj.data.network.model.Futurephase;

import java.util.ArrayList;


public class EventImageSliderActivity extends AppCompatActivity {
    private static ViewPager mPager;
    private  int currentPage = 0;
    private TextView back;
    private TextView current;
    private EventDetails mEventDetailsList;

    private ArrayList<String> imageArray = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slider);
        back = findViewById(R.id.back);
        current = findViewById(R.id.current);

        Intent intent = getIntent();
        currentPage = intent.getIntExtra("currentPosition",0);
        mEventDetailsList = new Gson().fromJson(intent.getStringExtra("mEventDetailsList"), EventDetails.class);

        init();
    }
    private void init() {
        for(int i=0;i<mEventDetailsList.getEventsImages().size();i++)
            imageArray.add(mEventDetailsList.getEventsImages().get(i).getImgPath());

        mPager =  findViewById(R.id.pager);
        mPager.setAdapter(new EventImageSliderAdapter(EventImageSliderActivity.this,imageArray));
        mPager.setCurrentItem(currentPage, true);
        current.setText(String.valueOf(currentPage));

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int mPosition = position+1;
                current.setText(String.format(getResources().getString(R.string.page_no_formatter),mPosition,mEventDetailsList.getEventsImages().size()));
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}