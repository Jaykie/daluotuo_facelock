package com.daluotuo.facelock;

import android.content.Context;
import android.view.View;

import com.moonma.common.UIView;
import com.moonma.common.UIViewController;
import com.daluotuo.facelock.UICamera;
import com.daluotuo.facelock.UIRegister;

import com.daluotuo.facelock.UICamera;
import com.daluotuo.facelock.UICameraOpenAiLab;

public class RegisterViewController extends UIViewController
{

    UIRegister ui;
   // UICamera  uiCamera;
    UICameraOpenAiLab  uiCamera;
    static private RegisterViewController _main;

    public static RegisterViewController main() {
        if(_main==null){
            _main = new RegisterViewController();
        }
        return _main;
    }



    public  void ViewDidLoad()
    {
        super.ViewDidLoad();
        createContent();
    }

    public void createContent()
    {
        int retId = R.layout.layout_register_view_controller;

//        string strPrefab = "Common/Prefab/TabBar/UITabBar";
//        GameObject obj = (GameObject)Resources.Load(strPrefab);
//        uiTabBarPrefab = obj.GetComponent<UITabBar>();
//


     //   uiCamera = new UICamera(R.layout.layout_camera,this.view);
        uiCamera = new UICameraOpenAiLab(R.layout.layout_camera_openailab,this.view);
        view.addView(uiCamera);

        ui = new UIRegister();
        ui.uiCamera = uiCamera;
        ui.CreateUI(retId,this.view);
        view.addView(ui);


    }
}
