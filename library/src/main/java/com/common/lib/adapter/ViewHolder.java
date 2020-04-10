package com.common.lib.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dengdayi
 * Date: 2019/1/23
 * 公用的ViewHolder
 */
public class ViewHolder {
    private SparseArray<View> mViews = new SparseArray();
    private View mConvertView;

    private ViewHolder(Context context, ViewGroup parent, int layoutId) {
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.mConvertView.setTag(this);
    }

    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder(context, parent, layoutId);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return viewHolder;
    }

    public <T extends View> T getView(int viewId) {
        View view = this.mViews.get(viewId);
        if (view == null) {
            view = this.mConvertView.findViewById(viewId);
            this.mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return this.mConvertView;
    }

    public ViewHolder setTextForTextView(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText((CharSequence) (text == null ? "" : text));
        return this;
    }

    public ViewHolder setTextForTextView(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text == null ? "" : text);
        return this;
    }

    //setImageViewBg
    public ViewHolder setImageForImageView(int viewId, int resId) {
        ImageView image = getView(viewId);
        image.setImageResource(resId);
        return this;
    }

    public ViewHolder setImageForImageView(int viewId, Drawable mDrawable) {
        ImageView image = getView(viewId);
        image.setBackgroundDrawable(mDrawable);
        return this;
    }

    public ViewHolder setImageForImageView(int viewId, Bitmap bitmap) {
        ImageView image = getView(viewId);
        image.setImageBitmap(bitmap);
        return this;
    }
}
