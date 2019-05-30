package kr.co.bluebright.www.myexperiment.common.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 */
public class PreferenceController {
    // Shared Preferences
    private SharedPreferences pref;


    public PreferenceController(Context context, String name) {
        pref = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public void putString(String key, String flag) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, flag);
        editor.apply();
    }

    public String getString(String key) {
        return pref.getString(key, null);
    }

    public void putBoolean(String key, boolean flag) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, flag);
        // commit changes
        editor.apply();
    }

    public boolean getBoolean(String key) {
        return pref.getBoolean(key, false);
    }

    public void putInt(String key, int flag) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, flag);
        // commit changes
        editor.apply();
    }

    public int getInt(String key) {
        return pref.getInt(key, 0);
    }

}
