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
import android.text.format.Time;
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
import com.daluotuo.facelock.UIFaceTips;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import com.moonma.FaceSDK.FaceInfo;
/**
 * TODO: document your custom view class.
 */
public class UIHome extends UIView implements View.OnClickListener, UICamera.OnUICameraListener {
    // public UICamera uiCamera;
    public UICameraOpenAiLab uiCamera;

    ;
    private ImageButton btnSetting;
    TextView textCompany;
    TextView textSystem;
    TextView textDate;
    String[] strWeekNum_cn = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    String[] strWeekNum_ = {"日", "一", "二", "三", "四", "五", "六"};

    //    private ExtImageView com.daluotuo.facelock.;
    private static final int REQUEST_CODE_OP = 3;

    public UIHome() {
    }

    public void CreateUI(int layoutId, UIView parent) {
        // LaodL(layoutId, parent);
        LoadLayoutRes(layoutId, parent);
        btnSetting = (ImageButton) findViewById(R.id.BtnSetting);
        btnSetting.setOnClickListener(this);

        textCompany = (TextView) findViewById(R.id.TextCompany);
        textSystem = (TextView) findViewById(R.id.TextSystem);
        textDate = (TextView) findViewById(R.id.TextDate);

        textCompany.setText(R.string.company);
        textSystem.setText(R.string.system);
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");// HH:mm:ss
////获取当前时间
//        Date date = new Date(System.currentTimeMillis());
//        textDate.setText("Date获取当前日期时间" + simpleDateFormat.format(date));

        Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
        t.setToNow(); // 取得系统时间。
        int year = t.year;
        int month = t.month + 1;
        int day = t.monthDay;
        int hour = t.hour; // 0-23
        int minute = t.minute;
        int second = t.second;
        int week = t.weekDay;

        textDate.setText(year + "年" + month + "月" + day + "日 " + strWeekNum_cn[week] + " " + hour + ":" + minute);

        uiCamera.setUICameraListener(this);
        uiCamera.setMode(FaceSDKBase.MODE_DETECT);
    }

    void OnClickBtnSetting() {
        SettingViewController.main().Show(null, null);
    }

    public void doRegister(Bitmap bmp) {

    }

    @Override
    public void CameraDidRegisterFace(UIView ui, FaceInfo info) {
        doRegister(info.bmp);
    }

    @Override
    public void CameraDidDetect(FaceInfo info) {
//        if (uiFaceTips != null) {
//            if (!uiFaceTips.isVisibility()) {
//                uiFaceTips.Show(true);
//                uiFaceTips.UpdateType(UIFaceTips.Type.DETECT_SUCCESS);
//            }
//        }
    }

    @Override
    public void CameraDetectFail(FaceInfo info) {
//        if (uiFaceTips != null) {
//            uiFaceTips.Show(false);
//        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.BtnSetting) {
            OnClickBtnSetting();
        }

    }


}
