package com.daluotuo.facelock;


import android.content.Context;
import android.view.View;

import com.moonma.common.UIView;
import com.moonma.common.UIViewController;
import com.moonma.common.PopViewController;
import com.moonma.FaceSDK.FaceSDKBase;

public class SettingViewController extends PopViewController {
    UISetting ui;


    static private SettingViewController _main;

    public static SettingViewController main() {
        if (_main == null) {
            _main = new SettingViewController();
        }
        return _main;
    }


    public void ViewDidLoad() {
        super.ViewDidLoad();
        createContent();
    }

    public void createContent() {


        int retId = R.layout.uisetting;
//
        ui = new UISetting(retId, view);
        view.addView(ui);
    }
}
