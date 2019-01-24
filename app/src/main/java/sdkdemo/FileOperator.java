package com.daluotuo.facelock;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperator {
    public final static int MAX_REGISTER=5000; // set max number of faces ,could be modify
    private final static int FEATURE_LEN=128;
    private final static String IMAGE_PATH= "/sdcard/openailab/image";
    private  static File storeFile=null;
    private static  int fIndex=0;

    public static float[][] flist=new float[MAX_REGISTER][FEATURE_LEN];
    public static String[] namelist=new String[MAX_REGISTER+1];
    public static int getfIndex(){
        return fIndex;
    }
    public static void incfIndex(){
        fIndex=fIndex+1;
        Log.d("morrisdebug","findex"+fIndex);
    }
    public static float[][] getflist(){
        return flist;
    }
    public static String[] getnamelist(){
        return namelist;
    }
    public static boolean setStorePath(String path){
        if(storeFile==null){
            storeFile=new File(path);
        }

        if(storeFile.exists()){
            return true;//already exist
        }else{
            return false;
        }
    }

    public static boolean saveOneItem(int index){
        FileWriter out = null;  //文件写入流
        try {
            out = new FileWriter(storeFile,true);//append
            out.write(namelist[index]+" ");
            for (int k = 0; k < 128; k++) {
                out.write(flist[index][k] + " ");
            }
            out.write("\r\n");//forget to add \r\n for another line
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean saveStoreFile(int filelenth){
        FileWriter out = null;  //文件写入流
        try {
            out = new FileWriter(storeFile,true);//append
            for(int j=fIndex-filelenth;j<fIndex;j++) { //from first register feature to last one
                out.write(namelist[j]+" ");//write file name as name
                for (int k = 0; k < 128; k++) {
                    out.write(flist[j][k] + " ");
                }
                out.write("\r\n");
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean loadStoreFile(){
        BufferedReader in = null;  //
        try {
            in = new BufferedReader(new FileReader(storeFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;  //一行数据

//逐行读取，并将每个数组放入到数组中
        try {
            while((line = in.readLine()) != null){
                String[] temp = line.split(" ");
                namelist[fIndex]=temp[0];
                for(int k=1;k<temp.length;k++){
                    flist[fIndex][k-1] = Float.parseFloat(temp[k]);
                    //Log.d("morrisdebug","value"+"["+k+"]"+flist[fIndex][k-1]);
                }
                fIndex++;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    public static boolean createFolder(String ffd) {
        File dir = new File(ffd);
        return createFolder(dir);
    }

    public static boolean createFolder(File dir) {
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


    public static boolean saveCalibrate(String path,int[] calibrate_rect,float calibrate_scale){
        FileWriter out = null;  //文件写入流
        try {
            out = new FileWriter(path,false);//append
            String writeStr =calibrate_scale+"";
            for(int i=0;i<calibrate_rect.length;i++) { //from first register feature to last one
                writeStr=writeStr+";"+calibrate_rect[i];
            }
            out.write(writeStr);//write file name as name
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String[] loadCalibrate(String calibrateFile){
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(calibrateFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] calibrateStrs =null;

        try {
            calibrateStrs = in.readLine().split(";");
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return calibrateStrs;
    }
}
