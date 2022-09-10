package com.app.example.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.example.project.APKInfo;
import com.app.example.R;

import java.util.List;

public class APPListAdapter extends BaseAdapter {

    private List<APKInfo> packageInfos;
    private LayoutInflater inflater;
    private Context ctx;

    public APPListAdapter(List<APKInfo> apks, Context context) {
        this.packageInfos = apks;
        this.inflater = LayoutInflater.from(context);
        ctx = context;
    }

    @Override
    public int getCount() {
        return packageInfos == null ? 0 : packageInfos.size();
    }

    @Override
    public APKInfo getItem(int position) {
        return packageInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载布局为一个视图
        View view = inflater.inflate(R.layout.app_item, null);
        APKInfo apk = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView apk_name = view.findViewById(R.id.apk_title);
        apk_name.setText(apk.apk_name);
        return view;
    }
}

