package com.tinnovat.app.daj.features.contact;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.ContactResponseModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmergencyContactActivity extends BaseActivity {

    private AppPreferanceStore appPreferanceStore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.emergency_contact));
        appPreferanceStore = new AppPreferanceStore(this);
        fetchContactList();
    }

    private void fetchContactList() {
        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<ContactResponseModel> call = apiInterface.getContactList(appPreferanceStore.getLanguage() ? "en" : "ar");
        call.enqueue(new Callback<ContactResponseModel>() {
            @Override
            public void onResponse(Call<ContactResponseModel> call, Response<ContactResponseModel> response) {
                endLoading();
                showMessage("ContactList Successfully");
                setData(response);
            }

            @Override
            public void onFailure(Call<ContactResponseModel> call, Throwable t) {

                endLoading();
                showMessage("ContactList Failed");
            }
        });
    }

    public void setData(Response<ContactResponseModel> response){
        RecyclerView recyclerView= findViewById(R.id.recycler_view);
        ContactListAdapter mAdapter = new ContactListAdapter(response);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initialiseViews() {

    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public void onClick(View v) {

    }
}
