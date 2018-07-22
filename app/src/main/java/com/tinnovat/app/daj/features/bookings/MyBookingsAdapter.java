package com.tinnovat.app.daj.features.bookings;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.MyServiceBookingResponseModel;
import com.tinnovat.app.daj.data.network.model.Service;
import com.tinnovat.app.daj.features.services.ServiceBookingActivity;
import com.tinnovat.app.daj.testing.TestActivity;

public class MyBookingsAdapter extends RecyclerView.Adapter<MyBookingsAdapter.MyViewHolder> {

    private  Context mContext;


    /*@Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }*/

    /*public static Context getContext(){
        return mContext;
    }*/



  //  private List<Movie> moviesList;
  private MyServiceBookingResponseModel response;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView time;
        public TextView serviceName;


        public MyViewHolder(View view) {
            super(view);
            time =  view.findViewById(R.id.time);
            serviceName =  view.findViewById(R.id.serviceName);
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

            //    Picasso.get().load(response[position].getServiceImages()).into(holder.servicesImage);
                holder.serviceName.setText(response.getServiceBooking().get(position).getServiceCategory());
                holder.time.setText("");


     //   holder.response

       /* holder.servicesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ServiceBookingActivity.class);
                i.putExtra("category_id",category_id);
                i.putExtra("response",new Gson().toJson(response[holder.getAdapterPosition()]));
                mContext.startActivity(i);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return response.getServiceBooking().size();
    }
}