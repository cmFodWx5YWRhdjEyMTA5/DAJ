package com.tinnovat.app.daj.features.services;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.Service;
import com.tinnovat.app.daj.testing.TestActivity;

import java.util.List;

public class ServicesListAdapter extends RecyclerView.Adapter<ServicesListAdapter.MyViewHolder> {

    private final List<Service> mServiceList;
    private final ServicesSelectedCategoryFragment.OnListFragmentInteractionListener mListener;
    private Context mContext;

    //  private List<Movie> moviesList;
    private int mCategoryId;
    private Service service;

    public ServicesListAdapter(List<Service> services, int categoryId, Context context, ServicesSelectedCategoryFragment.OnListFragmentInteractionListener listener) {
        mServiceList = services;
        mContext = context;
        mListener = listener;
        mCategoryId = categoryId;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private Service mItem;
        private ImageView servicesImage;
        private TextView serviceName;


        public MyViewHolder(View view) {
            super(view);
            servicesImage = view.findViewById(R.id.servicesImage);
            serviceName = view.findViewById(R.id.serviceName);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.services_list_row, parent, false);
        itemView.setOnClickListener(new TestActivity());
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.mItem = mServiceList.get(position);
        Picasso.get().load(holder.mItem.getServiceImages())
                .placeholder(ContextCompat.getDrawable(mContext, R.drawable.place_holder))
                .into(holder.servicesImage);
        holder.serviceName.setText(holder.mItem.getName());


        //   holder.response

        holder.servicesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                   mListener.onServiceListFragmentInteraction(holder.mItem,mCategoryId);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mServiceList.size();
    }
}