package com.tinnovat.app.daj.features.EmergencyContact;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.ContactResponseModel;
import com.tinnovat.app.daj.features.EmergencyContact.ContactListAdapter;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmergencyContactActivity extends BaseActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.emergency_contact));

        fetchContactList();
    }

    private void fetchContactList() {

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<ContactResponseModel> call = apiInterface.getContactList("en");
        call.enqueue(new Callback<ContactResponseModel>() {
            @Override
            public void onResponse(Call<ContactResponseModel> call, Response<ContactResponseModel> response) {
                showMessage("ContactList Successfully");
                setData(response);
            }

            @Override
            public void onFailure(Call<ContactResponseModel> call, Throwable t) {

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
