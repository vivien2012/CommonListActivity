package com.demo.commonlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.demo.commonlist.R;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonAdapter2<T> extends BaseAdapter {

    private Context mContext;
    private List<T> mDatas = new ArrayList();

    public CommonAdapter2(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_common, null);
            viewHolder.desc = (TextView) view.findViewById(R.id.desc);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.desc.setText(setDesc(getItem(i)));
        return view;
    }

    class ViewHolder {
        TextView desc;
    }

    public abstract String setDesc(T data);
}
