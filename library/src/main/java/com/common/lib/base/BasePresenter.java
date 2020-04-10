package com.common.lib.base;

import android.os.Bundle;

import java.lang.ref.WeakReference;

/**
 * Created by dengdayi
 * Date: 2019/01/10 15：29
 * Desc:Presenter生命周期包装、View的绑定和解除，P层实现的基类
 */
public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {
    //软引用，防止内存泄漏
    private WeakReference<V> viewRef;

    protected V getView() {
        return viewRef.get();
    }

    protected boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    private void _attach(V view, Bundle savedInstanceState) {
        viewRef = new WeakReference<>(view);
    }

    private void _detach() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

    @Override
    public void onAttachView(V view, Bundle savedInstanceState) {
        _attach(view, savedInstanceState);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onDetachView() {
        _detach();
    }

    @Override
    public void onDestroy() {

    }

}
