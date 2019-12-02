package kr.co.bluebright.www.myexperiment.my_custom_list.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import kr.co.bluebright.www.myexperiment.custom_bluetooth.item.CustomBluetoothItem;
import kr.co.bluebright.www.myexperiment.custom_wifi.item.CustomWifiItem;
import kr.co.bluebright.www.myexperiment.databinding.ItemCustomBluetoothBinding;
import kr.co.bluebright.www.myexperiment.databinding.ItemCustomWifiBinding;

public class MyCustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int CUSTOM_VIEW_TYPE_WIFI = 0;
    public static final int CUSTOM_VIEW_TYPE_BLUETOOTH = 1;


    @IntDef({
            CUSTOM_VIEW_TYPE_WIFI,
            CUSTOM_VIEW_TYPE_BLUETOOTH
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface MyCustomViewType {
    }


    //TODO Need to create super or abstract class of item class
    private List itemList;

    public MyCustomAdapter() {
        this.itemList = new ArrayList();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @MyCustomViewType int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case CUSTOM_VIEW_TYPE_WIFI:
            default:
                ItemCustomWifiBinding wifiBinding = ItemCustomWifiBinding.inflate(inflater, parent, false);
                return new CustomWifi_ViewHolder(wifiBinding);

            case CUSTOM_VIEW_TYPE_BLUETOOTH:
                ItemCustomBluetoothBinding bluetoothBinding = ItemCustomBluetoothBinding.inflate(inflater, parent, false);
                return new CustomBluetooth_ViewHolder(bluetoothBinding);
        }

        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        @MyCustomViewType int holderItemViewType = holder.getItemViewType();

        switch (holderItemViewType) {
            case CUSTOM_VIEW_TYPE_WIFI:
            default:
                CustomWifi_ViewHolder wifiViewHolder = (CustomWifi_ViewHolder) holder;
                CustomWifiItem wifiItem = (CustomWifiItem) itemList.get(position);
                wifiViewHolder.bind(wifiItem);
                break;

            case CUSTOM_VIEW_TYPE_BLUETOOTH:
                CustomBluetooth_ViewHolder bluetoothViewHolder = (CustomBluetooth_ViewHolder) holder;
                CustomBluetoothItem bluetoothItem = (CustomBluetoothItem) itemList.get(position);
                bluetoothViewHolder.bind(bluetoothItem);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItemList(List itemList) {
        if (itemList != null) {
            this.itemList = itemList;
            notifyDataSetChanged();
        }
    }

    @BindingAdapter("bind:item")
    public static void bindItem(RecyclerView recyclerView, ObservableArrayList items) {
        MyCustomAdapter myCustomAdapter = (MyCustomAdapter) recyclerView.getAdapter();

        //

        if (myCustomAdapter != null) {
            myCustomAdapter.setItemList(items);
        }

    }

    @Override
    @MyCustomViewType
    public int getItemViewType(int position) {

        Object item = itemList.get(position);

        if (item instanceof CustomWifiItem) {
            return CUSTOM_VIEW_TYPE_WIFI;
        } else if (item instanceof CustomBluetoothItem) {
            return CUSTOM_VIEW_TYPE_BLUETOOTH;
        }
        return super.getItemViewType(position);
    }

    class CustomWifi_ViewHolder extends RecyclerView.ViewHolder {

        ItemCustomWifiBinding wifiItemBinding;

        public CustomWifi_ViewHolder(ItemCustomWifiBinding wifiItemBinding) {
            super(wifiItemBinding.getRoot());
            this.wifiItemBinding = wifiItemBinding;
        }

        void bind(CustomWifiItem wifiItem) {
            wifiItemBinding.setVariable(kr.co.bluebright.www.myexperiment.BR.target_wifi, wifiItem);
            //wifiItemBinding.executePendingBindings();
        }

    }

    class CustomBluetooth_ViewHolder extends RecyclerView.ViewHolder {

        ItemCustomBluetoothBinding bluetoothItemBinding;

        public CustomBluetooth_ViewHolder(ItemCustomBluetoothBinding bluetoothItemBinding) {
            super(bluetoothItemBinding.getRoot());
            this.bluetoothItemBinding = bluetoothItemBinding;
        }

        void bind(CustomBluetoothItem bluetoothItem) {
            bluetoothItemBinding.setVariable(kr.co.bluebright.www.myexperiment.BR.target_bluetooth, bluetoothItem);

        }


    }

}
