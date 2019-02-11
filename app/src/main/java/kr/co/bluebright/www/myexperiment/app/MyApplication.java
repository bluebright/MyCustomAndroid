package kr.co.bluebright.www.myexperiment.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MyApplication extends Application {

    public NetworkHandler networkHandler;
    public LocationHandler locationHandler;


    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

        networkHandler = new NetworkHandler(this);
        locationHandler = new LocationHandler(this);
    }

}
