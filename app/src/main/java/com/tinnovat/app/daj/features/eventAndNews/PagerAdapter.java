package com.tinnovat.app.daj.features.eventAndNews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tinnovat.app.daj.data.network.model.EventDetails;
import com.tinnovat.app.daj.features.eventAndNews.fragments.EventListTabFragment;

import java.util.List;


public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    private List<EventDetails> data;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return EventListTabFragment.getNewInstance(data, 0);
            case 1:
                return EventListTabFragment.getNewInstance(data, 1);
            case 2:
                return EventListTabFragment.getNewInstance(data, 2);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public int getItemPosition(Object object) {
        // POSITION_NONE makes it possible to reload the PagerAdapter
        return POSITION_NONE;
    }

    public void setData(List<EventDetails> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}