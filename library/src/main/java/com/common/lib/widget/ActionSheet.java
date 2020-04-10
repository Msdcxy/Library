
package com.common.lib.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.common.lib.R;
import com.common.lib.utils.DensityUtils;

import java.lang.reflect.Method;

/**
 * 仿IOS 底部弹出框效果
 */
public class ActionSheet extends Fragment implements View.OnClickListener {

    private static final String ARG_CANCEL_BUTTON_TITLE = "cancel_button_title";
    private static final String ARG_OTHER_BUTTON_TITLES = "other_button_titles";
    private static final String ARG_CANCELABLE_ONTOUCHOUTSIDE = "cancelable_ontouchoutside";
    private static final int CANCEL_BUTTON_ID = 100;
    private static final int BG_VIEW_ID = 10;
    private static final int TRANSLATE_DURATION = 200;
    private static final int ALPHA_DURATION = 300;

    private static final String EXTRA_DISMISSED = "extra_dismissed";

    private boolean mDismissed = true;
    private ActionSheetListener mListener;
    private View mView;
    private LinearLayout mPanel;
    private ViewGroup mGroup;
    private View mBg;
    private Attributes mAttrs;
    private boolean isCancel = true;

    public void show(final FragmentManager manager, final String tag) {
        if (!mDismissed || manager.isDestroyed()) {
            return;
        }
        mDismissed = false;
        new Handler().post(new Runnable() {
            public void run() {
                FragmentTransaction ft = manager.beginTransaction();
                ft.add(ActionSheet.this, tag);
                ft.addToBackStack(null);
                ft.commitAllowingStateLoss();
            }
        });
    }

