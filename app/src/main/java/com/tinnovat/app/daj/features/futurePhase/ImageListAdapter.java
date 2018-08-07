package com.tinnovat.app.daj.features.futurePhase;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.Futurephase;
import com.tinnovat.app.daj.testing.TestActivity;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.MyViewHolder> {

    private Context mContext;
    private Futurephase futurephase;
    private ImagePopup imagePopup;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image1;

        public MyViewHolder(View view) {
            super(view);
            image1 = view.findViewById(R.id.image1);
        }
    }

    public ImageListAdapter(Futurephase futurephase) {
        this.futurephase = futurephase;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_row, parent, false);
        itemView.setOnClickListener(new TestActivity());
        mContext = parent.getContext();

        imagePopup = new ImagePopup(mContext);
        imagePopup.setBackgroundColor(Color.BLACK);
        imagePopup.setFullScreen(true);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {


        Picasso.get().load(futurephase.getPhaseImages().get(position).getImgPath()).into(holder.image1);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImageSliderActivity.class);
                intent.putExtra("currentPosition",holder.getAdapterPosition());
                intent.putExtra("futurephase", new Gson().toJson(futurephase));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return futurephase.getPhaseImages().size();
    }


}