package com.sfsj.asus.myapp.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.sfsj.asus.myapp.MainActivity;
import com.sfsj.asus.myapp.R;
import com.sfsj.asus.myapp.adapter.ContentAdapter;
import com.sfsj.asus.myapp.base.BasePager;
import com.sfsj.asus.myapp.base.impl.GovAffairsPager;
import com.sfsj.asus.myapp.base.impl.HomePager;
import com.sfsj.asus.myapp.base.impl.NewsCenterPager;
import com.sfsj.asus.myapp.base.impl.SettingPager;
import com.sfsj.asus.myapp.base.impl.SmartServicePager;
import com.sfsj.asus.myapp.view.NoScrollViewPager;

import java.util.ArrayList;

/**
 * Created by ${zhangxiaocong} on 2017/6/11.
 */
public class ContentFragment extends BaseFragment {
    NoScrollViewPager mViewPager;
    RadioGroup radioGroup;
   ArrayList<BasePager> mPagers;//    private ArrayList<BasePager> mPagers;// 五个标签页的集合
     View view;

    @Override
    public View initView() {
        view = View.inflate(activity, R.layout.fragment_content,null);
        mViewPager= (NoScrollViewPager) view.findViewById(R.id.vp_content);
        radioGroup= (RadioGroup) view.findViewById(R.id.rg_group);
        return view;
    }

    @Override
    public void initData() {
    mPagers=new ArrayList<>();
        mPagers.add(new HomePager(activity));
        mPagers.add(new NewsCenterPager(activity));
        mPagers.add(new SmartServicePager(activity));
        mPagers.add(new GovAffairsPager(activity));
        mPagers.add(new SettingPager(activity));
        mViewPager.setAdapter(new ContentAdapter(mPagers));
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        // 首页
                        // mViewPager.setCurrentItem(0);
                        mViewPager.setCurrentItem(0, false);// 参2:表示是否具有滑动动画
                        break;
                    case R.id.rb_news:
                        // 新闻中心
                        mViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb_smart:
                        // 智慧服务
                        mViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.rb_gov:
                        // 政务
                        mViewPager.setCurrentItem(3, false);
                        break;
                    case R.id.rb_setting:
                        // 设置
                        mViewPager.setCurrentItem(4, false);
                        break;

                    default:
                        break;
                }
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //被选择的。。。。。
                    BasePager pager=mPagers.get(position);
                    pager.initData();
                    if(position==0||position==mPagers.size()-1){
                        setSlidingMenuEnable(false);
                    }else {

                    }
                    setSlidingMenuEnable(true);
                }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mPagers.get(0).initData();
        setSlidingMenuEnable(false);
    }

    private void setSlidingMenuEnable(boolean b) {
        MainActivity mainUI= (MainActivity) activity;
        SlidingMenu slidingMenu=mainUI.getSlidingMenu();
        if(b){
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }


    public NewsCenterPager getNewsCenterPager(){
        NewsCenterPager pager= (NewsCenterPager) mPagers.get(1);
        return pager;
    }
}
