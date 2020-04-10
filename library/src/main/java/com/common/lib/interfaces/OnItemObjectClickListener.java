package com.common.lib.interfaces;

import android.view.View;

/**
 * Created by dengdayi
 * Date: 2019/01/04 10：16
 * Desc:自定义接口(常用列表点击事件)
 */
public interface OnItemObjectClickListener<T> {
    void onItemClick(View view, int position, T t);
}
