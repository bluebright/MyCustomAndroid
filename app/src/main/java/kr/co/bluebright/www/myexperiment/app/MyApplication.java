package kr.co.bluebright.www.myexperiment.app;

import android.app.Application;

public class MyApplication extends Application {

    public NetworkHandler networkHandler;
    public LocationHandler locationHandler;


    @Override
    public void onCreate() {
        super.onCreate();

        networkHandler = new NetworkHandler(this);
        locationHandler = new LocationHandler(this);
    }

}
