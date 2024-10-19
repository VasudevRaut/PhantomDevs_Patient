package com.example.pccoe_oct_2024_hack.ShatedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;

public class SharedPrefsHelper {

    private static final String PREFS_NAME = "app_prefs";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public SharedPrefsHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();  // Initialize Gson
    }

    // Save object in SharedPreferences
    public <T> void saveObject(String key, T object) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(object);  // Convert object to JSON string
        editor.putString(key, json);
        editor.apply();  // Save asynchronously
    }

    // Retrieve object from SharedPreferences
    public <T> T getObject(String key, Class<T> classType) {
        String json = sharedPreferences.getString(key, null);  // Get JSON string
        if (json != null) {
            return gson.fromJson(json, classType);  // Convert JSON string back to object
        } else {
            return null;  // Return null if no object is found
        }
    }

    // Clear object from SharedPreferences
    public void removeObject(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }
}
