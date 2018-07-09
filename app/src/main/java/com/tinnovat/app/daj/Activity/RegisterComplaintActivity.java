package com.tinnovat.app.daj.Activity;

import android.app.Activity;
import android.app.Dialog;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegisterComplaintActivity extends BaseActivity {

    TextView category;
    CharSequence categoryList[];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_complaint);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.reg_complaint));

        List<String> listItems = new ArrayList<String>();

        listItems.add(getResources().getString(R.string.electrical));
        listItems.add(getResources().getString(R.string.plumbing));
        listItems.add(getResources().getString(R.string.lift));
        listItems.add(getResources().getString(R.string.parking));
        listItems.add(getResources().getString(R.string.food));

        categoryList = listItems.toArray(new CharSequence[listItems.size()]);

        category = findViewById(R.id.category);


        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDialog();
                ViewDialog alert = new ViewDialog();
                alert.showDialog(RegisterComplaintActivity.this, "");
            }
        });


    }

    @Override
    public void initialiseViews() {

    }

    @Override
    public void initialiseEventListners() {

    }

    private void showDialog(){
        int cate = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
      //  builder.setTitle("Pick a color");
        builder.setItems(categoryList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // the user clicked on colors[which]
              //  Toast.makeText(RegisterComplaintActivity.this, "test :"+which, Toast.LENGTH_SHORT).show();
                category.setText(categoryList[which]);
            }
        });
        builder.show();
    }

    @Override
    public void onClick(View v) {

    }
    public class ViewDialog {

        public void showDialog(Activity activity, String msg){
            final Dialog dialog = new Dialog(RegisterComplaintActivity.this);
            dialog.setContentView(R.layout.sample1);
            final TextView family =  dialog.findViewById(R.id.family);
            final TextView friend =  dialog.findViewById(R.id.friend);
            final TextView maintenance =  dialog.findViewById(R.id.maintenance);
            final TextView taxi =  dialog.findViewById(R.id.taxi);
            final TextView delivery =  dialog.findViewById(R.id.delivery);




            family.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    category.setText(family.getText());
                    dialog.dismiss();
                }
            });

            friend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    category.setText(friend.getText());
                    dialog.dismiss();
                }
            });

            maintenance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    category.setText(maintenance.getText());
                    dialog.dismiss();
                }
            });taxi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    category.setText(taxi.getText());
                    dialog.dismiss();
                }
            });delivery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    category.setText(delivery.getText());
                    dialog.dismiss();
                }
            });

            dialog.show();

        }

    }
}
