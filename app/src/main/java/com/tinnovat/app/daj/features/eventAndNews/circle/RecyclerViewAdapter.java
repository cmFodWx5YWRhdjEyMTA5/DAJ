package com.tinnovat.app.daj.features.eventAndNews.circle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tinnovat.app.daj.R;

import java.util.List;

/**
 * Adapter for recycler view.
 */

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Model> list;

    RecyclerViewAdapter(Context context, List<Model> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_row1, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position < 4){
            holder.event.setText("");
            Picasso.get().load("https://android--code.blogspot.com/2015/08/android-listview-disable-item").into(holder.img);
         //   holder.img.setVisibility(View.GONE);
        }else {
            holder.img.setVisibility(View.VISIBLE);
            holder.event.setText(list.get(position - 4).getEvent());
        }
    }

    @Override
    public int getItemCount() {
        return list.size()+4;
       // return Integer.MAX_VALUE;
    }

    /**
     * View Holder for recycler view.
     */

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView event;
        ImageView img;

        MyViewHolder(View itemView) {
            super(itemView);
            event = itemView.findViewById(R.id.event);
            img = itemView.findViewById(R.id.img);
        }
    }
}
