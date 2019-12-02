package kr.co.bluebright.www.myexperiment.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;

import kr.co.bluebright.www.myexperiment.R;
import kr.co.bluebright.www.myexperiment.common.view.BaseActivity;
import kr.co.bluebright.www.myexperiment.custom_bluetooth.item.CustomBluetoothItem;
import kr.co.bluebright.www.myexperiment.custom_wifi.item.CustomWifiItem;
import kr.co.bluebright.www.myexperiment.databinding.ActivityChildBinding;
import kr.co.bluebright.www.myexperiment.my_custom_list.adapter.MyCustomAdapter;


public class ChildActivity extends BaseActivity {

    private ActivityChildBinding binding;

    private MyCustomAdapter myCustomAdapter;
    private ObservableArrayList customItemList;
    private int counter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = putContentView(R.layout.activity_child);

        backPressedAppExit = false;

        setTitle(ChildActivity.class.getSimpleName());

        myCustomAdapter = new MyCustomAdapter();
        customItemList = new ObservableArrayList<>();

        binding.recyclerCustomList.setAdapter(myCustomAdapter);
        binding.setCustomItemList(customItemList);

        binding.btnAddWifi.setOnClickListener(v -> {
            customItemList.add(new CustomWifiItem("SSID " + counter, "BSSID" + (counter * 10)));
            counter++;
        });

        binding.btnAddBluetooth.setOnClickListener(v -> {
            customItemList.add(new CustomBluetoothItem("Name " + counter, "Address" + (counter * 10)));
            counter++;
        });

    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        baseBinding.reverseToolbar.textToolbarTitle.setText(title);
    }


}
