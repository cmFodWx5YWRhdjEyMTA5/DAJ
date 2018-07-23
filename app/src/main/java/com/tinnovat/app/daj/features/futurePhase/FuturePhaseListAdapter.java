package com.tinnovat.app.daj.features.futurePhase;

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
import com.tinnovat.app.daj.data.network.model.FuturePhasesResponseModel;
import com.tinnovat.app.daj.data.network.model.Futurephase;
import com.tinnovat.app.daj.data.network.model.Service;
import com.tinnovat.app.daj.features.services.ServiceBookingActivity;
import com.tinnovat.app.daj.testing.TestActivity;

import retrofit2.Response;

public class FuturePhaseListAdapter extends RecyclerView.Adapter<FuturePhaseListAdapter.MyViewHolder> {

    private Context mContext;
    private Response<FuturePhasesResponseModel> response;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView servicesImage;
        public TextView serviceName;


        public MyViewHolder(View view) {
            super(view);
            servicesImage = view.findViewById(R.id.servicesImage);
            serviceName = view.findViewById(R.id.serviceName);
        }
    }


    public FuturePhaseListAdapter(Response<FuturePhasesResponseModel> res) {
        this.response = res;
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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if (response.body().getFuturephases().get(position).getPhaseImages().size() != 0)
            Picasso.get().load(response.body().getFuturephases().get(position).getPhaseImages().get(0).getImgPath()).into(holder.servicesImage);
        holder.serviceName.setText(response.body().getFuturephases().get(position).getPhaseName());


        holder.servicesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, FuturePhaseInfoDetailActivity.class);
                i.putExtra("response", new Gson().toJson(response.body().getFuturephases().get(holder.getAdapterPosition())));

                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return response.body().getFuturephases().size();
    }
}