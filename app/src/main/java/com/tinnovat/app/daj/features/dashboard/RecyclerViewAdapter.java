package com.tinnovat.app.daj.features.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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
    private boolean mIsTablet;
    private boolean mIsSmall;
    //  private Response<EventListModel> mResponse;

    RecyclerViewAdapter(Context context, List<String> list, boolean language, boolean isTablet, boolean isSmall) {
        this.context = context;
        this.list = list;
        this.mLanguage = language;
        this.mIsTablet = isTablet;
        this.mIsSmall = isSmall;
        //this.mResponse = response;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_row1, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.event.setJustificationMode(Layout.JUSTIFICATION_MODE_NONE);
        }

        if (mLanguage) {
            holder.event.setGravity(Gravity.END);
        } else {
            holder.event.setGravity(Gravity.START);
        }

        holder.event.setText(list.get(position));
        String pos = Integer.toString(position);

        holder.item.setText(pos);
                if (mLanguage) {
                    switch (position) {
                        //english

                        case 0:
                            holder.itemView.setVisibility(View.INVISIBLE);
                            holder.img.setVisibility(View.INVISIBLE);
                            holder.img.setImageResource(R.drawable.key);
                            break;
                        case 1:
                            holder.itemView.setVisibility(View.INVISIBLE);
                            holder.img.setVisibility(View.INVISIBLE);
                            holder.img.setImageResource(R.drawable.key);
                            break;
                        case 2:
                            holder.itemView.setVisibility(View.INVISIBLE);
                            holder.img.setVisibility(View.INVISIBLE);
                            holder.img.setImageResource(R.drawable.logout);
                            break;
                            case 3:
                            holder.itemView.setVisibility(View.INVISIBLE);
                            holder.img.setVisibility(View.INVISIBLE);
                            holder.img.setImageResource(R.drawable.logout);
                            break;
                        case 4:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_event_eng);
                            break;
                        case 5:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_service_eng);
                            break;

                        case 6:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_guest_eng);
                            break;
                       /* case 6:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_camer_eng);
                            break;*/
                        case 7:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_location_eng);
                            break;
                        case 8:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_projects_eng);
                            break;
                        case 9:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_calander_eng);
                            break;
                        case 10:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_profile_eng);
                            break;
                        case 11:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_food_eng);
                            break;
                        case 12:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_car_eng);
                            break;
                        case 13:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_complaint_eng);
                            break;
                        case 14:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_phone_eng);
                            break;
                        case 15:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_key_eng);
                            break;
                        case 16:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_lan_eng);
                            break;
                        case 17:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_logout_eng);
                            break;
                        case 18:
                            holder.itemView.setVisibility(View.INVISIBLE);
                            holder.img.setVisibility(View.INVISIBLE);
                            holder.img.setImageResource(R.drawable.key);
                            break;
                        case 19:
                            holder.itemView.setVisibility(View.INVISIBLE);
                            holder.img.setVisibility(View.INVISIBLE);
                            holder.img.setImageResource(R.drawable.logout);
                            break;
                        case 20:
                            holder.itemView.setVisibility(View.INVISIBLE);
                            holder.img.setVisibility(View.INVISIBLE);
                            holder.img.setImageResource(R.drawable.phone_golden);
                            break;
                        case 21:
                            holder.itemView.setVisibility(View.INVISIBLE);
                            holder.img.setVisibility(View.INVISIBLE);
                            holder.img.setImageResource(R.drawable.phone_golden);
                            break;
                            case 22:
                            holder.itemView.setVisibility(View.INVISIBLE);
                            holder.img.setVisibility(View.INVISIBLE);
                            holder.img.setImageResource(R.drawable.phone_golden);
                            break;

                    }
                } else {
                    //arabic
                    switch (position) {
                        case 0:
                            holder.itemView.setVisibility(View.INVISIBLE);
                            holder.img.setVisibility(View.INVISIBLE);
                            holder.img.setImageResource(R.drawable.phone_golden);
                            break;
                        case 1:
                            holder.itemView.setVisibility(View.INVISIBLE);
                            holder.img.setVisibility(View.INVISIBLE);
                            holder.img.setImageResource(R.drawable.key);
                            break;
                        case 2:
                            holder.itemView.setVisibility(View.INVISIBLE);
                            holder.itemView.setVisibility(View.INVISIBLE);
                            holder.img.setVisibility(View.INVISIBLE);
                            holder.img.setImageResource(R.drawable.logout);
                            break;
                            case 3:
                            holder.itemView.setVisibility(View.INVISIBLE);
                            holder.itemView.setVisibility(View.INVISIBLE);
                            holder.img.setVisibility(View.INVISIBLE);
                            holder.img.setImageResource(R.drawable.logout);
                            break;

                        case 4:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_event_arb);
                            break;
                        case 5:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_service_arb);
                            break;

                        case 6:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_guest_arb);
                            break;
                        /*case 6:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_camer_arb);
                            break;*/
                        case 7:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_location_arb);
                            break;
                        case 8:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_projects_arb);
                            break;
                        case 9:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_calander_arb);
                            break;
                        case 10:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_profile_arb);
                            break;
                        case 11:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_food_arb);
                            break;
                        case 12:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_car_arb);
                            break;
                        case 13:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_complaint_arb);
                            break;
                        case 14:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_phone_arb);
                            break;
                        case 15:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_key_arb);
                            break;
                        case 16:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_lan_arb);
                            break;
                        case 17:
                            holder.itemView.setVisibility(View.VISIBLE);
                            holder.img.setVisibility(View.VISIBLE);
                            holder.img.setImageResource(R.drawable.dash_logout_arb);
                            break;
                        case 18:
                            holder.itemView.setVisibility(View.INVISIBLE);
                            holder.img.setVisibility(View.INVISIBLE);
                            holder.img.setImageResource(R.drawable.key);
                            break;
                        case 19:
                            holder.itemView.setVisibility(View.INVISIBLE);
                            holder.img.setVisibility(View.INVISIBLE);
                            holder.img.setImageResource(R.drawable.logout);
                            break;
                        case 20:
                            holder.itemView.setVisibility(View.INVISIBLE);
                            holder.img.setVisibility(View.INVISIBLE);
                            holder.img.setImageResource(R.drawable.phone_golden);
                            break;

                    }
                }


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
