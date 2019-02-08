package com.daluotuo.facelock;


import android.app.Activity;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.moonma.common.Common;
import com.moonma.common.UIView;
import com.moonma.common.PopViewController;
import com.moonma.common.ItemInfo;
import com.moonma.common.UICellBase;


import java.util.ArrayList;
import java.util.List;


/**
 * TODO: document your custom view class.
 * <p>
 * android tableview https://www.jianshu.com/p/8669c3ebd10b
 */
public class UIFaceManager extends UIView implements View.OnClickListener {
    ImageButton btnClose;
    ImageButton btnRegister;


    ListView listView;
    BaseAdapter adapter;
    List<ItemInfo> listItem = new ArrayList<ItemInfo>();//实体类
    int oneCellNum = 4;

    public UIFaceManager(int layoutId, UIView parent) {
        super(layoutId, parent);

        btnClose = (ImageButton) findViewById(R.id.btn_setting_close);
        btnClose.setOnClickListener(this);

        btnRegister = (ImageButton) findViewById(R.id.btn_setting_register);
        btnRegister.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.list_facemanager);


        for (int i = 0; i < 50; i++) {
            ItemInfo info = new ItemInfo();//给实体类赋值
            info.title = "小米"+i;
            listItem.add(info);
        }

        final com.daluotuo.facelock.UIFaceManager pthis = this;
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

                if (convertView==null) {
                    //因为getView()返回的对象，adapter会自动赋给ListView
                   UICellBase uiCell = new UICellBase(-1);
                    for(int i=0;i<pthis.oneCellNum;i++){
                        UIFaceManagerCellItem ui = new UIFaceManagerCellItem();
                       // Size dp = com.moonma.common.Common.GetScreenDP();
                        Size pixel = Common.GetScreenPixel();
                        ui.LoadLayoutRes(R.layout.uifacemanagercellitem, uiCell);
                        ViewGroup.LayoutParams lp = ui.content.getLayoutParams();
                        lp.width = pixel.getWidth()/oneCellNum;
                        lp.height = lp.width;

                        ui.SetController(pthis.controller);
                        ui.SetParent(uiCell);
                    }
                    uiCell.SetController(pthis.controller);
                    view = uiCell.content;
                 //   view = inflater.inflate(R.layout.uisettingcellitem, null);
                }else{
                    view=convertView;
                    Log.i("info","有缓存，不需要重新生成"+position);
                }
               // TextView tv1 = (TextView) view.findViewById(R.id.Textviewname);//找到Textviewname
               // tv1.setText(listItem.get(position).title);//设置参数

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

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_setting_close) {
            OnClickBtnClose();
        }
        if (view.getId() == R.id.btn_setting_register) {
            OnClickBtnRegister();
        }
    }
}
