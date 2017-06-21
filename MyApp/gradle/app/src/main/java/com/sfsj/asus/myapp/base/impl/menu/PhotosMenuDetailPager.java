package com.sfsj.asus.myapp.base.impl.menu;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sfsj.asus.myapp.R;
import com.sfsj.asus.myapp.adapter.PhotoAdapter;
import com.sfsj.asus.myapp.base.BaseMenuDetailPager;
import com.sfsj.asus.myapp.global.GlobalConstants;
import com.sfsj.asus.myapp.utils.CacheUtils;

import java.util.ArrayList;

/**
 * Created by ${zhangxiaocong} on 2017/6/11.
 */
public class PhotosMenuDetailPager extends BaseMenuDetailPager implements View.OnClickListener{
    ImageButton btnPhoto;
    private boolean isListView = true;// 标记当前是否是listview展示
    @ViewInject(R.id.lv_photo)
    private ListView lvPhoto;
    @ViewInject(R.id.gv_photo)
    private GridView gvPhoto;
    private View view;
    private ArrayList<PhotosBean.PhotoNews> mNewsList;
    public PhotosMenuDetailPager(Activity activity, ImageButton btnPhoto) {
        super(activity);
        btnPhoto.setOnClickListener(this);// 组图切换按钮设置点击事件
        this.btnPhoto = btnPhoto;
    }

    @Override
    public void onClick(View v) {
        if(isListView){
            lvPhoto.setVisibility(View.GONE);
            gvPhoto.setVisibility(View.VISIBLE);
            btnPhoto.setImageResource(R.drawable.icon_pic_list_type);
            isListView=false;//改变
        }else {
            lvPhoto.setVisibility(View.VISIBLE);
            gvPhoto.setVisibility(View.GONE);
            btnPhoto.setImageResource(R.drawable.icon_pic_grid_type);
            isListView = true;
        }
    }

    @Override
    public View initView() {
        view = View.inflate(mActivity, R.layout.pager_photos_menu_detail,null);
        ViewUtils.inject(this,view);
        return view;
    }

    @Override
    public void initData() {
        String cache = CacheUtils.getCache(GlobalConstants.PHOTOS_URL,
                mActivity);
        if (!TextUtils.isEmpty(cache)) {
            processData(cache);
        }

        getDataFromServer();
    }

    private void getDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, GlobalConstants.PHOTOS_URL,
                new RequestCallBack<String>() {

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String result = responseInfo.result;
                        processData(result);

                        CacheUtils.setCache(GlobalConstants.PHOTOS_URL, result,
                                mActivity);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        // 请求失败
                        error.printStackTrace();
                        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }

    private void processData(String cache) {
        Gson gson = new Gson();
        PhotosBean photosBean = gson.fromJson(cache, PhotosBean.class);

        mNewsList = photosBean.data.news;

        lvPhoto.setAdapter(new PhotoAdapter(mActivity,mNewsList));
        gvPhoto.setAdapter(new PhotoAdapter(mActivity,mNewsList));// gridview的布局结构和listview完全一致,
        // 所以可以共用一个adapter
    }
}
