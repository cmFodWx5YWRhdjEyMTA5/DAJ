package com.tinnovat.app.daj.features.services;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.testing.Movie;
import com.tinnovat.app.daj.testing.TestActivity;

import java.util.List;

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



    private List<Movie> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cat_name;
        public RelativeLayout rowBackBg;
        public LinearLayout rowBackBgDesign;
        public ImageView  circle;

        public MyViewHolder(View view) {
            super(view);
            cat_name =  view.findViewById(R.id.cat_name);
            rowBackBg =  view.findViewById(R.id.hall);
            rowBackBgDesign =  view.findViewById(R.id.rowBackBgDesign);
            circle =  view.findViewById(R.id.circle);
        }
    }


    public ServicesAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
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
        Movie movie = moviesList.get(position);
        holder.cat_name.setText("10Am - 11am");
        if (position%2 == 0){
            holder.rowBackBg.setBackground(mContext.getResources().getDrawable(R.drawable.curve_small_bg_green));
            holder.rowBackBgDesign.setBackground(mContext.getResources().getDrawable(R.drawable.green_bg));
            holder.circle.setImageResource(R.drawable.green_circle);
        }else {
            holder.rowBackBg.setBackground(mContext.getResources().getDrawable(R.drawable.curve_small_bg_golden));
            holder.rowBackBgDesign.setBackground(mContext.getResources().getDrawable(R.drawable.golden_bg));
            holder.circle.setImageResource(R.drawable.golden_circle);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.cat_name.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
            }
        });

    }

    @Override
    public int getItemCount() {
       /// return moviesList.size();
        return 5;
    }
}