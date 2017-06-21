package com.sfsj.asus.myapp.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.sfsj.asus.myapp.MainActivity;
import com.sfsj.asus.myapp.R;

/**
 * Created by ${zhangxiaocong} on 2017/6/11.
 */
public  class BasePager {

    public Activity mActivity;
    public   View mRootView;

    public TextView tvTitle;
    public ImageButton btnMenu;
    public FrameLayout flContent;// 空的帧布局对象, 要动态添加布局

    public ImageButton btnPhoto;//组图切换按钮
    public BasePager(Activity activity) {
        mActivity = activity;
        mRootView = initView();
    }

    private View initView() {
        View view = View.inflate(mActivity, R.layout.base_pager, null);

        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        btnMenu = (ImageButton) view.findViewById(R.id.btn_menu);
        btnPhoto = (ImageButton) view.findViewById(R.id.btn_photo);
        flContent = (FrameLayout) view.findViewById(R.id.fl_content);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogle();
            }

        });
        return view;
    }

    private void toogle() {
        MainActivity mainUI = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUI.getSlidingMenu();
        slidingMenu.toggle();// 如果当前状态是开, 调用后就关; 反之亦然
    }

    public void initData() {

    }
}
