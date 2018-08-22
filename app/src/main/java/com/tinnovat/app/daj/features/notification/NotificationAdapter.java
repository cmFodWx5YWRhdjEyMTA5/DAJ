package com.tinnovat.app.daj.features.notification;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.ContactResponseModel;
import com.tinnovat.app.daj.data.network.model.Event;
import com.tinnovat.app.daj.testing.TestActivity;

import java.util.List;

import retrofit2.Response;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private Context mContext;
    private List<Event> mEvent;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView event;
        public TextView venue;
        public TextView startDate;
        public TextView endDate;
        public TextView category;


        public MyViewHolder(View view) {
            super(view);

            event = view.findViewById(R.id.event);
            venue = view.findViewById(R.id.venue);
            startDate = view.findViewById(R.id.startDate);
            endDate = view.findViewById(R.id.endDate);
            category = view.findViewById(R.id.category);
        }
    }


    public NotificationAdapter(List<Event> event) {
        this.mEvent = event;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_list_row, parent, false);
        itemView.setOnClickListener(new TestActivity());
        mContext = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        String[] startDateTime = mEvent.get(position).getStartDatetime().split(" ");
        String[] endDateTime = mEvent.get(position).getEndDatetime().split(" ");

        holder.event.setText(mEvent.get(position).getEventsName());
        holder.venue.setText(mEvent.get(position).getEventsVenue());
        holder.startDate.setText(startDateTime[0]);
        holder.endDate.setText(endDateTime[0]);

    }

    @Override
    public int getItemCount() {
        return mEvent.size();
    }
}