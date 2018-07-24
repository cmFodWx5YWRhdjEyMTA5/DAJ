package com.tinnovat.app.daj.features.complaint;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.testing.TestActivity;

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.MyViewHolder> {

    private ImageAdapterListener mListener;
    private Context mContext;
    private ArrayList<Bitmap> data;

    public void setData(ArrayList<Bitmap> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;


        public MyViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image_view);
        }
    }


    public ImagesAdapter(Context context, ImageAdapterListener listener ) {
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_image_item, parent, false);
        itemView.setOnClickListener(new TestActivity());
        mContext = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Bitmap bitmap = data.get(position);
        holder.imageView.setImageBitmap(bitmap);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.interactClick(bitmap);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data != null ?  data.size() : 0;
    }

    public interface ImageAdapterListener {
        void interactClick(Bitmap bitmapImg);
    }
}