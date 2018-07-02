package com.tinnovat.app.daj.circle;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kapil.circularlayoutmanager.CircularLayoutManager;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class EventAndNewsActivity1 extends BaseActivity {

    private RecyclerView recyclerView;
    private List<Model> list;

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
        setViews();
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
      //  scrollWheel = (ScrollWheel) findViewById(R.id.scroll_wheel);

      //  addItemButton = (FloatingActionButton) findViewById(R.id.add_item_button);
       // scrollWheelToggleButton = (FloatingActionButton) findViewById(R.id.scroll_wheel_toggle_button);
    }

    private void setViews() {
        initializeList();
        recyclerView.setAdapter(new RecyclerViewAdapter(getApplicationContext(), list));
        recyclerView.addItemDecoration(new RecyclerItemDecoration());
        recyclerView.setLayoutManager(new CircularLayoutManager(getApplicationContext(), 300, -100));
        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(getApplicationContext(),
                new OnRecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void OnItemClick(RecyclerView parent, int childIndex) {
                Toast.makeText(EventAndNewsActivity1.this, ((TextView) parent.getChildAt(childIndex)
                        .findViewById(R.id.event)).getText(), Toast.LENGTH_SHORT).show();


            }
        }));

       /* scrollWheel.setRecyclerView(recyclerView);
        scrollWheel.setScrollWheelEnabled(false);
        scrollWheel.setHighlightTouchAreaEnabled(false);*/
        //scrollWheel.setConsumeTouchOutsideTouchAreaEnabled(false);
       // scrollWheel.setTouchAreaThickness(10);
  /*      scrollWheel.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ScrollWheel scrollWheel, int childIndex) {
                Toast.makeText(EventAndNewsActivity1.this, "OC " + ((TextView) scrollWheel.getRecyclerView()
                        .getChildAt(childIndex).findViewById(R.id.event)).getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(ScrollWheel scrollWheel, int childIndex) {
                Toast.makeText(EventAndNewsActivity1.this, "OLC " + ((TextView) scrollWheel.getRecyclerView()
                        .getChildAt(childIndex).findViewById(R.id.event)).getText(), Toast.LENGTH_SHORT).show();
            }
        });*/

        //addItemButton.setOnClickListener(this);
      //  scrollWheelToggleButton.setOnClickListener(this);
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
 /*
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

           case R.id.add_item_button:

                Model model = new Model();
                model.setEvent("Event " + (1 + list.size()));
                model.setTimings("12:00am - 12:00pm");

                list.add(model);

                recyclerView.getAdapter().notifyItemChanged(list.size() - 2);
                recyclerView.getAdapter().notifyItemInserted(list.size() - 1);

                break;

            case R.id.scroll_wheel_toggle_button:

                scrollWheel.setScrollWheelEnabled(!scrollWheel.isScrollWheelEnabled());
                scrollWheel.setHighlightTouchAreaEnabled(!scrollWheel.isHighlightTouchAreaEnabled());

                break;
        }
    }*/
}
