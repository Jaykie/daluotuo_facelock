package com.moonma.common;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintLayout.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moonma.common.UIViewController;

public class UIView {
    public ViewGroup content;
    public UIViewController controller;

    public UIView() {
        // Context context = Common.appContext();
        //必须用MainActivity，用appContext的话ui layout 显示会出问题
        Context context = Common.getMainActivity();

        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        content = new ConstraintLayout(context);
        content.setLayoutParams(lp);
    }

    public UIView(int layoutId, UIView parent) {
        //  super(context);
        LoadLayoutRes(layoutId, parent);
    }

    public void LoadLayoutRes(int layoutId, UIView parent) {
        LoadLayoutRes(layoutId, parent.content);
    }

    public void LoadLayoutRes(int layoutId, ViewGroup parent) {
        //  super(context);
        // Context context = Common.appContext();
        //必须用MainActivity，用appContext的话ui layout 显示会出问题
        Activity ac = Common.getMainActivity();
        LayoutInflater inflater = LayoutInflater.from(ac);

        ViewGroup rootview = ac.findViewById(android.R.id.content);
        if (parent != null) {
            rootview = parent;
        }
        content = (ViewGroup) inflater.inflate(layoutId, rootview, false);
        // this.addView(v);
    }

    public void SetController(UIViewController con) {
        controller = con;

    }

    public void removeSelfFromParent(View child) {
        if (child != null) {
            ViewGroup parent = (ViewGroup) child.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                parent.removeView(child);
            }
        }
    }

    public void SetParent(ViewGroup parent) {
        if (parent != null) {
            parent.addView(content);
        }
    }

    public void SetParent(UIView parent) {
        if (parent != null) {
            parent.AddView(this);
        }
    }

    public void AddView(UIView child) {
        AddView(child.content);
    }

    public void AddView(View child) {
        content.addView(child);
    }

    public final <T extends View> T findViewById(int id) {
        return content.findViewById(id);
    }

    public void Show(boolean isShow) {
        if (content != null) {
            content.setVisibility(isShow ? View.VISIBLE : View.GONE);
        }
    }

    public boolean isVisibility() {
        if (content != null) {
            if (content.getVisibility() == View.VISIBLE) {
                return true;
            }
        }
        return false;
    }
}
