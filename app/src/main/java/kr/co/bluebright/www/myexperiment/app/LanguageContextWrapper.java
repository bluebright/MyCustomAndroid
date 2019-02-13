package kr.co.bluebright.www.myexperiment.app;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Build;

import java.util.Locale;

import kr.co.bluebright.www.myexperiment.pref.PreferenceController;

/**
 * Attach context that language option
 */
public class LanguageContextWrapper //extends ContextWrapper
{
    public static final String TAG = LanguageContextWrapper.class.getSimpleName();

    /*public LanguageContextWrapper(Context context) {
        super(context);
    }*/

    /**
     * Wrap language at BaseContext
     *
     * @param context BaseContext
     * @return {@code Context} which wrap language
     * @see ContextWrapper
     * @see Context
     * @see Configuration
     * @see Locale
     */
    public static Context wrap(Context context) {
        PreferenceController mPreferenceController = new PreferenceController(context.getApplicationContext(), KeyConstants.KEY_PREF_LANGUAGE);
        Configuration config = context.getResources().getConfiguration();
        Locale sysLocale;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sysLocale = getSystemLocale(config);
        } else {
            sysLocale = getSystemLocaleLegacy(config);
        }

        String prefLanguage = mPreferenceController.getString(KeyConstants.KEY_LANGUAGE);
        String sysLanguage = sysLocale.getLanguage();

        if (!sysLanguage.equals(prefLanguage)) {
            Locale locale;
            if (prefLanguage == null) {
                locale = new Locale(sysLanguage);
                mPreferenceController.putString(KeyConstants.KEY_LANGUAGE, sysLanguage);
            } else {
                locale = new Locale(prefLanguage);
            }

            Locale.setDefault(locale);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                setSystemLocale(config, locale);
            } else {
                setSystemLocaleLegacy(config, locale);
            }
        }

        return context.createConfigurationContext(config);
    }

    /**
     * Get application setting language.
     *
     * @param context BaseContext
     * @see Context
     * @see Configuration
     * @see Locale
     */
    public static String getAppLanguage(Context context) {
        Configuration config = context.getResources().getConfiguration();
        Locale sysLocale;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sysLocale = getSystemLocale(config);
        } else {
            sysLocale = getSystemLocaleLegacy(config);
        }

        return sysLocale.getLanguage();
    }

    @SuppressWarnings("deprecation")
    private static Locale getSystemLocaleLegacy(Configuration config) {
        return config.locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Locale getSystemLocale(Configuration config) {
        return config.getLocales().get(0);
    }

    @SuppressWarnings("deprecation")
    private static void setSystemLocaleLegacy(Configuration config, Locale locale) {
        config.locale = locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static void setSystemLocale(Configuration config, Locale locale) {
        config.setLocale(locale);
    }
}
