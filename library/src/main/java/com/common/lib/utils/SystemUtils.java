package com.common.lib.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import java.io.File;


/**
 * Created by dengdayi
 * Date: 2019/01/03 16：49
 * Desc: android 系统 帮助类
 */
public class SystemUtils {
    public static void callPhone(Context context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 打开系统浏览器
     *
     * @param context
     * @param url
     */
    public static void gotoBrowser(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);    //为Intent设置Action属性
        intent.setData(Uri.parse(url)); //为Intent设置DATA属性
        context.startActivity(intent);
    }

    /**
     * 跳转app应用详情
     *
     * @param activity
     */
    public static void gotoAppDetailSetting(Activity activity, int code) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivityForResult(intent, code);
    }
    public static void gotoAppDetailSetting(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivity(intent);
    }

    /**
     * 跳转定位设置页面
     *
     * @param activity
     */
    public static void startLocationSettings(Activity activity, int code) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        activity.startActivityForResult(intent, code);
    }

    /**
     * apk安装
     *
     * @param file
     */
    public static void installApk(Context context, File file) {
        if (file == null) {
            return;
        }
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){

        }else {

        }
        intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 跳应用市场评分
     *
     * @param context
     */
    public static void goToMarket(Context context) {
        String packageName = context.getApplicationContext().getApplicationInfo().packageName;
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            LogUtils.getInstance().logE("goToMarket  Exception : " + e.getLocalizedMessage());
        }
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getMobileDevice() {
        return Build.MODEL;
    }

    /**
     * 获取手机版本号
     *
     * @return
     */
    public static int getMobileVersion() {
        return Build.VERSION.SDK_INT;
    }
    public static String getSysVerName(){
        return Build.VERSION.RELEASE;
    }
}
