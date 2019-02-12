/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.moonma.common;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;

import com.moonma.common.InhaleMesh;
import com.moonma.common.InhaleMesh.InhaleDir;
//import com.nj1s.lib.test.R;

/*
 * Android 吸入动画效果详解（仿mac退出效果）：
 * https://blog.csdn.net/u013372185/article/details/46929763
 */

public class PathAnimation extends Animation {
    public interface IAnimationUpdateListener {
        public void onAnimUpdate(int index);
    }

    private int mFromIndex = 0;
    private int mEndIndex = 0;
    private boolean mReverse = false;
    private IAnimationUpdateListener mListener = null;

    public PathAnimation(int fromIndex, int endIndex, boolean reverse, IAnimationUpdateListener listener) {
        mFromIndex = fromIndex;
        mEndIndex = endIndex;
        mReverse = reverse;
        mListener = listener;
    }

    public boolean getTransformation(long currentTime, Transformation outTransformation) {

        boolean more = super.getTransformation(currentTime, outTransformation);
        Log.d("leehong2", "getTransformation    more = " + more);
        return more;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int curIndex = 0;
        Interpolator interpolator = this.getInterpolator();
        if (null != interpolator) {
            float value = interpolator.getInterpolation(interpolatedTime);
            interpolatedTime = value;
        }

        if (mReverse) {
            interpolatedTime = 1.0f - interpolatedTime;
        }

        curIndex = (int) (mFromIndex + (mEndIndex - mFromIndex) * interpolatedTime);

        if (null != mListener) {
            Log.i("leehong2", "onAnimUpdate  =========== curIndex = " + curIndex);
            mListener.onAnimUpdate(curIndex);
        }
    }
}

