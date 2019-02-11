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
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moonma.common.Common;
import com.moonma.common.UIView;
import com.moonma.common.PopViewController;
import com.moonma.common.UICellItemBase;

import com.moonma.FaceSDK.FaceSDKBase;
import com.moonma.FaceSDK.IFaceSDKBaseListener;
import com.moonma.FaceSDK.FaceSDKCommon;
import com.moonma.FaceSDK.FaceDBCommon;
import com.moonma.FaceSDK.IFaceDBBaseListener;
import com.daluotuo.facelock.RegisterViewController;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;

import static android.view.FrameMetrics.ANIMATION_DURATION;


import com.daluotuo.facelock.BitmapMesh;

/**
 * TODO: document your custom view class.
 * <p>
 * <p>
 * Android 吸入动画效果详解（仿mac退出效果）：
 * https://blog.csdn.net/u013372185/article/details/46929763
 * <p>
 * android 仿ios图标晃动动画：
 * https://www.cnblogs.com/lee0oo0/p/3583091.html
 * https://blog.csdn.net/mysimplelove/article/details/80230155
 * <p>
 * //iOS开发之各种动画各种页面切面效果
 * http://www.cocoachina.com/ios/20141226/10775.html
 * typedef enum : NSUInteger {
 *     Fade = 1,                   //淡入淡出
 *     Push,                       //推挤
 *     Reveal,                     //揭开
 *     MoveIn,                     //覆盖
 *     Cube,                       //立方体
 *     SuckEffect,                 //吮吸
 *     OglFlip,                    //翻转
 *     RippleEffect,               //波纹
 *     PageCurl,                   //翻页
 *     PageUnCurl,                 //反翻页
 *     CameraIrisHollowOpen,       //开镜头
 *     CameraIrisHollowClose,      //关镜头
 *     CurlDown,                   //下翻页
 *     CurlUp,                     //上翻页
 *     FlipFromLeft,               //左翻转
 *     FlipFromRight,              //右翻转
 *      
 * } AnimationType;
 */
public class UIFaceManagerCellItem extends UICellItemBase implements View.OnClickListener, BitmapMesh.SampleView.IBitmapMeshDelegate {

    private boolean mNeedShake = false;

    private static final int ICON_WIDTH = 80;
    private static final int ICON_HEIGHT = 94;
    private static final float DEGREE_0 = 1.8f;
    private static final float DEGREE_1 = -2.0f;
    private static final float DEGREE_2 = 2.0f;
    private static final float DEGREE_3 = -1.5f;
    private static final float DEGREE_4 = 1.5f;
    private static final int ANIMATION_DURATION = 100;//ms

    private int mCount = 0;

    float mDensity;

    ImageView imageBg;
    TextView textTitle;
    ImageButton btnClose;

    private BitmapMesh.SampleView mSampleView = null;

    IUIFaceManagerCellItemDelegate iDelegate;

    public interface IUIFaceManagerCellItemDelegate {
        public void OnUIFaceManagerCellItemDidLongPress(UIFaceManagerCellItem ui);

        public void OnUIFaceManagerCellItemDidDelete(UIFaceManagerCellItem ui);
    }

    public void Init() {
        imageBg = (ImageView) findViewById(R.id.uifacemanagercellitem_bg);
        textTitle = (TextView) findViewById(R.id.uifacemanagercellitem_title);
        btnClose = (ImageButton) findViewById(R.id.uifacemanagercellitem_close);
        //关闭触模事件
        textTitle.setFocusable(false);
        Activity ac = Common.getMainActivity();
//        Animation shake = AnimationUtils.loadAnimation(ac,R.anim.shake);//加载动画资源文件  
//        imageBg.startAnimation(shake);

        DisplayMetrics dm = new DisplayMetrics();
        ac.getWindowManager().getDefaultDisplay().getMetrics(dm);
        if (dm != null) {
            mDensity = dm.density;
        }
        final UIFaceManagerCellItem pthis = this;
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mNeedShake) {
                    if (iDelegate != null) {
                        // iDelegate.OnUIFaceManagerCellItemDidDelete(pthis);
                    }
                }

                boolean mReverse = false;

                if (mSampleView.startAnimation(mReverse)) {
                    mReverse = !mReverse;
                }

            }
        });

        imageBg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

/**
                  * 点击消息是否进行拦截？
                  * 如果是true   不会触发后续事件
                  * 如果是false  会触发后续事件 比如说单击事件
                  */
                if (iDelegate != null) {
                    iDelegate.OnUIFaceManagerCellItemDidLongPress(pthis);
                }

                return true;
            }
        });


        mSampleView = new BitmapMesh.SampleView(ac);
        mSampleView.setIsDebug(false);
        mSampleView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.AddView(mSampleView);
        mSampleView.iDelegate = this;
        mSampleView.setFocusable(false);

        btnClose.bringToFront();
        textTitle.bringToFront();
    }

    public void UpdateItem(boolean isEdit) {
        //CharSequence
        textTitle.setText(String.valueOf(index));
        if (isEdit) {
            StartShakeAnimation();
        } else {
            StopShakeAnimation();
        }
    }

    @Override
    public void onClick(View view) {


    }

    void StartShakeAnimation() {
        mNeedShake = true;
        ShakeAnimation(imageBg);

    }

    void StopShakeAnimation() {
        mNeedShake = false;
        mCount = 0;

    }

    private void ShakeAnimation(final View v) {
        float rotate = 0;
        int c = mCount++ % 5;
        if (c == 0) {
            rotate = DEGREE_0;
        } else if (c == 1) {
            rotate = DEGREE_1;
        } else if (c == 2) {
            rotate = DEGREE_2;
        } else if (c == 3) {
            rotate = DEGREE_3;
        } else {
            rotate = DEGREE_4;
        }
        final RotateAnimation mra = new RotateAnimation(rotate, -rotate, ICON_WIDTH * mDensity / 2, ICON_HEIGHT * mDensity / 2);
        final RotateAnimation mrb = new RotateAnimation(-rotate, rotate, ICON_WIDTH * mDensity / 2, ICON_HEIGHT * mDensity / 2);

        mra.setDuration(ANIMATION_DURATION);
        mrb.setDuration(ANIMATION_DURATION);

        mra.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                if (mNeedShake) {
                    mra.reset();
                    v.startAnimation(mrb);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationStart(Animation animation) {

            }

        });

        mrb.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                if (mNeedShake) {
                    mrb.reset();
                    v.startAnimation(mra);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationStart(Animation animation) {

            }

        });
        v.startAnimation(mra);
    }

    //BitmapMesh
    @Override
    public void OnBitmapMeshDidAnimationEnd(BitmapMesh.SampleView view) {
        if (iDelegate != null) {
            iDelegate.OnUIFaceManagerCellItemDidDelete(this);
        }
    }
    //BitmapMesh
}
