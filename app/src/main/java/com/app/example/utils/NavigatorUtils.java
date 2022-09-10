package com.app.example.utils;

import android.content.Context;
import android.content.Intent;

import com.app.example.ui.APPHooksSettingActivity;
import com.app.example.project.APKInfo;

public class NavigatorUtils {

    public static void toAPPPermissionsSetting(Context context, APKInfo apk) {
        if (context == null) { throw new RuntimeException("Context not be null!!!"); }
        Intent intent = new Intent(context, APPHooksSettingActivity.class);
        intent.putExtra("apkinfo", apk.package_name);
        context.startActivity(intent);
    }
}
