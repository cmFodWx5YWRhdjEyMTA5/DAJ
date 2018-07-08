package com.tinnovat.app.daj.features.authentication;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tinnovat.app.daj.Activity.ResetPasswordActivity;
import com.tinnovat.app.daj.BaseFragment;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.AppPreferanceStore;
import com.tinnovat.app.daj.features.dashboard.MainActivity;

import java.util.Locale;

/**
 * Created by Anjali on 08-07-2018.
 */

public class LoginFragment extends BaseFragment {

    private boolean language;
    private AppPreferanceStore appPreferenceStore;

    public static Fragment getInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_login, container, false);
        appPreferenceStore = new AppPreferanceStore(getActivity());

        initialiseViews(rootView);
        return rootView;
    }

    private void initialiseViews(View rootView) {
        String languageToLoad;
        if (appPreferenceStore.getLanguage()){
            languageToLoad  = "en"; // your language
        }else {
            languageToLoad  = "ar"; // your language
        }

        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getActivity().getResources().updateConfiguration(config,
                getActivity().getResources().getDisplayMetrics());

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signIn:
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
            case R.id.english:
                if (!appPreferenceStore.getLanguage()) {
                    appPreferenceStore.setLanguage(true);
                    getActivity().finish();
                    startActivity(getActivity().getIntent());
                }
                break;
            case R.id.arabic:
                Toast.makeText(getActivity(), "ar", Toast.LENGTH_SHORT).show();

                if(appPreferenceStore.getLanguage()){
                    appPreferenceStore.setLanguage(false);
                    this.getActivity().finish();
                    startActivity(getActivity().getIntent());
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
                intent = new Intent(this.getActivity(), ResetPasswordActivity.class);
                startActivity(intent);
                break;
        }
    }


}
