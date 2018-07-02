package com.tinnovat.app.daj.Activity;

import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import java.util.Objects;

public class RegisterComplaintActivity extends BaseActivity {

    TextView category;
    CharSequence categoryList[] = new CharSequence[] {"electrical","plumbing","lift","parking","food"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_complaint);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.reg_complaint));

        category = findViewById(R.id.category);


        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });


    }

    private void showDialog(){
        int cate = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
      //  builder.setTitle("Pick a color");
        builder.setItems(categoryList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // the user clicked on colors[which]
                Toast.makeText(RegisterComplaintActivity.this, "test :"+which, Toast.LENGTH_SHORT).show();
                category.setText(categoryList[which]);
            }
        });
        builder.show();
    }

}
