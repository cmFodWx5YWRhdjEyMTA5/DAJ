package com.tinnovat.app.daj.features.authentication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pchmn.androidverify.Form;
import com.tinnovat.app.daj.BaseFragment;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.LoginResponseModel;
import com.tinnovat.app.daj.data.network.model.RequestParams;
import com.tinnovat.app.daj.features.dashboard.MainActivity;

import java.util.Objects;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Rahul on 08-07-2018.
 */

public class LoginFragment extends BaseFragment {

    private boolean language;
    private AppPreferanceStore appPreferanceStore;

    Form form;
   private EditText userName ;
   private EditText password;

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

         userName = rootView.findViewById(R.id.userName);
        password = rootView.findViewById(R.id.password);

        form = new Form.Builder(getContext(), rootView)
                .showErrors(true)
                .build();

        //todo tooltip
       /* new SimpleTooltip.Builder(getActivity())
                .anchorView(facebook)
                .text("facebook")
                .gravity(Gravity.TOP)
                .animated(true)
                .transparentOverlay(false)
                .build()
                .show();*/

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
                doValidation();

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
                Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/DarJewar/?ref=br_rs"));
                startActivity(followIntent);
                break;

            case R.id.twitter:
                followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/darjewar?lang=en"));
                startActivity(followIntent);
                break;

            case R.id.instagram:
                followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/darjewar/"));
                startActivity(followIntent);
                break;

            case R.id.forgotPassword:
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, ResetPasswordFragment.getInstance()).addToBackStack(null);
                transaction.commit();

                break;
        }
    }

    private void doValidation(){


// or initiate from a fragment or from what you want by providing your own root view
      /*  Form form = new Form.Builder(getContext(), v)
                .showErrors(true)
                .build();*/

// validate the form
        if(form.isValid()) {
            // the form is valid
            invokeLoginService();
        }
        else {
            // the form is not valid
        }
    }

    private void showDilog(String message){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void invokeLoginService() {



        startLoading();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        RequestParams.LoginReequest loginReequest = new RequestParams().new LoginReequest(userName.getText().toString(), password.getText().toString(), appPreferanceStore.getLanguage() ? "en" : "ar");
        Call<LoginResponseModel> call = apiInterface.login(loginReequest);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getData() != null) {
                        seData(response);

                        if (response.body().getData().getStatusBoolean() == 0){
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.putExtra("status",true);
                            startActivity(intent);
                        }else {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.putExtra("status",false);
                            startActivity(intent);
                        }
                        if (getActivity() != null) {
                            getActivity().finish();
                            //endLoading();
                        }
                    }else if (response.body().getMessage() != null){
                        endLoading();
                        showDilog(response.body().getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                endLoading();
                showDilog("Login Failed Please Try Again Later!");
            }
        });
    }

    private void seData(Response<LoginResponseModel> response){
        appPreferanceStore.setToken(response.body().getData().getToken());
        appPreferanceStore.setDataLogin(""+response.body().getData().getFirstName()+" "+response.body().getData().getMiddleName()+" "+response.body().getData().getLastName(),
                response.body().getData().getUsername(),response.body().getData().getGender(),response.body().getData().getDateOfBirth(),response.body().getData().getJoiningDate(),response.body().getData().getEmail(),
                response.body().getData().getPermanentAddress(),response.body().getData().getOccupation(),response.body().getData().getMobileNo(),response.body().getData().getMaritalStatus(),response.body().getData().getNationality(),response.body().getData().getStatusBoolean(),response.body().getData().getVillaNo());

    }


}
