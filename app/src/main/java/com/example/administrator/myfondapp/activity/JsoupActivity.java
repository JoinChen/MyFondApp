package com.example.administrator.myfondapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.administrator.myfondapp.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.channels.UnresolvedAddressException;

/**
 * Created by Administrator on 2018\5\15 0015.
 * Jsoup抓取网页信息activity
 */
@Route(path = "/com/JsoupActivity")
public class JsoupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);
        //一定要在线程中操作网址的连接请求否则会出现android.os.NetworkOnMainThreadException异常
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect("http://home.meishichina.com/show-top-type-recipe.html").timeout(30000)
                            .userAgent("Mozilla")
                            .validateTLSCertificates(false)
                            .get();
                    Elements element = doc.select("div.top-bar");
                    String str = element.select("a").attr("title");
                    Log.e("Jsoup's print：", str);
                    //“椒麻鸡”和它对应的图片都在<div class="pic">中
                    Elements titleAndPic = doc.select("div.pic");
                    // 使用Element.select(String selector)查找元素，使用Node.attr(String key)方法取得一个属性的值
                    Log.i("mytag", "title:" + titleAndPic.get(1).select("a").attr("title") + "pic:" +
                            titleAndPic.get(1).select("a").select("img").attr("data-src"));

                    //所需链接在<div class="detail">中的<a>标签里面
                    Elements url = doc.select("div.detail").select("a");
                    Log.i("mytag", "url:" + url.get(1).attr("href"));

                    // 原料在<p class="subcontent">中
                    Elements burden = doc.select("p.subcontent");
                    //对于一个元素中的文本，可以使用Element.text()方法
                    Log.i("mytag", "burden:" + burden.get(1).text());

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Jsoup is error: ", e.toString());
                }
            }
        }).start();
    }
}
