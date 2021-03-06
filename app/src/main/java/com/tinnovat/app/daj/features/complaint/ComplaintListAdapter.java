package com.tinnovat.app.daj.features.complaint;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.ComplaintList;
import com.tinnovat.app.daj.testing.TestActivity;
import com.tinnovat.app.daj.utils.CommonUtils;

import java.util.List;
import java.util.Locale;

public class ComplaintListAdapter extends RecyclerView.Adapter<ComplaintListAdapter.MyViewHolder> {

    private final List<ComplaintList> mComplaintList;
    private final ComplaintListFragment.OnFragmentInteractionListener mListener;
    private Context mContext;

    public ComplaintListAdapter(List<ComplaintList> responseList, ComplaintListFragment.OnFragmentInteractionListener listener) {
        mComplaintList = responseList;
        mListener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ComplaintList complaintListItem;
        public View line;
        public TextView day;
        public TextView month;
        public TextView complaint;
        public TextView complaintId;
        public TextView status;


        public MyViewHolder(View view) {
            super(view);
            line = view.findViewById(R.id.line1);
            day = view.findViewById(R.id.day);
            month = view.findViewById(R.id.month);
            complaint = view.findViewById(R.id.complaint);
            complaintId = view.findViewById(R.id.complaintId);
            status = view.findViewById(R.id.status);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.complaint_list_row, parent, false);
        itemView.setOnClickListener(new TestActivity());
        mContext = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        int size = (mComplaintList.size()-1)-position;

        holder.complaintListItem = mComplaintList.get(size);

        Locale.setDefault(Locale.US);
        holder.day.setText(CommonUtils.getInstance().getDate(holder.complaintListItem.getSubmittedDate(), true));
        holder.month.setText(CommonUtils.getInstance().getDate(holder.complaintListItem.getSubmittedDate(), false));
        holder.complaint.setText(holder.complaintListItem.getCategoryName());
        holder.complaintId.setText(String.format(mContext.getString(R.string.formatter), holder.complaintListItem.getRegisterNo()));

        switch (holder.complaintListItem.getComplaintStatus()) {
            case 0:
                holder.status.setText(R.string.submitted);
                holder.status.setBackgroundResource(R.drawable.curve_small_bg_red);
                break;

            case 1:
                holder.status.setText(R.string.assigned);
                holder.status.setBackgroundResource(R.drawable.curve_small_bg_blue);
                break;

                case 2:
                holder.status.setText(R.string.in_progress);
                holder.status.setBackgroundResource(R.drawable.curve_small_bg_orange);
                break;

            case 3:
                holder.status.setText(R.string.completed);
                holder.status.setBackgroundResource(R.drawable.curve_small_bg_green);
                break;
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListItemClickListener(holder.complaintListItem);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mComplaintList.size();
    }
}