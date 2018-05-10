package com.example.administrator.myfondapp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.example.administrator.myfondapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018\5\10 0010.
 */

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.appbar_me)
    AppBarLayout appBarLayout_me;
    @BindView(R.id.toolbar_me)
    Toolbar toolbar_me;
    @BindView(R.id.nested_scroll_view)
    NestedScrollView nestedScrollView_me;
    @BindView(R.id.iv_me)
    ImageView iv_me;
    @BindView(R.id.collaps_toolbarlayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.webview_me)
    WebView webView_me;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_me);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar_me);
        //添加系统返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //添加返回按钮位置图标
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher_background);
        //添加返回键点击事件监听
        toolbar_me.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorAccent));//收缩时标题的颜色
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorAccent));//展开时标题的颜色
        //WebView加载web资源
        webView_me.loadUrl("https://blog.csdn.net/peixiaopao/article/details/75604979");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView_me.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }
}
