package com.app.example.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.app.example.App;
import com.app.example.R;
import com.app.example.project.Hook;
import com.app.example.project.PermissionSetting;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class APPPermissionsAdapter extends BaseAdapter {
    private List<PermissionSetting> permissions;
    private LayoutInflater inflater;
    private Context ctx;

    public APPPermissionsAdapter(List<PermissionSetting> permissions, Context context) {
        this.permissions = permissions;
        this.inflater = LayoutInflater.from(context);
        ctx = context;
    }

    @Override
    public int getCount() {
        return permissions == null ? 0 : permissions.size();
    }

    @Override
    public PermissionSetting getItem(int position) {
        return permissions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //加载布局为一个视图
        View view = inflater.inflate(R.layout.app_permisssion_item, null);
        String permission = getItem(position).getPrivacyItem();
        //在view视图中查找id为image_photo的控件
        TextView Permission_describe = view.findViewById(R.id.app_permission);
        Permission_describe.setText(permission);

        final CheckBox checkBox = view.findViewById(R.id.app_permission_check);

        switch (getItem(position).getEnable()) {
            case 0:
                ((CheckBox) view.findViewById(R.id.app_permission_check)).setChecked(false);
                break;
            case 1:
                ((CheckBox) view.findViewById(R.id.app_permission_check)).setChecked(true);
                break;
            case -1:
                view.findViewById(R.id.app_permission_check).setVisibility(View.GONE);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                BoxStore boxStore = ((App) ctx.getApplicationContext()).getBoxStore();
                Box<Hook> hookBox = boxStore.boxFor(Hook.class);
                if (isChecked) {
                    hookBox.put(getItem(position));
                    buttonView.setText(buttonView.getResources().getString(R.string.check));

                } else {
                    hookBox.remove(getItem(position));
                    buttonView.setText(buttonView.getResources().getString(R.string.uncheck));
                }
            }
        });
        return view;
    }
}
