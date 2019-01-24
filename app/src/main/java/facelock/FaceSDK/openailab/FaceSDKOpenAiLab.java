package com.moonma.FaceSDK;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.moonma.FaceSDK.IFaceSDKBaseListener;
import com.moonma.FaceSDK.FaceSDKBase;
import com.moonma.common.Common;
import com.openailab.facelibrary.FaceAPP;
import com.openailab.facelibrary.FaceInfo;
import com.openailab.sdkdemo.mixController;
import com.openailab.sdkdemo.FileOperator;
import com.openailab.sdkdemo.myDrawRectView;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FaceSDKOpenAiLab extends FaceSDKBase {

    private Mat mRgba;
    private boolean syncFlag = false;
    public void init() {

      //  startPreview();
    }

    public Object onPreview(Camera cam, byte[] data, int width, int height, int format, long timestamp) {
        Rect[] rects = new Rect[0];

        if (cam != null) {
            synchronized (this) {
                //  mCamera.addCallbackBuffer(buffers);
                mRgba.put(0, 0, data);
                syncFlag = true;
            }
            cam.addCallbackBuffer(data);
        }
        return rects;
    }


    /**
     * 开始预览
     */
    private void startPreview() {
        try {

            if (mRgba == null) {
                mRgba = new Mat(width, height, CvType.CV_8UC1);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    /**
     * 停止预览
     */
    public void stopPreview() {
//        if (mCamera != null) {
//            mCamera.stopPreview();
//            mCamera.setPreviewCallback(null);
//            mCamera.release();
//            mRgba.release();
//            syncFlag = false;
//            mCamera = null;
//            mRgba = null;
//        }
    }

    public Mat getmRgba() {
        return mRgba;
    }


    public boolean isSyncFlag() {
        return syncFlag;
    }



}