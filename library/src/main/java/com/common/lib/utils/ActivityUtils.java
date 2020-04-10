package com.common.lib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by dengdayi
 * Date: 2019/01/03 16：49
 * Desc:Activity辅助类
 */
public class ActivityUtils {
    /**
     * Activity 跳转
     *
     * @param context
     * @param bundle
     * @param targetActivity
     */
    public static void startActivity(Context context, Class<? extends Activity> targetActivity, Bundle bundle) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, targetActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class<? extends Activity> targetActivity) {
        startActivity(context, targetActivity, null);
    }

    /**
     * Activity 跳转
     *
     * @param context
     * @param bundle
     * @param targetActivity
     */
    public static void startActivityForResult(Context context, Class<? extends Activity> targetActivity, Bundle bundle, int requestCode) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, targetActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, requestCode);
        }
    }

    public static void startActivityForResult(Context context, Class<? extends Activity> targetActivity, int requestCode) {
        startActivityForResult(context, targetActivity, null, requestCode);
    }

    /**
     * 跳转返回
     *
     * @param context
     * @param bundle
     */
    public static void setResultToActivity(Context context, Bundle bundle, boolean isFinishActivity) {
        Intent intent = null;
        if (bundle == null) {
            intent = new Intent();
            intent.putExtras(bundle);
        }
        if (context instanceof Activity) {
            if (intent == null) {
                ((Activity) context).setResult(Activity.RESULT_OK);
            } else {
                ((Activity) context).setResult(Activity.RESULT_OK, intent);
            }
        }
        if (isFinishActivity) {
            ((Activity) context).finish();
        }
    }

    public static void setResultToActivity(Context context) {
        setResultToActivity(context, null, true);
    }

    public static void setResultToActivity(Context context, Bundle bundle) {
        setResultToActivity(context, bundle, true);
    }
}
