package com.sfsj.asus.myapp.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;
import com.sfsj.asus.myapp.bean.NewsTabBean;

import java.util.ArrayList;

/**
 * Created by ${zhangxiaocong} on 2017/6/11.
 */
public class TopNewsAdapter extends PagerAdapter {

    private final ArrayList<NewsTabBean.TopNews> mTopNews;
    private final Activity mActivity;
    private BitmapUtils mBitmapUtils;
    public TopNewsAdapter(ArrayList<NewsTabBean.TopNews> list, Activity activity) {
        mTopNews = list;
        mActivity = activity;
    }

    @Override
    public int getCount() {
        return mTopNews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {



        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView view=new ImageView(mActivity);
        view.setScaleType(ImageView.ScaleType.FIT_XY);//设置图片缩放
        String imageUrl=mTopNews.get(position).topimage;
        mBitmapUtils.display(view,imageUrl);
        container.addView(view);

        return view;
    }
}
