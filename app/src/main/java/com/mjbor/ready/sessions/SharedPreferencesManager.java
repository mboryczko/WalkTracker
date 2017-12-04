package com.mjbor.ready.sessions;

/**
 * Created by mjbor on 8/28/2017.
 */


import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class SharedPreferencesManager implements ISharedPreferencesManager {
    // Shared Preferences
    SharedPreferences pref;
    Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "com.mjbor";


    public static final String DISTANCE = "distance";
    public static final String SPEED = "speed";

    // Constructor
    public SharedPreferencesManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void savePreferences(String distance, String speed){
        editor.putString(DISTANCE, distance);
        editor.putString(SPEED, speed);

        editor.commit();
    }


    public String getDistance(){

        return pref.getString(DISTANCE, "km");
    }

    public String getSpeed(){

        return pref.getString(SPEED, "km/h");
    }


}
