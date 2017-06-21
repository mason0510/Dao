package com.sfsj.asus.myapp.base;

import android.app.Activity;
import android.view.View;

/**
 * Created by ${zhangxiaocong} on 2017/6/11.
 */
public abstract class BaseMenuDetailPager {
    public  Activity mActivity;
    public  View mRootrView;
    //详情页跟布局


    public BaseMenuDetailPager(Activity activity) {
        mActivity = activity;
        mRootrView = initView();
    }
//布局
    public abstract View initView() ;
//数据
    public void initData(){};
}
