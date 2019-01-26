package com.daluotuo.facelock;

import com.moonma.common.UIViewController;
import com.moonma.common.PopViewController;

public class RegisterViewController extends PopViewController
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
        int retId = R.layout.uiregister;

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
