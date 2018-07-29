package com.tinnovat.app.daj.features.services;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.ServiceCategory;
import com.tinnovat.app.daj.testing.TestActivity;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.MyViewHolder> {

    private final ServicesMainCategoryFragment.OnCategoryListFragmentInteractionListener mListener;
    private Context mContext;
    private List<ServiceCategory> mDataList;
    private boolean mLanguage;

    public ServicesAdapter(List<ServiceCategory> data, Context context, ServicesMainCategoryFragment.OnCategoryListFragmentInteractionListener listener, boolean language) {
        mDataList = data;
        mContext = context;
        mListener = listener;
        mLanguage = language;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ServiceCategory mItem;
        private TextView cat_name;
        private RelativeLayout rowBackBg;
        private LinearLayout rowBackBgDesign;
        private ImageView circle;
        private ImageView icon;
        private ImageView arcHall;

        private MyViewHolder(View view) {
            super(view);
            cat_name = view.findViewById(R.id.cat_name);
            rowBackBg = view.findViewById(R.id.hall);
            rowBackBgDesign = view.findViewById(R.id.rowBackBgDesign);
            circle = view.findViewById(R.id.circle);
            icon = view.findViewById(R.id.icon);
            arcHall = view.findViewById(R.id.arcHall);

        }
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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        holder.mItem = mDataList.get(position);
        // Movie movie = moviesList.get(position);
        Picasso.get().load(holder.mItem.getIcon())
                .placeholder(ContextCompat.getDrawable(mContext, R.drawable.ic_photo_place_holder))
                .into(holder.icon);
        holder.cat_name.setText(holder.mItem.getName());

        if (mLanguage) {
            holder.arcHall.setImageResource(R.drawable.arc_left);
            holder.arcHall.setScaleType(ImageView.ScaleType.FIT_END);
        } else {
            holder.arcHall.setImageResource(R.drawable.arc_right);
            holder.arcHall.setScaleType(ImageView.ScaleType.FIT_START);
        }

        if (position % 2 == 0) {
            holder.rowBackBg.setBackground(mContext.getResources().getDrawable(R.drawable.curve_small_bg_green));
            holder.rowBackBgDesign.setBackground(mContext.getResources().getDrawable(R.drawable.green_bg));
            holder.circle.setImageResource(R.drawable.green_circle);
        } else {
            holder.rowBackBg.setBackground(mContext.getResources().getDrawable(R.drawable.curve_small_bg_golden));
            holder.rowBackBgDesign.setBackground(mContext.getResources().getDrawable(R.drawable.golden_bg));
            holder.circle.setImageResource(R.drawable.golden_circle);
        }


        holder.rowBackBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onCategoryListFragmentInteraction(holder.mItem);
                }
                //  holder.cat_name.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
        // return 5;
    }
}