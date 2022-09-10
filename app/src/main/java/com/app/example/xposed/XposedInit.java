package com.app.example.xposed;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Process;
import android.util.Log;

import com.app.example.xposed.hooks.GetApplications;
import com.app.example.xposed.hooks.GetLocation;
import com.app.example.xposed.hooks.ReadNetwork;
import com.app.example.xposed.hooks.UseCamera;
import com.app.example.BuildConfig;
import com.app.example.project.Hook;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


public class XposedInit implements IXposedHookZygoteInit, IXposedHookLoadPackage {
    private static final String TAG = "example.Xposed";

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        int uid = Process.myUid();
        String self = XposedInit.class.getPackage().getName();
        Log.i(TAG, "加载 " + loadPackageParam.packageName + ":" + uid);
        if (!"android".equals(loadPackageParam.packageName) && !self.equals(loadPackageParam.packageName)) {hookApplication(loadPackageParam);}
    }

    private void hookApplication(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        final int uid = Process.myUid();
        Class<?> at = Class.forName("android.app.LoadedApk", false, lpparam.classLoader);

        XposedBridge.hookAllMethods(at, "makeApplication", new XC_MethodHook() {
            private boolean made = false;

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                try {
                    if (!made) {
                        made = true;
                        Application app = (Application) param.getResult();

                        ContentResolver resolver = app.getContentResolver();

                        // Get hooks
                        List<Hook> hooks = new ArrayList<>();
                        Cursor cursor = null;
                        try {
                            cursor = resolver.query(XhookSettingsProvider.HOOKSETTING_URI, null,
                                    lpparam.packageName, null, null);
                            while (cursor != null && cursor.moveToNext()) {
                                Hook hook = new Hook();
                                hook.setPrivacyItem(cursor.getString(cursor.getColumnIndex("privacyItem")));
                                hooks.add(hook);
                            }
                        } finally {
                            if (cursor != null) {cursor.close();}
                        }
                        hookPackage(app, hooks, uid);
                    }
                } catch (Throwable ex) {
                    Log.e(TAG, Log.getStackTraceString(ex));
                    XposedBridge.log(ex);
                }
            }

            private void hookPackage(final Context context, List<Hook> hooks, final int uid) throws Throwable {
                for (final Hook hook : hooks)
                    try {
                        switch (hook.getPrivacyItem()) {
                            case GetApplications.TAG:
                                GetApplications.hook(context, uid);
                                break;
                            case GetLocation.TAG:
                                GetLocation.hook(context);
                                break;
                            case ReadNetwork.TAG:
                                ReadNetwork.hook(context);
                                break;
                            case UseCamera.TAG:
                                UseCamera.hook(context);
                                break;
                            default:
                                break;
                        }
                    } catch (Throwable ex) {
                        XposedBridge.log(ex);
                    }
            }

        });
    }


    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        Log.i(TAG, "initZygote system=" + startupParam.startsSystemServer + " debug=" + BuildConfig.DEBUG);
    }

}
