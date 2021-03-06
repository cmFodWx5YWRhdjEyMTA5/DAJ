package com.tinnovat.app.daj.features.complaint;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.squareup.picasso.Picasso;
import com.tinnovat.app.daj.BaseFragment;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.ComplaintList;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import me.biubiubiu.justifytext.library.JustifyTextView;


public class ComplaintDetailFragment extends BaseFragment {


    private ComplaintList mComplaintList;
    private OnFragmentInteractionListener mListener;
    private TextView locationText;
    private RecyclerView recyclerView;
    ImagePopup imagePopup;
    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    LinearLayout banner;

    public ComplaintDetailFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(ComplaintList complaintList) {
        ComplaintDetailFragment fragment = new ComplaintDetailFragment();
        fragment.mComplaintList = complaintList;
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initialiseViews(View view) {
        mListener.setTitle(getActivity().getResources().getString(R.string.my_com));
        locationText = view.findViewById(R.id.locationText);
        recyclerView = view.findViewById(R.id.recycler_view);

        locationText.setPaintFlags(locationText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        TextView complaintTitle = view.findViewById(R.id.complaintTitle);
        TextView complaintDate = view.findViewById(R.id.complaintDate);
        TextView status = view.findViewById(R.id.status);
        JustifyTextView description = view.findViewById(R.id.description);

        imagePopup = new ImagePopup(getActivity());
        imagePopup.setBackgroundColor(Color.BLACK);
        imagePopup.setFullScreen(true);

        banner = view.findViewById(R.id.bannerText);

        complaintTitle.setText(mComplaintList.getCategoryName());
        complaintDate.setText(mComplaintList.getSubmittedDate());
        description.setText(String.format(getResources().getString(R.string.description_formatter),mComplaintList.getDescription()));

        switch (mComplaintList.getComplaintStatus()) {
            case 0:
                status.setText(R.string.submitted);
                status.setBackgroundResource(R.drawable.curve_small_bg_red);
                break;

            case 1:
                status.setText(R.string.assigned);
                status.setBackgroundResource(R.drawable.curve_small_bg_blue);
                break;

                case 2:
                status.setText(R.string.in_progress);
                status.setBackgroundResource(R.drawable.curve_small_bg_orange);
                break;

            case 3:
                status.setText(R.string.completed);
                status.setBackgroundResource(R.drawable.curve_small_bg_green);
                break;
        }

        if (mComplaintList.getImages().size() != 0) {
            image1 = view.findViewById(R.id.image1);
            image2 = view.findViewById(R.id.image2);
            image3 = view.findViewById(R.id.image3);
            image4 = view.findViewById(R.id.image4);
            LinearLayout secondRow = view.findViewById(R.id.secondRow);
            LinearLayout imageLayout = view.findViewById(R.id.imageLayout);

            //TextView locationText = view.findViewById(R.id.locationText);


      if (getLanguage()){
                 locationText.setGravity(Gravity.START|Gravity.CENTER_VERTICAL);
        }else {
                 locationText.setGravity(Gravity.END|Gravity.CENTER_VERTICAL);
        }




            if (getLocationText() != null) {
                banner.setVisibility(View.VISIBLE);
                locationText.setText(getLocationText());
                //locationText.setText(getAddress(mComplaintList.getLocation().getLat(), mComplaintList.getLocation().getLong()));
            } else
                banner.setVisibility(View.GONE);

            switch (mComplaintList.getImages().size()) {
                case 0:
                    imageLayout.setVisibility(View.GONE);
                    break;
                case 1:
                    imageLayout.setVisibility(View.VISIBLE);
                    secondRow.setVisibility(View.GONE);
                    Picasso.get().load(mComplaintList.getImages().get(0).getPath())
                            .resize(400,400)
                            .centerCrop()
                            .placeholder(ContextCompat.getDrawable(getContext(), R.drawable.place_holder))
                            .into(image1);



                    break;

                case 2:
                    imageLayout.setVisibility(View.VISIBLE);
                    secondRow.setVisibility(View.VISIBLE);
                    image4.setVisibility(View.VISIBLE);
                    Picasso.get().load(mComplaintList.getImages().get(0).getPath())
                            .resize(400,400)
                            .centerCrop()
                            .placeholder(ContextCompat.getDrawable(getContext(), R.drawable.place_holder))
                            .into(image1);
                    Picasso.get().load(mComplaintList.getImages().get(1).getPath())
                            .resize(400,400)
                            .centerCrop()
                            .placeholder(ContextCompat.getDrawable(getContext(), R.drawable.place_holder))
                            .into(image4);

                    break;

                case 3:
                    Picasso.get().load(mComplaintList.getImages().get(0).getPath())
                            .resize(400,400)
                            .centerCrop()
                            .placeholder(ContextCompat.getDrawable(getContext(), R.drawable.place_holder))
                            .into(image1);
                    Picasso.get().load(mComplaintList.getImages().get(1).getPath())
                            .resize(400,400)
                            .centerCrop()
                            .placeholder(ContextCompat.getDrawable(getContext(), R.drawable.place_holder))
                            .into(image2);
                    Picasso.get().load(mComplaintList.getImages().get(2).getPath())
                            .resize(400,400)
                            .centerCrop()
                            .placeholder(ContextCompat.getDrawable(getContext(), R.drawable.place_holder))
                            .into(image3);
                    image4.setVisibility(View.GONE);
                    break;

                default:
                    break;


            }


            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImagePopup(0);

                }
            });

            image2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImagePopup(1);

                }
            });

            image3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImagePopup(2);

                }
            });

            image4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImagePopup(3);

                }
            });


        } else {


            if (getLocationText() != null) {
                LinearLayout imageLayout = view.findViewById(R.id.imageLayout);
                imageLayout.setVisibility(View.GONE);

                banner.setVisibility(View.VISIBLE);
                locationText.setText(getLocationText());
                //locationText.setText(getAddress(mComplaintList.getLocation().getLat(), mComplaintList.getLocation().getLong()));
            } else
                banner.setVisibility(View.GONE);
        }





        setStatus(mComplaintList);
    }

    private String getLocationText(){
      /*  LinearLayout banner = view.findViewById(R.id.bannerText);


        if (getAddress(mComplaintList.getLocation().getLat(), mComplaintList.getLocation().getLong()) != null) {
            banner.setVisibility(View.VISIBLE);
            locationText.setText(getAddress(mComplaintList.getLocation().getLat(), mComplaintList.getLocation().getLong()));
        } else
            banner.setVisibility(View.VISIBLE);
        */
        return getAddress(mComplaintList.getLocation().getLat(), mComplaintList.getLocation().getLong());
    }

    private void ImagePopup(int position){

        String[] images = new String[mComplaintList.getImages().size()];
        for (int i=0 ;i<mComplaintList.getImages().size();i++){
            images[i] = mComplaintList.getImages().get(i).getPath();
        }


                Intent intent = new Intent(getContext(), ComplaintImageSliderActivity.class);
                intent.putExtra("currentPosition",position);
                intent.putExtra("images", images);
                getActivity().startActivity(intent);
    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_complaint, container, false);

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


    private String getAddress(double lat, double lng) {

        Geocoder geocoder = new Geocoder(getContext(), Locale.ENGLISH);
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            if (addresses.size() != 0)
                return addresses.get(0).getAddressLine(0) + ", " +addresses.get(0).getAddressLine(1) + ", " + addresses.get(0).getAddressLine(2);
            else
                return null;
        } catch (IOException e) {
            //e.printStackTrace();
            Log.e("Error : Geocoder", "Impossible to connect to Geocoder", e);
        }
        return null;
    }

    public void setStatus(ComplaintList response) {
        StatusListAdapter mAdapter = new StatusListAdapter(response, mListener,getLanguage());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View v) {

    }


    public interface OnFragmentInteractionListener {

        void setTitle(String title);
    }
}
