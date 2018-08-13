package com.tinnovat.app.daj.features.complaint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.Futurephase;
import com.tinnovat.app.daj.features.futurePhase.ImageSliderAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;


public class ComplaintImageSliderActivity extends AppCompatActivity {
    private static ViewPager mPager;
    private  int currentPage = 0;
    private TextView back;
    private TextView current;
    private String[] images;

    private ArrayList<String> imageArray = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slider);
        back = findViewById(R.id.back);
        current = findViewById(R.id.current);

        Intent intent = getIntent();
        currentPage = intent.getIntExtra("currentPosition",0);
        images = intent.getStringArrayExtra("images");

        init();
    }
    private void init() {
        imageArray.addAll(Arrays.asList(images));

        mPager =  findViewById(R.id.pager);
        mPager.setAdapter(new ComplaintImageSliderAdapter(ComplaintImageSliderActivity.this,imageArray));
        mPager.setCurrentItem(currentPage, true);
        Locale.setDefault(Locale.US);
        current.setText(String.valueOf(currentPage));

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int mPosition = position+1;
                current.setText(String.format(getResources().getString(R.string.page_no_formatter),mPosition,images.length));
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