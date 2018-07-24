package com.tinnovat.app.daj.features.bookings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.MyServiceBookingResponseModel;
import com.tinnovat.app.daj.utils.CommonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class MyBookingsAdapter extends RecyclerView.Adapter<MyBookingsAdapter.MyViewHolder> {

    private Context mContext;
    private int size = 0;
    private MyServiceBookingResponseModel response;
    private String date;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView time;
        public TextView serviceName;
        public LinearLayout linearLayout;


        public MyViewHolder(View view) {
            super(view);
            time = view.findViewById(R.id.time);
            serviceName = view.findViewById(R.id.serviceName);
            linearLayout = view.findViewById(R.id.linearLayout);
        }
    }


    public MyBookingsAdapter(MyServiceBookingResponseModel responseModel,String date) {
        this.response = responseModel;
        this.date = date;
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

        if ( date.equals(response.getServiceBooking().get(position).getServiceBookingDate()) ){
            holder.itemView.setVisibility(View.VISIBLE);
            holder.serviceName.setText(response.getServiceBooking().get(position).getServiceCategory());
            holder.time.setText("");
            holder.linearLayout.setVisibility(View.VISIBLE);
        }else {
            holder.linearLayout.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return response.getServiceBooking().size();
    }
}