package com.tinnovat.app.daj.features.complaint;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.ComplaintCategoriesResponseModel;
import com.tinnovat.app.daj.data.network.model.CompllaintUpdateResponseModel;
import com.tinnovat.app.daj.data.network.model.RequestParams;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterComplaintActivity extends BaseActivity implements ImagesAdapter.ImageAdapterListener {

    TextView category;
    List<String> listItems;
    List<Integer> catIds;
    ViewDialog alert;
    int mCatId = 0;

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private Button buttonSubmit;
    private ArrayList<Bitmap> images;
    private GoogleApiClient mGoogleApiClient;
    private String lattitude ="7.724600";
    private String longitude = "20.418335";
    private AppPreferanceStore appPreferanceStore;
    private RecyclerView recyclerViewImages;
    private ImagesAdapter mAdapter;
    private TextView textAddress;
    private LocationManager mLocationManager;

    List<String> sam = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_complaint);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.reg_complaint));

        appPreferanceStore = new AppPreferanceStore(getApplicationContext());
        LinearLayout takeImage = findViewById(R.id.takeImage);
        buttonSubmit = findViewById(R.id.button_submit);
        textAddress = findViewById(R.id.text_address);
        buttonSubmit.setOnClickListener(this);

        takeImage.setOnClickListener(this);

        recyclerViewImages = findViewById(R.id.recycler_view_images);
        mAdapter = new ImagesAdapter(getApplicationContext(), this);

        recyclerViewImages.setVisibility(View.GONE);
        images = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewImages.setLayoutManager(mLayoutManager);
        recyclerViewImages.setItemAnimator(new DefaultItemAnimator());
        recyclerViewImages.setAdapter(mAdapter);

        fetchCategory();
    }

    private void fetchCategory() {

        startLoading();
        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<ComplaintCategoriesResponseModel> call = apiInterface.getComplaintCategory("en");
        call.enqueue(new Callback<ComplaintCategoriesResponseModel>() {
            @Override
            public void onResponse(Call<ComplaintCategoriesResponseModel> call, Response<ComplaintCategoriesResponseModel> response) {
                endLoading();
                showMessage("Category list Successfully");
                setData(response);
            }

            @Override
            public void onFailure(Call<ComplaintCategoriesResponseModel> call, Throwable t) {

                endLoading();
                showMessage("Category list Failed");
            }
        });
    }

    private void setData(Response<ComplaintCategoriesResponseModel> response) {
        listItems = new ArrayList<String>();
        catIds = new ArrayList<Integer>();

        //Todo check chenge for loop
        for (int i = 0; i < response.body().getCategories().size(); i++) {
            listItems.add(response.body().getCategories().get(i).getCategoryName());
            catIds.add(response.body().getCategories().get(i).getId());
        }

       // CharSequence categoryList[] = listItems.toArray(new CharSequence[listItems.size()]);

        category = findViewById(R.id.category);


        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert = new ViewDialog();
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

    private void takePhoto() {
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
        switch (v.getId()) {
            case R.id.takeImage:
                takePhoto();
                break;

            case R.id.button_submit:
                doValidation();
                //registerComplaint();
                break;

            default:
                break;
        }

    }

    private void doValidation(){
        if (mCatId == 0){
            showMessage(getResources().getString(R.string.choose_category));
        }else {
            registerComplaint();
        }
    }

    private void registerComplaint() {

        List<String> imageArray = new ArrayList<>();
        if (images != null && !images.isEmpty()) {

            for (Bitmap bitmap : images) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                imageArray.add("data:image/jpeg;base64,"+Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT));
            }
        }

      /*  ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cctv);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        sam .add( "data:image/png;base64,"+Base64.encodeToString(imageBytes, Base64.DEFAULT));*/

        startLoading();
        RequestParams.ComplaintRequest request = new RequestParams().new ComplaintRequest(
                appPreferanceStore.getLanguage() ? "en" : "ar", mCatId, "test",
                imageArray, String.format("%s,%s", lattitude, longitude));

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);

        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<CompllaintUpdateResponseModel> call = apiInterface.registerComplaintService(request);
        call.enqueue(new Callback<CompllaintUpdateResponseModel>() {
            @Override
            public void onResponse(Call<CompllaintUpdateResponseModel> call, Response<CompllaintUpdateResponseModel> response) {
                endLoading();
                showMessage("Complaint Registered Successfully");

            }

            @Override
            public void onFailure(Call<CompllaintUpdateResponseModel> call, Throwable t) {
                endLoading();

                showMessage("Complaint Registration failed");
            }
        });
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

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            images.add(photo);
            mAdapter.setData(images);
            recyclerViewImages.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void interactClick(final Bitmap bitmapImage) {
        final Dialog dialog = new Dialog(RegisterComplaintActivity.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.image_dialog);

        ImageButton delete = dialog.findViewById(R.id.button_delete);
        ImageView imageView = dialog.findViewById(R.id.image);

        imageView.setImageBitmap(bitmapImage);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                images.remove(bitmapImage);
                mAdapter.setData(images);
                if (images.size() > 0)
                    recyclerViewImages.setVisibility(View.VISIBLE);
                else
                    recyclerViewImages.setVisibility(View.GONE);


                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public class ViewDialog implements ComplaintCategoryAdapter.CategoryAdapterListener{
         Dialog dialog;
        public void showDialog() {
            dialog = new Dialog(RegisterComplaintActivity.this);
            dialog.setContentView(R.layout.dialog_reg_complaint);
            RecyclerView recyclerView = dialog.findViewById(R.id.recycler_view);
            ComplaintCategoryAdapter mAdapter = new ComplaintCategoryAdapter(listItems,catIds,this);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);
            dialog.show();

        }

        @Override
        public void interactClick(String mCategory,int catId) {
            mCatId = catId;
            category.setText(mCategory);
            dialog.dismiss();
        }
    }
}
