package com.tinnovat.app.daj.features.Complaint;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.ComplaintCategoriesResponseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterComplaintActivity extends BaseActivity  {

    TextView category;
    List<String> listItems;

    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_complaint);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.reg_complaint));

        LinearLayout takeImage = findViewById(R.id.takeImage);

        takeImage.setOnClickListener(this);


        fetchCategory();

    }

    private void fetchCategory() {

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<ComplaintCategoriesResponseModel> call = apiInterface.getComplaintCategory("en");
        call.enqueue(new Callback<ComplaintCategoriesResponseModel>() {
            @Override
            public void onResponse(Call<ComplaintCategoriesResponseModel> call, Response<ComplaintCategoriesResponseModel> response) {
                showMessage("Category list Successfully");
                setData(response);
            }

            @Override
            public void onFailure(Call<ComplaintCategoriesResponseModel> call, Throwable t) {

                showMessage("Category list Failed");
            }
        });
    }

    private void setData(Response<ComplaintCategoriesResponseModel> response){
         listItems = new ArrayList<String>();

        //Todo check chenge for loop
        for(int i=0 ; i<response.body().getCategories().size();i++) {
            listItems.add(response.body().getCategories().get(i).getCategoryName());
        }

        CharSequence categoryList[] = listItems.toArray(new CharSequence[listItems.size()]);

        category = findViewById(R.id.category);


        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialog alert = new ViewDialog();
                alert.showDialog();
            }
        });
    }

    @Override
    public void initialiseViews() {

    }

    @Override
    public void initialiseEventListners() {

    }

    private void takePhoto(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        MY_CAMERA_PERMISSION_CODE);
            } else {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.takeImage:
                takePhoto();
                break;

                default:
                    break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }
    }

        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                imageView = findViewById(R.id.pto);

                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo);
            }
        }

        public class ViewDialog {

        public void showDialog(){
            final Dialog dialog = new Dialog(RegisterComplaintActivity.this);
            dialog.setContentView(R.layout.dialog_reg_complaint);


            RecyclerView recyclerView= dialog.findViewById(R.id.recycler_view);
            ComplaintCategoryAdapter mAdapter = new ComplaintCategoryAdapter(listItems);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);

            dialog.show();

        }

    }

}
