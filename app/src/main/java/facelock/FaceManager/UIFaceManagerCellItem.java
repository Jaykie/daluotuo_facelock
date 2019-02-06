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
import com.daluotuo.facelock.RegisterViewController;

/**
 * TODO: document your custom view class.
 *
 * android tableview https://www.jianshu.com/p/8669c3ebd10b
 */
public class UIFaceManagerCellItem extends UIView implements View.OnClickListener{
    ImageButton btnClose;

    void OnClickBtnClose() {

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_setting_close) {
            OnClickBtnClose();
        }

    }
}
