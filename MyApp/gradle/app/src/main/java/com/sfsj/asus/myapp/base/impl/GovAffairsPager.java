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
public class GovAffairsPager extends BasePager {
    public GovAffairsPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {

        // 要给帧布局填充布局对象
        TextView view = new TextView(mActivity);
        view.setText("政务");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);

        flContent.addView(view);

        // 修改页面标题
        tvTitle.setText("人口管理");

        // 显示菜单按钮
        btnMenu.setVisibility(View.VISIBLE);
    }
}
