package com.tinnovat.app.daj.features.complaint;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.ComplaintList;
import com.tinnovat.app.daj.data.network.model.Service;
import com.tinnovat.app.daj.data.network.model.ServiceCategory;
import com.tinnovat.app.daj.features.services.ServiceBookingFragment;
import com.tinnovat.app.daj.features.services.ServicesMainCategoryFragment;
import com.tinnovat.app.daj.features.services.ServicesSelectedCategoryFragment;

import java.util.Objects;

public class ComplaintMainActivity extends BaseActivity implements
        ComplaintListFragment.OnFragmentInteractionListener,
        ComplaintDetailFragment.OnFragmentInteractionListener,
        RegisterComplaintFragment.OnFragmentInteractionListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getText(R.string.dashboard));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       /* DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();*/

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setItemIconTintList(null);
    }

    @Override
    public void initialiseViews() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, ComplaintListFragment.newInstance());
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
    public void onListItemClickListener(ComplaintList complaintList) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, ComplaintDetailFragment.newInstance(complaintList));
        transaction.addToBackStack(null).commit();
       /*
        Intent i = new Intent(mContext, MyComplaintActivity.class);
        i.putExtra("response", new Gson().toJson(response.body().getComplaints().get(position)));

        mContext.startActivity(i);*/
    }

    @Override
    public void onFabButtonActionListener() {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, RegisterComplaintFragment.newInstance());
        transaction.addToBackStack(null).commit();


//        Intent i = new Intent(getActivity(), RegisterComplaintActivity.class);
//        startActivity(i);

    }

    @Override
    public void setTitle(String title) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }

   /* @Override
    public void onServiceListFragmentInteraction(Service item, int categoryId) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, ServiceBookingFragment.newInstance(item, categoryId));
        transaction.addToBackStack(null).commit();
    }

    @Override
    public void onCategoryListFragmentInteraction(ServiceCategory item) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, ServicesSelectedCategoryFragment.newInstance(1,item));
        transaction.addToBackStack(null).commit();
    }*/
}
