package kr.co.bluebright.www.myexperiment.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

public class MyLocationManager {

    private LocationManager locationManager;
    private boolean managerValid;


    MyLocationManager(Context context){
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        managerValid = locationManager != null;
    }

    public boolean isManagerValid() {
        return managerValid;
    }

    public boolean isGpsEnabled() {
        return isManagerValid() && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public void startGpsSettingActivity(Activity activity, int requestCode){
        Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        activity.startActivityForResult(intent, requestCode);
    }
}
