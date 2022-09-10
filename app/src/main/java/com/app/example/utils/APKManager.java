package com.app.example.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.app.example.project.APKInfo;
import com.app.example.project.PermissionSetting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class APKManager {
    public static List<APKInfo> getPackages(Context context) {
        // 获取已经安装的所有应用, PackageInfo　系统类，包含应用信息
        List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
        List<APKInfo> apks = new ArrayList<APKInfo>();

        for (PackageInfo i : packages) {
            if ((i.applicationInfo.flags&ApplicationInfo.FLAG_SYSTEM)==0) {
                APKInfo temp = new APKInfo();
                temp.apk_name = i.applicationInfo.loadLabel(context.getPackageManager()).toString();
                temp.package_name = i.packageName;
                temp.flags = i.applicationInfo.flags;
                apks.add(temp);
            }
        }
        Collections.sort(apks);
        return apks;
    }

    public static APKInfo getPackagePermission(Context context, String packageName) {
        APKInfo apkInfo = new APKInfo();

        try {
            PackageInfo pack = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
            apkInfo.package_name = pack.packageName;
            apkInfo.apk_name = pack.applicationInfo.loadLabel(context.getPackageManager()).toString();
            apkInfo.flags = pack.applicationInfo.flags;
            if (pack.requestedPermissions != null) {
                for (String permission : pack.requestedPermissions) {
                    PermissionSetting temp = new PermissionSetting();
                    temp.setPackageName(pack.packageName);
                    temp.setPrivacyItem(permission);
                    temp.setEnable(0);
                    apkInfo.permissionsSettings.add(temp);
                }
            }
        } catch (android.content.pm.PackageManager.NameNotFoundException e) { apkInfo = null; }
        return apkInfo;
    }
}
