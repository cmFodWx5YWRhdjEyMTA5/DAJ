package com.tinnovat.app.daj.features.bookings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.MyServiceBookingResponseModel;
import com.tinnovat.app.daj.utils.CommonUtils;

public class UpcomingMyBookingsAdapter extends RecyclerView.Adapter<UpcomingMyBookingsAdapter.MyViewHolder> {

    private Context mContext;
    private MyServiceBookingResponseModel response;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView time;
        public TextView date;
        public TextView serviceName;
        public RelativeLayout relativeLayout;



        public MyViewHolder(View view) {
            super(view);
            time = view.findViewById(R.id.time1);
            date = view.findViewById(R.id.date);
            serviceName = view.findViewById(R.id.title1);
            relativeLayout = view.findViewById(R.id.relativeLayout);
        }
    }


    public UpcomingMyBookingsAdapter(MyServiceBookingResponseModel responseModel) {
        this.response = responseModel;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.upcoming_booking_row, parent, false);
        mContext = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        if ( !CommonUtils.getInstance().getDate2(CalendarDay.today().getCalendar())
                .equals(response.getServiceBooking().get(position).getServiceBookingDate()) ){
            holder.itemView.setVisibility(View.VISIBLE);
            holder.serviceName.setText(response.getServiceBooking().get(position).getServiceCategory());
            holder.time.setText("");
            holder.relativeLayout.setVisibility(View.VISIBLE);
        }else {
            holder.relativeLayout.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return response.getServiceBooking().size();
    }
}