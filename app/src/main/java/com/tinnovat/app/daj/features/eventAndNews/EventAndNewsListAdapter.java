package com.tinnovat.app.daj.features.eventAndNews;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.EventDetails;
import com.tinnovat.app.daj.testing.TestActivity;

import java.util.List;

public class EventAndNewsListAdapter extends RecyclerView.Adapter<EventAndNewsListAdapter.MyViewHolder> {

    private Context mContext;

    private List<EventDetails> eventDetailsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.bannerText);
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
            holder.title.setText(eventDetailsList.get(position).getEventsName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.title.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return eventDetailsList == null ? 0 : eventDetailsList.size();
    }
}