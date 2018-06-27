package com.tinnovat.app.daj.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.tinnovat.app.daj.Activity.EventDetailActivity;
import com.tinnovat.app.daj.Activity.MainActivity;
import com.tinnovat.app.daj.Activity.ServicesActivity;
import com.tinnovat.app.daj.R;

public class TabFragment1 extends Fragment {

    RelativeLayout a1;
    RelativeLayout a2;
    RelativeLayout a3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         inflater.inflate(R.layout.fragment_tab_1, container, false);
        View view = inflater.inflate(R.layout.fragment_tab_1, container, false);
        a1 = view.findViewById(R.id.a1);

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EventDetailActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}