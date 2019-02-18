package com.moonma.FaceSDK;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.moonma.FaceSDK.IFaceDBBaseListener;
import com.moonma.FaceSDK.FaceInfo;
import com.moonma.common.ImageUtil;
import com.moonma.common.CommonUtils;

import java.io.File;
import java.util.List;

public class FaceDBBase {

    public IFaceDBBaseListener iListener;

    private final static String FACE_IMAGE_FILE_DIR = "face_image";


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

    public String GetFaceImageDir() {
        String dir = CommonUtils.getSDCardPath()+"/"+FACE_IMAGE_FILE_DIR;
        return dir;
    }

    public boolean CreateDir(String dirpath) {
        File dir = new File(dirpath);
        if (!dir.exists()) {
            try {
                dir.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public void SaveFaceBitmap(FaceInfo info) {
        String dir = GetFaceImageDir();
        CreateDir(dir);
        long currentTime = System.currentTimeMillis();
        String filepath = dir+"/"+info.name+"_"+currentTime+".jpg";
        if (info.bmp != null) {
            ImageUtil.SaveBitmapToFile(info.bmp, filepath, true);
        }
    }


    public boolean isEmpty() {
        return true;
    }
}

