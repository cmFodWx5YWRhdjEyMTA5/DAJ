package com.tinnovat.app.daj.features.bookings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.MyServiceBookingResponseModel;

public class MyBookingsAdapter extends RecyclerView.Adapter<MyBookingsAdapter.MyViewHolder> {

    private Context mContext;

    private MyServiceBookingResponseModel response;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView time;
        public TextView serviceName;


        public MyViewHolder(View view) {
            super(view);
            time = view.findViewById(R.id.time);
            serviceName = view.findViewById(R.id.serviceName);
        }
    }


    public MyBookingsAdapter(MyServiceBookingResponseModel responseModel) {
        this.response = responseModel;
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


       // CommonUtils.getInstance().getMonthWithYear(response.getServiceBooking().get(position).getServiceBookingDate().getDate();

        holder.serviceName.setText(response.getServiceBooking().get(position).getServiceCategory());
        holder.time.setText("");

    }

    @Override
    public int getItemCount() {
        return response.getServiceBooking().size();
    }
}