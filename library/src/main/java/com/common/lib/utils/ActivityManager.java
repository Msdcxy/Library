package com.common.lib.utils;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dengdayi
 * Date: 2019/01/03 16：49
 * Desc:Activity管理类：用于管理Activity和退出程序
 */
public class ActivityManager {
    // 单例模式
    private static ActivityManager instance;

    private List<Activity> activityList;

    private ActivityManager() {
    }

    /**
     * 单例
     */
    public static ActivityManager getActivityManager() {
        if (instance == null) {
            synchronized (ActivityManager.class) {
                if (instance == null) {
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }

    /**
     *
     */
    public void addActivity(Activity activity) {
        if (activityList == null) {
            activityList = new LinkedList<>();
        }
        activityList.add(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityList.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public Activity getTopActivity() {
        return activityList.get(activityList.size() - 1);
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (activityList != null && !activityList.isEmpty()) {
            for (int i = 0; i < activityList.size(); i++) {
                if (activityList.get(i) != null) {
                    activityList.get(i).finish();
                }
            }
            activityList.clear();
        }
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        finishAllActivity();
    }

    /**
     * 退出应用程序
     *
     * @param isExit:
     */
    public void AppExit(boolean isExit) {
        finishAllActivity();
        if (isExit) {
            try {
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

