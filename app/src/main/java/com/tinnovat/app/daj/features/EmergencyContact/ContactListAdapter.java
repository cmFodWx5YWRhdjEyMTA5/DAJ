package com.tinnovat.app.daj.features.EmergencyContact;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.ContactResponseModel;
import com.tinnovat.app.daj.testing.TestActivity;

import retrofit2.Response;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyViewHolder> {

    private  Context mContext;
    private Response<ContactResponseModel> response;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView occupation;
        public TextView number;
        public LinearLayout layoutBackground;

        public MyViewHolder(View view) {
            super(view);
            name =  view.findViewById(R.id.name);
            occupation =  view.findViewById(R.id.occupation);
            number =  view.findViewById(R.id.number);
            layoutBackground =  view.findViewById(R.id.layoutBackground);
        }
    }


    public ContactListAdapter(Response<ContactResponseModel> response) {
        this.response = response;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.emergency_contact_row, parent, false);
        itemView.setOnClickListener(new TestActivity());
        mContext = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        /*Movie movie = moviesList.get(position);
        holder.title.setText("10Am - 11am");*/
        holder.name.setText(response.body().getContact().get(position).getName());
        holder.occupation.setText(response.body().getContact().get(position).getOccupation());
        holder.number.setText(response.body().getContact().get(position).getNumber());
        holder.name.setText(response.body().getContact().get(position).getName());

        if (position%2 == 0){
            holder.layoutBackground.setBackground(mContext.getResources().getDrawable(R.drawable.curve_small_bg_orange));

        }else {
            holder.layoutBackground.setBackground(mContext.getResources().getDrawable(R.drawable.curve_small_bg_blue));
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
        return response.body().getContact().size();
    }
}