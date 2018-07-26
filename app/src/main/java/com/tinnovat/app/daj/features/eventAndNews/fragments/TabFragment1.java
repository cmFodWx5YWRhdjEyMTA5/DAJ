package com.tinnovat.app.daj.features.eventAndNews.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tinnovat.app.daj.BaseFragment;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.EventCategory;
import com.tinnovat.app.daj.features.eventAndNews.EventDetailActivity;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.features.eventAndNews.EventAndNewsListAdapter;
import com.tinnovat.app.daj.features.eventAndNews.EventNewsActivity;
import com.tinnovat.app.daj.testing.Movie;

import java.util.ArrayList;
import java.util.List;

public class TabFragment1 extends BaseFragment implements EventNewsActivity.EventAndNewsListener{

    RelativeLayout a1;
    RelativeLayout a2;
    RelativeLayout a3;

    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private EventAndNewsListAdapter mAdapter;

    private List<EventCategory> mEventCategories;
    private AppPreferanceStore appPreferanceStore;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflater.inflate(R.layout.fragment_tab_1, container, false);
        View view = inflater.inflate(R.layout.fragment_tab_1, container, false);

        appPreferanceStore = new AppPreferanceStore(getContext());

        setView(view);

        Button btn = view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EventDetailActivity.class);
                startActivity(i);
            }
        });
        a1 = view.findViewById(R.id.a1);

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EventDetailActivity.class);
                startActivity(i);
            }
        });

        return view;
    }

    private void setView(View view) {
        TextView banner = view.findViewById(R.id.bannerText);

        recyclerView = view.findViewById(R.id.recycler_view);
        mAdapter = new EventAndNewsListAdapter(mEventCategories);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        /*if (appPreferanceStore.getDataEventAndNews().body() != null &&
                appPreferanceStore.getDataEventAndNews().body().getCategory() != null) {

            banner.setText(appPreferanceStore.getDataEventAndNews().body().getCategory().get(0).getCategoryName());

        }*/

       // prepareMovieData();

    }


    @Override
    public void initialiseViews(View view) {

    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void EventCategories(List<EventCategory> eventCategories) {
        mEventCategories = eventCategories;

    }
}