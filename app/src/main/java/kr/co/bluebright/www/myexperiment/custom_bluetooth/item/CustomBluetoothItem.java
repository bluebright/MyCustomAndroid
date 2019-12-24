package kr.co.bluebright.www.myexperiment.custom_bluetooth.item;

public class CustomBluetoothItem {

   private String deviceName;
   private String deviceAddress;


    public CustomBluetoothItem(String deviceName, String deviceAddress) {
        this.deviceName = deviceName;
        this.deviceAddress = deviceAddress;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }
}
