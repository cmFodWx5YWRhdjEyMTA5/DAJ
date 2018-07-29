package com.tinnovat.app.daj.features.futurePhase;

import android.content.Context;
import android.os.Bundle;
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
import com.tinnovat.app.daj.data.network.model.FuturePhasesResponseModel;
import com.tinnovat.app.daj.data.network.model.Futurephase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class FuturePhaseInfoFragment extends BaseFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private AppPreferanceStore appPreferanceStore;
    private RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FuturePhaseInfoFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FuturePhaseInfoFragment newInstance(int columnCount) {
        FuturePhaseInfoFragment fragment = new FuturePhaseInfoFragment();
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
        View view = inflater.inflate(R.layout.fragment_future_phase_info, container, false);
        if (getActivity() != null && getActivity().getActionBar() != null)
            getActivity().getActionBar().setTitle(getString(R.string.future_phase_info_list));

        appPreferanceStore = new AppPreferanceStore(getContext());
        mListener.setTitle(getString(R.string.future_phase_info_list));

        fetchFuturePhasesInfo();
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

    private void fetchFuturePhasesInfo() {
        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<FuturePhasesResponseModel> call = apiInterface.getFuturePhasesInfo(appPreferanceStore.getLanguage() ? "en" : "ar");
        call.enqueue(new Callback<FuturePhasesResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<FuturePhasesResponseModel> call, @NonNull Response<FuturePhasesResponseModel> response) {
                endLoading();
                FuturePhasesResponseModel responseData = response.body();
                if (responseData != null && responseData.getFuturephases() != null) {
                    showMessage("Future PhasesInfo Successfully");
                    setData(responseData.getFuturephases());
                }

            }

            @Override
            public void onFailure(@NonNull Call<FuturePhasesResponseModel> call, @NonNull Throwable t) {
                endLoading();

                showMessage("FuturePhasesInfo Failed");
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
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

    public void setData(List<Futurephase> data) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new FuturePhaseRecyclerViewAdapter(data, getActivity(), mListener));
    }

    public interface OnListFragmentInteractionListener {

        void setTitle(String title);

        void onListFragmentInteraction(Futurephase item);
    }
}
