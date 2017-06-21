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
public class HomePager extends BasePager {
    public HomePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        TextView textview=new TextView(mActivity);
        textview.setText("首页");
        textview.setTextColor(Color.RED);
        textview.setTextSize(32);
        textview.setGravity(Gravity.CENTER);
        flContent.addView(textview);
        tvTitle.setText("智慧北京");
        btnMenu.setVisibility(View.GONE);
    }
}
