package com.demo.commonlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.demo.commonlist.R;
import com.demo.commonlist.data.CommonData;

import java.util.ArrayList;
import java.util.List;

public class CommonAdapter extends BaseAdapter {

    private Context mContext;
    private List<CommonData> mDatas = new ArrayList();

    public CommonAdapter(Context context, List<CommonData> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public CommonData getItem(int i) {
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
        CommonData data = getItem(i);
        viewHolder.desc.setText(data.desc);
        return view;
    }

    class ViewHolder {
        TextView desc;
    }
}
