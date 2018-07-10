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
import com.tinnovat.app.daj.features.dashboard.MainActivity;

/**
 * Created by Rahul on 08-07-2018.
 */

public class LoginFragment extends BaseFragment {

    private boolean language;

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

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                if (getActivity() != null)
                    getActivity().finish();
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
}
