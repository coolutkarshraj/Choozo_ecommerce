package com.io.choozo.localStorage;

import android.content.Context;
import android.content.SharedPreferences;



public class PreferenceManager {
    private static PreferenceManager instance;
    private SharedPreferences storage;
    private SharedPreferences.Editor ed;
    public static final String loginData = "loginData";
    public static final String token = "token";
    public static String email = "email";
    public static final String profileData = "profileData";
    public static String isFirstLaunch = "isFirstLanunch";


    public PreferenceManager(Context context) {
        storage = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static PreferenceManager getInstance(Context context) {
        if (instance == null)
            instance = new PreferenceManager(context);
        return instance;
    }

    public void putString(String name, String value) {
        storage.edit().putString(name, String.valueOf(value)).apply();
    }

    public String getString(String name) {
        return storage.getString(name, "");
    }

    public void putInt(String name, int value) {
        storage.edit().putInt(name, value).apply();
    }

    public int getInt(String name) {
        return storage.getInt(name, -1);
    }


    public void clearAll() {
        storage.edit().clear().apply();
    }
    public void editor(){
    ed=storage.edit();
    }

    public void commit()
    {
      ed.commit();
      ed.apply();
    }



}

