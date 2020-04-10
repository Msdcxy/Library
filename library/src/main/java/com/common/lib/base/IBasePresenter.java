package com.common.lib.base;

import android.os.Bundle;

/**
 * Created by dengdayi
 * Date: 2019/01/10 15：26
 * Desc:定义P层生命周期与V层同步
 */
public interface IBasePresenter<V extends IBaseView> {
    /**
     * 绑定View
     *
     * @param view
     */
    void onAttachView(V view, Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onSaveInstanceState(Bundle savedInstanceState);

    void onDetachView();

    void onDestroy();
}
