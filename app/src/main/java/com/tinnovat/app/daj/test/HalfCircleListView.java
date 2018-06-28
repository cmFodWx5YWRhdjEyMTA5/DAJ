package com.tinnovat.app.daj.test;

import android.content.Context;
import android.widget.AbsListView;
import android.widget.ListView;


public class HalfCircleListView extends ListView implements AbsListView.OnScrollListener {

    public HalfCircleListView(Context context) {
        super(context);
        setOnScrollListener(this);
    }


    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        //Ignored
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        absListView.invalidateViews();
    }
}