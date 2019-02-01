package com.moonma.FaceSDK;

import com.moonma.common.Common;
import com.moonma.common.Source;
import com.moonma.FaceSDK.IFaceSDKBaseListener;
//import com.moonma.FaceSDK.FaceSDKArc;
import com.moonma.FaceSDK.FaceSDKOpenAiLab;
import com.moonma.FaceSDK.FaceSDKBase;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.hardware.Camera;
import com.moonma.FaceSDK.FaceInfo;


public class FaceSDKCommon implements IFaceSDKBaseListener {

    FaceSDKBase faceSDK;
    IFaceSDKBaseListener iListener;

    Uri mImage = null;
    int width;
    int height;

    static private FaceSDKCommon _main;

    public static FaceSDKCommon main() {
        if (_main == null) {
            _main = new FaceSDKCommon();
        }
        return _main;
    }

    public void setCaptureImage(Uri uri) {
        mImage = uri;
    }

    public Uri getCaptureImage() {
        return mImage;
    }

    public void InitFaceDB() {

    }

    public void SetSize(int w, int h) {
        width = w;
        height = h;
        if (faceSDK != null) {
            faceSDK.width = w;
            faceSDK.height = h;
        }
    }

    public void setMode(int mode) {
        if (faceSDK != null) {
            faceSDK.faceMode = mode;
        }
    }

    public int getMode() {
        if (faceSDK != null) {
            return faceSDK.faceMode;
        }
        return FaceSDKBase.MODE_PREVIEW;
    }

    public void createSDK(String source) {

        if (source.equals(Source.FACE_ARC)) {
            //   faceSDK = new FaceSDKArc();
        } else if (source.equals(Source.FACE_OPENAILAB)) {
            faceSDK = new FaceSDKOpenAiLab();
        }
        if (faceSDK != null) {
            faceSDK.width = width;
            faceSDK.height = height;
            faceSDK.init();
            faceSDK.setListener(this);
        }
    }


    public void setListener(IFaceSDKBaseListener listener) {
        iListener = listener;
    }

    public Object onPreview(Camera cam, byte[] data, int width, int height, int format,
                            long timestamp) {
        return faceSDK.onPreview(cam, data, width, height, format, timestamp);
    }

    public int CheckRegisterFace(Bitmap bmp) {
        return faceSDK.CheckRegisterFace(bmp);
    }

    @Override
    public void FaceDidDetect(FaceInfo info) {
        if (iListener != null) {
            iListener.FaceDidDetect(info);
        }
    }

    @Override
    public void FaceDidFail(FaceInfo info) {
        if (iListener != null) {
            iListener.FaceDidFail(info);
        }
    }

    @Override
    public void FaceDidRegister(FaceInfo info,boolean isRedo) {
        if (iListener != null) {
            iListener.FaceDidRegister(info, isRedo);
        }
    }
}
