package com.common.lib.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.common.lib.R;
import com.common.lib.interfaces.InitView;
import com.common.lib.statusBar.ImmersionBar;
import com.common.lib.statusBar.ImmersionFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dengdayi
 * Date: 2019/01/03 16：49
 * Desc:Fragment基类
 */
public abstract class BaseFragment extends ImmersionFragment implements InitView {
    protected FragmentActivity context;
    private Unbinder unbinder;
    private int layoutId;
    private View mView;
    private boolean isDarkFont = false;


    public BaseFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (FragmentActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        onCreateProxy(inflater, container, savedInstanceState);
        if (mView != null) {
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (parent != null) {
                parent.removeView(mView);
            }
            return mView;
        }
        mView = inflater.inflate(layoutId, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        onViewCreatedProxy(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }

    }

    public void setContentView(int resId) {
        this.layoutId = resId;
        initImmersionBar();
    }

    protected abstract void onCreateProxy(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract void onViewCreatedProxy(View view, Bundle savedInstanceState);

    @Override
    public void init() {

    }

    @Override
    public void setListeners() {
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar immersionBar = ImmersionBar.with(this)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.color_status_bar);
        if (isDarkFont) {
            immersionBar.statusBarDarkFont(true, 0.2f);
        } else {
            immersionBar.statusBarDarkFont(false);
        }
        immersionBar.init();
    }

    public void setDarkFont(boolean darkFont) {
        isDarkFont = darkFont;
    }

    //是否返回
    public boolean isBackPressed() {
        return false;
    }
}
