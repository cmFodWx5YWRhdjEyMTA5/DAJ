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

    private static final String NAME = "Name";

    private static final String USER_NAME = "Username";

    private static final String GENDER = "Gender";

    private static final String DOB = "DateOfBirth";

    private static final String JOD = "JoiningDate";

    private static final String EMAIL = "email";

    private static final String PERMANENT_ADDRESS = "PermanentAddress";

    private static final String OCCUPATION = "Occupation";

    private static final String MOB_NO = "MobileNo";

    private static final String MARITAL_STATUS = "MaritalStatus";

    private static final String NATIONALITY = "Nationality";

    private static final String STATUSBOOLEAN = "StatusBoolean";

    private static final String VILLANO = "VillaNo";


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

    public void setData(String name, String userName, int gender, String dob,String jod, String email,
                        String address, String occupation, String mobNo, int maritalStatus, String nationality, int statusBoolean, String villaNo){
        editor = pref.edit();
        editor.putString(NAME, name);
        editor.putString(USER_NAME, userName);
        editor.putInt(GENDER, gender);
        editor.putString(DOB, dob);
        editor.putString(JOD, jod);
        editor.putString(EMAIL, email);
        editor.putString(PERMANENT_ADDRESS, address);
        editor.putString(OCCUPATION, occupation);
        editor.putString(MOB_NO, mobNo);
        editor.putInt(MARITAL_STATUS, maritalStatus);
        editor.putString(NATIONALITY, nationality);
        editor.putInt(MARITAL_STATUS, maritalStatus);
        editor.putString(NATIONALITY, nationality);
        editor.putInt(STATUSBOOLEAN, statusBoolean);
        editor.putString(VILLANO, villaNo);
        editor.apply();
    }

    public String getName(){
        return pref.getString(NAME, null);
    }

    public String getUserName(){
        return pref.getString(USER_NAME, null);
    }

    public int getGender(){
        return pref.getInt(GENDER, 0);
    }

    public String getDob(){
        return pref.getString(DOB, null);
    }

    public String getJod(){
        return pref.getString(JOD, null);
    }

    public String getEmail(){
        return pref.getString(EMAIL, null);
    }

    public String getAddress(){
        return pref.getString(PERMANENT_ADDRESS, null);
    }

    public String getOccupation(){
        return pref.getString(OCCUPATION, null);
    }

    public String getMobNo(){
        return pref.getString(MOB_NO, null);
    }

    public int getMaritalStatus(){
        return pref.getInt(MARITAL_STATUS, 0);
    }

    public String getNationality(){
        return pref.getString(NATIONALITY, null);
    }

    public int getStatusBoolean(){
        return pref.getInt(MARITAL_STATUS, 0);
    }

    public String getVillaNo(){
        return pref.getString(VILLANO, null);
    }

    /**
     * Get Language
     * */
    public String getToken(){
        return pref.getString(AUTH_TOKEN, null);
    }




    public void changeLocaleLanguage(boolean isEnglish) {
        String languageToLoad;
        if (isEnglish){
            languageToLoad  = "en"; // English
        }else {
            languageToLoad  = "ar"; // Arabic
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
