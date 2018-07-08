package com.tinnovat.app.daj;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tinnovat.app.daj.data.AppPreferanceStore;

/**
 * Created by Anjali on 08-07-2018.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private AppPreferanceStore appPreferenceStore;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appPreferenceStore = new AppPreferanceStore(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        initialiseViews(view);
        initialiseEventListners();

        return view;
    }

    public abstract void initialiseViews(View view);

    public abstract void initialiseEventListners();

    public void setLanguage(Boolean language){
        appPreferenceStore.setLanguage(language);
    }

    public boolean getLanguage(){
        return appPreferenceStore.getLanguage();
    }

    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
