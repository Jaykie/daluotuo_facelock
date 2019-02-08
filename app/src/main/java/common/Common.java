package com.moonma.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Size;
import android.view.View;

import com.moonma.common.UIView;

import android.content.Intent;

public class Common {

    static public Context appContext() {
        return MyApplication.getAppContext();
    }

    static public Activity getMainActivity() {
        MyApplication app = (MyApplication) MyApplication.getAppContext();
        return app.getMainActivity();
    }


    static public String getRunningActivityName() {
        // <uses-permission android:name="android.permission.GET_TASKS" />
        ActivityManager activityManager = (ActivityManager) appContext().getSystemService(Context.ACTIVITY_SERVICE);
        // Activity ac = null;
        String str = activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
        return str;
    }


    static public String stringFromResId(int res) {
        return appContext().getResources().getString(res);
    }
    static public Size GetScreenPixel()
    {
        DisplayMetrics dm = getMainActivity().getResources().getDisplayMetrics();
        Size sz = new Size(dm.widthPixels,dm.heightPixels);
        return  sz;
    }
    static public Size GetScreenDP()
    {
        Size sz =  GetScreenPixel();
        int w = Pixel2DP(sz.getWidth());
        int h = Pixel2DP(sz.getHeight());
        return new Size(w,h);
    }

    static public int DP2Pixel(int size) {

        DisplayMetrics metrics = new DisplayMetrics();

        getMainActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return (size * metrics.densityDpi) / DisplayMetrics.DENSITY_DEFAULT;

    }

    static public int Pixel2DP(int size) {

        DisplayMetrics metrics = new DisplayMetrics();

        getMainActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return (DisplayMetrics.DENSITY_DEFAULT * metrics.densityDpi) / size;

    }

}
