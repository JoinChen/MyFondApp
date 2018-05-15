package com.example.administrator.myfondapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.administrator.myfondapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018\5\14 0014.
 * 这里只是做了一个简单的Arouter的使用
 * 具体内容可以查看http://blog.csdn.net/zhaoyanjun6/article/details/76165252
 */
@Route(path = "/com/ArouterActivity")
public class ArouterActivity extends AppCompatActivity {
@BindView(R.id.tv_toJoup)
    TextView tv_toJsoup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arouter);
        ButterKnife.bind(this);
    }

    @OnClick ({R.id.tv_toJoup})
    public void myClick(View view){
        switch (view.getId()){
            case R.id.tv_toJoup:
                ARouter.getInstance().build("/com/JsoupActivity").navigation();
                break;
        }
    }
}
