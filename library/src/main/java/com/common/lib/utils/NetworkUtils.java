package com.common.lib.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by dengdayi
 * Date: 2019/01/03 16：49
 * Desc:网络工具类
 */

public class NetworkUtils {
    /**
     * 检查手机网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(final Context context) {
        boolean hasWifoCon = false;
        boolean hasMobileCon = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo[] netInfos = cm.getAllNetworkInfo();
        for (NetworkInfo net : netInfos) {

            String type = net.getTypeName();
            if (type.equalsIgnoreCase("WIFI")) {
                LogUtils.getInstance().logI("get Wifi connection");
                if (net.isConnected()) {
                    hasWifoCon = true;
                }
            }

            if (type.equalsIgnoreCase("MOBILE")) {
                LogUtils.getInstance().logI("get Mobile connection");
                if (net.isConnected()) {
                    hasMobileCon = true;
                }
            }
        }
        return hasWifoCon || hasMobileCon;
    }
}
