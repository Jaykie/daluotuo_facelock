package com.moonma.FaceSDK;

import android.hardware.Camera;
import android.util.Log;

//通过手机摄像头识别环境亮度
//https://blog.csdn.net/bluewindtalker/article/details/79999172
public class CameraLightUtil {
    private final String TAG = this.getClass().toString();
    //上次记录的时间戳
    long lastRecordTime = System.currentTimeMillis();

    //上次记录的索引
    int darkIndex = 0;
    //一个历史记录的数组，255是代表亮度最大值
    long[] darkList = new long[]{255, 255, 255, 255};
    //扫描间隔
    int waitScanTime = 300;//ms

    //亮度低的阀值
    int darkValue = 60;

    float lightValue = 1f;

    static private CameraLightUtil _main;

    public static CameraLightUtil main() {
        if (_main == null) {
            _main = new CameraLightUtil();
        }
        return _main;
    }

    //亮度值 0-1f
    public float GetLight() {
        return lightValue;
    }

    //data 为YUV
    public void OnProcessLight(Camera camera, byte[] data) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastRecordTime < waitScanTime) {
            return;
        }
        lastRecordTime = currentTime;

        int width = camera.getParameters().getPreviewSize().width;
        int height = camera.getParameters().getPreviewSize().height;
        //像素点的总亮度
        long pixelLightCount = 0L;
        //像素点的总数
        long pixeCount = width * height;
        //采集步长，因为没有必要每个像素点都采集，可以跨一段采集一个，减少计算负担，必须大于等于1。
        int step = 10;
        //data.length - allCount * 1.5f的目的是判断图像格式是不是YUV420格式，只有是这种格式才相等
        //因为int整形与float浮点直接比较会出问题，所以这么比
        if (Math.abs(data.length - pixeCount * 1.5f) < 0.00001f) {
            for (int i = 0; i < pixeCount; i += step) {
                //如果直接加是不行的，因为data[i]记录的是色值并不是数值，byte的范围是+127到—128，
                // 而亮度FFFFFF是11111111是-127，所以这里需要先转为无符号unsigned long参考Byte.toUnsignedLong()
                pixelLightCount += ((long) data[i]) & 0xffL;
            }
            //平均亮度
            long cameraLight = pixelLightCount / (pixeCount / step);
            //更新历史记录
            int lightSize = darkList.length;
            darkList[darkIndex = darkIndex % lightSize] = cameraLight;
            darkIndex++;
            boolean isDarkEnv = true;
            //判断在时间范围waitScanTime * lightSize内是不是亮度过暗
            for (int i = 0; i < lightSize; i++) {
                if (darkList[i] > darkValue) {
                    isDarkEnv = false;
                }
            }
            lightValue = cameraLight*1f/255;
            Log.e(TAG, "摄像头环境亮度为 ： " + cameraLight);
//            if (!isFinishing()) {
//                //亮度过暗就提醒
//                if (isDarkEnv) {
//                    lightTV.setVisibility(View.VISIBLE);
//                } else {
//                    lightTV.setVisibility(View.GONE);
//                }
//            }
        }
    }
}
