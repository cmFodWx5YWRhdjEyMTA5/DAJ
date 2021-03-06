package com.tinnovat.app.daj.features.complaint;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.test.mock.MockPackageManager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.pchmn.androidverify.Form;
import com.tinnovat.app.daj.BaseFragment;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.ComplaintCategoriesResponseModel;
import com.tinnovat.app.daj.data.network.model.CompllaintUpdateResponseModel;
import com.tinnovat.app.daj.data.network.model.RequestParams;

import org.ankit.gpslibrary.MyTracker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterComplaintFragment extends BaseFragment implements ImagesAdapter.ImageAdapterListener {

    TextView category;
    List<String> listItems;
    List<Integer> catIds;
    //ViewDialog alert;
    int mCatId = 0;

    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

//    private FusedLocationProviderClient mFusedLocationClient;

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private Button buttonSubmit;
    private ArrayList<Bitmap> images;
    private GoogleApiClient mGoogleApiClient;
    private double lattitude;
    private double longitude;
    private AppPreferanceStore appPreferanceStore;
    private RecyclerView recyclerViewImages;
    private ImagesAdapter mAdapter;
    private TextView textAddress;
    private ImageView location;
    private EditText commentBox;
    private LocationManager mLocationManager;
    private Spinner spinner;
    private LinearLayout takeImage;

    List<String> sam = new ArrayList<>();
    private Uri imageUri;
    LocationManager locationManager;
    Form form;
    private OnFragmentInteractionListener mListener;

    public RegisterComplaintFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        RegisterComplaintFragment fragment = new RegisterComplaintFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initialiseViews(View view) {
        mListener.setTitle(getString(R.string.reg_complaint));

        appPreferanceStore = new AppPreferanceStore(getContext());
        takeImage = view.findViewById(R.id.takeImage);
        buttonSubmit = view.findViewById(R.id.button_submit);
        textAddress = view.findViewById(R.id.text_address);
        commentBox = view.findViewById(R.id.commentBox);
        location = view.findViewById(R.id.location);
        category = view.findViewById(R.id.category);

        spinner = view.findViewById(R.id.spinner_purpose);

        buttonSubmit.setOnClickListener(this);

        takeImage.setOnClickListener(this);

        recyclerViewImages = view.findViewById(R.id.recycler_view_images);
        mAdapter = new ImagesAdapter(getContext(), this);

        recyclerViewImages.setVisibility(View.GONE);
        images = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewImages.setLayoutManager(mLayoutManager);
        recyclerViewImages.setItemAnimator(new DefaultItemAnimator());
        recyclerViewImages.setAdapter(mAdapter);

        location.setOnClickListener(this);

//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        form = new Form.Builder(getActivity())
                .showErrors(true)
                .build();

        checkIsLocationEnabled();

//        getLocation();

//        checkLocation();
        if (isNetworkConnected()){
            fetchCategory();

        }else {
            showErrorDilog(false);
        }




    }

    private void checkLocation(){
        try {
            if (ActivityCompat.checkSelfPermission(getActivity(), mPermission)
                    != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(), new String[]{mPermission, Manifest.permission.READ_PHONE_STATE},
                        REQUEST_CODE_PERMISSION);
            }else{
                //read location
                getLocations();
            }
        } catch (Exception e) {
            showMessage("error");
            e.printStackTrace();
        }
    }
   /* @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocations();
                } else {
                    System.out.println("permission denied!");
                }
                break;
        }
    }*/


    private void getLocations(){
        MyTracker tracker=new MyTracker(getActivity());

        lattitude = tracker.getLatitude();
        longitude = tracker.getLongitude();

        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.ENGLISH);
            List<Address> addresses = geocoder.getFromLocation(tracker.getLatitude(), tracker.getLongitude(), 1);
            textAddress.setText(addresses.get(0).getAddressLine(0) + ", " +
                    addresses.get(0).getAddressLine(1) + ", " + addresses.get(0).getAddressLine(2));
        } catch (Exception e) {

            Log.i("location error","");
        }
    }

    private void setSpinner(List<String> listItems) {

        spinner.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listItems));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                /*if (i == 0) {
                  //  showMessage("nothing");
                }*/
                if (i != 0) {

                    mCatId = catIds.get(i - 1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_register_complaint, container, false);

        initialiseViews(view);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        if (images != null) {
            if (images.size() < 3) {
                takeImage.setVisibility(View.VISIBLE);
            } else {
                takeImage.setVisibility(View.GONE);
            }
        }
        super.onResume();
    }

    private void checkIsLocationEnabled() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("LOCATION services disabled");  // GPS not found
            builder.setMessage("Please enable GPS"); // Want to enable?
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    //checkIsLocationEnabled();
                }
            });
            builder.setNegativeButton(R.string.no, null);
            builder.create().show();
        } else {
            checkLocation();
        }
    }


    /*private void getLocation() {



        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
            checkIsLocationEnabled();

        } else {



            try {
                locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
              *//*  locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);*//*

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
                locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 1000, 0, this);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);
               *//* mFusedLocationClient.getLastLocation()
                        .addOnSuccessListener((Executor) this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations this can be null.
                                if (location != null) {
                                    // Logic to handle location object
                                    showMessage("hell");
                                }
                            }
                        });*//*
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }*/

    private void showDilog(String message){
        android.support.v7.app.AlertDialog.Builder builder1 = new android.support.v7.app.AlertDialog.Builder(getActivity());
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        getActivity().onBackPressed();
                    }
                });

        android.support.v7.app.AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void fetchCategory() {

        startLoading();
        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<ComplaintCategoriesResponseModel> call = apiInterface.getComplaintCategory(appPreferanceStore.getLanguage() ? "en" : "ar");
        call.enqueue(new Callback<ComplaintCategoriesResponseModel>() {
            @Override
            public void onResponse(Call<ComplaintCategoriesResponseModel> call, Response<ComplaintCategoriesResponseModel> response) {
                endLoading();
                setData(response);
            }

            @Override
            public void onFailure(Call<ComplaintCategoriesResponseModel> call, Throwable t) {

                endLoading();
            }
        });
    }

    private void setData(Response<ComplaintCategoriesResponseModel> response) {
        listItems = new ArrayList<String>();
        listItems.add(getResources().getString(R.string.choose_category));
        catIds = new ArrayList<Integer>();

        //Todo check chenge for loop
        for (int i = 0; i < response.body().getCategories().size(); i++) {
            listItems.add(response.body().getCategories().get(i).getCategoryName());
            catIds.add(response.body().getCategories().get(i).getId());
        }

        // CharSequence categoryList[] = listItems.toArray(new CharSequence[listItems.size()]);

        setSpinner(listItems);


       /* category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert = new ViewDialog();
                alert.showDialog();
            }
        });*/
    }

    private void takePhoto() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            String[] PERMISSIONS = {
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.CAMERA
            };

            if (!hasPermissions(getContext(), PERMISSIONS)) {
                ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, MY_CAMERA_PERMISSION_CODE);
            }
           /* if (getActivity().checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED  ) {
                requestPermissions(new String[]{Manifest.permission.CAMERA
                        },
                        MY_CAMERA_PERMISSION_CODE);
            } */
            else {
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                File file = new File(Environment.getExternalStorageDirectory(), timeStamp + ".png");
                //"/DAJ_Files" + "/reference_img_" +
                imageUri = Uri.fromFile(file);

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, CAMERA_REQUEST);
                /*
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);*/
            }
        }else{
            fromGallery();
        }
    }

    private void fromGallery(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , 1);
    }

    private void showAlert(){
        String[] selection = {"From Camera", "From Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(selection, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // the user clicked on colors[which]
                switch (which){
                    case 0:
                        takePhoto();
                        break;
                    case 1:
                        fromGallery();
                        break;
                }
            }
        });
        builder.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.takeImage:
                //takePhoto();
                showAlert();
                break;

            case R.id.button_submit:
                if (form.isValid()) {
                    // the form is valid
                    doValidation();
                }

                break;

            case R.id.location:
                checkIsLocationEnabled();
                break;

                case R.id.text_address:
                checkIsLocationEnabled();
                break;

            default:
                break;
        }

    }

    private void doValidation() {
        if (lattitude == 0 && longitude == 0){
            checkIsLocationEnabled();
        }else {

            List<String> imageArray = new ArrayList<>();
            if (mCatId == 0) {
                showMessage(getResources().getString(R.string.choose_category));
            } else {
                startLoading();

                if (images != null && !images.isEmpty()) {

                    for (Bitmap bitmap : images) {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 30, baos);
                        imageArray.add("data:image/jpeg;base64," + Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT));
                    }

                    registerComplaint(imageArray);
                } else {
                    registerComplaint(imageArray);
                }


            }
        }
    }

    private void registerComplaint(List<String> imageArray) {
        if (isNetworkConnected()){
            RequestParams.ComplaintRequest request = new RequestParams().new ComplaintRequest(
                    appPreferanceStore.getLanguage() ? "en" : "ar", mCatId, commentBox.getText() == null ? " " : commentBox.getText().toString(),
                    imageArray, String.format("%s,%s", lattitude, longitude));

            ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);

            //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
            Call<CompllaintUpdateResponseModel> call = apiInterface.registerComplaintService(request);
            call.enqueue(new Callback<CompllaintUpdateResponseModel>() {
                @Override
                public void onResponse(Call<CompllaintUpdateResponseModel> call, Response<CompllaintUpdateResponseModel> response) {
                    endLoading();
                    if (response.body() != null) {
                        if (response.body().isStatus()) {
                            showDilog(response.body().getMessage());
                        } else {
                            showDilog(response.body().getMessage());
                        }
                    } else {
                        getActivity().onBackPressed();
                    }

                }

                @Override
                public void onFailure(Call<CompllaintUpdateResponseModel> call, Throwable t) {
                    endLoading();
                    showDilog(getResources().getString(R.string.try_again_later));
                }
            });

        }else {
            showErrorDilog(false);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        /*if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(getContext(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }*/

        switch (requestCode) {
            case REQUEST_CODE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocations();
                } else {
                    showMessage("Please Enable GPS and Internet");
                }
                break;

            case MY_CAMERA_PERMISSION_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(), "camera permission granted", Toast.LENGTH_LONG).show();
                    Intent cameraIntent = new
                            Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                } else {
                    Toast.makeText(getContext(), "camera permission denied", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 1:
                try {
                    Uri selectedImage = data.getData();
                    //imageview.setImageURI(selectedImage);

                    Bitmap thumbnail = MediaStore.Images.Media.getBitmap(
                            getActivity().getContentResolver(), selectedImage);
                    images.add(thumbnail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//            images.add(srcBmp);
                mAdapter.setData(images);
                recyclerViewImages.setVisibility(View.VISIBLE);
                break;

                default:
                    if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

                        try {
                            Bitmap thumbnail = MediaStore.Images.Media.getBitmap(
                                    getActivity().getContentResolver(), imageUri);
                            images.add(thumbnail);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//            images.add(srcBmp);
                        mAdapter.setData(images);
                        recyclerViewImages.setVisibility(View.VISIBLE);
                    }
                    break;
        }



            /*if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

                try {
                    Bitmap thumbnail = MediaStore.Images.Media.getBitmap(
                            getActivity().getContentResolver(), imageUri);
                    images.add(thumbnail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//            images.add(srcBmp);
                mAdapter.setData(images);
                recyclerViewImages.setVisibility(View.VISIBLE);
            }*/

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void interactClick(final Bitmap bitmapImage) {
        final Dialog dialog = new Dialog(getContext(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.image_dialog);

        ImageButton delete = dialog.findViewById(R.id.button_delete);
        final ImageView imageView = dialog.findViewById(R.id.image);

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

                if (images.size()<3)
                    takeImage.setVisibility(View.VISIBLE);
                else
                    takeImage.setVisibility(View.GONE);


                dialog.dismiss();
            }
        });

        dialog.show();
    }

    /*@Override
    public void onLocationChanged(Location location) {
        lattitude = location.getLatitude();
        longitude = location.getLongitude();

        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.ENGLISH);
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            textAddress.setText(addresses.get(0).getAddressLine(0) + ", " +
                    addresses.get(0).getAddressLine(1) + ", " + addresses.get(0).getAddressLine(2));
        } catch (Exception e) {

        }

    }*/

   /* @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        showMessage("Please Enable GPS and Internet");
    }

    @Override
    public void onProviderDisabled(String provider) {

    }*/

    /*public class ViewDialog implements ComplaintCategoryAdapter.CategoryAdapterListener {
        Dialog dialog;

        public void showDialog() {
            dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.dialog_reg_complaint);
            RecyclerView recyclerView = dialog.findViewById(R.id.recycler_view);
            ComplaintCategoryAdapter mAdapter = new ComplaintCategoryAdapter(listItems, catIds, this);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);
            dialog.show();

        }

        @Override
        public void interactClick(String mCategory, int catId) {
            mCatId = catId;
            category.setText(mCategory);
            dialog.dismiss();
        }
    }*/

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public interface OnFragmentInteractionListener {

        void setTitle(String title);
    }
}