    public void dismiss() {
        if (mDismissed) {
            return;
        }
        mDismissed = true;
        new Handler().post(new Runnable() {
            public void run() {
                getFragmentManager().popBackStack();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.remove(ActionSheet.this);
                ft.commitAllowingStateLoss();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(EXTRA_DISMISSED, mDismissed);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mDismissed = savedInstanceState.getBoolean(EXTRA_DISMISSED);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        InputMethodManager imm = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            View focusView = getActivity().getCurrentFocus();
            if (focusView != null) {
                imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
            }
        }

        mAttrs = readAttribute();

        mView = createView();
        mGroup = (ViewGroup) getActivity().getWindow().getDecorView();

        createItems();

        mGroup.addView(mView);
        mBg.startAnimation(createAlphaInAnimation());
        mPanel.startAnimation(createTranslationInAnimation());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        mPanel.startAnimation(createTranslationOutAnimation());
        mBg.startAnimation(createAlphaOutAnimation());
        mView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mGroup.removeView(mView);
            }
        }, ALPHA_DURATION);
        if (mListener != null) {
            mListener.onDismiss(this, isCancel);
        }
        super.onDestroyView();
    }

    private Animation createTranslationInAnimation() {
        int type = TranslateAnimation.RELATIVE_TO_SELF;
        TranslateAnimation an = new TranslateAnimation(type, 0, type, 0, type,
                1, type, 0);
        an.setDuration(TRANSLATE_DURATION);
        return an;
    }

    private Animation createAlphaInAnimation() {
        AlphaAnimation an = new AlphaAnimation(0, 1);
        an.setDuration(ALPHA_DURATION);
        return an;
    }

    private Animation createTranslationOutAnimation() {
        int type = TranslateAnimation.RELATIVE_TO_SELF;
        TranslateAnimation an = new TranslateAnimation(type, 0, type, 0, type,
                0, type, 1);
        an.setDuration(TRANSLATE_DURATION);
        an.setFillAfter(true);
        return an;
    }

    private Animation createAlphaOutAnimation() {
        AlphaAnimation an = new AlphaAnimation(1, 0);
        an.setDuration(ALPHA_DURATION);
        an.setFillAfter(true);
        return an;
    }

    private View createView() {
        FrameLayout parent = new FrameLayout(getActivity());
        parent.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        mBg = new View(getActivity());
        mBg.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        mBg.setBackgroundColor(Color.argb(136, 0, 0, 0));
        mBg.setId(ActionSheet.BG_VIEW_ID);
        mBg.setOnClickListener(this);

        mPanel = new LinearLayout(getActivity());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM;
        params.bottomMargin = getNavBarHeight(getActivity());
        mPanel.setLayoutParams(params);
        mPanel.setOrientation(LinearLayout.VERTICAL);
        parent.addView(mBg);
        parent.addView(mPanel);
        return parent;
    }

    public int getNavBarHeight(Context context) {
        int navigationBarHeight = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Resources rs = context.getResources();
            int id = rs.getIdentifier("navigation_bar_height", "dimen", "android");
            if (id > 0 && checkDeviceHasNavigationBar(context)) {
                navigationBarHeight = rs.getDimensionPixelSize(id);
            }
        }
        return navigationBarHeight;
    }

    private boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasNavigationBar;

    }

    private void createItems() {
        String[] titles = getOtherButtonTitles();
        if (titles != null) {
            for (int i = 0; i < titles.length; i++) {
                if (i > 0 && mAttrs.otherButtonSpacing > 0) {
                    TextView line = new TextView(getActivity());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT, mAttrs.otherButtonSpacing);
                    line.setLayoutParams(params);
                    line.setBackgroundResource(R.drawable.actionsheet_middle_pressed);
                    mPanel.addView(line);
                }
                Button bt = (Button) LayoutInflater.from(getActivity()).inflate(R.layout.action_sheet_btn, mPanel, false);
                bt.setId(CANCEL_BUTTON_ID + i + 1);
                bt.setOnClickListener(this);
                bt.setBackgroundDrawable(getOtherButtonBg(titles, i));
                bt.setText(titles[i]);
                bt.setTextColor(mAttrs.otherButtonTextColor);
                mPanel.addView(bt);
            }
        }
        Button bt = (Button) LayoutInflater.from(getActivity()).inflate(R.layout.action_sheet_btn, mPanel, false);
        bt.getPaint().setFakeBoldText(true);
        bt.setId(ActionSheet.CANCEL_BUTTON_ID);
        bt.setBackgroundDrawable(mAttrs.cancelButtonBackground);
        bt.setText(getCancelButtonTitle());
        bt.setTextColor(mAttrs.cancelButtonTextColor);
        bt.setOnClickListener(this);
        LinearLayout.LayoutParams params = createButtonLayoutParams();
        params.topMargin = mAttrs.cancelButtonMarginTop;
        mPanel.addView(bt, params);
        mPanel.setBackgroundDrawable(mAttrs.background);
        mPanel.setPadding(mAttrs.padding, mAttrs.padding, mAttrs.padding,
                mAttrs.padding);
    }

    public LinearLayout.LayoutParams createButtonLayoutParams() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        return params;
    }

    private Drawable getOtherButtonBg(String[] titles, int i) {
        if (titles.length == 1) {
            return mAttrs.otherButtonSingleBackground;
        }
        if (titles.length == 2) {
            switch (i) {
                case 0:
                    return mAttrs.otherButtonTopBackground;
                case 1:
                    return mAttrs.otherButtonBottomBackground;
            }
        }
        if (titles.length > 2) {
            if (i == 0) {
                return mAttrs.otherButtonTopBackground;
            }
            if (i == (titles.length - 1)) {
                return mAttrs.otherButtonBottomBackground;
            }
            return mAttrs.getOtherButtonMiddleBackground();
        }
        return null;
    }

    private Attributes readAttribute() {
        Attributes attrs = new Attributes(getActivity());
        TypedArray a = getActivity().getTheme().obtainStyledAttributes(null,
                R.styleable.ActionSheet, R.attr.actionSheetStyle, 0);
        Drawable background = a
                .getDrawable(R.styleable.ActionSheet_actionSheetBackground);
        if (background != null) {
            attrs.background = background;
        }
        Drawable cancelButtonBackground = a
                .getDrawable(R.styleable.ActionSheet_cancelButtonBackground);
        if (cancelButtonBackground != null) {
            attrs.cancelButtonBackground = cancelButtonBackground;
        }
        Drawable otherButtonTopBackground = a
                .getDrawable(R.styleable.ActionSheet_otherButtonTopBackground);
        if (otherButtonTopBackground != null) {
            attrs.otherButtonTopBackground = otherButtonTopBackground;
        }
        Drawable otherButtonMiddleBackground = a
                .getDrawable(R.styleable.ActionSheet_otherButtonMiddleBackground);
        if (otherButtonMiddleBackground != null) {
            attrs.otherButtonMiddleBackground = otherButtonMiddleBackground;
        }
        Drawable otherButtonBottomBackground = a
                .getDrawable(R.styleable.ActionSheet_otherButtonBottomBackground);
        if (otherButtonBottomBackground != null) {
            attrs.otherButtonBottomBackground = otherButtonBottomBackground;
        }
        Drawable otherButtonSingleBackground = a
                .getDrawable(R.styleable.ActionSheet_otherButtonSingleBackground);
        if (otherButtonSingleBackground != null) {
            attrs.otherButtonSingleBackground = otherButtonSingleBackground;
        }
        attrs.cancelButtonTextColor = a.getColor(
                R.styleable.ActionSheet_cancelButtonTextColor,
                attrs.cancelButtonTextColor);
        attrs.otherButtonTextColor = a.getColor(
                R.styleable.ActionSheet_otherButtonTextColor,
                attrs.otherButtonTextColor);
        attrs.padding = (int) a.getDimension(
                R.styleable.ActionSheet_actionSheetPadding, attrs.padding);
        attrs.otherButtonSpacing = (int) a.getDimension(
                R.styleable.ActionSheet_otherButtonSpacing,
                attrs.otherButtonSpacing);
        attrs.cancelButtonMarginTop = (int) a.getDimension(
                R.styleable.ActionSheet_cancelButtonMarginTop,
                attrs.cancelButtonMarginTop);
        a.recycle();
        return attrs;
    }

    private String getCancelButtonTitle() {
        return getArguments().getString(ARG_CANCEL_BUTTON_TITLE);
    }

    private String[] getOtherButtonTitles() {
        return getArguments().getStringArray(ARG_OTHER_BUTTON_TITLES);
    }

    private boolean getCancelableOnTouchOutside() {
        return getArguments().getBoolean(ARG_CANCELABLE_ONTOUCHOUTSIDE);
    }

    public void setActionSheetListener(ActionSheetListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == ActionSheet.BG_VIEW_ID && !getCancelableOnTouchOutside()) {
            return;
        }
        dismiss();
        if (v.getId() != ActionSheet.CANCEL_BUTTON_ID && v.getId() != ActionSheet.BG_VIEW_ID) {
            if (mListener != null) mListener.onOtherButtonClick(this, v.getId() - CANCEL_BUTTON_ID
                    - 1);
            isCancel = false;
        }
    }

    public static Builder createBuilder(Context context,
                                        FragmentManager fragmentManager) {
        return new Builder(context, fragmentManager);
    }

    //region
    private static class Attributes {
        private Context mContext;

        public Attributes(Context context) {
            mContext = context;
            this.background = new ColorDrawable(Color.TRANSPARENT);
            this.cancelButtonBackground = new ColorDrawable(Color.BLACK);
            ColorDrawable gray = new ColorDrawable(Color.GRAY);
            this.otherButtonTopBackground = gray;
            this.otherButtonMiddleBackground = gray;
            this.otherButtonBottomBackground = gray;
            this.otherButtonSingleBackground = gray;
            this.cancelButtonTextColor = Color.WHITE;
            this.otherButtonTextColor = Color.BLACK;
            this.padding = DensityUtils.dip2px(context, 20);
            this.otherButtonSpacing = DensityUtils.dip2px(context, 1);
            this.cancelButtonMarginTop = DensityUtils.dip2px(context, 10);
        }

        public Drawable getOtherButtonMiddleBackground() {
            if (otherButtonMiddleBackground instanceof StateListDrawable) {
                TypedArray a = mContext.getTheme().obtainStyledAttributes(null,
                        R.styleable.ActionSheet, R.attr.actionSheetStyle, 0);
                otherButtonMiddleBackground = a
                        .getDrawable(R.styleable.ActionSheet_otherButtonMiddleBackground);
                a.recycle();
            }
            return otherButtonMiddleBackground;
        }

        Drawable background;
        Drawable cancelButtonBackground;
        Drawable otherButtonTopBackground;
        Drawable otherButtonMiddleBackground;
        Drawable otherButtonBottomBackground;
        Drawable otherButtonSingleBackground;
        int cancelButtonTextColor;
        int otherButtonTextColor;
        int padding;
        int otherButtonSpacing;
        int cancelButtonMarginTop;
    }

    public static class Builder {

        private Context mContext;
        private FragmentManager mFragmentManager;
        private String mCancelButtonTitle;
        private String[] mOtherButtonTitles;
        private String mTag = "actionSheet";
        private boolean mCancelableOnTouchOutside;
        private ActionSheetListener mListener;

        Builder(Context context, FragmentManager fragmentManager) {
            mContext = context;
            mFragmentManager = fragmentManager;
        }

        public Builder setCancelButtonTitle(String title) {
            mCancelButtonTitle = title;
            return this;
        }

        public Builder setCancelButtonTitle(int strId) {
            return setCancelButtonTitle(mContext.getString(strId));
        }

        public Builder setOtherButtonTitles(String... titles) {
            mOtherButtonTitles = titles;
            return this;
        }

        public Builder setTag(String tag) {
            mTag = tag;
            return this;
        }

        public Builder setListener(ActionSheetListener listener) {
            this.mListener = listener;
            return this;
        }

        public Builder setCancelableOnTouchOutside(boolean cancelable) {
            mCancelableOnTouchOutside = cancelable;
            return this;
        }

        public Bundle prepareArguments() {
            Bundle bundle = new Bundle();
            bundle.putString(ARG_CANCEL_BUTTON_TITLE, mCancelButtonTitle);
            bundle.putStringArray(ARG_OTHER_BUTTON_TITLES, mOtherButtonTitles);
            bundle.putBoolean(ARG_CANCELABLE_ONTOUCHOUTSIDE,
                    mCancelableOnTouchOutside);
            return bundle;
        }

        public ActionSheet show() {
            ActionSheet actionSheet = (ActionSheet) Fragment.instantiate(
                    mContext, ActionSheet.class.getName(), prepareArguments());
            actionSheet.setActionSheetListener(mListener);
            actionSheet.show(mFragmentManager, mTag);
            return actionSheet;
        }

    }

    public interface ActionSheetListener {

        void onDismiss(ActionSheet actionSheet, boolean isCancel);

        void onOtherButtonClick(ActionSheet actionSheet, int index);
    }
//endregion
}
