package com.tinnovat.app.daj.testing;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        recyclerView = findViewById(R.id.recycler_view);

        mAdapter = new MoviesAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);





    }

    public interface RecyclerViewClickListener {

        void onClick(View view, int position);
    }



    @Override
    public void initialiseViews() {

    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public void onClick(View v) {

    }
}