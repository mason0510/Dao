package com.sfsj.asus.myapp.bean;

import java.util.ArrayList;

/**
 * Created by ${zhangxiaocong} on 2017/6/11.
 */
public class NewsMenu {
    public int retcode;
    public ArrayList<Integer> extend;
    public ArrayList<NewsMenuData> data;//所需数据

    @Override
    public String toString() {
        return "NewsMenu{" +
                "retcode=" + retcode +
                ", extend=" + extend +
                ", data=" + data +
                '}';
    }

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public ArrayList<NewsMenuData> getData() {
        return data;
    }

    public void setData(ArrayList<NewsMenuData> data) {
        this.data = data;
    }

    public ArrayList<Integer> getExtend() {
        return extend;
    }

    public void setExtend(ArrayList<Integer> extend) {
        this.extend = extend;
    }
}
