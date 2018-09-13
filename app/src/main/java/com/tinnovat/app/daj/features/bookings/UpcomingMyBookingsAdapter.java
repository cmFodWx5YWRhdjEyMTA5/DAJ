package com.tinnovat.app.daj.features.bookings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.ServiceBooking;

import java.util.ArrayList;
import java.util.List;

public class UpcomingMyBookingsAdapter extends RecyclerView.Adapter<UpcomingMyBookingsAdapter.MyViewHolder> {

    private Context mContext;
    private List<ServiceBooking> mServiceBookings = new ArrayList<>();
    private DeleteEventListener mDeleteEventListener;
    //private SelectAdapterListener mSelectAdapterListener;
    private boolean mIsEnglish;
    private int mCount = 0;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView time;
        public TextView date;
        public TextView serviceName;
        public LinearLayout relativeLayout;
        public ImageView delete;




        public MyViewHolder(View view) {
            super(view);
            time = view.findViewById(R.id.time1);
            date = view.findViewById(R.id.startDate);
            serviceName = view.findViewById(R.id.title1);
            relativeLayout = view.findViewById(R.id.relativeLayout);
            delete = view.findViewById(R.id.delete1);
        }
    }


    public UpcomingMyBookingsAdapter(List<ServiceBooking> serviceBookings, DeleteEventListener deleteEventListener, boolean isEnglish) {
        this.mServiceBookings = serviceBookings;
        this.mDeleteEventListener = deleteEventListener;
        //this.mSelectAdapterListener = selectAdapterListener;
        this.mIsEnglish = isEnglish;
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
        String times = "";


       /* if ( !CommonUtils.getInstance().getDate2(CalendarDay.today().getCalendar())
                .equals(response.getServiceBooking().get(position).getServiceBookingDate()) ){*/
            if (mIsEnglish){
                holder.time.setGravity(Gravity.START);
            }else {
                holder.time.setGravity(Gravity.END);
            }

            holder.itemView.setVisibility(View.VISIBLE);
            holder.serviceName.setText(mServiceBookings.get(position).getService());
            holder.time.setText("");
            holder.relativeLayout.setVisibility(View.VISIBLE);
            holder.date.setText(mServiceBookings.get(position).getServiceBookingDate());

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
        /*}else {
            holder.relativeLayout.setVisibility(View.GONE);
        }*/

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> selectedBookings = new ArrayList<>();
                selectedBookings.add(mServiceBookings.get(holder.getAdapterPosition()).getId());
                mDeleteEventListener.onDeleteItemSelected(selectedBookings);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mServiceBookings.size();
    }

    public interface DeleteEventListener {

        void onDeleteItemSelected(List<Integer> selectedItems);
    }
}