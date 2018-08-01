package com.tinnovat.app.daj.features.services;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.Service;
import com.tinnovat.app.daj.data.network.model.ServiceCategory;
import com.tinnovat.app.daj.features.futurePhase.FuturePhaseInfoFragment;

import java.util.Objects;

public class ServicesMainActivity extends BaseActivity implements ServicesMainCategoryFragment.OnCategoryListFragmentInteractionListener,
ServicesSelectedCategoryFragment.OnListFragmentInteractionListener, ServiceBookingFragment.OnFragmentInteractionListener{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getText(R.string.dashboard));
        }*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setItemIconTintList(null);
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
        transaction.replace(R.id.content_frame, ServiceBookingFragment.newInstance(item, categoryId));
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
