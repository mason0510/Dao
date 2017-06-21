package com.sfsj.asus.myapp.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sfsj.asus.myapp.R;
import com.sfsj.asus.myapp.bean.NewsMenu;
import com.sfsj.asus.myapp.bean.NewsMenuData;

import java.util.ArrayList;

/**
 * Created by ${zhangxiaocong} on 2017/6/11.
 */
public class LeftMenuAdapter extends BaseAdapter{
    private ArrayList<NewsMenuData> datas;
    private Activity mactivity;
    private int mCurrentPos;
    //适配器
    public LeftMenuAdapter(ArrayList<NewsMenuData> mNewsMenuData, Activity activity) {
    datas=mNewsMenuData;
     mactivity=activity;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View view=View.inflate(mactivity, R.layout.list_item_left_menu,null);
        TextView textView= (TextView) view.findViewById(R.id.tv_menu);
        NewsMenuData item= (NewsMenuData) getItem(position);
        textView.setText(item.title);//设置数据
        //选中则切换
        if(position==mCurrentPos){
            textView.setEnabled(true);
        }else {
            textView.setEnabled(false);
        }
        return view;
    }
}
