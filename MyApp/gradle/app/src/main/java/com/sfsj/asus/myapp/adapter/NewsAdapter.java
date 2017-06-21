package com.sfsj.asus.myapp.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.sfsj.asus.myapp.R;
import com.sfsj.asus.myapp.base.BasePager;
import com.sfsj.asus.myapp.bean.NewsTabBean;
import com.sfsj.asus.myapp.utils.PrefUtils;

import java.util.ArrayList;

/**
 * Created by ${zhangxiaocong} on 2017/6/11.
 */
public class NewsAdapter extends BaseAdapter{

    private final ArrayList<NewsTabBean.NewsData> mNewsList;
    private final Activity mActivity;
    private BitmapUtils mBitmapUtils;
    public NewsAdapter(ArrayList<NewsTabBean.NewsData> list, Activity activity) {
        mNewsList = list;
        mActivity = activity;
    }

    @Override
    public int getCount() {
        return mNewsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNewsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder;
        if(convertView!=null){
            convertView=View.inflate(mActivity, R.layout.list_item_news,null);

            holder = new ViewHolder();
            holder.ivIcon = (ImageView) convertView
                    .findViewById(R.id.iv_icon);
            holder.tvTitle = (TextView) convertView
                    .findViewById(R.id.tv_title);
            holder.tvDate = (TextView) convertView
                    .findViewById(R.id.tv_date);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        NewsTabBean.NewsData news= (NewsTabBean.NewsData) getItem(position);
        holder.tvTitle.setText(news.title);
        holder.tvDate.setText(news.pubdate);
//记录 本剧本地记录标记
        String readIds= PrefUtils.getString(mActivity,"read_ids",null);
        if(readIds.contains(news.id+"")){
            holder.tvTitle.setTextColor(Color.GRAY);
        }else {
            holder.tvTitle.setTextColor(Color.BLACK);
        }
        mBitmapUtils.display(holder.ivIcon,news.listimage);
        return convertView;
    }

    private class ViewHolder {
        public ImageView ivIcon;
        public TextView tvTitle;
        public TextView tvDate;
    }
}
