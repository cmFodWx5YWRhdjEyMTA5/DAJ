package com.tinnovat.app.daj.features.complaint;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.ComplaintList;
import com.tinnovat.app.daj.testing.TestActivity;

public class StatusListAdapter extends RecyclerView.Adapter<StatusListAdapter.MyViewHolder> {

    private final ComplaintDetailFragment.OnFragmentInteractionListener mListener;
    private  Context mContext;

    private ComplaintList responseData;
    private boolean mLanguage;

    public StatusListAdapter(ComplaintList complaintList, ComplaintDetailFragment.OnFragmentInteractionListener listener,boolean language) {

        responseData = complaintList;
        mListener = listener;
        mLanguage = language;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView headingSubmitted;
        public TextView logNote;
        public TextView submittedDate;
        public View lineGreen;

        public MyViewHolder(View view) {
            super(view);
            headingSubmitted =  view.findViewById(R.id.headingSubmitted);
            logNote =  view.findViewById(R.id.logNote);
            submittedDate =  view.findViewById(R.id.submittedDate);
            lineGreen =  view.findViewById(R.id.lineGreen);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.status_row, parent, false);
        itemView.setOnClickListener(new TestActivity());
        mContext = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (mLanguage){
            holder.logNote.setGravity(Gravity.START);
        }else {
            holder.logNote.setGravity(Gravity.END);
        }

        switch (responseData.getLogDetails().get(position).getComplaintStatus()) {
            case 0:
                holder.headingSubmitted.setText(R.string.submitted);
                holder.lineGreen.setBackgroundColor(mContext.getResources().getColor(R.color.red));
                break;

            case 1:
                holder.headingSubmitted.setText(R.string.in_progress);
                holder.lineGreen.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
                break;

            case 2:
                holder.headingSubmitted.setText(R.string.completed);
                holder.lineGreen.setBackgroundColor(mContext.getResources().getColor(R.color.greenText));
                break;
        }
        if (responseData.getLogDetails().get(position).getComplaintsLogNote() != null)
        holder.logNote.setText(responseData.getLogDetails().get(position).getComplaintsLogNote().toString());
        holder.submittedDate.setText(responseData.getLogDetails().get(position).getSubmittedDate());

    }

    @Override
    public int getItemCount() {
        return responseData.getLogDetails().size();
    }
}