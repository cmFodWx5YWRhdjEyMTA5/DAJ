package com.tinnovat.app.daj.features.futurePhase;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.model.Futurephase;

import java.util.Objects;

public class FuturePhaseMainActivity extends BaseActivity
        implements FuturePhaseInfoFragment.OnListFragmentInteractionListener, FuturePhaseDetailFragment.OnFragmentInteractionListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initialiseViews() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, FuturePhaseInfoFragment.newInstance(1));
        transaction.commit();
    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setTitle(String title) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(Futurephase item) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, FuturePhaseDetailFragment.newInstance(item));
        transaction.addToBackStack(null).commit();
    }
}
