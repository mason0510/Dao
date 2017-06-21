package com.sfsj.asus.myapp.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.sfsj.asus.myapp.base.BasePager;

import java.util.ArrayList;

/**
 * Created by ${zhangxiaocong} on 2017/6/12.
 */
public class ContentAdapter extends PagerAdapter {
    ArrayList<BasePager> mPagers;
    public ContentAdapter(ArrayList<BasePager> mPagers) {
        this.mPagers=mPagers;
    }

    @Override
    public int getCount() {
        return mPagers.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BasePager pagers=mPagers.get(position);
        View view=pagers.mRootView;
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.addView((View) object);
    }
}
