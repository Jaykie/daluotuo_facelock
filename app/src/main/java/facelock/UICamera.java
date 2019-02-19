package com.daluotuo.facelock;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.hardware.Camera;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

//import com.guo.android_extend.GLES2Render;
//import com.guo.android_extend.tools.CameraHelper;
//import com.guo.android_extend.widget.CameraFrameData;
//import com.guo.android_extend.widget.CameraGLSurfaceView;
//import com.guo.android_extend.widget.CameraSurfaceView;
import com.moonma.common.Common;
import com.moonma.common.Device;
import com.moonma.common.Source;
import com.moonma.common.UIView;

import com.moonma.FaceSDK.FaceSDKBase;
import com.moonma.FaceSDK.IFaceSDKBaseListener;
import com.moonma.FaceSDK.FaceSDKCommon;
//import com.moonma.FaceSDK.FaceDB;
import com.moonma.FaceSDK.FaceDBCommon;
import com.moonma.FaceSDK.IFaceDBBaseListener;

import java.util.List;
import com.moonma.FaceSDK.FaceInfo;


import com.daluotuo.facelock.UIFaceTips;

public class UICamera extends UIView
        implements
//        CameraSurfaceView.OnCameraListener,
//        View.OnTouchListener,
//        Camera.AutoFocusCallback,
        IFaceSDKBaseListener {

    private final String TAG = this.getClass().getSimpleName();

    public int mWidth, mHeight, mFormat;
//    private CameraSurfaceView mSurfaceView;
//    private CameraGLSurfaceView mGLSurfaceView;
    private Camera mCamera;

    public int mCameraID;
    public int mCameraRotate;
    public int mCameraMirror;

    //ui
    ImageButton btnCamSelect;

    //FACESDK
    public FaceSDKCommon faceSDKCommon;
    public OnUICameraListener mListener;
    public UIFaceTips uiFaceTips;


    public interface OnUICameraListener {
        public void CameraDidRegisterFace(UIView ui,FaceInfo info);
        public void CameraDidDetect(FaceInfo info);
        public void CameraDetectFail(FaceInfo info);
    }

    public UICamera() {

    }

    public UICamera(int layoutId, UIView parent) {
        super(layoutId, parent);
    }

    public void CreateUI(int layoutId, UIView parent) {
        LoadLayoutRes(layoutId, parent);
    }

    public void setUICameraListener(OnUICameraListener listener) {
        mListener = listener;
    }

    public void setMode(int mode) {
        if (faceSDKCommon != null) {
            faceSDKCommon.setMode(mode);
        }
    }

    //face相似度阀值 0-1f
    public void SetFaceSimilarityMin(float value) {

    }

    public Rect GetFaceRect()
    {
        return new Rect(0,0,0,0);
    }

//    public void updateCameraSize( ) {
//        if (mCameraID == Camera.CameraInfo.CAMERA_FACING_FRONT) {
//            mCameraRotate = 270;
//            mCameraMirror = GLES2Render.MIRROR_X;
//            if(Device.isLandscape())
//            {
//                mCameraRotate = 180;
//            }
//        } else {
//            mCameraRotate = 90;
//            mCameraMirror = GLES2Render.MIRROR_NONE;
//
//            if(Device.isLandscape())
//            {
//                mCameraRotate = 0;
//            }else {
//                mCameraRotate = 0;
//            }
//        }
//    }
//
//    @Override
//    public Camera setupCamera() {
//        // TODO Auto-generated method stub
//        mCamera = Camera.open(mCameraID);
//        try {
//            Camera.Parameters parameters = mCamera.getParameters();
//            parameters.setPreviewSize(mWidth, mHeight);
//            parameters.setPreviewFormat(mFormat);
//
//            for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
//                Log.d(TAG, "SIZE:" + size.width + "x" + size.height);
//            }
//            for (Integer format : parameters.getSupportedPreviewFormats()) {
//                Log.d(TAG, "FORMAT:" + format);
//            }
//
//            List<int[]> fps = parameters.getSupportedPreviewFpsRange();
//            for (int[] count : fps) {
//                Log.d(TAG, "T:");
//                for (int data : count) {
//                    Log.d(TAG, "V=" + data);
//                }
//            }
//            //parameters.setPreviewFpsRange(15000, 30000);
//            //parameters.setExposureCompensation(parameters.getMaxExposureCompensation());
//            //parameters.setWhiteBalance(Camera.Parameters.WHITE_BALANCE_AUTO);
//            //parameters.setAntibanding(Camera.Parameters.ANTIBANDING_AUTO);
//            //parmeters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
//            //parameters.setSceneMode(Camera.Parameters.SCENE_MODE_AUTO);
//            //parameters.setColorEffect(Camera.Parameters.EFFECT_NONE);
//            mCamera.setParameters(parameters);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (mCamera != null) {
//            mWidth = mCamera.getParameters().getPreviewSize().width;
//            mHeight = mCamera.getParameters().getPreviewSize().height;
//        }
//        return mCamera;
//    }
//
//    @Override
//    public void setupChanged(int format, int width, int height) {
//
//    }
//
//    @Override
//    public boolean startPreviewImmediately() {
//        return true;
//    }
//
//
//    @Override
//    public Object onPreview(byte[] data, int width, int height, int format, long timestamp) {
//
//        if (faceSDKCommon != null) {
//            return faceSDKCommon.onPreview(mCamera,data, width, height, format, timestamp);
//        }
//        Rect[] rects = new Rect[0];
//        return rects;
//    }
//
//    @Override
//    public void onBeforeRender(CameraFrameData data) {
//
//    }
//
//    @Override
//    public void onAfterRender(CameraFrameData data) {
//        mGLSurfaceView.getGLES2Render().draw_rect((Rect[]) data.getParams(), Color.GREEN, 2);
//    }
//
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        CameraHelper.touchFocus(mCamera, event, v, this);
//        return false;
//    }
//
//    @Override
//    public void onAutoFocus(boolean success, Camera camera) {
//        if (success) {
//            Log.d(TAG, "Camera Focus SUCCESS!");
//        }
//    }

    @Override
    public void FaceDidDetect(com.moonma.FaceSDK.FaceInfo info ) {
        final  com.moonma.FaceSDK.FaceInfo info_show = info;
        Common.getMainActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (faceSDKCommon.getMode() == FaceSDKBase.MODE_DETECT) {
                    if (mListener != null) {
                        mListener.CameraDidDetect(info_show);
                    }

                    if (uiFaceTips != null) {
                        if (!uiFaceTips.isVisibility()) {
                            uiFaceTips.Show(true);
                            uiFaceTips.UpdateType(UIFaceTips.Type.DETECT_SUCCESS, info_show.name);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void FaceDidFail(com.moonma.FaceSDK.FaceInfo info) {
        final com.moonma.FaceSDK.FaceInfo info_show = info ;
        Common.getMainActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (faceSDKCommon.getMode() == FaceSDKBase.MODE_DETECT) {
                    if (mListener != null) {
                        mListener.CameraDetectFail(info_show);
                    }
                    if (uiFaceTips != null) {
                        uiFaceTips.Show(false);
                    }
                }
            }
        });
    }

    @Override
    public void FaceDidRegister(com.moonma.FaceSDK.FaceInfo info, final boolean isRedo) {
        final com.moonma.FaceSDK.FaceInfo info_show = info ;
        final UIView ui = this;
        Common.getMainActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (faceSDKCommon.getMode() == FaceSDKBase.MODE_REGISTR) {
                    // doRegister(bmp_show);
                    if (mListener != null) {
                        mListener.CameraDidRegisterFace(ui, info_show);
                    }

                    if (uiFaceTips != null) {
                        //   if (!uiFaceTips.isVisibility())
                        {
                            uiFaceTips.Show(true);
                            uiFaceTips.UpdateType(isRedo?UIFaceTips.Type.REGISTER_REDO:UIFaceTips.Type.REGISTER_SUCCESS, null);
                        }
                    }

                    FaceDBCommon.main().AddFace(info_show);
                }
            }
        });

    }


}
