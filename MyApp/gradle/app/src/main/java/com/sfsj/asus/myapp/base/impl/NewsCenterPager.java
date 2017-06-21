package com.sfsj.asus.myapp.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.sfsj.asus.myapp.MainActivity;
import com.sfsj.asus.myapp.base.BaseMenuDetailPager;
import com.sfsj.asus.myapp.base.BasePager;
import com.sfsj.asus.myapp.base.impl.menu.InteractMenuDetailPager;
import com.sfsj.asus.myapp.base.impl.menu.NewsMenuDetailPager;
import com.sfsj.asus.myapp.base.impl.menu.PhotosMenuDetailPager;
import com.sfsj.asus.myapp.base.impl.menu.TopicMenuDetailPager;
import com.sfsj.asus.myapp.bean.NewsMenu;
import com.sfsj.asus.myapp.fragment.LeftMenuFragment;
import com.sfsj.asus.myapp.global.GlobalConstants;
import com.sfsj.asus.myapp.utils.CacheUtils;

import java.util.ArrayList;

/**
 * Created by ${zhangxiaocong} on 2017/6/11.
 */
public class NewsCenterPager extends BasePager {

    private NewsMenu mNewsData;
    private ArrayList<BaseMenuDetailPager> mMenuDetailPagers;
    public ImageButton btnPhoto;//组图切换按钮
    public NewsCenterPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        TextView textView=new TextView(mActivity);
        textView.setText("新闻中心");
        textView.setTextColor(Color.RED);
        textView.setTextSize(22);
        textView.setGravity(Gravity.CENTER);
        flContent.addView(textView);
        tvTitle.setText("新闻");
        btnMenu.setVisibility(View.VISIBLE);
        //判断缓存
        String cache= CacheUtils.getCache(GlobalConstants.CATEGORY_URL,mActivity);
        if(!TextUtils.isEmpty(cache)){
            //非空的话加载数据
            processData(cache);
        }
       getDataFromServer();
    }

    private void getDataFromServer() {//可以进一步封装
        HttpUtils utils=new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, GlobalConstants.CATEGORY_URL, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                String result= (String) responseInfo.result;
                processData(result);
                Log.d("当前获取到的数据",result.toString());
                CacheUtils.setCache(GlobalConstants.CATEGORY_URL,result,mActivity);
            }

            @Override
            public void onFailure(HttpException e, String s) {
               // error.printStackTrace();
                Toast.makeText(mActivity, s, Toast.LENGTH_SHORT)
                        .show();
                Log.d("当前获取到的数据","获取失败"+s);
            }
        });


    }

    private void processData(String json) {
        Gson gson=new Gson();
        mNewsData = gson.fromJson(json, NewsMenu.class);
        System.out.println("解析结果:" + mNewsData);

        MainActivity mainActivity= (MainActivity) mActivity;
        LeftMenuFragment leftMenuFragment=mainActivity.getLeftMenuFragment();
        leftMenuFragment.setMenuData(mNewsData.data);
        //初始化四个详情页
        mMenuDetailPagers = new ArrayList();
        mMenuDetailPagers.add(new NewsMenuDetailPager(mainActivity,mNewsData.data.get(0).children));
        mMenuDetailPagers.add(new TopicMenuDetailPager(mainActivity));
        mMenuDetailPagers.add(new PhotosMenuDetailPager(mainActivity,btnPhoto));
        mMenuDetailPagers.add(new InteractMenuDetailPager(mainActivity));
        setCurrentDetailPager(0);
    }
public void setCurrentDetailPager(int position){
    //添加页面
    BaseMenuDetailPager pager=mMenuDetailPagers.get(position);
    View view=pager.mRootrView;
//清楚以前的布局
    flContent.removeAllViews();
    flContent.addView(view);
    pager.initData();//c初始化
    tvTitle.setText(mNewsData.data.get(position).title);
    //如果是组图
    if(pager instanceof PhotosMenuDetailPager){
        btnPhoto.setVisibility(View.VISIBLE);
    }else {
        btnPhoto.setVisibility(View.GONE);
    }
}

}
