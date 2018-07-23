package com.tinnovat.app.daj.features.bookings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.testing.TestActivity;

import java.util.List;

public class GuestTimeSlotAdapter extends RecyclerView.Adapter<GuestTimeSlotAdapter.MyViewHolder> {

    private Context mContext;
    private DateAdapterListener mListener;
    private List<Integer> selectedDates;
    private int timeSlot = 0;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date;

        public MyViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.date);
        }
    }

    public GuestTimeSlotAdapter(Context context, DateAdapterListener listener) {
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
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (position < 12) {

            holder.date.setText(String.format(mContext.getString(R.string.formatter_number), position + 1, mContext.getString(R.string.am)));
        } else {
            holder.date.setText(String.format(mContext.getString(R.string.formatter_number), position -11, mContext.getString(R.string.pm)));

        }

        if (timeSlot == position+1){
            holder.date.setBackgroundResource(R.drawable.curve_small_bg_orange);
        }else {
            holder.date.setBackgroundResource(R.drawable.curve_small_bg_white);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (timeSlot == 0){
                    timeSlot = holder.getAdapterPosition()+1;
                    holder.date.setBackgroundResource(R.drawable.curve_small_bg_orange);
                }else if (timeSlot == holder.getAdapterPosition()+1){
                    timeSlot = 0;
                    holder.date.setBackgroundResource(R.drawable.curve_small_bg_white);
                }else {
                    timeSlot = holder.getAdapterPosition()+1;
                    holder.date.setBackgroundResource(R.drawable.curve_small_bg_orange);
                }

                mListener.onDateSelected(timeSlot);
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return 24;
    }

    public interface DateAdapterListener {

        void onDateSelected(int selectedTimeSlots);
    }
}