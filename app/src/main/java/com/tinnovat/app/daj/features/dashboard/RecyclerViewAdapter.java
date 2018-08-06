package com.tinnovat.app.daj.features.dashboard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
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
    private List<String> list;
    private boolean mLanguage;
    //  private Response<EventListModel> mResponse;

    RecyclerViewAdapter(Context context, List<String> list, boolean language) {
        this.context = context;
        this.list = list;
        this.mLanguage = language;
        //this.mResponse = response;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_row1, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        if (mLanguage) {
            holder.event.setGravity(Gravity.END);
        } else {
            holder.event.setGravity(Gravity.START);
        }

        holder.event.setText(list.get(position));
        String pos = Integer.toString(position);

        holder.item.setText(pos);

        switch (position) {
            case 0:
                holder.img.setVisibility(View.INVISIBLE);
                holder.img.setImageResource(R.drawable.phone_golden);
                break;
            case 1:
                holder.img.setVisibility(View.INVISIBLE);
                holder.img.setImageResource(R.drawable.key);
                break;
            case 2:
                holder.img.setVisibility(View.INVISIBLE);
                holder.img.setImageResource(R.drawable.logout);
                break;
            case 3:
                holder.img.setVisibility(View.VISIBLE);
                holder.img.setImageResource(R.drawable.event);
                break;
            case 4:
                holder.img.setVisibility(View.VISIBLE);
                holder.img.setImageResource(R.drawable.service);
                break;

            case 5:
                holder.img.setVisibility(View.VISIBLE);
                holder.img.setImageResource(R.drawable.guest);
                break;
            case 6:
                holder.img.setVisibility(View.VISIBLE);
                holder.img.setImageResource(R.drawable.camera);
                break;
            case 7:
                holder.img.setVisibility(View.VISIBLE);
                holder.img.setImageResource(R.drawable.navigation);
                break;
            case 8:
                holder.img.setVisibility(View.VISIBLE);
                holder.img.setImageResource(R.drawable.projects);
                break;
            case 9:
                holder.img.setVisibility(View.VISIBLE);
                holder.img.setImageResource(R.drawable.my_booking);
                break;
            case 10:
                holder.img.setVisibility(View.VISIBLE);
                holder.img.setImageResource(R.drawable.profile);
                break;
            case 11:
                holder.img.setVisibility(View.VISIBLE);
                holder.img.setImageResource(R.drawable.food);
                break;
            case 12:
                holder.img.setVisibility(View.VISIBLE);
                holder.img.setImageResource(R.drawable.car);
                break;
            case 13:
                holder.img.setVisibility(View.VISIBLE);
                holder.img.setVisibility(View.VISIBLE);
                holder.img.setImageResource(R.drawable.complaint);
                break;
            case 14:
                holder.img.setVisibility(View.VISIBLE);
                holder.img.setImageResource(R.drawable.phone_golden);
                break;
            case 15:
                holder.img.setVisibility(View.VISIBLE);
                holder.img.setImageResource(R.drawable.key);
                break;
            case 16:
                holder.img.setVisibility(View.VISIBLE);
                holder.img.setImageResource(R.drawable.language);
                break;
            case 17:
                holder.img.setVisibility(View.VISIBLE);
                holder.img.setImageResource(R.drawable.logout);
                break;
            case 18:
                holder.img.setVisibility(View.INVISIBLE);
                holder.img.setImageResource(R.drawable.key);
                break;
            case 19:
                holder.img.setVisibility(View.INVISIBLE);
                holder.img.setImageResource(R.drawable.logout);
                break;
            case 20:
                holder.img.setVisibility(View.INVISIBLE);
                holder.img.setImageResource(R.drawable.phone_golden);
                break;

        }

      /*  holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EventNewsActivity.class);
                i.putExtra("response",new Gson().toJson(mResponse.body().getCategory().get(holder.getAdapterPosition()).getEvents()));
                context.startActivity(i);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
        // return Integer.MAX_VALUE;
    }

    /**
     * View Holder for recycler view.
     */

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView event;
        TextView item;
        ImageView img;

        MyViewHolder(View itemView) {
            super(itemView);
            event = itemView.findViewById(R.id.event);
            img = itemView.findViewById(R.id.img);
            item = itemView.findViewById(R.id.item);
        }
    }
}
