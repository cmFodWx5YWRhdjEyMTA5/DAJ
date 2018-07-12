package com.tinnovat.app.daj.features.services;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tinnovat.app.daj.Activity.ServiceListActivity;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.testing.Movie;
import com.tinnovat.app.daj.testing.MoviesAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServicesActivity extends BaseActivity {

    RelativeLayout hall;
    RelativeLayout hall2;
    RelativeLayout pool;
    RelativeLayout court;

    ImageView arcHall;
    ImageView arcHall1;
    ImageView arcCourt;
    ImageView arcPool;

    CharSequence categoryList[];

    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ServicesAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.services));

        recyclerView = findViewById(R.id.recycler_view);

        mAdapter = new ServicesAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);



        prepareMovieData();

        List<String> listItems = new ArrayList<String>();

        listItems.add(getResources().getString(R.string.hall1));
        listItems.add(getResources().getString(R.string.hall2));
        listItems.add(getResources().getString(R.string.hall3));
        listItems.add(getResources().getString(R.string.hall4));

        categoryList = listItems.toArray(new CharSequence[listItems.size()]);

        arcHall = findViewById(R.id.arcHall);
        arcHall1 = findViewById(R.id.arcHall1);
        arcCourt = findViewById(R.id.arcCourt);
        arcPool = findViewById(R.id.arcPool);

        if (!getLanguage()){
            arcHall.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_right));
            arcHall.setScaleType(ImageView.ScaleType.FIT_START);

            arcCourt.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_right));
            arcCourt.setScaleType(ImageView.ScaleType.FIT_START);

            arcPool.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_right));
            arcPool.setScaleType(ImageView.ScaleType.FIT_START);

            arcHall1.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_right));
            arcHall1.setScaleType(ImageView.ScaleType.FIT_START);
        }else {
            arcHall.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_left));
            arcHall.setScaleType(ImageView.ScaleType.FIT_END);

            arcCourt.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_left));
            arcCourt.setScaleType(ImageView.ScaleType.FIT_END);

            arcPool.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_left));
            arcPool.setScaleType(ImageView.ScaleType.FIT_END);

            arcHall1.setImageDrawable(ContextCompat.getDrawable(ServicesActivity.this, R.drawable.arc_left));
            arcHall1.setScaleType(ImageView.ScaleType.FIT_END);
        }

        hall = findViewById(R.id.hall);
        hall2 = findViewById(R.id.hall2);
        pool = findViewById(R.id.pool);
        court = findViewById(R.id.court);



        hall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        hall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        pool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        court.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });


    }

    @Override
    public void initialiseViews() {

    }

    @Override
    public void initialiseEventListners() {

    }

    private void prepareMovieData() {
        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);

        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);

        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movieList.add(movie);

        movie = new Movie("Shaun the Sheep", "Animation", "2015");
        movieList.add(movie);

        movie = new Movie("The Martian", "Science Fiction & Fantasy", "2015");
        movieList.add(movie);

        movie = new Movie("Mission: Impossible Rogue Nation", "Action", "2015");
        movieList.add(movie);

        movie = new Movie("Up", "Animation", "2009");
        movieList.add(movie);

        movie = new Movie("Star Trek", "Science Fiction", "2009");
        movieList.add(movie);

        movie = new Movie("The LEGO Movie", "Animation", "2014");
        movieList.add(movie);

        movie = new Movie("Iron Man", "Action & Adventure", "2008");
        movieList.add(movie);

        movie = new Movie("Aliens", "Science Fiction", "1986");
        movieList.add(movie);

        movie = new Movie("Chicken Run", "Animation", "2000");
        movieList.add(movie);

        movie = new Movie("Back to the Future", "Science Fiction", "1985");
        movieList.add(movie);

        movie = new Movie("Raiders of the Lost Ark", "Action & Adventure", "1981");
        movieList.add(movie);

        movie = new Movie("Goldfinger", "Action & Adventure", "1965");
        movieList.add(movie);

        movie = new Movie("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }

    private void showDialog(){

        Intent i = new Intent(ServicesActivity.this, ServiceListActivity.class);
        startActivity(i);
        int cate = 0;
       /* AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle("S a color");
        builder.setItems(categoryList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // the user clicked on colors[which]
                Intent i = new Intent(ServicesActivity.this, ServiceBookingActivity.class);
                startActivity(i);
            }
        });
        builder.show();*/
    }

    @Override
    public void onClick(View v) {

    }
}
