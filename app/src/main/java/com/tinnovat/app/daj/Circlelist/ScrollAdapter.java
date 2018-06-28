package com.tinnovat.app.daj.Circlelist;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tinnovat.app.daj.R;

import java.util.List;

/**
 * Created by Anjali on 27-06-2018.
 */

public class ScrollAdapter extends BaseAdapter {

    private Context context;
    private List<String> scrollViews;
    private static final String TAG = ScrollAdapter.class.getSimpleName();

    public ScrollAdapter(List<String> scrollViews){

        this.scrollViews = scrollViews;
    }

    public ScrollAdapter(Context context, List<String> listOfStrings) {

        this.context = context;
        this.scrollViews = listOfStrings;

    }

    @Override
    public int getCount() {
        return scrollViews.size();
    }

    @Override
    public String getItem(int i) {
        return scrollViews.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
//            view = LayoutInflater.from(context).
//                    inflate(R.layout.list_item, viewGroup, false);
            view = new MyView(viewGroup.getContext());
        }
        MyView currentView = (MyView) view;
        String itemViewType = getItem(i);
        Log.d(TAG, itemViewType);

//        ((MyView)  view).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_brightness_low_black_24dp));
        ((MyView) view).setText(itemViewType);
//        ((MyView) view).setCompoundDrawables(ContextCompat.getDrawable(context, R.drawable.ic_brightness_low_black_24dp),null,ContextCompat.getDrawable(context, R.drawable.ic_brightness_low_black_24dp),null);
        return view  ;
    }
}