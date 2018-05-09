package com.example.administrator.myfondapp.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.myfondapp.R;
import com.example.administrator.myfondapp.bean.ChatMultipleItem;

import java.util.List;

/**
 * Created by Administrator on 2018\5\8 0008.
 */

public class ChatQuickAdapter extends BaseQuickAdapter<ChatMultipleItem, BaseViewHolder> {
    private String imge1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517034699540&di=6a9afc9b394eafeec7dd4234dd42aca1&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fa044ad345982b2b713b5ad7d3aadcbef76099b65.jpg";

    public ChatQuickAdapter(int layoutResId, @Nullable List<ChatMultipleItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChatMultipleItem item) {
        helper.setText(R.id.tv, item.getContent());
        ImageView imageView = helper.getView(R.id.iv);
        Glide.with(mContext).load(imge1).into(imageView);
    }
}
