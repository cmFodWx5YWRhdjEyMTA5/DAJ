package com.tinnovat.app.daj.features.services;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.ServiceCategory;
import com.tinnovat.app.daj.data.network.model.ServicesResponseModel;
import com.tinnovat.app.daj.testing.TestActivity;

import retrofit2.Response;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.MyViewHolder> {

    private  Context mContext;

    /*@Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }*/

    /*public static Context getContext(){
        return mContext;
    }*/



    private Response<ServicesResponseModel> response;
    private Boolean language;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cat_name;
        private RelativeLayout rowBackBg;
        public LinearLayout rowBackBgDesign;
        public ImageView  circle;
        public ImageView  icon;
        public ImageView  arcHall;

        private MyViewHolder(View view) {
            super(view);
            cat_name =  view.findViewById(R.id.cat_name);
            rowBackBg =  view.findViewById(R.id.hall);
            rowBackBgDesign =  view.findViewById(R.id.rowBackBgDesign);
            circle =  view.findViewById(R.id.circle);
            icon =  view.findViewById(R.id.icon);
            arcHall =  view.findViewById(R.id.arcHall);

        }
    }


    public ServicesAdapter(Response<ServicesResponseModel> response, Boolean language) {
        this.response = response;
        this.language = language;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.services_row, parent, false);
        itemView.setOnClickListener(new TestActivity());
        mContext = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final ServiceCategory category = response.body().getServiceCategory().get(position);
       // Movie movie = moviesList.get(position);
        Picasso.get().load(category.getIcon()).into(holder.icon);
        holder.cat_name.setText(category.getName());

        if (this.language){
            holder.arcHall.setImageResource(R.drawable.arc_left);
            holder.arcHall.setScaleType(ImageView.ScaleType.FIT_END);
        }else {
            holder.arcHall.setImageResource(R.drawable.arc_right);
            holder.arcHall.setScaleType(ImageView.ScaleType.FIT_START);
        }

        if (position%2 == 0){
            holder.rowBackBg.setBackground(mContext.getResources().getDrawable(R.drawable.curve_small_bg_green));
            holder.rowBackBgDesign.setBackground(mContext.getResources().getDrawable(R.drawable.green_bg));
            holder.circle.setImageResource(R.drawable.green_circle);
        }else {
            holder.rowBackBg.setBackground(mContext.getResources().getDrawable(R.drawable.curve_small_bg_golden));
            holder.rowBackBgDesign.setBackground(mContext.getResources().getDrawable(R.drawable.golden_bg));
            holder.circle.setImageResource(R.drawable.golden_circle);
        }


        holder.rowBackBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String res = new Gson().toJson(response.body());
              //  holder.cat_name.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
                Intent i = new Intent(mContext, ServiceListActivity.class);
                i.putExtra("response",res);
               /* i.putExtra("id",category.getName());
                Bundle bundle = new Bundle();
                bundle.putBundle("Name",response); //This is for a String
                i.setClass(CurrentClass.this, Class.class);
                i.putExtras(bundle);
                i.*/
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return response.body().getServiceCategory().size();
       // return 5;
    }
}