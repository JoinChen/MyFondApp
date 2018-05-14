package com.example.administrator.myfondapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.administrator.myfondapp.R;

/**
 * Created by Administrator on 2018\5\14 0014.
 * 这里只是做了一个简单的Arouter的使用
 * 具体内容可以查看http://blog.csdn.net/zhaoyanjun6/article/details/76165252
 */
@Route(path = "/com/ArouterActivity")
public class ArouterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arouter);
    }
}
