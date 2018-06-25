package com.tinnovat.app.daj.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Objects;


public class GuestRegistrationActivity extends BaseActivity {
    MaterialBetterSpinner spinner2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_registration);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Guest Registration");
        String[] items = new String[]{"-- Select Purpose --","Family", "Friend", "Maintenance", "Taxi", "Delivery"};
        spinner2 = findViewById(R.id.spinner2);

       /* //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1);
//create a list of items for the spinner.
        String[] items = new String[]{"-- Select Purpose --","Family", "Friend", "Maintenance", "Taxi", "Delivery"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.

        dropdown.setPrompt("haiiiiiiiiiii");
        dropdown.setAdapter(adapter);
*/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, items);

        spinner2.setAdapter(adapter);
    }
}
