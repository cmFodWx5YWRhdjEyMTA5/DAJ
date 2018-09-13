package com.tinnovat.app.daj.features.complaint;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.testing.TestActivity;

import java.util.List;

public class ComplaintCategoryAdapter extends RecyclerView.Adapter<ComplaintCategoryAdapter.MyViewHolder>  {

    private  Context mContext;
    private List<String> listItems;
    private List<Integer> catIds;
    private CategoryAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView category;

        public MyViewHolder(View view) {
            super(view);
            category =  view.findViewById(R.id.category);
        }
    }



    public ComplaintCategoryAdapter(List<String> listItems,List<Integer> catIds,CategoryAdapterListener listener) {
        this.listItems = listItems;
        this.listener = listener;
        this.catIds = catIds;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_row, parent, false);
        itemView.setOnClickListener(new TestActivity());
        mContext = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.category.setText(listItems.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.interactClick(listItems.get(holder.getAdapterPosition()),catIds.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public interface CategoryAdapterListener {
        void interactClick(String mCategory, int catId);
    }
}