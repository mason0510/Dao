package com.sfsj.asus.myapp.base.impl.menu;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.CustomViewAbove;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sfsj.asus.myapp.MainActivity;
import com.sfsj.asus.myapp.R;
import com.sfsj.asus.myapp.base.BaseMenuDetailPager;
import com.sfsj.asus.myapp.bean.NewsTabData;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

/**
 * Created by ${zhangxiaocong} on 2017/6/11.
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager implements CustomViewAbove.OnPageChangeListener {

    @ViewInject(R.id.vp_news_menu_detail)
    private ViewPager mViewPager;

    @ViewInject(R.id.indicator)
    private TabPageIndicator mIndicator;
    private  ArrayList<NewsTabData> mTabData;
    private  ArrayList<TabDetailPager> mPagers;
    public NewsMenuDetailPager(MainActivity mainActivity, ArrayList<NewsTabData> children) {
        super(mainActivity);
        // 页签网络数据
        mTabData = children;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_news_menu_detail, null);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }
}
