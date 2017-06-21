package com.sfsj.asus.myapp.global;

public class GlobalConstants {

	// public static final String SERVER_URL =
	// "http://zhihuibj.sinaapp.com/zhbj";//服务器线上前缀地址
	/*
	换成自己的
	 */
	public static final String SERVER_URL1 = "http://127.0.0.1:8090/zhbj03";// 服务器主域名
	public static final String SERVER_URL = "http://192.168.1.8:8080/zhbj03";// 服务器主域
	public static final String CATEGORY_URL = SERVER_URL + "/categories.json";// 分类信息接口
	public static final String PHOTOS_URL = SERVER_URL
			+ "/photos/photos_1.json";// 组图信息接口

}
