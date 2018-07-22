package com.tinnovat.app.daj.testing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tinnovat.app.daj.R;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

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
        //public TextView title;

        public MyViewHolder(View view) {
            super(view);
           // title =  view.findViewById(R.id.title);
        }
    }


    public MoviesAdapter() {
       // this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);
        itemView.setOnClickListener(new TestActivity());
        mContext = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        /*Movie movie = moviesList.get(position);
        holder.title.setText("10Am - 11am");*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // holder.title.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}