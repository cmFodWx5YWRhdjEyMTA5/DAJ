package com.tinnovat.app.daj.features.services;

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
import com.tinnovat.app.daj.data.network.model.Service;
import com.tinnovat.app.daj.data.network.model.ServicesResponseModel;
import com.tinnovat.app.daj.testing.TestActivity;

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
  private Service[] response;
  private int category_id;
  private Service service;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView servicesImage;
        public TextView serviceName;


        public MyViewHolder(View view) {
            super(view);
            servicesImage =  view.findViewById(R.id.servicesImage);
            serviceName =  view.findViewById(R.id.serviceName);
        }
    }


    public ServicesListAdapter(Service[] res , int category_id) {
        this.response = res;
        this.category_id = category_id;
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

                Picasso.get().load(response[position].getServiceImages()).into(holder.servicesImage);
                holder.serviceName.setText(response[position].getName());


     //   holder.response

        holder.servicesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ServiceBookingActivity.class);
                i.putExtra("category_id",category_id);
                i.putExtra("response",new Gson().toJson(response[holder.getAdapterPosition()]));
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return response.length;
    }
}