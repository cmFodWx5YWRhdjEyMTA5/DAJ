package com.tinnovat.app.daj.features.bookings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.ServiceBooking;

import java.util.ArrayList;
import java.util.List;

public class MyBookingsAdapter extends RecyclerView.Adapter<MyBookingsAdapter.MyViewHolder> {

    private Context mContext;
    private SelectAdapterListener mListener;
    private List<Integer> selectedBookings = new ArrayList<>();
    private List<ServiceBooking> mServiceBookings = new ArrayList<>();
    private boolean mIsEnglish;
    private int mCount = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView time;
        public TextView serviceName;
        public LinearLayout linearLayout;
        public CheckBox checkBox;


        public MyViewHolder(View view) {
            super(view);
            time = view.findViewById(R.id.time);
            serviceName = view.findViewById(R.id.serviceName);
            linearLayout = view.findViewById(R.id.linearLayout);
            checkBox = view.findViewById(R.id.checkBox);
        }
    }


    public MyBookingsAdapter(List<ServiceBooking> serviceBookings,SelectAdapterListener mListener,boolean isEnglish) {
        this.mServiceBookings = serviceBookings;
        this.mListener = mListener;
        this.mIsEnglish = isEnglish;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_bookings_row, parent, false);
        mContext = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        String times = "";

        if (mIsEnglish){
            holder.time.setGravity(Gravity.START);
        }else {
            holder.time.setGravity(Gravity.END);
        }

            holder.itemView.setVisibility(View.VISIBLE);
            holder.serviceName.setText(mServiceBookings.get(position).getService());
            if (mServiceBookings.get(position).getTimeSlots().size() != 0){
                for (int i = 0 ; i< mServiceBookings.get(position).getTimeSlots().size();i++){
                    if (i == 0){
                        //Todo change
                        times = times+mServiceBookings.get(position).getTimeSlots().get(i).getSlots();
                    }else{
                    times = times+"\n"+mServiceBookings.get(position).getTimeSlots().get(i).getSlots();
                    }

                }
                holder.time.setText(times);
            }


        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    selectedBookings.add(mServiceBookings.get(holder.getAdapterPosition()).getId());
                    mListener.onBookingSelected(selectedBookings);
                }else if (selectedBookings.contains(mServiceBookings.get(holder.getAdapterPosition()).getId())) {
                    selectedBookings.remove(mServiceBookings.get(holder.getAdapterPosition()).getId());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mServiceBookings.size();
    }

    public interface SelectAdapterListener {

        void onBookingSelected(List<Integer> selectedBookings );
    }
}