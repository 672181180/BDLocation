package com.plugin.bdlocation;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * 百度定位
 * Created by killer on 15/5/15..
 */
public class BDLocationProvider {

    public static void location(LocationClient client, BDLocationListener listener, boolean address) {
        if (client == null) {
            return;
        }
        if (listener != null) {
            client.registerLocationListener(listener);
        }

        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setNeedDeviceDirect(true);
        option.setIsNeedAddress(address);
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        option.setScanSpan(1000);

        client.setLocOption(option);
    }

    public static void location(LocationClient client) {
        location(client, null, false);
    }

    public static void location(LocationClient client, boolean address) {
        location(client, null, address);
    }
}
