package com.sfsj.asus.myapp.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.sfsj.asus.myapp.base.BasePager;

/**
 * Created by ${zhangxiaocong} on 2017/6/12.
 */
public class SmartServicePager extends BasePager {
    public SmartServicePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {

        // 要给帧布局填充布局对象
        TextView view = new TextView(mActivity);
        view.setText("智慧服务");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);

        flContent.addView(view);

        // 修改页面标题
        tvTitle.setText("生活");

        // 显示菜单按钮
        btnMenu.setVisibility(View.VISIBLE);
    }
}
