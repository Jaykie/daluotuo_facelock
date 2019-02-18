package com.moonma.FaceSDK;

import com.moonma.common.Common;
import com.moonma.common.Device;
import com.moonma.common.Source;
import com.moonma.FaceSDK.IFaceDBBaseListener;
//import com.moonma.FaceSDK.FaceDBArc;
import com.moonma.FaceSDK.FaceDBOpenAiLab;
import com.moonma.FaceSDK.FaceDBBase;
import com.moonma.FaceSDK.FaceInfo;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import java.util.List;

public class FaceDBCommon implements IFaceDBBaseListener {

    FaceDBBase faceDB;
    IFaceDBBaseListener iListener;

    Uri mImage = null;

    static private FaceDBCommon _main;

    public static FaceDBCommon main() {
        if (_main == null) {
            _main = new FaceDBCommon();
        }
        return _main;
    }

    public void CreateSDK(String source) {

        if (Device.isEmulator()) {
            return;
        }
        if (source.equals(Source.FACE_ARC)) {
            //   faceDB = new FaceDBArc();
        } else if (source.equals(Source.FACE_OPENAILAB)) {
            faceDB = new FaceDBOpenAiLab();
        }
        if (faceDB != null) {
            faceDB.Init();
            faceDB.SetListener(this);
        }
    }

    public List<FaceInfo> GetAllFace() {
        if (faceDB != null) {
            return faceDB.GetAllFace();
        }
        return null;
    }

    public void AddFace(FaceInfo info) {
        if (faceDB != null) {
            faceDB.AddFace(info);
        }
    }

    public void DeleteFace(FaceInfo info) {
        if (faceDB != null) {
            faceDB.DeleteFace(info);
        }
    }

    public void DeleteAllFace() {
        if (faceDB != null) {
            faceDB.DeleteFaceImageDir();
            faceDB.DeleteAllFace();
        }
    }

    public boolean isEmpty() {
        if (faceDB != null) {
            return faceDB.isEmpty();
        }
        return true;
    }

    public void SetListener(IFaceDBBaseListener listener) {
        iListener = listener;
    }

}
