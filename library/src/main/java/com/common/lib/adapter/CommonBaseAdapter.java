package com.common.lib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengdayi
 * Date: 2019/1/23
 * 公用的adapter
 */
public abstract class CommonBaseAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected List<T> list;
    protected Context context;

    public CommonBaseAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public CommonBaseAdapter(Context context, List<T> list) {
        if (list == null) {
            list = new ArrayList();
        }

        this.mInflater = LayoutInflater.from(context);
        this.list = (List) list;
        this.context = context;
    }

    /**
     * 清除数据
     */
    public void clearData() {
        list.clear();
    }

    /**
     * 更新数据
     *
     * @param datas
     */
    public void updateData(List<T> datas) {
        updateData(datas, true);
    }

    /**
     * 更新数据
     *
     * @param datas
     */
    public void updateData(List<T> datas, boolean isNotify) {
        if (datas == null) {
            datas = new ArrayList();
        }
        list.addAll(datas);
        if (isNotify) {
            notifyDataSetChanged();
        }
    }


    /**
     * 删除数据
     *
     * @param t
     */
    public void removeData(T t) {
        list.remove(t);
        notifyDataSetChanged();
    }

    public List<T> getDatas() {
        return list;
    }

    public void addData(T t) {
        list.add(t);
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.list.size();
    }

    public T getItem(int position) {
        return this.list.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public abstract View getView(int position, View convertView, ViewGroup parent);


}
