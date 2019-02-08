package com.moonma.common;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.widget.LinearLayout;

import com.moonma.common.UIView;

public class UICellBase extends UIView {
    public UICellBase(int h) {
        // Context context = Common.appContext();
        //必须用MainActivity，用appContext的话ui layout 显示会出问题
        Context context = Common.getMainActivity();
        if(h<0){
            h = LinearLayout.LayoutParams.WRAP_CONTENT;
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, h);
        content = new LinearLayout(context);
        content.setLayoutParams(lp);
    }
}
