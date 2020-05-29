package kr.co.bluebright.www.myexperiment.core;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;


public class MyApplication extends Application {

    private static MyApplication instance;

    public MyNetworkManager myNetworkManager;
    public MyLocationManager myLocationManager;
    public ActivityManager manager;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        myNetworkManager = new MyNetworkManager(this);
        myLocationManager = new MyLocationManager(this);

        manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    }

    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * @see <a href="http://susemi99.kr/5149/">Blog</a>
     * @see <a href="https://namget.tistory.com/entry/%EC%BD%94%ED%8B%80%EB%A6%B0-mvvm%ED%8C%A8%ED%84%B4-%EC%86%8D-application-context-%EA%B0%80%EC%A0%B8%EC%98%A4%EA%B8%B0">Tistory blog</a>
     */
    public Context getContextFromApp() {
        return getApplicationContext();
    }

    @SuppressWarnings("deprecation")
    public boolean isTargetServiceRunning(Class<?> targetServiceClass) {

        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (targetServiceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
