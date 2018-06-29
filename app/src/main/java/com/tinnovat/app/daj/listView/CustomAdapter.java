package com.tinnovat.app.daj.listView;

import android.content.Context;
import android.graphics.drawable.ScaleDrawable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tinnovat.app.daj.R;

import java.util.ArrayList;


/**
 * Created by anupamchugh on 09/02/16.
 */
public class CustomAdapter extends ArrayAdapter<DataModel> implements View.OnClickListener{

    private ArrayList<DataModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
       // TextView txtType;
        RelativeLayout row;
        ImageView icon;
    }



    public CustomAdapter(ArrayList<DataModel> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }


    @Override
    public void onClick(View v) {


        int position=(Integer) v.getTag();
        Object object= getItem(position);
        DataModel dataModel=(DataModel)object;




       /* switch (v.getId())
        {

            case R.id.item_info:

                Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();

                break;


        }*/


    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.txtName =  convertView.findViewById(R.id.name);
            viewHolder.icon =  convertView.findViewById(R.id.icon);
            viewHolder.row =  convertView.findViewById(R.id.row);
          /*  viewHolder.txtVersion = (TextView) convertView.findViewById(R.id.version_number);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);*/

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

       /* Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition)
                ? R.anim.up_from_bottom : R.anim.down_from_top);*/
      //  result.startAnimation(animation);


        lastPosition = position;
       // LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //params.setMargins(100,10,10,10);


        RelativeLayout.LayoutParams par=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        /*viewHolder.icon = new ScaleDrawable(viewHolder.icon, 0, 50, 50).getDrawable();
        viewHolder.icon.setBounds(50,50);*/
       // viewHolder.icon.setMaxWidth(2);



      /*  LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(50,50);
        viewHolder.icon.setLayoutParams(parms);*/
        int[] images = new int[]{R.drawable.cooking, R.drawable.family, R.drawable.festival, R.drawable.play, R.drawable.tennis_bat, R.drawable.wedding};
        String[] text = new String[]{"COOKING COMPETITION", "FAMILY MEETING EVENT", "FESTIVAL EVENTS", "CHILDREN'S PLAY EVENT", "SPORTS EVENT", "WEDDING EVENT"};
        viewHolder.icon.setImageResource(images[position % images.length]);
        viewHolder.txtName.setText(text[position % text.length]);

        if (position == 0) {
            par.setMargins(0,100, 0, 10);
           // viewHolder.icon.setImageResource(R.drawable.cooking);
           // viewHolder.txtName.setText("pos ="+position);
            viewHolder.row.setLayoutParams(par);
        }else if (position == 1) {
            par.setMargins(0,100, 250, 10);
          //  viewHolder.icon.setImageResource(R.drawable.family);
            // viewHolder.txtName.setText("pos ="+position);
            viewHolder.row.setLayoutParams(par);
        }else if (position == 2) {
            par.setMargins(0,100, 450, 10);
           // viewHolder.icon.setImageResource(R.drawable.festival);
            // viewHolder.txtName.setText("pos ="+position);
            viewHolder.row.setLayoutParams(par);
        }else if (position == 3) {
            par.setMargins(0,100, 450, 10);
            //viewHolder.icon.setImageResource(R.drawable.play);
            // viewHolder.txtName.setText("pos ="+position);
            viewHolder.row.setLayoutParams(par);
        }else if (position == 4) {
            par.setMargins(0,100, 250, 10);
          //  viewHolder.icon.setImageResource(R.drawable.tennis_bat);
            // viewHolder.txtName.setText("pos ="+position);
            viewHolder.row.setLayoutParams(par);
        }else if (position == 5) {
            par.setMargins(0,0, 0, 100);
           // viewHolder.icon.setImageResource(R.drawable.wedding);
            // viewHolder.txtName.setText("pos ="+position);
            viewHolder.row.setLayoutParams(par);
        }/*else {
            viewHolder.txtName.setText("pos ="+position);
        }*/
       /* viewHolder.txtVersion.setText(dataModel.getVersion_number());
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);*/
        // Return the completed view to render on screen
        return convertView;
    }


}
