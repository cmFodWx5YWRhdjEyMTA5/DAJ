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
import com.tinnovat.app.daj.data.network.model.EventDetails;
import com.tinnovat.app.daj.features.eventAndNews.EventDetailActivity;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.features.eventAndNews.EventAndNewsListAdapter;
import com.tinnovat.app.daj.testing.Movie;

import java.util.ArrayList;
import java.util.List;

public class EventListTabFragment extends BaseFragment {

    RelativeLayout a1;
    RelativeLayout a2;
    RelativeLayout a3;

    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private EventAndNewsListAdapter mAdapter;

    private AppPreferanceStore appPreferanceStore;
    private List<EventDetails> eventDetailsList;
    private int selectedPosition;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflater.inflate(R.layout.fragment_tab_1, container, false);
        View view = inflater.inflate(R.layout.fragment_tab_1, container, false);

        appPreferanceStore = new AppPreferanceStore(getContext());

        List<EventDetails> list = new ArrayList();
        if (eventDetailsList != null && !eventDetailsList.isEmpty()) {
            switch (selectedPosition) {
                case 0 :
                    for (EventDetails details : eventDetailsList) {
                        if (details.getDateCategory().equalsIgnoreCase("TODAY"))  {
                            list.add(details);
                        }
                    }
                    break;
                case 1 :
                    for (EventDetails details : eventDetailsList) {
                        if (details.getDateCategory().equalsIgnoreCase("TOMORROW"))  {
                            list.add(details);
                        }
                    }
                    break;
                case 2 :
                    list = eventDetailsList;
                    break;
            }
        }


        eventDetailsList = list;

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
        mAdapter = new EventAndNewsListAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.setData(eventDetailsList);
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

    public static EventListTabFragment getNewInstance(List<EventDetails> data, int position) {
        EventListTabFragment fragment = new EventListTabFragment();
        fragment.eventDetailsList = data;
        fragment.selectedPosition = position;
        return fragment;
    }
}