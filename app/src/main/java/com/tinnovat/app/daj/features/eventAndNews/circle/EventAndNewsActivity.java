package com.tinnovat.app.daj.features.eventAndNews.circle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.kapil.circularlayoutmanager.CircularLayoutManager;
import com.tinnovat.app.daj.features.eventAndNews.EventNewsActivity;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.EventListModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EventAndNewsActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private List<Model> list;
    private AppPreferanceStore appPreferanceStore;

    ImageView logoEn;
    ImageView logoAr;
   // private ScrollWheel scrollWheel;

  //  private FloatingActionButton addItemButton;
   // private FloatingActionButton scrollWheelToggleButton;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_and_news_arabic);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.event_and_news));

        appPreferanceStore = new AppPreferanceStore(this);


        logoEn = findViewById(R.id.logo_en);
        logoAr = findViewById(R.id.logo_ar);
        if (getLanguage()){
            logoEn.setVisibility(View.VISIBLE);
            logoAr.setVisibility(View.INVISIBLE);
        } else {
            logoEn.setVisibility(View.INVISIBLE);
            logoAr.setVisibility(View.VISIBLE);
        }

        initViews();
        fetchEventList();//for api
       // setViews();//for view
    }

    @Override
    public void initialiseViews() {

    }

    @Override
    public void initialiseEventListners() {

    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycler_view);
      //  scrollWheel = (ScrollWheel) findViewById(R.id.scroll_wheel);

      //  addItemButton = (FloatingActionButton) findViewById(R.id.add_item_button);
       // scrollWheelToggleButton = (FloatingActionButton) findViewById(R.id.scroll_wheel_toggle_button);
    }

    private void fetchEventList() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjU3NzNjNjJjMGVhMzMyMTcxNWVkN2U2YTg3ZGE1NWU2Zjg5NmE4NWE1NTdlMWM3NTJjNTQyYWQyZWZlMWM5ZjQ2MjQwNWQxNGZiMTczMjg1In0.eyJhdWQiOiIyIiwianRpIjoiNTc3M2M2MmMwZWEzMzIxNzE1ZWQ3ZTZhODdkYTU1ZTZmODk2YTg1YTU1N2UxYzc1MmM1NDJhZDJlZmUxYzlmNDYyNDA1ZDE0ZmIxNzMyODUiLCJpYXQiOjE1MzEzODcyOTksIm5iZiI6MTUzMTM4NzI5OSwiZXhwIjoxNTYyOTIzMjk4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.iybjrJeHEnAeRz974dayPopw1O7jOLLCaRfmViostLhtoNN2wWcTQUAp1JfuGDIqy5Vt_6q9IpdubOVSV--eP14-BlgH0zlRI4rCDvSOlvoVgl6sTPsRDqRNJSN2khefPIwokT2cCyHYDk8GefiZ7g2LeUYtbE5_hNdv8bsmJi3ATvwW8YeJjCDBK7fI0V2I5ZmPdSBGcn7jJc2XPlWjm2M2bukdXbSU1yXH67n7h-AdvPQhSSwneqD0gh2A2DgDaniTExfJ_iZHSyrmi7sYzYRPJuxYuhxTlKy31lDn3GW0pnwEGLrz5kUWzkDJqdZSGwE-FPTOckbJPKjiGgsN9Q-Q2jG5ZXr3MTRGNCRQh7DQjEJks7cjV_HfZDrA1y0rfDhLE0F4wigrlw70Dkrv0yVbXNEqRbGnPe2XojhqieacixWoKBmbn-uXMqSDDsCnsAsUASePkwk7tu72fxHPewjOwf1dsNoDXwEvXOLz8YygAfb4dyzqqufZu40DR7PR7-_1untYJzgqSwBo74Py4zyxZEZsRJ3X0oXSLqHwPXq8w_shobCJ7-QE5YEg0X0p5CThXNyznxyk7Q8I1Bgm29mcemLtKT_J8_twk8Qo6CXUCTxBbVT_XDx55J4SoFYV41_lp-LZGJG6OuEcAB2-Z7hY8VswOVZbNONslRGTnQ0";

        ApiInterface apiInterface = ApiClient.getAuthClient(token).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<EventListModel> call = apiInterface.getEventsAndNews("en");
        call.enqueue(new Callback<EventListModel>() {
            @Override
            public void onResponse(Call<EventListModel> call, Response<EventListModel> response) {
                showMessage("Data Fetched Successfully");
                if (response.body() != null && response.body().getCategory() != null) {

                    setData(response);

                }
            }

            @Override
            public void onFailure(Call<EventListModel> call, Throwable t) {

                showMessage("Login Failed");
            }
        });
    }

    private void setData(Response<EventListModel> response){
        list = new ArrayList<>();
        String event = getString(R.string.tennis_court);

        for (int i = 0; i < response.body().getCategory().size(); i++) {
            Model model = new Model();
            model.setEvent(response.body().getCategory().get(i).getCategoryName() );

            list.add(model);list.add(model);
        }
        appPreferanceStore.setDataEventAndNews(response);
        setViews(response);
    }

    private void setViews(Response<EventListModel> response) {
       // initializeList();
        recyclerView.setAdapter(new RecyclerViewAdapter(getApplicationContext(), list));
        recyclerView.addItemDecoration(new RecyclerItemDecoration());
        recyclerView.setLayoutManager(new CircularLayoutManager(getApplicationContext(), 300, -100));
        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(getApplicationContext(),
                new OnRecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void OnItemClick(RecyclerView parent, int childIndex) {

                Intent i = new Intent(EventAndNewsActivity.this, EventNewsActivity.class);
                startActivity(i);

            }
        }));


    }

    private void initializeList() {
        list = new ArrayList<>();
        String event = getString(R.string.tennis_court);

        for (int i = 0; i < 15; i++) {
            Model model = new Model();
            model.setEvent(event + (i + 1));

            list.add(model);
        }
    }

    @Override
    public void onClick(View v) {

    }

}
