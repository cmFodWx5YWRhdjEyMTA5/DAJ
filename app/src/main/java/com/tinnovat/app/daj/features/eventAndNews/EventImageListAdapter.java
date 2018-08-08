package com.tinnovat.app.daj.features.eventAndNews;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.EventDetails;
import com.tinnovat.app.daj.data.network.model.EventsImage;
import com.tinnovat.app.daj.data.network.model.Futurephase;
import com.tinnovat.app.daj.features.futurePhase.ImageSliderActivity;
import com.tinnovat.app.daj.testing.TestActivity;

import java.util.List;
import java.util.Objects;

public class EventImageListAdapter extends RecyclerView.Adapter<EventImageListAdapter.MyViewHolder> {

    private Context mContext;
    private EventDetails mEventDetailsList;
    private List<Integer> selectedDates;
    ImagePopup imagePopup;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image1;

        public MyViewHolder(View view) {
            super(view);
            image1 = view.findViewById(R.id.image1);
        }
    }

    public EventImageListAdapter(EventDetails eventDetailsList) {
        this.mEventDetailsList = eventDetailsList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_row, parent, false);
        itemView.setOnClickListener(new TestActivity());
        mContext = parent.getContext();

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        Picasso.get().load(mEventDetailsList.getEventsImages().get(position).getImgPath())
                .placeholder(Objects.requireNonNull(ContextCompat.getDrawable(mContext, R.drawable.place_holder)))
                .into(holder.image1);

        holder.image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, EventImageSliderActivity.class);
                intent.putExtra("currentPosition",holder.getAdapterPosition());
                intent.putExtra("mEventDetailsList", new Gson().toJson(mEventDetailsList));
                mContext.startActivity(intent);

                /*imagePopup.initiatePopup(holder.image1.getDrawable());
                imagePopup.viewPopup();*/

            }
        });




    }

    @Override
    public int getItemCount() {
        return mEventDetailsList.getEventsImages().size();
    }


}