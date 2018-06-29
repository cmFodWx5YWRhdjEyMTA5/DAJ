package com.tinnovat.app.daj.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tinnovat.app.daj.BaseActivity;
import com.tinnovat.app.daj.R;

import org.w3c.dom.Text;

import java.util.Locale;

public class LoginActivity extends BaseActivity {
    Button signIn;
    TextView forgotPassword;
    TextView english;
    TextView arabic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String languageToLoad;
        if (getLanguage()){
            languageToLoad  = "en"; // your language
        }else {
            languageToLoad  = "ar"; // your language
        }

        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        setContentView(R.layout.activity_login);
        signIn = findViewById(R.id.signIn);
        forgotPassword = findViewById(R.id.forgotPassword);

        english = findViewById(R.id.english);
        arabic = findViewById(R.id.arabic);

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!getLanguage()) {
                    setLanguage(true);
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "ar", Toast.LENGTH_SHORT).show();

                if(getLanguage()){
                    setLanguage(false);
                    finish();
                    startActivity(getIntent());
                }

            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(i);
            }
        });
    }
}
