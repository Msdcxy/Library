package com.common.lib.base;

/**
 * Created by dengdayi
 * Date: 2019/01/10 15：20
 * Desc:定义BaseView回调接口
 */
public interface IBaseView {
    void showToast(String msg);

    void validate(String msg);

    void commitBack(boolean isSus, String message);
    void onLoadEnd();
}
