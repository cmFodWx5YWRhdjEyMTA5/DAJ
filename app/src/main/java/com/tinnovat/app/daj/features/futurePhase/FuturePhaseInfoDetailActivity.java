package com.tinnovat.app.daj.features.futurePhase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.Futurephase;
import com.tinnovat.app.daj.data.network.model.PhaseImage;
import com.tinnovat.app.daj.data.network.model.Service;
import com.tinnovat.app.daj.data.network.model.ServiceAvailableDate;
import com.tinnovat.app.daj.features.services.ChooseDateAdapter;

import java.util.List;
import java.util.Objects;

public class FuturePhaseInfoDetailActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_phase_info_detail);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getText(R.string.future_phase_info_details));

        setData();
    }

    private void setData() {
        Intent i = getIntent();
        Futurephase res = new Gson().fromJson(i.getStringExtra("response"), Futurephase.class);

        TextView bannerText = findViewById(R.id.bannerText);
        TextView area = findViewById(R.id.area);
        TextView descriptionText = findViewById(R.id.descriptionText);

        bannerText.setText(res.getPhaseName());
        area.setText(res.getArea());
        descriptionText.setText(res.getDesc());

        setAdapter(res);


    }
    private void setAdapter(Futurephase futurephase){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        ImageListAdapter mAdapter = new ImageListAdapter(futurephase);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);
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
