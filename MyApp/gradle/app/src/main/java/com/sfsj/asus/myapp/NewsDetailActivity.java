package com.sfsj.asus.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.lidroid.xutils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${zhangxiaocong} on 2017/6/11.
 */
public class NewsDetailActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.wv_news_detail)
    WebView mWvNewsDetail;
    @BindView(R.id.pb_loading)
    ProgressBar mPbLoading;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_news_datail);
        ButterKnife.bind(this);
      //  ViewUtils.inject(this);
    }

    @butterknife.OnClick({R.id.wv_news_detail, R.id.pb_loading})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wv_news_detail:
                break;
            case R.id.pb_loading:
                break;
        }
    }
}
