package com.common.lib.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.common.lib.R;
import com.common.lib.base.IBasePresenter;
import com.common.lib.base.IBaseView;
import com.common.lib.utils.ToastUtils;
import com.common.lib.widget.PromptDialog;
/**
 * Created by dengdayi
 * Date: 2019/01/10 16：24
 * Desc:
 */
public abstract class BaseMvpFragment<P extends IBasePresenter> extends BaseFragment implements IBaseView {
    protected P presenter;

    protected abstract P createPresenter();

    private boolean isDebug = false;

    @Override
    protected void onCreateProxy(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        presenter = createPresenter();
        if (presenter == null) {
            if (!isDebug) {
                throw new NullPointerException(getString(R.string.presenter_obj_is_null));
            }
        }
        presenter.onAttachView(this, savedInstanceState);
    }

    @Override
    protected void onViewCreatedProxy(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (presenter != null) {
            presenter.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDetachView();
            presenter.onDestroy();
        }
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showLong(context, msg);
    }

    @Override
    public void validate(String msg) {
        PromptDialog dialog = new PromptDialog();
        dialog.setMessage(msg);
        dialog.setDialogBtnType(PromptDialog.DialogBtnType.SINGLE);
        dialog.show(getFragmentManager(), "TAG_FORM_VALIDATE");
    }

    @Override
    public void commitBack(boolean isSus, String message) {

    }

    @Override
    public void onLoadEnd() {

    }
}
