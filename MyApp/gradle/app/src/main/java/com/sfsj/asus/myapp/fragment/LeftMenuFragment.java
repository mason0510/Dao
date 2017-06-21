package com.sfsj.asus.myapp.fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.sfsj.asus.myapp.MainActivity;
import com.sfsj.asus.myapp.R;
import com.sfsj.asus.myapp.adapter.LeftMenuAdapter;
import com.sfsj.asus.myapp.base.impl.NewsCenterPager;
import com.sfsj.asus.myapp.bean.NewsMenuData;

import java.util.ArrayList;

/**
 * Created by ${zhangxiaocong} on 2017/6/11.
 */
//左侧划
public class LeftMenuFragment extends BaseFragment {
    private int mCurrentPos;
    ArrayList<NewsMenuData> menuDatas;
    private LeftMenuAdapter menuAdapter;
    private ListView lvList;
    @Override
    public View initView() {
        //左边片段
        View view=View.inflate(getActivity(), R.layout.fragment_left_menu,null);
        com.lidroid.xutils.ViewUtils.inject(this,view);
        return view;
    }

    @Override
    public void initData() {

    }
    public void setMenuData(ArrayList<NewsMenuData> data){
        //更新页面数据 并设置可以点击
       mCurrentPos=0;
        menuDatas=data;
        menuAdapter = new LeftMenuAdapter(menuDatas,activity);
        lvList.setAdapter(menuAdapter);
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentPos=position;
                menuAdapter.notifyDataSetChanged();
                toggle();
                setCurrentDetailPager(position);
            }


        });
    }
    private void setCurrentDetailPager(int position) {
        //获取点集中的对象并且刷新数据
        MainActivity mainUI= (MainActivity) activity;
        ContentFragment fragment=mainUI.getContentFragment();
        //新闻是第一个页面
        NewsCenterPager newsCenterPager=fragment.getNewsCenterPager();
        newsCenterPager.setCurrentDetailPager(position);
    }
    //打开关闭
    protected void toggle(){
        MainActivity mainActivity= (MainActivity) activity;
        SlidingMenu slidingMenu=mainActivity.getSlidingMenu();
        slidingMenu.toggle();//开就关 关就开
    }
}
