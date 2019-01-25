package com.daluotuo.facelock;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.text.InputFilter;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.guo.android_extend.GLES2Render;
//import com.guo.android_extend.widget.ExtImageView;
import com.moonma.common.ImageUtil;
import com.moonma.common.UIView;
import com.moonma.common.Common;


import com.moonma.FaceSDK.FaceSDKBase;
import com.moonma.FaceSDK.IFaceSDKBaseListener;
import com.moonma.FaceSDK.FaceSDKCommon;
//import com.moonma.FaceSDK.FaceDB;
import com.moonma.FaceSDK.FaceDBCommon;
import com.moonma.FaceSDK.IFaceDBBaseListener;

import com.daluotuo.facelock.UICamera;
import com.daluotuo.facelock.UICameraOpenAiLab;
/**
 * TODO: document your custom view class.
 */
public class UIHome extends UIView implements View.OnClickListener, UICamera.OnUICameraListener {
   // public UICamera uiCamera;
    public UICameraOpenAiLab uiCamera;

    private ImageButton btnSetting;
    TextView textCompany;
    TextView textSystem;
    TextView textDate;

//    private ExtImageView com.daluotuo.facelock.;
    private static final int REQUEST_CODE_OP = 3;

    public UIHome() {
    }

    public void CreateUI(int layoutId, UIView parent) {
       // LaodL(layoutId, parent);
        LoadLayoutRes(layoutId,parent);
        btnSetting = (ImageButton) findViewById(R.id.BtnRegister);
        btnSetting.setOnClickListener(this);

        textCompany = (TextView) findViewById(R.id.TextCompany);
        textSystem = (TextView) findViewById(R.id.TextSystem);
        textDate = (TextView) findViewById(R.id.TextDate);

        textCompany.setText(R.string.company);
        textSystem.setText(R.string.system);
        textDate.setText(R.string.setting);
    }

    void OnClickBtnSetting() {
        SettingViewController.main().Show(null,null);
    }

    public void doRegister(Bitmap bmp) {

    }

    @Override
    public void CameraDidRegisterFace(UIView ui, Bitmap bmp) {
        doRegister(bmp);
    }

    @Override
    public void CameraDidDetect(String name, float score, Bitmap bmp) {


    }

    @Override
    public void CameraDetectFail(Bitmap bmp) {

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.BtnSetting) {
            OnClickBtnSetting();
        }

    }


}
