package com.tinnovat.app.daj.Activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Objects;


public class GuestRegistrationActivity extends BaseActivity {
    TextView purpose;
    CharSequence purposeList[] = new CharSequence[] {"Family", "Friend", "Maintenance", "Taxi", "Delivery"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_registration);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Guest Registration");


        purpose = findViewById(R.id.purpose);
        purpose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

       /* //get the spinner from the xml.
       spinner2 = findViewById(R.id.spinner2);
        Spinner dropdown = findViewById(R.id.spinner1);
//create a list of items for the spinner.
        String[] items = new String[]{"-- Select Purpose --","Family", "Friend", "Maintenance", "Taxi", "Delivery"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.

        dropdown.setPrompt("haiiiiiiiiiii");
        dropdown.setAdapter(adapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, items);
spinner2.setHintTextColor(getResources().getColor(R.color.black));
spinner2.setFloatingLabelTextColor(getResources().getColor(R.color.black));
        spinner2.setAdapter(adapter);*/
    }

    private void showDialog(){
        int cate = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //  builder.setTitle("Pick a color");
        builder.setItems(purposeList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // the user clicked on colors[which]
                Toast.makeText(GuestRegistrationActivity.this, "test :"+which, Toast.LENGTH_SHORT).show();
                purpose.setText(purposeList[which]);
            }
        });
        builder.show();
    }
}
