package com.tinnovat.app.daj.features.foodAndTaxi;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.FoodResponseModel;

import retrofit2.Response;


public class OrderAdapter extends BaseAdapter {
    private Context mContext;
    private Response<FoodResponseModel> foodResponseModel;

    // Constructor
     OrderAdapter(Context context, Response<FoodResponseModel> foodResponseModel) {
        mContext = context;
        this.foodResponseModel= foodResponseModel;
    }

    public int getCount() {
        return foodResponseModel.body().getData().size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.order_row, null);
        }

        ImageView icon =convertView.findViewById(R.id.icon);
        Picasso.get().load(foodResponseModel.body().getData().get(position).getAppIcon()).into(icon);
        final String url = foodResponseModel.body().getData().get(position).getUrl();
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                mContext.startActivity(followIntent);
            }
        });

        return convertView;
    }




}

