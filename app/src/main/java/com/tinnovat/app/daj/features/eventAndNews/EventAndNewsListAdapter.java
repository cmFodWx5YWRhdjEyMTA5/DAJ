package com.tinnovat.app.daj.features.eventAndNews;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.EventDetails;
import com.tinnovat.app.daj.testing.TestActivity;

import java.util.List;

public class EventAndNewsListAdapter extends RecyclerView.Adapter<EventAndNewsListAdapter.MyViewHolder> {

    private Context mContext;

    private List<EventDetails> eventDetailsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView startDate;
        public TextView endDate;
        public Button interested;
        public ImageView bannerImage;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.bannerText);
            startDate = view.findViewById(R.id.startDate);
            endDate = view.findViewById(R.id.endDate);
            interested = view.findViewById(R.id.interested);
            bannerImage = view.findViewById(R.id.bannerImage);
        }
    }

    class ViewPagerViewHolder extends RecyclerView.ViewHolder {
        ViewPager viewPager;

        public ViewPagerViewHolder(View view) {
            super(view);
            viewPager = view.findViewById(R.id.pager);
        }
    }


    public void setData(List<EventDetails> eventDetails) {
        this.eventDetailsList = eventDetails;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_news_list_row
                        , parent, false);
        itemView.setOnClickListener(new TestActivity());
        mContext = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (eventDetailsList != null && !eventDetailsList.isEmpty()) {
            if (!eventDetailsList.get(position).getEventsImages().isEmpty()){
                Picasso.get().load(eventDetailsList.get(position).getEventsImages().get(0).getImgPath()).into(holder.bannerImage);
            }

            String[] startDate = eventDetailsList.get(position).getStartDatetime().split(" ");
            String[] endDate = eventDetailsList.get(position).getEndDatetime().split(" ");

            holder.title.setText(eventDetailsList.get(position).getEventsName());
            holder.startDate.setText(startDate[0]);
            holder.endDate.setText(endDate[0]);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, EventDetailActivity.class);
                    i.putExtra("response",new Gson().toJson(eventDetailsList.get(holder.getAdapterPosition())));
                    mContext.startActivity(i);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return eventDetailsList == null ? 0 : eventDetailsList.size();
    }
}