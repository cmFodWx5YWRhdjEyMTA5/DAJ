package com.tinnovat.app.daj.features.complaint;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tinnovat.app.daj.BaseFragment;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.ComplaintList;
import com.tinnovat.app.daj.data.network.model.ComplaintListResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ComplaintListFragment extends BaseFragment {

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;

    private AppPreferanceStore appPreferanceStore;

    TextView empty ;


    public ComplaintListFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        ComplaintListFragment fragment = new ComplaintListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initialiseViews(View view) {
        FloatingActionButton fab = view.findViewById(R.id.fab);
        recyclerView = view.findViewById(R.id.recycler_view);
        empty = view.findViewById(R.id.empty);

        fab.setColorFilter(Color.WHITE);

        fab.setOnClickListener(this);


        fetchContactList();
    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_complaint_list, container, false);
        appPreferanceStore = new AppPreferanceStore(getContext());
        mListener.setTitle(getString(R.string.my_complaint_list));

        initialiseViews(view);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                if (mListener != null) {
                    mListener.onFabButtonActionListener();
                }
                break;
        }
    }

    private void fetchContactList() {

        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<ComplaintListResponseModel> call = apiInterface.getComplaintList(appPreferanceStore.getLanguage() ? "en" : "ar");
        call.enqueue(new Callback<ComplaintListResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ComplaintListResponseModel> call, @NonNull Response<ComplaintListResponseModel> response) {
                endLoading();
                ComplaintListResponseModel responseBody = response.body();
                if (responseBody != null && responseBody.getComplaints() != null)
                    setData(responseBody.getComplaints());
            }

            @Override
            public void onFailure(@NonNull Call<ComplaintListResponseModel> call, @NonNull Throwable t) {
                endLoading();
            }
        });
    }
    public void setData(List<ComplaintList> responseList){
        if (responseList.size() == 0){
            empty.setVisibility(View.VISIBLE);
        }else {
            empty.setVisibility(View.GONE);
        }
        ComplaintListAdapter mAdapter = new ComplaintListAdapter(responseList, mListener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    public interface OnFragmentInteractionListener {
        void setTitle(String title);

        void onListItemClickListener(ComplaintList complaintList);

        void onFabButtonActionListener();
    }
}
