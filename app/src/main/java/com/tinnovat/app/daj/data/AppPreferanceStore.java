package com.tinnovat.app.daj.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * Created by Rahul on 09-09-2017.
 */

public class AppPreferanceStore {

    private SharedPreferences pref;

    private SharedPreferences.Editor editor;

    private Context _context;

    private static final int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "DAJSharedPref";

    private static final String IS_ENGLISH = "IS_ENGLISH";


    public AppPreferanceStore(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
    }

    /**
     * Set Language
     * */
    public void setLanguage(boolean isEnglish){
        editor = pref.edit();
        editor.putBoolean(IS_ENGLISH, isEnglish);
        editor.apply();
        changeLocaleLanguage(isEnglish);
    }

    public void changeLocaleLanguage(boolean isEnglish) {
        String languageToLoad;
        if (isEnglish){
            languageToLoad  = "en"; // your language
        }else {
            languageToLoad  = "ar"; // your language
        }
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        _context.getResources().updateConfiguration(config,
                _context.getResources().getDisplayMetrics());
    }

    /**
     * Get Laguage
     * */
    public boolean getLanguage(){
        return pref.getBoolean(IS_ENGLISH, false);
    }

}
