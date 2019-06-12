package kr.co.bluebright.www.myexperiment.common.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 */
public class PreferenceController {
    @IntDef(value = {
            TYPE_DEFAULT,
            TYPE_CUSTOM
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface PREFERENCE_TYPE {
    }

    public static final int TYPE_DEFAULT = -1;
    public static final int TYPE_CUSTOM = 0;


    private Context context;
    private SharedPreferences pref;

    @PREFERENCE_TYPE
    private final int preferenceType;

    public PreferenceController(Context context, @Nullable String name, int prefMode) {
        this.context = context;

        if (name != null && name.length() > 0) {
            preferenceType = TYPE_CUSTOM;
            pref = context.getSharedPreferences(name, prefMode);
        } else {
            preferenceType = TYPE_DEFAULT;
            pref = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    @PREFERENCE_TYPE
    public int getPreferenceType() {
        return preferenceType;
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

    public void putBoolean(@StringRes int stringResKey, boolean flag) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(context.getString(stringResKey), flag);
        // commit changes
        editor.apply();
    }

    public boolean getBoolean(String key) {
        return pref.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return pref.getBoolean(key, defaultValue);
    }

    public boolean getBoolean(@StringRes int stringResKey) {
        return pref.getBoolean(context.getString(stringResKey), false);
    }

    public boolean getBoolean(@StringRes int stringResKey, boolean defaultValue) {
        return pref.getBoolean(context.getString(stringResKey), defaultValue);
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
