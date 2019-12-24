package kr.co.bluebright.www.myexperiment.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import androidx.annotation.IntDef;
import androidx.core.util.Pair;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Android
 * <p>
 * Check network state
 * <p>
 * Origin source from my blog
 * http://sunjin220.blog.me/221149414526
 */

public class NetworkHandler {

    private static final String TAG = NetworkHandler.class.getSimpleName();

    public static final int NETWORK_TYPE_NONE = 1111;
    public static final int NETWORK_TYPE_WIFI = 2222;
    public static final int NETWORK_TYPE_MOBILE = 3333;

    @IntDef({
            NETWORK_TYPE_NONE,
            NETWORK_TYPE_WIFI,
            NETWORK_TYPE_MOBILE
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface NETWORK_TYPE {
    }

    private ConnectivityManager connectivityManager;
    private WifiManager wifiManager;

    private boolean connectivityManagerValid;
    private boolean wifiManagerValid;


    NetworkHandler(Context context) {
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        connectivityManagerValid = connectivityManager != null;
        wifiManagerValid = wifiManager != null;

    }

    public boolean isConnectivityManagerValid() {
        return connectivityManagerValid;
    }

    public boolean isWifiManagerValid() {
        return wifiManagerValid;
    }

    public Pair<Integer, Boolean> getNetworkTypeAndState() {

        @NETWORK_TYPE int networkType = NETWORK_TYPE_NONE;
        boolean connected = false;

        try {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo != null) {
                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    networkType = NETWORK_TYPE_WIFI;
                    connected = networkInfo.isConnected();
                } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    networkType = NETWORK_TYPE_MOBILE;
                    connected = networkInfo.isConnected();
                } else {
                    Log.d(TAG, "Network type is " + networkInfo.getTypeName());
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            networkType = NETWORK_TYPE_NONE;
            connected = false;
        }

        return Pair.create(networkType, connected);
    }

    public boolean turnWiFi(boolean enable) {
        if (isWifiManagerValid()) {
            wifiManager.setWifiEnabled(enable);
            return true;
        } else {
            return false;
        }
    }


}