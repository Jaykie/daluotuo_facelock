package com.daluotuo.facelock;

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

        uiCamera = new UICameraOpenAiLab();
        uiCamera.SetController(this);
        uiCamera.CreateUI(R.layout.uicamera_openailab, this.view);
        view.AddView(uiCamera);


        ui = new UIRegister();
        ui.SetController(this);
        ui.uiCamera = uiCamera;
        ui.CreateUI(retId,this.view);
        view.AddView(ui);


    }
}
