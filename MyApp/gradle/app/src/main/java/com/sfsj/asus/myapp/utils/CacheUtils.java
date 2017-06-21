package com.sfsj.asus.myapp.utils;

import android.content.Context;

/**
 * Created by ${zhangxiaocong} on 2017/6/11.
 */
public class CacheUtils {
    //缓存工具类 接受三个参数
    public static void setCache(String url, String json, Context context){
        //保存文件用sp保存
        PrefUtils.setString(context,url,json);
    }
    public static String getCache(String url,Context context){
        //获取缓存 查找一个叫做url 的文件 有的话返回数据
        return PrefUtils.getString(context,url,null);
    }
}
