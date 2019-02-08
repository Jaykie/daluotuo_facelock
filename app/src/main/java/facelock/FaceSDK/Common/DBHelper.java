package com.moonma.FaceSDK;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import com.moonma.FaceSDK.FaceInfo;

import java.nio.ByteBuffer;


class DBHelper extends SQLiteOpenHelper {
    private final String TAG = this.getClass().toString();
    private static final String DATABASE_NAME = "face_data.db";//数据库名字
    private static final String TABLE_FACE = "table_face";

    private static final int DATABASE_VERSION = 1;//数据库版本号
    private static final String SQL_CREATE_TABLE_FACE = "create table " + TABLE_FACE + " ("
            + "id text primary key,"//autoincrement
            + "name text, "
            + "data blob )";//数据库里的表

    SQLiteDatabase dbOpen;

    public DBHelper(Context context) {
        this(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);//调用到SQLiteOpenHelper中
        Log.d(TAG, "New CustomSQLiteOpenHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");
        db.execSQL(SQL_CREATE_TABLE_FACE);
        dbOpen = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public byte[] GetRGBDataOfBmp(Bitmap bmp) {
        try {
            int bytes = bmp.getByteCount();
            int size = bmp.getWidth() * bmp.getHeight();
            int bit = bytes / size;
            ByteBuffer buf = ByteBuffer.allocate(bytes);
            bmp.copyPixelsToBuffer(buf);

            byte[] data = buf.array();
            return data;
        } catch (Exception e) {
            return null;
        }

    }

    public void AddItem(FaceInfo info) {
        //    db.execSQL("insert into test (id,photo) values(?,?)",new Object[]{photo.getId(),photoByte});
        // String strSql="insert into "+TABLE_FACE+" values(1,'TT','一起去旅游','10月1号')";
        if (dbOpen != null) {
            if(info.bmp!=null)
            {
                byte[] data = GetRGBDataOfBmp(info.bmp);
                dbOpen.execSQL("insert into " + TABLE_FACE + "  (id,name,data) values(?,?)", new Object[]{info.id, info.name, data});
            }else {
                dbOpen.execSQL("insert into " + TABLE_FACE + "  (id,name) values(?,?)", new Object[]{info.id, info.name});
            }

        }
    }

    public boolean isEmpty() {
        int number = 0;
        if (dbOpen != null) {
            Cursor c = dbOpen.rawQuery("select * from " + TABLE_FACE, null);
            number = c.getCount();
        }
        return true;
    }
}
