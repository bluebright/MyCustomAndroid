package kr.co.bluebright.www.myexperiment.custom_wifi.item;

public class CustomWifiItem {

   private String SSID;
   private String BSSID;


    public CustomWifiItem(String SSID, String BSSID) {
        this.SSID = SSID;
        this.BSSID = BSSID;
    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public String getBSSID() {
        return BSSID;
    }

    public void setBSSID(String BSSID) {
        this.BSSID = BSSID;
    }


}
