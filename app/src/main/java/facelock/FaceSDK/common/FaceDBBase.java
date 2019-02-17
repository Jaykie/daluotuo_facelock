package com.moonma.FaceSDK;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.moonma.FaceSDK.IFaceDBBaseListener;
import com.moonma.FaceSDK.FaceInfo;

import java.util.List;

public class FaceDBBase {

    public IFaceDBBaseListener iListener;

    public void SetListener(IFaceDBBaseListener listener) {
        iListener = listener;
    }

    public void Init() {

    }

    public List<FaceInfo> GetAllFace() {
        return null;
    }

    public void AddFace(FaceInfo info) {
    }

    public void DeleteFace(FaceInfo info) {
    }

    public void DeleteAllFace() {

    }

    public boolean isEmpty() {
        return true;
    }
}

