package kr.co.bluebright.www.myexperiment.app;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class KeyConstants {

    public static final String TAG_TITLE_MY_EXPERIMENT = "kr.co.bluebright.www.myexperiment";
    public static final String TAG_EXTRA = ".extra.";
    public static final String TAG_ACTION = ".action.";
    public static final String TAG_KEY = ".key.";
    public static final String TAG_PREF = ".pref.";


    public static final String PREF
            = TAG_TITLE_MY_EXPERIMENT + TAG_PREF + "PREF";
    public static final String KEY_PREF_LANGUAGE
            = TAG_TITLE_MY_EXPERIMENT + TAG_KEY + TAG_PREF + "LANGUAGE";

    public static final String KEY_LANGUAGE
            = TAG_TITLE_MY_EXPERIMENT + TAG_KEY + "LANGUAGE";


    public static final String LANGUAGE_KOREAN = "ko";
    public static final String LANGUAGE_ENGLISH = "en";
    public static final String LANGUAGE_CHINA = "zh";

    @StringDef({LANGUAGE_KOREAN,
            LANGUAGE_ENGLISH,
            LANGUAGE_CHINA})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LANGUAGE {
    }
}
