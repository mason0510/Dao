package com.sfsj.asus.myapp.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ${zhangxiaocong} on 2017/6/11.
 */
public abstract class BaseFragment extends Fragment {

    public Activity activity;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();//可以互相获取
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = initView();
        //return super.onCreateView(inflater, container, savedInstanceState);
    return view;
    }
//再出启动会启动的
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    //抽象类 抽象方法
    public abstract View initView() ;
    public abstract void initData();
}
