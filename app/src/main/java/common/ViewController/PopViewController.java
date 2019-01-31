package com.moonma.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

import com.moonma.common.Common;
import com.moonma.common.UIView;
import com.moonma.common.UIViewController;
import com.moonma.common.MainActivityBase;

public class PopViewController extends UIViewController implements View.OnClickListener {

    IPopViewControllerDelegate iDelegate;

    public interface IPopViewControllerDelegate {
        public void OnPopViewControllerDidClose(PopViewController controller);

    }


    public void Show(UIViewController controller, IPopViewControllerDelegate dele) {
        iDelegate = dele;
        UIViewController root = controller;
        if (root == null) {
            MainActivityBase ac = (MainActivityBase)Common.getMainActivity();
            root = ac.rootViewController;
        }
        if (root != null) {
            SetViewParent(root.view.content);
        }

        //屏蔽PopViewController底下ViewController的触模点击
        this.view.content.setOnClickListener(this);
    }

    public void Close() {
        if (iDelegate != null) {
            iDelegate.OnPopViewControllerDidClose(this);
        }
        DestroyView();
    }

    @Override
    public void onClick(View view) {

    }
}
