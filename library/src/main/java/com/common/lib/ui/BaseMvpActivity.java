package com.common.lib.ui;

import android.os.Bundle;

import com.common.lib.R;
import com.common.lib.base.IBasePresenter;
import com.common.lib.base.IBaseView;

/**
 * Created by dengdayi
 * Date: 2019/01/10 15：35
 * Desc:MVP架构Activity基类
 */
public abstract class BaseMvpActivity<P extends IBasePresenter> extends BaseActivity implements IBaseView {
    protected P presenter;

    protected abstract P createPresenter();

    private boolean isDebug = false;

    @Override
    protected void onCreateProxy(Bundle savedInstanceState) {
        presenter = createPresenter();
        if (presenter == null) {
            if (!isDebug) {
                throw new NullPointerException(getString(R.string.presenter_obj_is_null));
            }
        }
        presenter.onAttachView(this, savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (presenter != null) {
            presenter.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDetachView();
            presenter.onDestroy();
        }
    }

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }

    @Override
    public void validate(String msg) {
        super.validate(msg);
    }

    @Override
    public void commitBack(boolean isSus, String message) {

    }

    @Override
    public void onLoadEnd() {

    }
}
