package com.common.lib.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by dengdayi
 * Date: 2019/01/03 16：49
 * Desc:获取手机版本号， 和版本名称
 */

public class PackageUtils {
    public static PackageInfo getAppPackageInfo(Context context) {
        PackageInfo pi = null;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            //versionName = pi.versionName;
            //versioncode = pi.versionCode;

        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return pi;
    }

    public static String getAppVersionName(Context context) {
        PackageInfo info = getAppPackageInfo(context);
        if (info != null) {
            return info.versionName;
        } else {
            return "";
        }
    }

    public static int getAppVersionCode(Context context) {
        PackageInfo info = getAppPackageInfo(context);
        if (info != null) {
            return info.versionCode;
        } else {
            return 0;
        }
    }
}
