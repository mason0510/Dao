package com.sfsj.asus.myapp.base.impl.menu;

import java.util.ArrayList;

/**
 * Created by ${zhangxiaocong} on 2017/6/12.
 */
public class PhotosBean {
    public PhotosData data;

    public class PhotosData {
        public ArrayList<PhotoNews> news;
    }

    public class PhotoNews {
        public int id;
        public String listimage;
        public String title;
    }
}
