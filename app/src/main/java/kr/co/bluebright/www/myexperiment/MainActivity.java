package kr.co.bluebright.www.myexperiment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.navigation.NavigationView;
import com.tedpark.tedpermission.rx2.TedRx2Permission;

import java.io.File;

import kr.co.bluebright.www.myexperiment.app.MyApplication;
import kr.co.bluebright.www.myexperiment.databinding.ActivityMainBinding;
import kr.co.bluebright.www.myexperiment.databinding.ContentMainBinding;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int ACTIVITY_RESULT_GPS = 0x0009;


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


        contentMainBinding.btnCheckGps.setOnClickListener(v -> TedRx2Permission.with(this)
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

                }, Throwable::printStackTrace));

        contentMainBinding.btnToggleWifi.setOnClickListener(v -> TedRx2Permission.with(this)
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
                }, Throwable::printStackTrace));

        contentMainBinding.btnCheckFileList.setOnClickListener(v -> {
            contentMainBinding.txtCheckFileListResult.setText("");

            TedRx2Permission.with(this)
                    .setDeniedMessage(R.string.storage_permission_denied_message)
                    .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                    .request()
                    .subscribe(tedPermissionResult -> {
                        if (tedPermissionResult.isGranted()) {
                            int numberOfFile = checkFileArrayInDirectory();

                            contentMainBinding.txtCheckFileListResult.setText((numberOfFile >= 0)
                                    ? getString(R.string.string_print_number_of_file_list, numberOfFile)
                                    : getString(R.string.error_unknown_occur)
                            );
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
                        //getString(R.string.string_current_gps_state) + application.locationHandler.isGpsEnabled(),
                        getString(R.string.string_current_gps_state2, application.locationHandler.isGpsEnabled()),
                        Toast.LENGTH_SHORT)
                        .show();
                break;
        }
    }

    private int checkFileArrayInDirectory() {

        try {
            String state = Environment.getExternalStorageState();
            if (!Environment.MEDIA_MOUNTED.equals(state)) {
                return -1;
            }

            //Get current device "Download" path
            File downloadLocation = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

            File[] fileArray = downloadLocation.listFiles();

            return fileArray.length;

        } catch (NullPointerException e) {
            e.printStackTrace();
            return -2;
        }

    }

}
