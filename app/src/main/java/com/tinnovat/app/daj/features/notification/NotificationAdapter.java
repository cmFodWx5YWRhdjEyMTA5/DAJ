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

import com.google.gson.Gson;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.ContactResponseModel;
import com.tinnovat.app.daj.data.network.model.Event;
import com.tinnovat.app.daj.data.network.model.Notifications;
import com.tinnovat.app.daj.features.bookings.GuestRegistrationActivityMain;
import com.tinnovat.app.daj.features.complaint.ComplaintMainActivity;
import com.tinnovat.app.daj.features.eventAndNews.EventDetailActivity;
import com.tinnovat.app.daj.features.eventAndNews.EventImageSliderActivity;
import com.tinnovat.app.daj.features.eventAndNews.EventNewsActivity;
import com.tinnovat.app.daj.testing.TestActivity;

import java.util.List;

import retrofit2.Response;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private Context mContext;
    private List<Notifications> mNotifications;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mBanner1;
        TextView mBanner2;
        TextView mBanner3;
        TextView mBanner4;
        TextView mBanner5;

        TextView mText1;
        TextView mText2;
        TextView mText3;
        TextView mText4;
        TextView mText5;

        public MyViewHolder(View view) {
            super(view);

            mBanner1 = view.findViewById(R.id.banner1);
            mBanner2 = view.findViewById(R.id.banner2);
            mBanner3 = view.findViewById(R.id.banner3);
            mBanner4 = view.findViewById(R.id.banner4);
            mBanner5 = view.findViewById(R.id.banner5);

            mText1 = view.findViewById(R.id.text1);
            mText2 = view.findViewById(R.id.text2);
            mText3 = view.findViewById(R.id.text3);
            mText4 = view.findViewById(R.id.text4);
            mText5 = view.findViewById(R.id.text5);
        }
    }


    NotificationAdapter(List<Notifications> notifications) {
        this.mNotifications = notifications;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_list_row, parent, false);
        itemView.setOnClickListener(new TestActivity());
        mContext = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {




        switch (mNotifications.get(position).getType()){
            case "event":
                holder.mBanner1.setText(mContext.getResources().getString(R.string.event));
                holder.mBanner2.setText(mContext.getResources().getString(R.string.venue));
                holder.mBanner3.setText(mContext.getResources().getString(R.string.capacity));
                holder.mBanner4.setText(mContext.getResources().getString(R.string.start_date));
                holder.mBanner5.setText(mContext.getResources().getString(R.string.end_date));

                holder.mText1.setText(mNotifications.get(position).getEventsName());
                holder.mText2.setText(mNotifications.get(position).getEventsVenue());
                holder.mText3.setText(String.format(mContext.getResources().getString(R.string.number),mNotifications.get(position).getGuestsCapacity()));
                String[] startDateTime = mNotifications.get(position).getStartDatetime().split(" ");
                String[] endDateTime = mNotifications.get(position).getEndDatetime().split(" ");

                holder.mText4.setText(startDateTime[0]);
                holder.mText5.setText(endDateTime[0]);

                break;
            case "guest":
                holder.mBanner1.setText(mContext.getResources().getString(R.string.guest_name));
                holder.mBanner2.setText(mContext.getResources().getString(R.string.purpose_banner));
                holder.mBanner3.setText(mContext.getResources().getString(R.string.date));
                holder.mBanner4.setText(mContext.getResources().getString(R.string.time));
                holder.mBanner5.setText(mContext.getResources().getString(R.string.status));

                holder.mText1.setText(mNotifications.get(position).getName());
                holder.mText5.setText(mContext.getResources().getString(R.string.arrived));

                String[] date = mNotifications.get(position).getNotifyTime().split(" ");

                holder.mText3.setText(date[0]);
                holder.mText4.setText(String.format(mContext.getResources().getString(R.string.time_formatter),date[1],date[2]));

                switch (mNotifications.get(position).getPurpose()) {

                    case 1:
                        holder.mText2.setText(mContext.getResources().getString(R.string.family));
                        break;

                    case 2:
                        holder.mText2.setText(mContext.getResources().getString(R.string.friend));
                        break;

                    case 3:
                        holder.mText2.setText(mContext.getResources().getString(R.string.maintenance));
                        break;

                    case 4:
                        holder.mText2.setText(mContext.getResources().getString(R.string.taxi1));
                        break;

                    case 5:
                        holder.mText2.setText(mContext.getResources().getString(R.string.delivery));
                        break;

                }

                break;
            case "complaint":
                holder.mBanner1.setText(mContext.getResources().getString(R.string.complaint_no));
                holder.mBanner2.setText(mContext.getResources().getString(R.string.category));
                holder.mBanner3.setText(mContext.getResources().getString(R.string.status));
                holder.mBanner4.setText(mContext.getResources().getString(R.string.date));
                holder.mBanner5.setText(mContext.getResources().getString(R.string.time));

                holder.mText1.setText(String.format(mContext.getResources().getString(R.string.cmp),mNotifications.get(position).getRegisterNo()));
                holder.mText2.setText(mNotifications.get(position).getCategoryName());

                String[] dateTime = mNotifications.get(position).getNotifyTime().split(" ");
                holder.mText4.setText(dateTime[0]);
                holder.mText5.setText(String.format(mContext.getResources().getString(R.string.time_formatter),dateTime[1],dateTime[2]));


                switch (mNotifications.get(position).getComplaintStatus()) {
                    case 0:
                        holder.mText3.setText(R.string.submitted);
                        break;

                    case 1:
                        holder.mText3.setText(R.string.assigned);
                        break;

                    case 2:
                        holder.mText3.setText(R.string.in_progress);
                        break;

                    case 3:
                        holder.mText3.setText(R.string.completed);
                        break;
                }

                break;
            case "technician":
                holder.mBanner1.setText(mContext.getResources().getString(R.string.complaint_no));
                holder.mBanner2.setText(mContext.getResources().getString(R.string.technician));
                holder.mBanner3.setText(mContext.getResources().getString(R.string.email_id));
                holder.mBanner4.setText(mContext.getResources().getString(R.string.date));
                holder.mBanner5.setText(mContext.getResources().getString(R.string.time));

                holder.mText1.setText(String.format(mContext.getResources().getString(R.string.cmp),mNotifications.get(position).getRegisterNo()));
                holder.mText2.setText(mNotifications.get(position).getTechnicianName());
                holder.mText3.setText(mNotifications.get(position).getTechnicianEmail());

                String[] dateAndTime = mNotifications.get(position).getNotifyTime().split(" ");
                holder.mText4.setText(dateAndTime[0]);
                holder.mText5.setText(String.format(mContext.getResources().getString(R.string.time_formatter),dateAndTime[1],dateAndTime[2]));
                break;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mNotifications.get(holder.getAdapterPosition()).getType()) {
                    case "event": {

                        Intent i = new Intent(mContext, EventDetailActivity.class);
                        i.putExtra("response",new Gson().toJson(mNotifications.get(holder.getAdapterPosition())));
                        mContext.startActivity(i);

                        break;
                    }
                    case "complaint": {
                        Intent intent = new Intent(mContext, ComplaintMainActivity.class);
                        intent.putExtra("response",new Gson().toJson(mNotifications.get(holder.getAdapterPosition())));
                        intent.putExtra("fromNotification",true);
                        mContext.startActivity(intent);

                        break;
                    }
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return mNotifications.size();
    }
}