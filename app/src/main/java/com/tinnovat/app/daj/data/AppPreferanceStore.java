package com.tinnovat.app.daj.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.LocaleList;

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

    private static final String AUTH_TOKEN = "AUTH_TOKEN";


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

    /**
     * Set Language
     * */
    public void setToken(String token){
        editor = pref.edit();
        editor.putString(AUTH_TOKEN, token);
        editor.apply();
    }

    /**
     * Get Laguage
     * */
    public String getToken(){
        return pref.getString(AUTH_TOKEN, null);
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
        /*Locale locale = null;
        LocaleList locale1 = null;
        Locale newLocale = new Locale(languageToLoad);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            locale1 = new LocaleList(newLocale);
            LocaleList.setDefault(locale1);
        }else {
            locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
        }*/

        Configuration config = new Configuration();
        config.locale = locale;
        _context.getResources().updateConfiguration(config,
                _context.getResources().getDisplayMetrics());

      /*  configuration.setLocale(newLocale);
        LocaleList localeList = new LocaleList(newLocale);
        LocaleList.setDefault(localeList);
        configuration.setLocales(localeList);
        context = context.createConfigurationContext(configuration);*/
    }

    /**
     * Get Laguage
     * */
    public boolean getLanguage(){
        return pref.getBoolean(IS_ENGLISH, false);
    }

}
