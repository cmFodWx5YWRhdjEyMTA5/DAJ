package com.tinnovat.app.daj.features.Complaint;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.ComplaintListResponseModel;
import com.tinnovat.app.daj.data.network.model.ContactResponseModel;
import com.tinnovat.app.daj.testing.TestActivity;
import com.tinnovat.app.daj.utils.CommonUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Response;

public class ComplaintListAdapter extends RecyclerView.Adapter<ComplaintListAdapter.MyViewHolder> {

    private Context mContext;
    private Response<ComplaintListResponseModel> response;


    public class MyViewHolder extends RecyclerView.ViewHolder {
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


    public ComplaintListAdapter(Response<ComplaintListResponseModel> response) {
        this.response = response;
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



        holder.day.setText(CommonUtils.getInstance().getDate(response.body().getComplaints().get(position).getSubmittedDate()));
       // holder.day.setText(response.body().getComplaints().get(position).getSubmittedDate());
        holder.month.setText(CommonUtils.getInstance().getMonth(response.body().getComplaints().get(position).getSubmittedDate()));
        holder.complaint.setText(response.body().getComplaints().get(position).getCategoryName());
        holder.complaintId.setText(String.format(mContext.getString(R.string.formatter) , response.body().getComplaints().get(position).getRegisterNo()));

        switch (response.body().getComplaints().get(position).getComplaintStatus()) {
            case 0:
                holder.status.setText(R.string.submitted);
                holder.status.setBackgroundColor(mContext.getResources().getColor(R.color.greenText));
                break;

            case 1:
                holder.status.setText(R.string.in_progress);
                holder.status.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
                break;

            case 2:
                holder.status.setText(R.string.completed);
                holder.status.setBackgroundColor(mContext.getResources().getColor(R.color.red));
                break;
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // holder.title.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
            }
        });

    }

    @Override
    public int getItemCount() {
        return response.body().getComplaints().size();
    }
}