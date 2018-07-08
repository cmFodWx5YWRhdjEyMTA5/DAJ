package com.tinnovat.app.daj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.tinnovat.app.daj.data.AppPreferanceStore;

import java.util.Objects;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private AppPreferanceStore appPreferenceStore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActionBar() != null)
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        appPreferenceStore = new AppPreferanceStore(getApplicationContext());
        appPreferenceStore.changeLocaleLanguage(appPreferenceStore.getLanguage());

        initialiseViews();
        initialiseEventListners();

        //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }

    public abstract void initialiseViews();

    public abstract void initialiseEventListners();


    public void changeOrientation() {
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }

    public void setLanguage(Boolean language) {
        appPreferenceStore.setLanguage(language);
    }

    public boolean getLanguage() {
        return appPreferenceStore.getLanguage();
    }

    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
