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
    int[] checkedPositions;
    private DateAdapterListener mListener;
    private List<String> selectedDates;

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
        checkedPositions = new int[serviceAvailableDates.size()];

        for (int i = 0; i < serviceAvailableDates.size(); i++) {
            checkedPositions[i] = 0;
        }

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final ServiceAvailableDate item = serviceAvailableDates.get(position);
        holder.date.setText(item.getSlotsName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedDates != null && !selectedDates.isEmpty()) {
                    selectedDates = new ArrayList<>();
                    selectedDates.add(item.getSlotsName());
                    holder.date.setBackgroundResource(R.drawable.curve_small_bg_orange);
                } else if (selectedDates.contains(item.getSlotsName())) {
                    selectedDates.remove(item.getSlotsName());
                    holder.date.setBackgroundResource(R.drawable.curve_small_bg_white);
                } else {
                    selectedDates.add(item.getSlotsName());
                    holder.date.setBackgroundResource(R.drawable.curve_small_bg_orange);
                }

                mListener.onDateSelected(selectedDates);

               /* if (checkedPositions[holder.getAdapterPosition()] == 0) {
                    checkedPositions[holder.getAdapterPosition()] = 1;
                    holder.date.setBackgroundResource(R.drawable.curve_small_bg_orange);
                    mListener.onDateSelected();
                } else {
                    checkedPositions[holder.getAdapterPosition()] = 0;
                    holder.date.setBackgroundResource(R.drawable.curve_small_bg_white);
                }*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return serviceAvailableDates.size();
    }

    public interface DateAdapterListener {

        void onDateSelected(List<String> selectedTimeSlots);
    }
}