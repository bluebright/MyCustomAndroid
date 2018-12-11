package kr.co.bluebright.www.myexperiment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import kr.co.bluebright.www.myexperiment.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;
    private Handler handler;

    private long delayTime = TimeUnit.SECONDS.toMillis(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        Runnable activityStarter = () -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        };

        handler = new Handler();
        handler.postDelayed(activityStarter, delayTime);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }
}