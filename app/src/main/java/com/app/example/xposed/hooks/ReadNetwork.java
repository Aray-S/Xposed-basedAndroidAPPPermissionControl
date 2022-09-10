package com.app.example.xposed.hooks;

import android.content.Context;
import android.os.Build;

import java.lang.reflect.Member;
import java.util.ArrayList;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

public class ReadNetwork extends BaseHook {
    public static final String TAG = "ReadNetwork";

    public static boolean hook(final Context context) {
        boolean status = true;
        classLoader = context.getClassLoader();
        if (hookWifiManagerGetConfiguredNetworks(context)) status = false;
        if (hookWifiManagerGetScanResults(context)) status = false;
        if (hookWifiInfoGetBSSID(context)) status = false;
        if (hookWifiInfoGetSSID(context)) status = false;
        return status;
    }

    private static boolean hookWifiManagerGetConfiguredNetworks(final Context context) {
        int minSdk = 1;
        int maxSdk = Integer.MAX_VALUE;
        try {
            if (Build.VERSION.SDK_INT < minSdk || Build.VERSION.SDK_INT > maxSdk) return false;
            Member member = getClassForName(context.getSystemService(Context.WIFI_SERVICE).getClass().getName())
                    .getDeclaredMethod("getConfiguredNetworks");
            XposedBridge.hookMethod(member, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    if (param.getResult() == null) return;
                    param.setResult(new ArrayList<>());
                }
            });
        } catch (Exception e) {
            XposedBridge.log(e.getMessage());
            return false;
        }
        return true;
    }

    private static boolean hookWifiManagerGetScanResults(final Context context) {
        int minSdk = 1;
        int maxSdk = Integer.MAX_VALUE;
        try {
            if (Build.VERSION.SDK_INT < minSdk || Build.VERSION.SDK_INT > maxSdk) return false;
            Member member = getClassForName(context.getSystemService(Context.WIFI_SERVICE).getClass().getName())
                    .getDeclaredMethod("getScanResults");
            XposedBridge.hookMethod(member, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    if (param.getResult() == null) return;
                    param.setResult(new ArrayList<>());
                }
            });
        } catch (Exception e) {
            XposedBridge.log(e.getMessage());
            return false;
        }
        return true;
    }

    private static boolean hookWifiInfoGetBSSID(final Context context) {
        int minSdk = 1;
        int maxSdk = Integer.MAX_VALUE;
        try {
            if (Build.VERSION.SDK_INT < minSdk || Build.VERSION.SDK_INT > maxSdk) {return false;}
            Member member = getClassForName(android.net.wifi.WifiInfo.class.getName()).getDeclaredMethod("getBSSID");
            XposedBridge.hookMethod(member, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    if (param.getResult() == null) {return;}
                    param.setResult("00:00:00:00:00:00");
                }
            });
        } catch (Exception e) {
            XposedBridge.log(e.getMessage());
            return false;
        }
        return true;
    }

    private static boolean hookWifiInfoGetSSID(final Context context) {
        int minSdk = 1;
        int maxSdk = Integer.MAX_VALUE;
        try {
            if (Build.VERSION.SDK_INT < minSdk || Build.VERSION.SDK_INT > maxSdk) {return false;}
            Member member = getClassForName(android.net.wifi.WifiInfo.class.getName()).getDeclaredMethod("getSSID");
            XposedBridge.hookMethod(member, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    if (param.getResult() == null) {return;}
                    param.setResult("\"private\"");
                }
            });
        } catch (Exception e) {
            XposedBridge.log(e);
            return false;
        }
        return true;
    }
}
