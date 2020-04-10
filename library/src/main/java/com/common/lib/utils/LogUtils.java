package com.common.lib.utils;

import android.util.Log;

import com.common.lib.BuildConfig;

/**
 * Created by dengdayi
 * Date: 2019/01/03 16：49
 * Desc:日志工具类
 */
public class LogUtils {
    private static LogUtils sSingleton = null;
    private static final String TAG = LogUtils.class.getSimpleName();
    /**
     * 是否为debug模式
     */
    private boolean isDebug = BuildConfig.DEBUG;

    public static synchronized LogUtils getInstance() {
        if (sSingleton == null) {
            sSingleton = new LogUtils();
        }
        return sSingleton;
    }

    private LogUtils() {
    }

    public boolean isDebug() {
        return isDebug;
    }

    /**
     * 设置是否为debug
     *
     * @param debug
     */
    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    /**
     * 自定义tag标签
     *
     * @param tag
     * @param msg
     */
    public void logV(String tag, String msg) {
        if (isDebug()) {
            Log.v(tag, msg);
        }
    }

    public void logV(String msg) {
        logV(TAG, msg);
    }

    public void logD(String tag, String msg) {
        if (isDebug()) {
            Log.d(tag, msg);
        }
    }

    public void logD(String msg) {
        logD(TAG, msg);
    }

    public void logI(String tag, String msg) {
        if (isDebug()) {
            Log.i(tag, msg);
        }
    }

    public void logI(String msg) {
        logI(TAG, msg);
    }

    public void logW(String tag, String msg) {
        if (isDebug()) {
            Log.w(tag, msg);
        }
    }

    public void logW(String msg) {
        logW(TAG, msg);
    }

    public void logE(String tag, String msg) {
        if (isDebug()) {
            Log.e(tag, msg);
        }
    }

    public void logE(String msg) {
        logE(TAG, msg);
    }
}
