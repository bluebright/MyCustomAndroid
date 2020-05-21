package kr.co.bluebright.www.myexperiment.common.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

/**
 * @see <a href="http://sjava.net/2014/11/android-dialog%EB%A5%BC-%EC%95%88%EC%A0%84%ED%95%98%EA%B3%A0-%EA%B0%84%EB%8B%A8%ED%95%98%EA%B2%8C-%EC%A2%85%EB%A3%8C%ED%95%98%EA%B8%B0/">WordPress blog</a>
 */
public class DialogDismisser {

    public static void dismiss(DialogInterface d) {
        if (d == null)
            return;

        try {
            if (d instanceof AlertDialog) {
                if (((AlertDialog) d).isShowing())
                    d.dismiss();
            } else if (d instanceof ProgressDialog) {
                if (((ProgressDialog) d).isShowing())
                    d.dismiss();
            } else if (d instanceof Dialog) {
                if (((Dialog) d).isShowing())
                    d.dismiss();
            }
        } catch (Exception e) {
            Log.e("Dismiss error", e.getMessage());
        }
    }

    public static void dismissAll(DialogInterface... d) {
        for (DialogInterface element : d) {
            dismiss(element);
        }
    }
}
