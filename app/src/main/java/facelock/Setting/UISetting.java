package com.daluotuo.facelock;


import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


import com.moonma.common.UIView;
import com.moonma.common.PopViewController;
import com.moonma.common.ItemInfo;
import com.daluotuo.facelock.UISettingCellItem;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: document your custom view class.
 * <p>
 * android tableview https://www.jianshu.com/p/8669c3ebd10b
 */
public class UISetting extends UIView implements View.OnClickListener, UISettingCellItem.IUISettingCellItemDelegate {
    ImageButton btnClose;


    ListView listView;
    BaseAdapter adapter;
    List<ItemInfo> listItem = new ArrayList<ItemInfo>();//实体类

    public UISetting(int layoutId, UIView parent) {
        super(layoutId, parent);

        btnClose = (ImageButton) findViewById(R.id.btn_setting_close);
        btnClose.setOnClickListener(this);


        listView = (ListView) findViewById(R.id.setting_list);


        for (int i = 0; i < 2; i++) {
            ItemInfo info = new ItemInfo();//给实体类赋值
            info.title = "小米" + i;
            listItem.add(info);
        }

        final UISetting pthis = this;
        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return listItem.size();//数目
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Activity ac = com.moonma.common.Common.getMainActivity();
                LayoutInflater inflater = ac.getLayoutInflater();
                View view;

                if (convertView == null) {
                    //因为getView()返回的对象，adapter会自动赋给ListView
                    UISettingCellItem ui = new UISettingCellItem(R.layout.uisettingcellitem, parent);
                    ui.SetController(pthis.controller);
                    ui.iDelegate = pthis;
                    view = ui.content;
                    ui.index = position;
                    //   view = inflater.inflate(R.layout.uisettingcellitem, null);
                } else {
                    view = convertView;
                    Log.i("info", "有缓存，不需要重新生成" + position);
                }
               TextView tv = (TextView) view.findViewById(R.id.setting_item_title);//找到Textviewname
                tv.setText(listItem.get(position).title);//设置参数

                return view;
            }

            @Override
            public long getItemId(int position) {//取在列表中与指定索引对应的行id
                return 0;
            }

            @Override
            public Object getItem(int position) {//获取数据集中与指定索引对应的数据项
                return null;
            }
        };
        listView.setAdapter(adapter);
    }

    void OnClickBtnClose() {
        PopViewController p = (PopViewController) this.controller;
        if (p != null) {
            p.Close();
        }
    }

    void OnClickBtnRegister() {
        RegisterViewController.main().Show(null, null);
        OnClickBtnClose();
    }

    void OnClickBtnFaceManager() {
        FaceManagerViewController.main().Show(null, null);
        OnClickBtnClose();
    }


    @Override
    public void OnUISettingCellItemDidClick(UISettingCellItem ui) {
        switch (ui.index) {
            case 0:
                OnClickBtnRegister();
                break;
            case 1: {
                OnClickBtnFaceManager();
            }
            break;
            case 2: {

            }
            break;
        }

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_setting_close) {
            OnClickBtnClose();
        }
    }
}
