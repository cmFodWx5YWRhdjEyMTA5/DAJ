package com.tinnovat.app.daj.features.futurePhase;

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
import com.tinnovat.app.daj.data.network.model.Futurephase;
import com.tinnovat.app.daj.features.futurePhase.FuturePhaseInfoFragment.OnListFragmentInteractionListener;

import java.util.List;

public class FuturePhaseRecyclerViewAdapter extends RecyclerView.Adapter<FuturePhaseRecyclerViewAdapter.ViewHolder> {

    private final List<Futurephase> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context mContext;

    public FuturePhaseRecyclerViewAdapter(List<Futurephase> items, Context context, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.services_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        if (holder.mItem.getPhaseImages().size() != 0)
            Picasso.get().load(holder.mItem.getPhaseImages().get(0).getImgPath())
                    .placeholder(ContextCompat.getDrawable(mContext, R.drawable.place_holder))
                    .into(holder.servicesImage);
        holder.serviceName.setText(holder.mItem.getPhaseName());
        holder.servicesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
//                Intent i = new Intent(mContext, FuturePhaseInfoDetailActivity.class);
//                i.putExtra("response", new Gson().toJson(response.body().getFuturephases().get(holder.getAdapterPosition())));
//
//                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView servicesImage;
        public TextView serviceName;
        public Futurephase mItem;

        public ViewHolder(View view) {
            super(view);
            servicesImage = view.findViewById(R.id.servicesImage);
            serviceName = view.findViewById(R.id.serviceName);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + serviceName.getText() + "'";
        }
    }
}
