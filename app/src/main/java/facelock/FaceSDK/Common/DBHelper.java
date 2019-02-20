package com.moonma.FaceSDK;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import com.moonma.FaceSDK.FaceInfo;
import com.moonma.common.DeleteFileUtil;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;


class DBHelper extends SQLiteOpenHelper {
    private final String TAG = this.getClass().toString();
    private static final String DATABASE_NAME = "face_data.db";//数据库名字
    private static final String TABLE_FACE = "table_face";

    private static final int DATABASE_VERSION = 1;//数据库版本号
    private static final String SQL_CREATE_TABLE_FACE = "create table " + TABLE_FACE + " ("
            + "id text primary key,"//autoincrement
            + "name text, "
            + "time ong, "
            + "pic text )";//数据库里的表 data blob

    public SQLiteDatabase dbOpen;

    public DBHelper(Context context) {
        this(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);//调用到SQLiteOpenHelper中
        Log.d(TAG, "New CustomSQLiteOpenHelper");
    }

    public void Open() {
        if (dbOpen == null) {
            dbOpen = this.getWritableDatabase();//创建数据库
            //dbOpen.execSQL(SQL_CREATE_TABLE_FACE);
        }
    }

    public void Close() {
        this.close();
    }

    //在调getReadableDatabase或getWritableDatabase时，会判断指定的数据库是否存在，不存在则调SQLiteDatabase.create创建， onCreate只在数据库第一次创建时才执行
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");
        db.execSQL(SQL_CREATE_TABLE_FACE);
        //dbOpen = db;
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

    public List<FaceInfo> GetAllItem() {
        //    db.execSQL("insert into test (id,photo) values(?,?)",new Object[]{photo.getId(),photoByte});
        // String strSql="insert into "+TABLE_FACE+" values(1,'TT','一起去旅游','10月1号')";
        List<FaceInfo> listItem = new ArrayList<FaceInfo>();
        if (dbOpen != null) {
            //：select * from table1 order by field1，field2 [desc]
            //SQL 语句中, asc是指定列按升序排列，desc则是指定列按降序排列。
            // dbOpen.execSQL("select * from " + TABLE_FACE + "order by time [desc]");
            Cursor cr;
            cr = dbOpen.rawQuery("select * from " + TABLE_FACE + " order by time desc", null);
            if (cr.moveToFirst()) {

                for (int i = 0; i < cr.getCount(); i++) {
                    //cr.getString();
                    FaceInfo info = new FaceInfo();
                    info.id = cr.getString(cr.getColumnIndex("id"));
                    info.name = cr.getString(cr.getColumnIndex("name"));
                    info.time = cr.getLong(cr.getColumnIndex("time"));
                    info.pic = cr.getString(cr.getColumnIndex("pic"));
                    //info.data = cr.getBlob(cr.getColumnIndex("data"));
                    listItem.add(info);
                    cr.moveToNext();
                }
            }
        }
        return listItem;
    }

    public void AddItem(FaceInfo info) {
        //    db.execSQL("insert into test (id,photo) values(?,?)",new Object[]{photo.getId(),photoByte});
        // String strSql="insert into "+TABLE_FACE+" values(1,'TT','一起去旅游','10月1号')";

        long currentTime = System.currentTimeMillis();
        if (dbOpen != null) {
            dbOpen.execSQL("insert into " + TABLE_FACE + "  (id,name,pic,time) values(?,?,?,?)", new Object[]{info.id, info.name, info.pic, currentTime});
        }
    }

    //删除
    public void DeleteItem(FaceInfo info) {
        //    db.execSQL("insert into test (id,photo) values(?,?)",new Object[]{photo.getId(),photoByte});
        // String strSql="insert into "+TABLE_FACE+" values(1,'TT','一起去旅游','10月1号')";
        //
        if (info.pic != null) {
            DeleteFileUtil.deleteFile(info.pic);
        }
        if (dbOpen != null) {
            dbOpen.execSQL("delete from " + TABLE_FACE + " where name=" + info.name);
        }
    }

    public void DeleteAll() {
        if (dbOpen != null) {
            dbOpen.execSQL("delete from " + TABLE_FACE);
        }

    }

    public boolean isEmpty() {
        int number = 0;
        if (dbOpen != null) {
            Cursor c = dbOpen.rawQuery("select * from " + TABLE_FACE, null);
            number = c.getCount();
        }
        if (number == 0) {
            return true;
        }
        return false;
    }
}
