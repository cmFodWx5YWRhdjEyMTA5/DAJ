package com.tinnovat.app.daj.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

public class TestActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ListView listView = new HalfCircleListView(this);
        listView.setClipChildren(false);
        listView.setDivider(null);
        List<String> listOfStrings = new ArrayList<String>();
        for(int i = 0; i < 30 ; i++){
            listOfStrings.add("Value " + i);
        }
        listView.setAdapter(new ScrollAdapter(this, listOfStrings));
        setContentView(listView);
    }
}
