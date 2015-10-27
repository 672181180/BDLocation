package com.plugin.bdlocation;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;

/**
 * 城市定位
 * Created by killer on 15/5/15..
 */
public class CityLocationManager {

    private static CityLocationManager manager;

    private final LocationClient client;

    private final MyLocationListener listener;

    private CityLocationManager(Context context, Handler handler) {

        client = new LocationClient(context);
        listener = new MyLocationListener();
        client.registerLocationListener(listener);

        BDLocationProvider.location(client, true);

        client.start();
    }

    private void onDestroy() {
        client.unRegisterLocationListener(listener);
        client.stop();
    }

    public static void location(Context context, Handler handler) {
        if (manager == null) {
            manager = new CityLocationManager(context, handler);
        }
    }

    public static void unLocation() {
        if (manager != null) {
            manager.onDestroy();
            manager = null;
        }
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation == null) {
                return;
            }

            String city = bdLocation.getCity() == null ? "" : bdLocation.getCity();
            if (TextUtils.isEmpty(city)) {
                return;
            }

            client.unRegisterLocationListener(listener);
            client.stop();
            manager = null;
        }
    }
}
