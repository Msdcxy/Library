package com.common.lib.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
/**
 * Created by dengdayi
 * Date: 2019/01/03 16：49
 * Desc:键盘辅助类
 */
public class KeyboardUtils {
    /**
     * 打开软键盘
     */
    public static void openKeyboard(Context mContext, EditText mEditText) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static void openKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 关闭软键盘
     */
    public static void closeKeyboard(Context mContext, EditText mEditText) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }
}
