package com.example.administrator.myfondapp.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2018\5\8 0008.
 */

public class ChatMultipleItem implements MultiItemEntity {
    public static int CHATTYPETEXT=0;
    public static  int CHATTYPETEXTANDIMG=1;
    private int itemType;
    private String content;

    public ChatMultipleItem(int itemType, String content) {
        this.itemType = itemType;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
