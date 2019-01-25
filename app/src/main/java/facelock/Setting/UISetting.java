package com.daluotuo.facelock;


import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.moonma.common.UIView;
import com.moonma.common.PopViewController;

import com.moonma.FaceSDK.FaceSDKBase;
import com.moonma.FaceSDK.IFaceSDKBaseListener;
import com.moonma.FaceSDK.FaceSDKCommon;
import com.moonma.FaceSDK.FaceDBCommon;
import com.moonma.FaceSDK.IFaceDBBaseListener;

/**
 * TODO: document your custom view class.
 */
public class UISetting extends UIView implements View.OnClickListener{
    ImageButton btnClose;

    public UISetting(int layoutId, UIView parent) {
        super(layoutId, parent);

        btnClose = (ImageButton) findViewById(R.id.BtnRegister);
        btnClose.setOnClickListener(this);
    }

    void OnClickBtnClose() {
       PopViewController p = (PopViewController)this.controller;
       if(p!=null){
           p.Close();
       }
    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.BtnSetting) {
            OnClickBtnClose();
        }

    }
}
