package com.common.lib.widget;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.common.lib.R;


/**
 * Created by dengdayi
 * Date: 2019/03/18 15：26
 * Desc:
 */
public abstract class AttachDialogFragment extends DialogFragment {
    public Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.MyDialogStyle);
    }
}
