package com.moonma.FaceSDK;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.hardware.Camera;

import com.moonma.FaceSDK.IFaceDBBaseListener;
import com.moonma.FaceSDK.FaceDBBase;
import com.moonma.FaceSDK.DBHelper;
import com.moonma.common.Common;
import com.moonma.FaceSDK.FaceInfo;

import java.util.List;

public class FaceDBOpenAiLab extends FaceDBBase {
    DBHelper dbHelper;

    public void Init() {
        Activity ac = Common.getMainActivity();
        dbHelper = new DBHelper(ac);//
        dbHelper.getWritableDatabase();//创建数据库
    }

    public void AddFace(FaceInfo info) {
        SaveFaceBitmap(info);
        if (dbHelper != null) {
            dbHelper.AddItem(info);
        }
    }

    public List<FaceInfo> GetAllFace() {
        if (dbHelper != null) {
            return dbHelper.GetAllItem();
        }
        return null;
    }

    public void DeleteAllFace() {
        if (dbHelper != null) {
            dbHelper.DeleteAll();
        }
    }

    public void DeleteFace(FaceInfo info) {
        if (dbHelper != null) {
            dbHelper.DeleteItem(info);
        }
    }

    public boolean isEmpty() {
        if (dbHelper != null) {
            return dbHelper.isEmpty();
        }
        return true;
    }


}

