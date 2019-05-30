package kr.co.bluebright.www.myexperiment.common.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;

import java.lang.reflect.Field;


public class CommonFunc {

    /**
     * Get resource id using String only
     *
     * @author Daniel
     * @see <a href="https://stackoverflow.com/questions/4427608/android-getting-resource-id-from-string">Stack overflow</a>
     * @see <a href="http://daniel-codes.blogspot.com/2009/12/dynamically-retrieving-resources-in.html">Blog spot</a>
     */
    public static int getId(@NonNull String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (NullPointerException
                | NoSuchFieldException
                | IllegalArgumentException
                | IllegalAccessException
                e) {
            //throw new RuntimeException("No resource ID found for: " + resourceName + " / " + c, e);
            return -1;
        }
    }

    /**
     * Get uri to any resource type
     *
     * @param context context
     * @param resId resource id
     * @return Uri to resource by given id Or null if error occurred
     * @throws Resources.NotFoundException if the given ID does not exist.
     * @see <a href="https://stackoverflow.com/a/36062748/7017299">Stack overflow</a>
     */
    public static Uri getUriFromResource(@NonNull Context context, @AnyRes int resId) {

        try {
            //Return a Resources instance for your application's package.
            Resources res = context.getResources();

            return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                    "://" + res.getResourcePackageName(resId)
                    + '/' + res.getResourceTypeName(resId)
                    + '/' + res.getResourceEntryName(resId));
        } catch (Resources.NotFoundException | NullPointerException e) {
            return null;
        }
    }


    /**
     * Get uri to any resource type<br>
     * <p>
     * This method using {@link CommonFunc#getId(String, Class)} and {@link CommonFunc#getUriFromResource(Context, int)}
     * </p>
     *
     * @param context context
     * @param resourceName resource type name
     * @return Uri to resource by given id Or null if error occurred
     * @see CommonFunc#getId(String, Class)
     * @see CommonFunc#getUriFromResource(Context, int)
     */
    public static Uri getUriFromResource(@NonNull Context context, @NonNull String resourceName, Class<?> c) {

        try {
            int resourceId = getId(resourceName, c);
            return getUriFromResource(context, resourceId);
        } catch (NullPointerException
                | IllegalArgumentException
                | Resources.NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

