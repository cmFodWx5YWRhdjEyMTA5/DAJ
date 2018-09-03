package com.tinnovat.app.daj.features.services;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.Service;
import com.tinnovat.app.daj.data.network.model.ServiceCategory;
import com.tinnovat.app.daj.features.futurePhase.FuturePhaseInfoFragment;

import java.util.Objects;

public class ServicesMainActivity extends BaseActivity implements ServicesMainCategoryFragment.OnCategoryListFragmentInteractionListener,
ServicesSelectedCategoryFragment.OnListFragmentInteractionListener, ServiceBookingFragment.OnFragmentInteractionListener{

    String mDay = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       if (getIntent().getExtras() != null){
           mDay = getIntent().getStringExtra("selectedDay");
       }


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(2).setChecked(true);
        navigationView.setItemIconTintList(null);

        ImageView facebook = findViewById(R.id.facebook);
        ImageView twitter = findViewById(R.id.twitter);
        ImageView instagram = findViewById(R.id.instagram);


        if (!getLanguage()) {
            //for arabic
            navigationView.getMenu().getItem(0).setIcon(R.drawable.home_ar_nav);
            navigationView.getMenu().getItem(1).setIcon(R.drawable.event_ar_nav);
            navigationView.getMenu().getItem(2).setIcon(R.drawable.service_ar_nav);
            navigationView.getMenu().getItem(3).setIcon(R.drawable.guest_ar_nav);
            //navigationView.getMenu().getItem(4).setIcon(R.drawable.camera_ar_nav);
            navigationView.getMenu().getItem(6).setIcon(R.drawable.navigation_ar_nav);
            navigationView.getMenu().getItem(7).setIcon(R.drawable.project_ar_nav);
            navigationView.getMenu().getItem(8).setIcon(R.drawable.booking_ar_nav);
            navigationView.getMenu().getItem(9).setIcon(R.drawable.profile_ar_nav);
            navigationView.getMenu().getItem(10).setIcon(R.drawable.food_ar_nav);
            navigationView.getMenu().getItem(11).setIcon(R.drawable.taxi_ar_nav);
            navigationView.getMenu().getItem(12).setIcon(R.drawable.complaint_ar_nav);
            navigationView.getMenu().getItem(13).setIcon(R.drawable.contact_ar_nav);
            navigationView.getMenu().getItem(14).setIcon(R.drawable.password_ar_nav);
            navigationView.getMenu().getItem(15).setIcon(R.drawable.language_ar_nav);
            navigationView.getMenu().getItem(16).setIcon(R.drawable.logout_ar_nav);

        } else {
            //for Eng

            navigationView.getMenu().getItem(0).setIcon(R.drawable.home_nav);
            navigationView.getMenu().getItem(1).setIcon(R.drawable.event_nav);
            navigationView.getMenu().getItem(2).setIcon(R.drawable.service_nav);
            navigationView.getMenu().getItem(3).setIcon(R.drawable.guest_nav);
            // navigationView.getMenu().getItem(4).setIcon(R.drawable.camera_nav);
            navigationView.getMenu().getItem(4).setIcon(R.drawable.navigation_nav);
            navigationView.getMenu().getItem(5).setIcon(R.drawable.project_nav);
            navigationView.getMenu().getItem(6).setIcon(R.drawable.booking_nav);
            navigationView.getMenu().getItem(7).setIcon(R.drawable.profile_nav);
            navigationView.getMenu().getItem(8).setIcon(R.drawable.food_nav);
            navigationView.getMenu().getItem(9).setIcon(R.drawable.taxi_nav);
            navigationView.getMenu().getItem(10).setIcon(R.drawable.complaint_nav);
            navigationView.getMenu().getItem(11).setIcon(R.drawable.contact_nav);
            navigationView.getMenu().getItem(12).setIcon(R.drawable.password_nav);
            navigationView.getMenu().getItem(13).setIcon(R.drawable.language_nav);
            navigationView.getMenu().getItem(14).setIcon(R.drawable.logout_nav);

        }


        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(MainActivity.this,"facebook clicked",Toast.LENGTH_SHORT).show();
                Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/DarJewar/?ref=br_rs"));
                startActivity(followIntent);
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/darjewar?lang=en"));
                startActivity(followIntent);
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/darjewar/"));
                startActivity(followIntent);
            }
        });
    }

    @Override
    public void initialiseViews() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, ServicesMainCategoryFragment.newInstance(1));
        transaction.commit();
    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(String title) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }

    @Override
    public void onServiceListFragmentInteraction(Service item, int categoryId) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, ServiceBookingFragment.newInstance(item, categoryId,mDay));
        transaction.addToBackStack(null).commit();

     /*   Intent i = new Intent(mContext, ServiceBookingActivity.class);
        i.putExtra("category_id", category_id);
        i.putExtra("response", new Gson().toJson(response[holder.getAdapterPosition()]));
        mContext.startActivity(i);*/
    }

    @Override
    public void onCategoryListFragmentInteraction(ServiceCategory item) {


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, ServicesSelectedCategoryFragment.newInstance(1,item));
        transaction.addToBackStack(null).commit();

       /* Intent i = new Intent(mContext, ServiceListActivity.class);
        i.putExtra("category_id",response.body().getServiceCategory().get(holder.getAdapterPosition()).getId());
        i.putExtra("response",new Gson().toJson(response.body().getServiceCategory().get(holder.getAdapterPosition()).getServices()));
        mContext.startActivity(i);*/
    }



   /* @Override
    public void onListFragmentInteraction(Futurephase item) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, FuturePhaseDetailFragment.newInstance(item));
        transaction.addToBackStack(null).commit();
    }*/
}
