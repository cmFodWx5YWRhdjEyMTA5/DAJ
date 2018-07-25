package com.tinnovat.app.daj.features.eventAndNews.circle;

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
import com.tinnovat.app.daj.data.network.model.EventListModel;
import com.tinnovat.app.daj.features.eventAndNews.EventNewsActivity;

import java.util.List;

import retrofit2.Response;

/**
 * Adapter for recycler view.
 */

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Model> list;
    private Response<EventListModel> mResponse;

    RecyclerViewAdapter(Context context, List<Model> list, Response<EventListModel> response) {
        this.context = context;
        this.list = list;
        this.mResponse = response;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_row1, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (position < 4){
            holder.event.setText("");
            Picasso.get().load("https://android--code.blogspot.com/2015/08/android-listview-disable-item").into(holder.img);
         //   holder.img.setVisibility(View.GONE);
        }else {
            holder.img.setVisibility(View.VISIBLE);
            holder.event.setText(list.get(position - 4).getEvent());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EventNewsActivity.class);
                i.putExtra("response",new Gson().toJson(mResponse.body().getCategory().get(holder.getAdapterPosition()).getEvents()));
                context.startActivity(i);
            }
        });
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
