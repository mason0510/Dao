package com.sfsj.asus.myapp.base.impl.menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sfsj.asus.myapp.NewsDetailActivity;
import com.sfsj.asus.myapp.R;
import com.sfsj.asus.myapp.adapter.NewsAdapter;
import com.sfsj.asus.myapp.adapter.TopNewsAdapter;
import com.sfsj.asus.myapp.base.BaseMenuDetailPager;
import com.sfsj.asus.myapp.bean.NewsMenuData;
import com.sfsj.asus.myapp.bean.NewsTabBean;
import com.sfsj.asus.myapp.bean.NewsTabData;
import com.sfsj.asus.myapp.global.GlobalConstants;
import com.sfsj.asus.myapp.utils.CacheUtils;
import com.sfsj.asus.myapp.utils.PrefUtils;
import com.sfsj.asus.myapp.view.PullToRefreshListView;
import com.sfsj.asus.myapp.view.TopNewsViewPager;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

/**
 * Created by ${zhangxiaocong} on 2017/6/11.
 */
public class TabDetailPager extends BaseMenuDetailPager{
    @ViewInject(R.id.vp_top_news)
    private TopNewsViewPager mViewPager;
    @ViewInject(R.id.indicator)
    private CirclePageIndicator mIndicator;

    @ViewInject(R.id.tv_title)
    private TextView tvTitle;

    @ViewInject(R.id.lv_list)
    private PullToRefreshListView lvList;
    private final NewsTabData mTabData;
    private final String mUrl;
    private NewsAdapter mNewsAdapter;

    public TabDetailPager(Activity activity, NewsTabData data) {
        super(activity);
        mTabData = data;
        mUrl = GlobalConstants.SERVER_URL+mTabData.url;
    }

    @Override
    public View initView() {
        View view=View.inflate(mActivity, R.layout.pager_tab_detail,null);
        ViewUtils.inject(this,view);
        //给listview添加头部
        // 给listview添加头布局
        final View mHeaderView = View.inflate(mActivity, R.layout.list_item_header,
                null);
        ViewUtils.inject(this, mHeaderView);// 此处必须将头布局也注入
        lvList.addHeaderView(mHeaderView);
        lvList.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataFromServer();
            }

            @Override
            public void onLoadMore() {
                //判断是否有下一页
                if(mMoreUrl!=null){
                    getMoreDataFromServer();
                }else {
                    //没有数据了
                    lvList.onRefreshComplete(true);
                }
            }
        });
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int headerViewCount=lvList.getHeaderViewsCount();
                position=position-headerViewCount;
                NewsTabBean.NewsData news=mNewsList.get(position);
                //读取
                String resdIds= PrefUtils.getString(mActivity,"read_ids","");
                if(!resdIds.contains(news.id+"")){//不包含当前id 追加
                    resdIds=resdIds+news.id+"";
                    PrefUtils.setString(mActivity,"read_ids",resdIds);

                }
                TextView tvTitle= (TextView) view.findViewById(R.id.tv_title);
                tvTitle.setTextColor(Color.GRAY);
                //也可以全局出刷新
                //.notifyDataSetChanged();//全局刷新, 浪费性能
                Intent intent=new Intent(mActivity,NewsDetailActivity.class);
                intent.putExtra("url",news.url);
                mActivity.startActivity(intent);
            }
        });
        return view;
    }



    private void getMoreDataFromServer() {
    }

    private void getDataFromServer() {
        HttpUtils utils=new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, mUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result=responseInfo.result;
                processData(result,false);
                CacheUtils.setCache(mUrl,result,mActivity);
                lvList.onRefreshComplete(true);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(mActivity, s, Toast.LENGTH_SHORT).show();
                lvList.onRefreshComplete(false);
            }
        });

    }
    private String mMoreUrl;// 下一页数据链接
    private ArrayList<NewsTabBean.TopNews> mTopNews;
    private Handler mHandler;
    private ArrayList<NewsTabBean.NewsData> mNewsList;
    private void processData(String result, boolean isMore) {
        Gson gson=new Gson();
        NewsTabBean newsTabBean=gson.fromJson(result,NewsTabBean.class);
        String moreUrl=newsTabBean.data.more;
        if(!TextUtils.isEmpty(moreUrl)){
            mMoreUrl=GlobalConstants.SERVER_URL+moreUrl;
        }else{
            mMoreUrl=null;
        }
        if(!isMore){
            mTopNews=newsTabBean.data.topnews;
            if(mTopNews!=null){
                mViewPager.setAdapter(new TopNewsAdapter(mTopNews,mActivity));
                mIndicator.setViewPager(mViewPager);
                mIndicator.setSnap(true);
                mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        NewsTabBean.TopNews topNews=mTopNews.get(position);
                      //  NewsTabBean.TopNews topNews=mTopNews.get(position);
                            tvTitle.setText(topNews.title);
                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                tvTitle.setText(mTopNews.get(0).title);
                mIndicator.onPageSelected(0);//默认选中第一个
            }
            mNewsList=newsTabBean.data.news;
            if(mNewsList!=null){
                mNewsAdapter = new NewsAdapter(mNewsList,mActivity);
                lvList.setAdapter(mNewsAdapter);

            }
            if(mHandler!=null){
                mHandler=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        int currentItem=mViewPager.getCurrentItem();
                        currentItem++;//自增
                        if(currentItem>mTopNews.size()-1){
                            currentItem=0;//到最后一页 跳到下一页
                        }
                        mViewPager.setCurrentItem(currentItem);
                        mHandler.sendEmptyMessageDelayed(0,3000);



                        super.handleMessage(msg);
                    }
                };
                mHandler.sendEmptyMessageDelayed(0,3000);
                mViewPager.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN:
                                mHandler.removeCallbacksAndMessages(null);
                        break;
                            case MotionEvent.ACTION_CANCEL:
                                mHandler.sendEmptyMessageDelayed(0,3000);
                        break;
                            case MotionEvent.ACTION_UP:
                                mHandler.sendEmptyMessageDelayed(0,3000);
                                break;
                          default:
                              break;
                        }

                        return false;
                    }
                });
            }
        }else {
            ArrayList<NewsTabBean.NewsData> moreNews=newsTabBean.data.news;
            mNewsList.addAll(moreNews);
            mNewsAdapter.notifyDataSetChanged();
        }
    }

}
