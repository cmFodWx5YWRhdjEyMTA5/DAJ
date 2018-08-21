package com.tinnovat.app.daj.features.futurePhase;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tinnovat.app.daj.BaseFragment;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.Futurephase;

import me.biubiubiu.justifytext.library.JustifyTextView;


public class FuturePhaseDetailFragment extends BaseFragment implements OnMapReadyCallback {

    private Futurephase futurePhaseItem;
    private JustifyTextView descriptionText;
    private TextView readMore;
    private TextView readLess;

    private OnFragmentInteractionListener mListener;
    private double lat;
    private double lng;

    public FuturePhaseDetailFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(Futurephase item) {
        FuturePhaseDetailFragment fragment = new FuturePhaseDetailFragment();
        fragment.futurePhaseItem = item;
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initialiseViews(View view) {
        mListener.setTitle(futurePhaseItem.getPhaseName());

        TextView bannerText = view.findViewById(R.id.bannerText);
        TextView area = view.findViewById(R.id.area);
        descriptionText = view.findViewById(R.id.descriptionText);
        readMore = view.findViewById(R.id.readMore);
        readLess = view.findViewById(R.id.readLess);

        lat = Double.parseDouble(futurePhaseItem.getLocationlat());
        lng = Double.parseDouble(futurePhaseItem.getLocationlng());



        bannerText.setText(futurePhaseItem.getPhaseName());
        area.setText(futurePhaseItem.getArea());
        descriptionText.setText(String.format(getString(R.string.description_formatter),futurePhaseItem.getDesc()));


        descriptionText.post(new Runnable() {
            @Override
            public void run() {
                int lineCount = descriptionText.getLineCount();
                if (lineCount > 5){
                    readLess.setVisibility(View.GONE);
                    readMore.setVisibility(View.VISIBLE);
                }else {
                    readLess.setVisibility(View.GONE);
                    readMore.setVisibility(View.GONE);
                }
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        ImageListAdapter mAdapter = new ImageListAdapter(futurePhaseItem);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        readMore.setOnClickListener(this);
        readLess.setOnClickListener(this);
    }

    private void setData(Futurephase futurePhaseItem) {

    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_future_phase_detail, container, false);

        initialiseViews(view);
        //mListener.setTitle(getString(R.string.future_phase_info_details));
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
            case R.id.readMore:
                doReadMore();
                break;

            case R.id.readLess:
                doReadLess();
                break;
        }
    }

    private void doReadMore() {
        descriptionText.setMaxLines(50);
        readLess.setVisibility(View.VISIBLE);
        readMore.setVisibility(View.GONE);
    }

    private void doReadLess() {
        descriptionText.setMaxLines(6);
        readLess.setVisibility(View.GONE);
        readMore.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        LatLng latLng = new LatLng(lat, lng);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(futurePhaseItem.getPhaseName());
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        Marker mCurrLocationMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

    }


    public interface OnFragmentInteractionListener {

        void setTitle(String title);
    }
}
