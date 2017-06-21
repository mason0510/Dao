package com.sfsj.asus.myapp.bean;

import java.util.ArrayList;

/**
 * Created by ${zhangxiaocong} on 2017/6/11.
 */
public class NewsMenuData {
    public int id;
    public String title;
    public int type;
    public ArrayList<NewsTabData> children;

    @Override
    public String toString() {
        return "NewsMenuData{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", children=" + children +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<NewsTabData> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<NewsTabData> children) {
        this.children = children;
    }
}
