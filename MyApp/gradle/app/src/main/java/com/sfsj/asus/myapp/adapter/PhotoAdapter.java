package com.sfsj.asus.myapp.adapter;

import android.app.Activity;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.sfsj.asus.myapp.R;
import com.sfsj.asus.myapp.base.BasePager;
import com.sfsj.asus.myapp.base.impl.menu.PhotosBean;

import java.util.ArrayList;

/**
 * Created by ${zhangxiaocong} on 2017/6/12.
 */
public class PhotoAdapter extends BaseAdapter {
    private BitmapUtils mBitmapUtils;
    ArrayList<PhotosBean.PhotoNews> mNewsList;
    Activity activity;
    public PhotoAdapter(Activity activity,ArrayList<PhotosBean.PhotoNews> mNewsList) {
      this.mNewsList=mNewsList;
        this.activity=activity;
        mBitmapUtils
                .configDefaultLoadingImage(R.drawable.pic_item_list_default);

    }

    @Override
    public int getCount() {
        return mNewsList.size();
    }

    @Override
    public PhotosBean.PhotoNews getItem(int position) {
        return mNewsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            View.inflate(activity,R.layout.list_item_phtos,null);
            holder = new ViewHolder();
            holder.ivPic = (ImageView) convertView
                    .findViewById(R.id.iv_pic);
            holder.tvTitle = (TextView) convertView
                    .findViewById(R.id.tv_title);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        PhotosBean.PhotoNews item = getItem(position);
        holder.tvTitle.setText(item.title);
        mBitmapUtils.display(holder.ivPic,item.listimage);
        return convertView;
    }

    static class ViewHolder {
        public ImageView ivPic;
        public TextView tvTitle;
    }
}
