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
import android.os.Handler;
import android.text.InputFilter;
import android.text.TextPaint;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.daluotuo.facelock.RegisterViewController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO: document your custom view class.
 */
public class UIFaceTips extends UIView implements View.OnClickListener {

    public enum Type {
        DETECT_SUCCESS,
        UNREGISTER,
        REGISTER_SUCCESS,
        REGISTER_REDO,//已经注册
    }

    TextView textTitle;
    ImageView imageBg;
    ImageView imageIcon;
    Button btnRegister;

    //    private ExtImageView com.daluotuo.facelock.;
    private static final int REQUEST_CODE_OP = 3;

    public UIFaceTips() {
    }

    public void CreateUI(int layoutId, UIView parent) {
        // LaodL(layoutId, parent);
        LoadLayoutRes(layoutId, parent);

        textTitle = (TextView) findViewById(R.id.text_facetips);

        imageBg = (ImageView) findViewById(R.id.bg_facetips);

        imageIcon = (ImageView) findViewById(R.id.icon_facetips);

        btnRegister = (Button) findViewById(R.id.btn_facetips_register);
        btnRegister.setOnClickListener(this);
        btnRegister.setText(R.string.register);
    }

    public void UpdateType(Type ty, String name) {
        Activity ac = Common.getMainActivity();
        if (ty == Type.DETECT_SUCCESS) {
            if (name != null) {
                String str = Common.stringFromResId(R.string.facetips_success) + ":" + name;
                textTitle.setText(str);//R.string.facetips_success
            }

            imageBg.setImageResource(R.drawable.bg_detect_success);
            imageIcon.setImageResource(R.drawable.icon_success);
            btnRegister.setVisibility(View.GONE);
            textTitle.setTextColor(ac.getResources().getColor(R.color.facetips_detect_success));
        }
        if (ty == Type.UNREGISTER) {
            textTitle.setText(R.string.facetips_unregister);
            imageBg.setImageResource(R.drawable.bg_unregister);
            imageIcon.setImageResource(R.drawable.icon_warning);
            btnRegister.setVisibility(View.VISIBLE);
            textTitle.setTextColor(ac.getResources().getColor(R.color.facetips_unregister));
        }
        if ((ty == Type.REGISTER_SUCCESS)||(ty == Type.REGISTER_REDO))
        {
            if (ty == Type.REGISTER_SUCCESS)
            {
                textTitle.setText(R.string.facetips_register_success);
            }else {
                textTitle.setText(R.string.facetips_register_redo);
            }
            imageBg.setImageResource(R.drawable.bg_unregister);
            imageIcon.setImageResource(R.drawable.icon_warning);
            btnRegister.setVisibility(View.GONE);
            textTitle.setTextColor(ac.getResources().getColor(R.color.facetips_unregister));
            OnHideDelay();
        }
    }


    void OnHideDelay() {
        long time = 2000;//ms
        //定时更新
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Show(false);
                FaceSDKCommon.main().setMode(FaceSDKBase.MODE_DETECT);
            }
        };

        handler.postDelayed(r, time);//延时毫秒
    }

    void OnClickBtnRegister() {
        this.Show(false);
        RegisterViewController.main().Show(null, null);

    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_facetips_register) {
            OnClickBtnRegister();
        }
    }


}
