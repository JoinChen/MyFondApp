package com.example.administrator.myfondapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import com.example.administrator.myfondapp.MainActivity;
import com.example.administrator.myfondapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018\5\7 0007.
 */

public class MineFragment extends Fragment {
    @BindView(R.id.appbar_me)
    AppBarLayout appBarLayout_me;
    @BindView(R.id.toolbar_me)
    Toolbar toolbar_me;
    @BindView(R.id.nested_scroll_view)
    NestedScrollView nestedScrollView_me;
    @BindView(R.id.iv_me)
    ImageView iv_me;
    @BindView(R.id.webview_me)
    WebView webView_me;
    private View view;

    public MineFragment() {
    }


    public static MineFragment newInstance(){
        return new MineFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me,container,false);
        ButterKnife.bind(this,view);
        MainActivity activity = (MainActivity) getActivity();
        activity.setSupportActionBar(toolbar_me);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
