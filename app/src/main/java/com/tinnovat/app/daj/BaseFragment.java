package com.tinnovat.app.daj;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.tinnovat.app.daj.data.AppPreferanceStore;

import java.util.Objects;

/**
 * Created by Rahul on 08-07-2018.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private AppPreferanceStore appPreferenceStore;
    Dialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appPreferenceStore = new AppPreferanceStore(getActivity());
        appPreferenceStore.changeLocaleLanguage(appPreferenceStore.getLanguage());
        progressDialog = new Dialog(Objects.requireNonNull(getActivity()));
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.dialog_progress);
        Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setCancelable(false);
    }
    public void startLoading(){
        progressDialog.show();
    }

    public void endLoading() {
        if (progressDialog != null) {
            progressDialog.cancel();
            progressDialog.hide();
        }
    }

    public abstract void initialiseViews(View view);

    public abstract void initialiseEventListners();

    public void setLanguage(Boolean language){
        appPreferenceStore.setLanguage(language);
    }

    public String getToken(){
        return appPreferenceStore.getToken();
    }

    public boolean getLanguage(){
        return appPreferenceStore.getLanguage();
    }

    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
