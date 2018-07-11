package com.tinnovat.app.daj.features.authentication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tinnovat.app.daj.BaseFragment;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.EventListModel;
import com.tinnovat.app.daj.data.network.model.LoginResponseModel;
import com.tinnovat.app.daj.data.network.model.RequestParams;
import com.tinnovat.app.daj.features.dashboard.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Rahul on 08-07-2018.
 */

public class LoginFragment extends BaseFragment {

    private boolean language;
    private AppPreferanceStore appPreferanceStore;

    public static Fragment getInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initialiseViews(view);
        // super.onCreateView(inflater,container,savedInstanceState);
        return view;
    }

    @Override
    public void initialiseViews(View rootView) {

        appPreferanceStore = new AppPreferanceStore(getContext());
        Button signIn = rootView.findViewById(R.id.signIn);
        TextView forgotPassword = rootView.findViewById(R.id.forgotPassword);

        ImageView facebook = rootView.findViewById(R.id.facebook);
        ImageView twitter = rootView.findViewById(R.id.twitter);
        ImageView instagram = rootView.findViewById(R.id.instagram);

        TextView english = rootView.findViewById(R.id.english);
        TextView arabic = rootView.findViewById(R.id.arabic);

        signIn.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);

        facebook.setOnClickListener(this);
        twitter.setOnClickListener(this);
        instagram.setOnClickListener(this);

        english.setOnClickListener(this);
        arabic.setOnClickListener(this);
    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.signIn:
               // fetchEventList();
                invokeLoginService();
                break;

            case R.id.english:
                if (!getLanguage()) {
                    setLanguage(true);
                    if (getActivity() != null) {

                        getActivity().finish();
                        startActivity(getActivity().getIntent());
                        //getActivity().recreate();
                    }
                }
                break;

            case R.id.arabic:
                showMessage("ar");
                if (getLanguage()) {

                    setLanguage(false);
                    if (getActivity() != null) {
                        getActivity().finish();
                        startActivity(getActivity().getIntent());
                        // getActivity().recreate();

                    }
                }

                break;

            case R.id.facebook:
                Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/MadinahKEC/"));
                startActivity(followIntent);
                break;

            case R.id.twitter:
                followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/MadinahKEC"));
                startActivity(followIntent);
                break;

            case R.id.instagram:
                followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/madinahkec/"));
                startActivity(followIntent);
                break;

            case R.id.forgotPassword:
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, ResetPasswordFragment.getInstance()).addToBackStack(null);
                transaction.commit();
//                intent = new Intent(this.getActivity(), ResetPasswordActivity.class);
//                startActivity(intent);
                break;
        }
    }

    private void invokeLoginService() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        RequestParams.LoginReequest loginReequest = new RequestParams().new LoginReequest("Jainy", "123456", "en");
        Call<LoginResponseModel> call = apiInterface.login(loginReequest);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                showMessage("Login Successful");
                if (response.body() != null && response.body().getData() != null) {
                    seData(response);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    if (getActivity() != null)
                        getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                showMessage("Login Failed");
            }
        });
    }

    private void seData(Response<LoginResponseModel> response){
        appPreferanceStore.setToken(response.body().getData().getToken());
        appPreferanceStore.setData(""+response.body().getData().getFirstName()+" "+response.body().getData().getMiddleName()+" "+response.body().getData().getLastName(),
                response.body().getData().getUsername(),response.body().getData().getGender(),response.body().getData().getDateOfBirth(),response.body().getData().getJoiningDate(),response.body().getData().getEmail(),
                response.body().getData().getPermanentAddress(),response.body().getData().getOccupation(),response.body().getData().getMobileNo(),response.body().getData().getMaritalStatus(),response.body().getData().getNationality(),response.body().getData().getStatusBoolean(),response.body().getData().getVillaNo());

    }

    private void fetchEventList() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImQ0MTVlYjEyMDE3NmM2MDNmNjIyMThmZjhiZjA1ZDM5ZGU1ZTNjMTQxNDZiN2I0OWExN2NiYzJmNjllZTBkZWNjZTgxOTQwMzEwNGIzM2I0In0.eyJhdWQiOiIyIiwianRpIjoiZDQxNWViMTIwMTc2YzYwM2Y2MjIxOGZmOGJmMDVkMzlkZTVlM2MxNDE0NmI3YjQ5YTE3Y2JjMmY2OWVlMGRlY2NlODE5NDAzMTA0YjMzYjQiLCJpYXQiOjE1MjkzODM1MDcsIm5iZiI6MTUyOTM4MzUwNywiZXhwIjoxNTYwOTE5NTA3LCJzdWIiOiIyIiwic2NvcGVzIjpbXX0.Gg02Gdo4Gw-o6MzubJsJutKHbgjEG4anz0nLXzdVWiz4mxhc2zv84Aa8nE_mrgDNswxl6mex6fz-Esk2NXAva1auBHS1g4ZyQ9eud49_zUC1uwdPSXwn9Ebj9SDNMol0q6I84FTH8YUNyM_mlBAh5Z2knMgnGbBnmJzD-cOLokhhoV5UfEnfKl5gKEeSikgh4ptGWAmOCi6M_UMY89oQ24xbWRmDt3L8gHNJh2zUtKhEtxm4iEnwaMWW3RlVHJiWnqrKSOE6EJV1c1p-sFOMqMLmmlt-Ia8SUUVkiAy19w6uwdkQ6LlMd85GdtBHZdxazyiTyX4imib5SIxZ3sTPoCGxjIeV5rMhGEhwUNseqUilPUZnXN8UQ7pGiF7T31v6F95t1P5RK0_yShSJm1_ptWirZDXNbCW6GUKMkJIMkKeODkPbczY1LP1GQLdJytD_3GuHC4rOrNeiaHJyQp__1I6YTYqbxNUuMPnWFedDcKZAFyfyv945nSmIfbByTbMbijDg25gYaTrrRkMVbUMt9P5jX2KlDNRYJlrDlUBrf7MpFmAFoLyakVpiqIrX1OCIsRIzw5F4VX2pxc3UFGcI_EFnO2BlmdQtIMmjJn74qhwtz2hHgNVOWwhwpTtXNNkivm5Qq6GCQhBPi4L9JTXZfwrDEn_Rdi2-GglmELG4YFA";

        ApiInterface apiInterface = ApiClient.getAuthClient(token).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<EventListModel> call = apiInterface.getEventsAndNews("en");
        call.enqueue(new Callback<EventListModel>() {
            @Override
            public void onResponse(Call<EventListModel> call, Response<EventListModel> response) {
                showMessage("Data Fetched Successfully");
            }

            @Override
            public void onFailure(Call<EventListModel> call, Throwable t) {

                showMessage("Login Failed");
            }
        });
    }
}
