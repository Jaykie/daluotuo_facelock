package com.daluotuo.facelock;

import com.daluotuo.facelock.UIFaceTips;
import com.moonma.common.UIViewController;

public class HomeViewController extends UIViewController {

    UIHome ui;
    UIFaceTips uiFaceTips;
    // UICamera  uiCamera;
    UICameraOpenAiLab uiCamera;
    static private HomeViewController _main;

    public static HomeViewController main() {
        if (_main == null) {
            _main = new HomeViewController();
        }
        return _main;
    }


    public void ViewDidLoad() {
        super.ViewDidLoad();
        createContent();
    }

    public void createContent() {
        int retId = R.layout.uihome;

//        string strPrefab = "Common/Prefab/TabBar/UITabBar";
//        GameObject obj = (GameObject)Resources.Load(strPrefab);
//        uiTabBarPrefab = obj.GetComponent<UITabBar>();
//


        //   uiCamera = new UICamera(R.layout.layout_camera,this.view);
        uiCamera = new UICameraOpenAiLab(R.layout.uicamera_openailab, this.view);
        view.addView(uiCamera);

        ui = new UIHome();
        ui.SetController(this);
        ui.uiCamera = uiCamera;
        ui.CreateUI(retId, this.view);
        view.addView(ui);

        uiFaceTips = new UIFaceTips();
        uiFaceTips.SetController(this);
        uiFaceTips.CreateUI(R.layout.uifacetips, this.view);
        view.addView(uiFaceTips);
        uiFaceTips.UpdateType(UIFaceTips.Type.DETECT_SUCCESS);
       // uiFaceTips.Show(false);

        ui.uiFaceTips = uiFaceTips;
    }
}
