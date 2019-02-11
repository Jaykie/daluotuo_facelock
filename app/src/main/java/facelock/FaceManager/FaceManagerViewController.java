package com.daluotuo.facelock;


import android.content.Context;
import android.view.View;

import com.moonma.common.UIView;
import com.moonma.common.UIViewController;
import com.moonma.common.PopViewController;
import com.moonma.FaceSDK.FaceSDKBase;

public class FaceManagerViewController extends PopViewController {
    UIFaceManager ui;


    static private FaceManagerViewController _main;

    public static FaceManagerViewController main() {
        if (_main == null) {
            _main = new FaceManagerViewController();
        }
        return _main;
    }


    public void ViewDidLoad() {
        super.ViewDidLoad();
        createContent();
    }

    public void createContent() {


        int retId = R.layout.uifacemanager;
//
        ui = new UIFaceManager(retId, view);
        ui.SetController(this);
        view.AddView(ui);
    }
}
