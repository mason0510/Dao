package com.sfsj.asus.myapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.sfsj.asus.myapp.fragment.ContentFragment;
import com.sfsj.asus.myapp.fragment.LeftMenuFragment;

public class MainActivity extends SlidingFragmentActivity {
    private SlidingMenu slidingMenu;
    private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
    private static final String TAG_CONTENT = "TAG_CONTENT";
    private FragmentManager fm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //设置左侧布局
        setBehindContentView(R.layout.left_menu);
        slidingMenu = getSlidingMenu();
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setBehindOffset(200);
        //初始化布局
        initFragment();
    }

    private void initFragment() {

        fm = getSupportFragmentManager();
        FragmentTransaction tr= fm.beginTransaction();
        tr.replace(R.id.fl_left_menu,new LeftMenuFragment(),TAG_LEFT_MENU);
        tr.replace(R.id.fl_main,new ContentFragment(),TAG_CONTENT);
        tr.commit();
        //找到对应的片段的方法
        //fm.findFragmentByTag();
    }
    public LeftMenuFragment getLeftMenuFragment(){
        LeftMenuFragment fragment= (LeftMenuFragment) fm.findFragmentByTag(TAG_LEFT_MENU);
        return fragment;
    }
    public ContentFragment getContentFragment(){
        ContentFragment fragment= (ContentFragment) fm.findFragmentByTag(TAG_CONTENT);
        return fragment;
    }
}
