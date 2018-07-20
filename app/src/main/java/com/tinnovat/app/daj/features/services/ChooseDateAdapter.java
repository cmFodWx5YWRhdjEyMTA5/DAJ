package com.tinnovat.app.daj.features.services;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.ServiceAvailableDate;
import com.tinnovat.app.daj.testing.TestActivity;

import java.util.ArrayList;
import java.util.List;

public class ChooseDateAdapter extends RecyclerView.Adapter<ChooseDateAdapter.MyViewHolder> {

    private Context mContext;
    private List<ServiceAvailableDate> serviceAvailableDates;
    private DateAdapterListener mListener;
    private List<Integer> selectedDates;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date;

        public MyViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.date);
        }
    }

    public ChooseDateAdapter(Context context, DateAdapterListener listener, List<ServiceAvailableDate> serviceAvailableDates) {
        this.serviceAvailableDates = serviceAvailableDates;
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.choose_date_list_row, parent, false);
        itemView.setOnClickListener(new TestActivity());
        mContext = parent.getContext();

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final ServiceAvailableDate item = serviceAvailableDates.get(position);

        holder.date.setText(item.getSlotsName());

        if (selectedDates != null && !selectedDates.isEmpty())
        {
            if (selectedDates.contains(item.getId())){
                holder.date.setBackgroundResource(R.drawable.curve_small_bg_orange);
            }else {
                holder.date.setBackgroundResource(R.drawable.curve_small_bg_white);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedDates == null ) {
                    selectedDates = new ArrayList<>();
                    selectedDates.add(item.getId());
                    holder.date.setBackgroundResource(R.drawable.curve_small_bg_orange);
                } else if (selectedDates.contains(item.getId())) {
                    selectedDates.remove(item.getId());
                    holder.date.setBackgroundResource(R.drawable.curve_small_bg_white);
                } else {
                    if (!selectedDates.isEmpty()){
                        selectedDates.add(item.getId());
                        holder.date.setBackgroundResource(R.drawable.curve_small_bg_orange);
                    }else {
                        selectedDates = new ArrayList<>();
                        selectedDates.add(item.getId());
                        holder.date.setBackgroundResource(R.drawable.curve_small_bg_orange);
                    }

                }

                mListener.onDateSelected(selectedDates);

            }
        });

    }

    @Override
    public int getItemCount() {
        return serviceAvailableDates.size();
    }

    public interface DateAdapterListener {

        void onDateSelected(List<Integer> selectedTimeSlots);
    }
}