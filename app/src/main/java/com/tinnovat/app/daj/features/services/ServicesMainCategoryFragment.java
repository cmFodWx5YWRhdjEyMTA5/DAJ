package com.tinnovat.app.daj.features.services;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
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
import com.tinnovat.app.daj.data.network.model.ServiceCategory;
import com.tinnovat.app.daj.data.network.model.ServicesResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnCategoryListFragmentInteractionListener}
 * interface.
 */
public class ServicesMainCategoryFragment extends BaseFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnCategoryListFragmentInteractionListener mListener;
    private AppPreferanceStore appPreferanceStore;
    private RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ServicesMainCategoryFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ServicesMainCategoryFragment newInstance(int columnCount) {
        ServicesMainCategoryFragment fragment = new ServicesMainCategoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public void initialiseViews(View view) {

    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
       /* if (getActivity() != null && getActivity().getActionBar() != null)
            getActivity().getActionBar().setTitle(getString(R.string.future_phase_info_list));*/

        appPreferanceStore = new AppPreferanceStore(getContext());
        mListener.setTitle(getString(R.string.services));

        if (isNetworkConnected()){
            fetchServiceList();
        }else {
            showErrorDilog(true);
        }
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
        }
        return view;
    }

    private void fetchServiceList() {

        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<ServicesResponseModel> call = apiInterface.getServiceList(appPreferanceStore.getLanguage() ? "en" : "ar");
        call.enqueue(new Callback<ServicesResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ServicesResponseModel> call, @NonNull Response<ServicesResponseModel> response) {
                //endLoading();
                //showMessage("Data Fetched Successfully");

                ServicesResponseModel responseBody = response.body();
                if (responseBody != null && responseBody.getServiceCategory() != null) {
                    setData(responseBody.getServiceCategory());
                }else {
                    endLoading();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ServicesResponseModel> call, @NonNull Throwable t) {
                endLoading();

                //showMessage("ServiceList Failed");
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCategoryListFragmentInteractionListener) {
            mListener = (OnCategoryListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

    }

    public void setData(List<ServiceCategory> data) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new ServicesAdapter(data, getActivity(), mListener, getLanguage()));

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                endLoading();
            }
        }, 2000);

    }

    public interface OnCategoryListFragmentInteractionListener {

        void setTitle(String title);

        void onCategoryListFragmentInteraction(ServiceCategory item);
    }
}
