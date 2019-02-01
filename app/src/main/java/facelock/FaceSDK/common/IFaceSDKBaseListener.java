package com.moonma.FaceSDK;

import android.graphics.Bitmap;
import com.moonma.FaceSDK.FaceInfo;

public interface IFaceSDKBaseListener {
    public void FaceDidDetect(FaceInfo info);
    public void FaceDidFail(FaceInfo info);
    public void FaceDidRegister(FaceInfo info,boolean isRedo);
}