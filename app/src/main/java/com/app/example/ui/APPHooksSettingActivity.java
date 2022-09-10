package com.app.example.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.app.example.App;
import com.app.example.ui.adapter.APPPermissionsAdapter;
import com.app.example.xposed.hooks.GetApplications;
import com.app.example.xposed.hooks.GetLocation;
import com.app.example.xposed.hooks.ReadNetwork;
import com.app.example.xposed.hooks.UseCamera;
import com.app.example.R;
import com.app.example.project.APKInfo;
import com.app.example.project.Hook;
import com.app.example.project.Hook_;
import com.app.example.project.PermissionSetting;
import com.app.example.common.NoScrollListView;
import com.app.example.utils.APKManager;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.query.Query;

public class APPHooksSettingActivity extends AppCompatActivity {

    NoScrollListView listview;
    private Box<Hook> hookBox;
    private Query<Hook> hooksQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //查询Hook配置
        hookBox = ((App) getApplication()).getBoxStore().boxFor(Hook.class);
        hooksQuery = hookBox.query().order(Hook_.privacyItem).equal(Hook_.packageName, getIntent().getStringExtra("apkinfo")).build();
        List<Hook> hooks = hooksQuery.find();
        List<PermissionSetting> permissionSettingList = getHookList();
        for (PermissionSetting permissionSetting : permissionSettingList) {
            for (Hook hook : hooks) {
                if (permissionSetting.getPrivacyItem().equals(hook.getPrivacyItem())) {
                    permissionSetting.setEnable(1);
                    permissionSetting.setId(hook.getId());
                }
            }
        }

        APKInfo apkInfo = APKManager.getPackagePermission(getApplicationContext(), getIntent().getStringExtra("apkinfo"));

        setContentView(R.layout.activity_apppermissions_setting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listview = findViewById(R.id.app_permissions_list);

        toolbar.setTitle(apkInfo.apk_name);

        APPPermissionsAdapter adapter = new APPPermissionsAdapter(permissionSettingList, getApplicationContext());
        listview.setAdapter(adapter);
    }

    private List<PermissionSetting> getHookList() {
        List<PermissionSetting> permissionsSettings = new ArrayList<PermissionSetting>();
        permissionsSettings.add(new PermissionSetting(0L, getIntent().getStringExtra("apkinfo"), GetApplications.TAG, 0));
        permissionsSettings.add(new PermissionSetting(0L, getIntent().getStringExtra("apkinfo"), GetLocation.TAG, 0));
        permissionsSettings.add(new PermissionSetting(0L, getIntent().getStringExtra("apkinfo"), ReadNetwork.TAG, 0));
        permissionsSettings.add(new PermissionSetting(0L, getIntent().getStringExtra("apkinfo"), UseCamera.TAG, 0));
        return permissionsSettings;
    }
}
