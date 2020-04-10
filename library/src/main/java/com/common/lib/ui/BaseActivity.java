package com.common.lib.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.common.lib.R;
import com.common.lib.interfaces.InitView;
import com.common.lib.statusBar.ImmersionBar;
import com.common.lib.utils.ActivityManager;
import com.common.lib.utils.ToastUtils;
import com.common.lib.widget.PromptDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dengdayi
 * Date: 2019/01/03 16：49
 * Desc:Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity implements InitView {
    protected AppCompatActivity context;
    Unbinder unbinder;
    private boolean isDarkFont = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getActivityManager().addActivity(this);
        context = this;
        onCreateProxy(savedInstanceState);
    }

    protected abstract void onCreateProxy(Bundle savedInstanceState);


    @Override
    public void init() {

    }

    @Override
    public void setListeners() {

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        //Butterknife 绑定
        unbinder = ButterKnife.bind(this);
        //初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Butterknife 解绑
        if (unbinder != null) {
            unbinder.unbind();
        }
        ActivityManager.getActivityManager().finishActivity(this);
        ImmersionBar.with(this).destroy();
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    protected void initImmersionBar() {
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

    protected int getUserColor(int id) {
        return ContextCompat.getColor(this, id);
    }

    protected Activity getTopActivity() {
        return ActivityManager.getActivityManager().getTopActivity();
    }


    public void showToast(String msg) {
        ToastUtils.showLong(context, msg);
    }

    public void validate(String msg) {
        PromptDialog dialog = new PromptDialog();
        dialog.setMessage(msg);
        dialog.setDialogBtnType(PromptDialog.DialogBtnType.SINGLE);
        dialog.show(getSupportFragmentManager(), "TAG_FORM_VALIDATE");
    }
}
