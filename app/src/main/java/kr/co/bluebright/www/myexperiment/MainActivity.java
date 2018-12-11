package kr.co.bluebright.www.myexperiment;

import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tedpark.tedpermission.rx2.TedRx2Permission;

import kr.co.bluebright.www.myexperiment.app.MyApplication;
import kr.co.bluebright.www.myexperiment.databinding.ActivityMainBinding;
import kr.co.bluebright.www.myexperiment.databinding.ContentMainBinding;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int ACTIVITY_RESULT_GPS = 0x0001;


    private ActivityMainBinding binding;
    private ContentMainBinding contentMainBinding;
    private MyApplication application;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        contentMainBinding = binding.contentMain;

        setSupportActionBar(binding.toolbar);

        application = (MyApplication) getApplication();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(this);


        contentMainBinding.btnCheckGps.setOnClickListener(v -> {
            TedRx2Permission.with(this)
                    .setDeniedMessage(R.string.location_permission_denied_message)
                    .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                    .request()
                    .subscribe(tedPermissionResult -> {
                        if (tedPermissionResult.isGranted()) {
                            if (application.locationHandler.isGpsEnabled()) {
                                Toast.makeText(MainActivity.this,
                                        R.string.string_gps_enabled,
                                        Toast.LENGTH_SHORT)
                                        .show();
                            } else {
                                application.locationHandler.startGpsSettingActivity(MainActivity.this, ACTIVITY_RESULT_GPS);
                            }
                        } else {
                            Toast.makeText(MainActivity.this,
                                    R.string.string_permission_denied,
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }

                    }, Throwable::printStackTrace);
        });

        contentMainBinding.btnToggleWifi.setOnClickListener(v -> {
            TedRx2Permission.with(this)
                    .setDeniedMessage(R.string.wifi_permission_denied_message)
                    .setPermissions(Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.CHANGE_WIFI_STATE)
                    .request()
                    .subscribe(tedPermissionResult -> {
                        if (tedPermissionResult.isGranted()) {
                            Pair<Integer, Boolean> networkPair = application.networkHandler.getNetworkTypeAndState();
                            application.networkHandler.turnWiFi(!networkPair.second);
                        } else {
                            Toast.makeText(MainActivity.this,
                                    R.string.string_permission_denied,
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }, Throwable::printStackTrace);
        });

    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case ACTIVITY_RESULT_GPS:
                Toast.makeText(MainActivity.this,
                        getString(R.string.string_current_gps_state) + application.locationHandler.isGpsEnabled(),
                        Toast.LENGTH_SHORT)
                        .show();
                break;
        }
    }
}
