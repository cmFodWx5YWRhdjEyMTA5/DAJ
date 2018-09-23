package com.tinnovat.app.daj.features.eventAndNews.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tinnovat.app.daj.BaseFragment;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.EventDetails;
import com.tinnovat.app.daj.data.network.model.SuccessResponseModel;
import com.tinnovat.app.daj.features.bookings.MyBookingsAdapter;
import com.tinnovat.app.daj.features.eventAndNews.EventAndNewsListAdapter;
import com.tinnovat.app.daj.features.eventAndNews.EventNewsActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventListTabFragment extends BaseFragment implements EventAndNewsListAdapter.SelectAdapterListener{

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
                        if (details.getDateCategory(0).equalsIgnoreCase("TODAY"))  {
                            list.add(details);
                        }
                    }
                    break;
                case 1 :
                    for (EventDetails details : eventDetailsList) {
                        if (details.getDateCategory(1).equalsIgnoreCase("TOMORROW"))  {
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
        return view;
    }

    private void setView(View view) {

        recyclerView = view.findViewById(R.id.recycler_view);
        mAdapter = new EventAndNewsListAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.setData(eventDetailsList);
    }

    public void doValidation(int mId){
        if (isNetworkConnected()){
            actionInterested(mId);
        }else {
            showErrorDilog(false);
        }
    }


    private void actionInterested(int mId) {
        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        Call<SuccessResponseModel> call = apiInterface.postEventInterset(mId);
        call.enqueue(new Callback<SuccessResponseModel>() {
            @Override
            public void onResponse(Call<SuccessResponseModel> call, Response<SuccessResponseModel> response) {
                endLoading();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        showDilog(response.body().getMessage());
//                        doInterested(true);

                    } else {
                        showDilog(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<SuccessResponseModel> call, Throwable t) {
                endLoading();
                showDilog(getResources().getString(R.string.try_again_later));
            }
        });
    }

    private void showDilog(String message){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        getActivity().finish();
                        Intent i = new Intent(getActivity(), EventNewsActivity.class);
                        startActivity(i);
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
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

    @Override
    public void onInterested(int eventId) {

        doValidation(eventId);

    }
}