package kr.co.bluebright.www.myexperiment.custom_wifi.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kr.co.bluebright.www.myexperiment.BR;
import kr.co.bluebright.www.myexperiment.custom_wifi.item.CustomWifiItem;
import kr.co.bluebright.www.myexperiment.databinding.ItemCustomWifiBinding;

public class CustomWifiAdapter
        extends RecyclerView.Adapter<CustomWifiAdapter.CustomWifiViewHolder> {


    private List<CustomWifiItem> wifiItemList;

    public CustomWifiAdapter() {
        this.wifiItemList = new ArrayList<>();
    }


    @NonNull
    @Override
    public CustomWifiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCustomWifiBinding binding = ItemCustomWifiBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new CustomWifiViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomWifiViewHolder holder, int position) {
        CustomWifiItem item = wifiItemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return wifiItemList.size();
    }


    public void setWifiItemList(List<CustomWifiItem> wifiItemList) {
        if (wifiItemList != null) {
            this.wifiItemList = wifiItemList;
            notifyDataSetChanged();
        }
    }

    @BindingAdapter("bind:item")
    public static void bindItem(RecyclerView recyclerView, ObservableArrayList<CustomWifiItem> items) {
        CustomWifiAdapter wifiAdapter = (CustomWifiAdapter) recyclerView.getAdapter();

        //

        if (wifiAdapter != null) {
            wifiAdapter.setWifiItemList(items);
        }

    }

    class CustomWifiViewHolder extends RecyclerView.ViewHolder {

        ItemCustomWifiBinding binding;

        public CustomWifiViewHolder(ItemCustomWifiBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(CustomWifiItem item) {
            binding.setVariable(BR.target_wifi,item);
            //binding.executePendingBindings();
        }

    }

}
