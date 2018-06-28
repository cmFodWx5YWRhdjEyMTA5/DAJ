package com.tinnovat.app.daj.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;

import com.tinnovat.app.daj.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = new HalfCircleListView(this);
        listView.setDivider(null);
        List<String> listOfStrings = new ArrayList<String>();
        for(int i = 0; i < 106 ; i++){
            listOfStrings.add("Value " + i);
        }
        listView.setAdapter(new ScrollAdapter(this, listOfStrings));
        setContentView(listView);
      /*  String[] res = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "OFF"};
        List<MenuItemData> menuItemDatas = new ArrayList<MenuItemData>();
        for (int i = 0; i < res.length; i++) {
            menuItemDatas.add(new MenuItemData(res[i]));
        }
        SimpleTextAdapter simpleTextAdapter = new SimpleTextAdapter(this, menuItemDatas, Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        CustomTV mTestCircleMenuLeft = findViewById(R.id.test_circle_menu_left);
        mTestCircleMenuLeft.setAdapter(simpleTextAdapter);
        mTestCircleMenuLeft.setOnMenuSelectedListener(this);*/
    }

    /*@Override
    public void onItemSelected(CustomTV parent, View view, int pos) {

    }*/
}
