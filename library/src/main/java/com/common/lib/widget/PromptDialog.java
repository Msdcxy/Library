package com.common.lib.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.common.lib.R;
import com.common.lib.utils.DensityUtils;
import com.common.lib.utils.PhoneUtils;

/**
 * Created by dengdayi
 * Date: 2019/03/18 15：29
 * Desc:
 */
public class PromptDialog extends AttachDialogFragment implements View.OnClickListener {
    private TextView mTvTitle, mTvContent;
    private Button mBtnOk, mBtnCancel;
    private View btn_divider, buttonLayout, view_content_divider;
    private View.OnClickListener mOnOkClickListener, mOnCancelClickListener;
    private DialogType dialogType = DialogType.TITLE_MESSAGE;
    private DialogBtnType btnType = DialogBtnType.ALL;
    private String title, message, okText, cancelText;
    private int cancelBtnColorId = 0;
    private boolean cancel = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //无标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_prompt, container);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createDialog();
    }

    private void initView(View view) {
        mBtnOk = view.findViewById(R.id.btn_ok);
        mBtnCancel = view.findViewById(R.id.btn_cancel);
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvContent = view.findViewById(R.id.tv_content);
        btn_divider = view.findViewById(R.id.btn_divider);
        buttonLayout = view.findViewById(R.id.buttonLayout);
        view_content_divider = view.findViewById(R.id.view_content_divider);
        mBtnOk.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null) return;
        int dialogWidth = PhoneUtils.getScreenWidth(mContext) - DensityUtils.dip2px(mContext, 40);
        dialog.getWindow().setLayout(dialogWidth, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setCanceledOnTouchOutside(cancel); //点击边际可消失
        //   dialog.getWindow().setGravity(Gravity.CENTER);
    }

    public void createDialog() {
        if (TextUtils.isEmpty(title)) {
            title = getString(R.string.dialog_title);
        }
        mTvTitle.setText(title);
        mTvContent.setText(message);
        if (!TextUtils.isEmpty(okText)) {
            mBtnOk.setText(okText);
        }
        if (!TextUtils.isEmpty(cancelText)) {
            mBtnCancel.setText(cancelText);
        }
        if (cancelBtnColorId != 0) {
            mBtnCancel.setTextColor(ContextCompat.getColor(getContext(), cancelBtnColorId));
        }
        switch (btnType) {
            case ALL:
                view_content_divider.setVisibility(View.VISIBLE);
                buttonLayout.setVisibility(View.VISIBLE);
                btn_divider.setVisibility(View.VISIBLE);
                mBtnCancel.setVisibility(View.VISIBLE);
                mBtnOk.setVisibility(View.VISIBLE);
                mBtnOk.setBackgroundResource(R.drawable.button_right);
                mBtnCancel.setBackgroundResource(R.drawable.button_left);
                break;
            case SINGLE:
                //只显示确定按钮
                view_content_divider.setVisibility(View.VISIBLE);
                buttonLayout.setVisibility(View.VISIBLE);
                btn_divider.setVisibility(View.GONE);
                mBtnCancel.setVisibility(View.GONE);
                mBtnOk.setVisibility(View.VISIBLE);
                mBtnOk.setBackgroundResource(R.drawable.button_center);
                break;
            case NONE:
                view_content_divider.setVisibility(View.GONE);
                buttonLayout.setVisibility(View.GONE);
                break;
        }

        switch (dialogType) {
            case ONLY_TITLE:
                mTvTitle.setVisibility(View.VISIBLE);
                mTvContent.setVisibility(View.GONE);
                break;
            case ONLY_MESSAGE:
                mTvContent.setVisibility(View.VISIBLE);
                mTvTitle.setVisibility(View.GONE);
                break;
            case TITLE_MESSAGE:
                mTvTitle.setVisibility(View.VISIBLE);
                mTvContent.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        dismiss();
        if (v.getId() == R.id.btn_cancel) {
            if (mOnCancelClickListener != null) {
                mOnCancelClickListener.onClick(v);
            }
        }
        if (v.getId() == R.id.btn_ok) {
            if (mOnOkClickListener != null) {
                mOnOkClickListener.onClick(v);
            }
        }
    }

    public void setCancel(boolean b) {
        this.cancel = b;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOkText(String text) {
        this.okText = text;
    }

    public void setCancelText(String text) {
        this.cancelText = text;
    }

    public void setCancelBtnColorId(int cancelBtnColorId) {
        this.cancelBtnColorId = cancelBtnColorId;
    }

    public void setDialogType(DialogType dialogType) {
        this.dialogType = dialogType;
    }

    public void setDialogBtnType(DialogBtnType btnType) {
        this.btnType = btnType;
    }

    public void setOnOkClickListener(View.OnClickListener onOkClickListener) {
        mOnOkClickListener = onOkClickListener;
    }

    public void setOnCancelClickListener(View.OnClickListener onCancelClickListener) {
        mOnCancelClickListener = onCancelClickListener;
    }

    public enum DialogType {
        ONLY_TITLE,//只有标题
        ONLY_MESSAGE,//只有内容
        TITLE_MESSAGE,//消息+标题
    }

    public enum DialogBtnType {
        ALL,
        SINGLE,
        NONE
    }
}