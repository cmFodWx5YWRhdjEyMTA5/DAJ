package com.tinnovat.app.daj.features.eventAndNews;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.EventCategory;
import com.tinnovat.app.daj.data.network.model.EventDetails;
import com.tinnovat.app.daj.data.network.model.EventListModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventNewsActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    private ViewPager viewPager;
    private PagerAdapter adapter;
    private List<EventDetails> eventDetailsList;
    private List<EventCategory> eventCategories;
    private AppPreferanceStore appPreferanceStore;
    private EventAndNewsListener mEventAndNewsListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_event_news);
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.event_news));

        appPreferanceStore = new AppPreferanceStore(this);

        fetchEventList();

    }

    @Override
    public void initialiseViews() {

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.today1)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tomorrow)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.weekend)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = findViewById(R.id.pager);


        adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#2C7861"));
        tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#2C7861"));

        tabLayout.addOnTabSelectedListener(this);

    }


    @Override
    public void initialiseEventListners() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    public void fetchEventList() {
        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<EventListModel> call = apiInterface.getEventsAndNews(appPreferanceStore.getLanguage() ? "en" : "ar");
        call.enqueue(new Callback<EventListModel>() {
            @Override
            public void onResponse(Call<EventListModel> call, Response<EventListModel> response) {
                endLoading();
                showMessage("Data Fetched Successfully");
                if (response.body() != null && response.body().getCategory() != null) {

                    List<EventDetails> eventDetails = new ArrayList<>();
                    for (EventCategory category : response.body().getCategory()) {
                        for (EventDetails details : category.getEvents()) {
                            eventDetails.add(details);
                        }
                    }

                    adapter.setData(eventDetails);
                }
            }

            @Override
            public void onFailure(Call<EventListModel> call, Throwable t) {

                endLoading();
                showMessage("Login Failed");
            }
        });
    }

    public interface EventAndNewsListener {
        void EventCategories(List<EventCategory> eventCategories);
    }
}