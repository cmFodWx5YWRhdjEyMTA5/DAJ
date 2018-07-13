package com.tinnovat.app.daj.features.services;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tinnovat.app.daj.Activity.ServiceBookingActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.ServicesResponseModel;
import com.tinnovat.app.daj.testing.Movie;
import com.tinnovat.app.daj.testing.TestActivity;

import java.util.List;

import retrofit2.Response;

public class ServicesListAdapter extends RecyclerView.Adapter<ServicesListAdapter.MyViewHolder> {

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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView servicesImage;
        public TextView serviceName;


        public MyViewHolder(View view) {
            super(view);
            servicesImage =  view.findViewById(R.id.servicesImage);
            serviceName =  view.findViewById(R.id.serviceName);
        }
    }


    public ServicesListAdapter() {
       // this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.services_list_row, parent, false);
        itemView.setOnClickListener(new TestActivity());
        mContext = parent.getContext();


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //Movie movie = moviesList.get(position);
        Picasso.get().load("http://5.2.89.2/Dar_alJewar/public/storage/services_images/dsDEeBTmoR8Va7pediW52i233h7GIZt5O3bkqf9C.jpeg").into(holder.servicesImage);
        holder.serviceName.setText("10Am - 11am");

     //   holder.response

        holder.servicesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ServiceBookingActivity.class);
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}