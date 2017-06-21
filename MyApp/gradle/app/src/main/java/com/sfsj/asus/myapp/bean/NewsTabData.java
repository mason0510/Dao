package com.sfsj.asus.myapp.bean;

/**
 * Created by ${zhangxiaocong} on 2017/6/11.
 */

public class NewsTabData {
    public int id;
    public String title;
    public int type;

    @Override
    public String toString() {
        return "NewsTabData{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", url='" + url + '\'' +
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String url;
}
