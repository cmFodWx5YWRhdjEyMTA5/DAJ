package com.tinnovat.app.daj.features.notification;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.Event;
import com.tinnovat.app.daj.data.network.model.Guest;
import com.tinnovat.app.daj.testing.TestActivity;

import java.util.List;

public class NotificationGuestAdapter extends RecyclerView.Adapter<NotificationGuestAdapter.MyViewHolder> {

    private Context mContext;
    private List<Guest> mGuest;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView guestName;
        public TextView purpose;
        public TextView status;
        public TextView dateAndTime;
        public TextView time;

        public MyViewHolder(View view) {
            super(view);


            guestName = view.findViewById(R.id.guestName);
            purpose = view.findViewById(R.id.purpose);
            status = view.findViewById(R.id.status);
            dateAndTime = view.findViewById(R.id.dateTime);
            time = view.findViewById(R.id.time);
        }
    }


    public NotificationGuestAdapter(List<Guest> event) {
        this.mGuest = event;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_guest_list_row, parent, false);
        itemView.setOnClickListener(new TestActivity());
        mContext = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        String[] dateTime = mGuest.get(position).getDateTime().split(" ");

        holder.guestName.setText(mGuest.get(position).getName());
        holder.dateAndTime.setText(dateTime[0]);
        holder.time.setText(String.format(mContext.getResources().getString(R.string.time_formatter),dateTime[1],dateTime[2]));
        holder.status.setText(mContext.getResources().getString(R.string.arrived));

        switch (mGuest.get(position).getPurpose()) {

            case 1:
                holder.purpose.setText(mContext.getResources().getString(R.string.family));
                break;

            case 2:
                holder.purpose.setText(mContext.getResources().getString(R.string.friend));
                break;

            case 3:
                holder.purpose.setText(mContext.getResources().getString(R.string.maintenance));
                break;

            case 4:
                holder.purpose.setText(mContext.getResources().getString(R.string.taxi1));
                break;

            case 5:
                holder.purpose.setText(mContext.getResources().getString(R.string.delivery));
                break;

        }

    }

    @Override
    public int getItemCount() {
        return mGuest.size();
    }
}