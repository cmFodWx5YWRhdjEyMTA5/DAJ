package com.tinnovat.app.daj.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tinnovat.app.daj.R;

import java.util.ArrayList;

public class CustomListAdapterDialog extends BaseAdapter {

    private ArrayList<ItemClass> listData;

    private LayoutInflater layoutInflater;

    public CustomListAdapterDialog(Context context, ArrayList<ItemClass> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.row_item, null);
            holder = new ViewHolder();
            holder.unitView = (TextView) convertView.findViewById(R.id.name);
           // holder.quantityView = (TextView) convertView.findViewById(R.id.quantity);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

      /*  holder.unitView.setText(listData.get(position).getVariant().toString());
        holder.quantityView.setText(listData.get(position).getUnit().toString());*/

        return convertView;
    }

    static class ViewHolder {
        TextView unitView;
        TextView quantityView;
    }

    private class ItemClass {
    }
}