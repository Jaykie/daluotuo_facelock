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


import com.moonma.common.PopViewController;

/**
 * TODO: document your custom view class.
 */
public class UIRegister extends UIView implements View.OnClickListener, UICamera.OnUICameraListener {
    // public UICamera uiCamera;
    public UICameraOpenAiLab uiCamera;

    private ImageButton btnRegister;
    private ImageButton btnDelAll;
    private ImageButton btnOpenImageLib;
    private ImageButton btnClose;
    private EditText mEditText;
    //    private ExtImageView com.daluotuo.facelock.;
    private static final int REQUEST_CODE_OP = 3;

    public UIRegister() {
    }

    public void CreateUI(int layoutId, UIView parent) {
        // LaodL(layoutId, parent);
        LoadLayoutRes(layoutId, parent);
        btnRegister = (ImageButton) findViewById(R.id.BtnRegister);
        btnRegister.setOnClickListener(this);
        btnDelAll = (ImageButton) findViewById(R.id.BtnDelAll);
        btnDelAll.setOnClickListener(this);

        btnOpenImageLib = (ImageButton) findViewById(R.id.BtnOpenImageLib);
        btnOpenImageLib.setOnClickListener(this);

        btnClose = (ImageButton) findViewById(R.id.btn_register_close);
        btnClose.setOnClickListener(this);

    }

    void OnFaceRegister() {
        uiCamera.setUICameraListener(this);
        uiCamera.setMode(FaceSDKBase.MODE_REGISTR);
    }

    public void doRegister(Bitmap bmp) {
        Activity ac = Common.getMainActivity();
        if (ac == null) {
            return;
        }
        if (uiCamera.faceSDKCommon != null) {
            int ret = uiCamera.faceSDKCommon.CheckRegisterFace(bmp);
            if (ret != FaceSDKBase.MSG_EVENT_REG) {
                switch (ret) {
                    case FaceSDKBase.MSG_EVENT_NO_FEATURE: {
                        Toast.makeText(ac, "人脸特征无法检测，请换一张图片", Toast.LENGTH_SHORT).show();
                    }
                    break;
                    case FaceSDKBase.MSG_EVENT_NO_FACE: {
                        Toast.makeText(ac, "没有检测到人脸，请换一张图片", Toast.LENGTH_SHORT).show();
                    }
                    break;
                    case FaceSDKBase.MSG_EVENT_FD_ERROR: {
                        Toast.makeText(ac, "FD初始化失败", Toast.LENGTH_SHORT).show();
                    }
                    break;

                    case FaceSDKBase.MSG_EVENT_FR_ERROR: {
                        Toast.makeText(ac, "FR初始化失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                    case FaceSDKBase.MSG_EVENT_IMG_ERROR: {
                        Toast.makeText(ac, "图像格式错误", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                return;
            }

        }
        LayoutInflater inflater = LayoutInflater.from(ac);
        View layout = inflater.inflate(R.layout.dialog_register, null);

        mEditText = (EditText) layout.findViewById(R.id.editview);
        mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
//        com.daluotuo.facelock. = (ExtImageView) layout.findViewById(R.id.extimageview);
//        com.daluotuo.facelock..setImageBitmap(bmp);
//
//        com.daluotuo.facelock..setRotation(uiCamera.mCameraRotate);
//        com.daluotuo.facelock..setScaleY(-uiCamera.mCameraMirror);
        final Bitmap bmpFace = bmp;

        new AlertDialog.Builder(ac)
                .setTitle("请输入注册名字")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setView(layout)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //
                        FaceDBCommon.main().registerFace(mEditText.getText().toString(), bmpFace);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
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

    void OnClickBtnClose() {
        PopViewController p = (PopViewController) this.controller;
        if (p != null) {
            p.Close();
        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.BtnRegister) {
            OnFaceRegister();
        }

        if (view.getId() == R.id.BtnDelAll) {
            FaceDBCommon.main().deleteAllFace();
        }

        if (view.getId() == R.id.BtnOpenImageLib) {
            ImageUtil.OpenSystemImageLib();
        }

        if (view.getId() == R.id.btn_register_close) {
            OnClickBtnClose();
        }


    }


}
