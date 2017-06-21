package com.sfsj.asus.myapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ${zhangxiaocong} on 2017/6/11.
 */
public class PrefUtils {
    public  static boolean getBoolean(Context context,String key,Boolean defValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences("config",Context.MODE_APPEND);
        return sharedPreferences.getBoolean(key,defValue);
    }

    public  static boolean setBoolean(Context context,String key,Boolean defValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences("config",Context.MODE_APPEND);
        return sharedPreferences.edit().putBoolean(key,defValue).commit();
    }

    public  static String getString(Context context,String key,String defValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences("config",Context.MODE_APPEND);
        return sharedPreferences.getString(key,defValue);
    }

    public  static void setString(Context context, String key, String defValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences("config",Context.MODE_APPEND);
        sharedPreferences.edit().putString(key,defValue).apply();
    }
    public  static int getInt(Context context,String key,int defValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences("config",Context.MODE_APPEND);
        return sharedPreferences.getInt(key,defValue);
    }

    public  static void setInt(Context context,String key,int defValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences("config",Context.MODE_APPEND);
         sharedPreferences.edit().putInt(key,defValue).apply();
    }
}
