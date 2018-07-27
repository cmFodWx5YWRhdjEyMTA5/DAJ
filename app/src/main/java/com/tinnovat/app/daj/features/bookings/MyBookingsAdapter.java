package com.tinnovat.app.daj.features.bookings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.MyServiceBookingResponseModel;
import com.tinnovat.app.daj.utils.CommonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyBookingsAdapter extends RecyclerView.Adapter<MyBookingsAdapter.MyViewHolder> {

    private Context mContext;
    private int size = 0;
    private MyServiceBookingResponseModel response;
    private String date;
    private SelectAdapterListener mListener;
    private List<Integer> selectedBookings = new ArrayList<>();;

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


    public MyBookingsAdapter(MyServiceBookingResponseModel responseModel,String date,SelectAdapterListener mListener) {
        this.response = responseModel;
        this.date = date;
        this.mListener = mListener;
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

        if ( date.equals(response.getServiceBooking().get(position).getServiceBookingDate()) ){
            holder.itemView.setVisibility(View.VISIBLE);
            holder.serviceName.setText(response.getServiceBooking().get(position).getServiceCategory());
            if (response.getServiceBooking().get(position).getTimeSlots().size() != 0){
                for (int i = 0 ; i< response.getServiceBooking().get(position).getTimeSlots().size();i++){
                    if (i == 0){
                        times = times+response.getServiceBooking().get(position).getTimeSlots().get(i).getSlots();
                    }else{
                    times = times+"\n"+response.getServiceBooking().get(position).getTimeSlots().get(i).getSlots();
                    }

                }
                holder.time.setText(times);
            }

            holder.linearLayout.setVisibility(View.VISIBLE);
        }else {
            holder.linearLayout.setVisibility(View.GONE);
        }

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    selectedBookings.add(response.getServiceBooking().get(holder.getAdapterPosition()).getId());
                    mListener.onBookingSelected(selectedBookings);
                }else if (selectedBookings.contains(response.getServiceBooking().get(holder.getAdapterPosition()).getId())) {
                    selectedBookings.remove(response.getServiceBooking().get(holder.getAdapterPosition()).getId());
                }
                /*if (selectedBookings == null){
                    selectedBookings = new ArrayList<>();
                    selectedBookings.add(response.getServiceBooking().get(holder.getAdapterPosition()).getId());
                }else if (selectedBookings.contains(response.getServiceBooking().get(holder.getAdapterPosition()).getId())) {
                    selectedBookings.remove(response.getServiceBooking().get(holder.getAdapterPosition()).getId());
                }else {
                    if (!selectedBookings.isEmpty()){
                        selectedBookings.add(response.getServiceBooking().get(holder.getAdapterPosition()).getId());
                    }else {
                        selectedBookings = new ArrayList<>();
                        selectedBookings.add(response.getServiceBooking().get(holder.getAdapterPosition()).getId());
                    }

                }*/
            }
        });


       // mListener.onBookingSelected(selectedBookings);
    }

    @Override
    public int getItemCount() {
        return response.getServiceBooking().size();
    }

    public interface SelectAdapterListener {

        void onBookingSelected(List<Integer> selectedBookings);
    }
}