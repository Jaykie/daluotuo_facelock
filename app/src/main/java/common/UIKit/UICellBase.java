package com.moonma.common;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.widget.LinearLayout;

import com.moonma.common.UIView;

import java.util.ArrayList;
import java.util.List;

public class UICellBase extends UIView {
    public static String TAG = "UICellBase";

    List<Object> listItem = new ArrayList<Object>();//实体类

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
    public void AddItem(Object item)
    {
        listItem.add(item);
    }

    public Object GetItem(int idx)
    {
        return listItem.get(idx);

    }
    public int GetItemCount()
    {
        return listItem.size();

    }
}
