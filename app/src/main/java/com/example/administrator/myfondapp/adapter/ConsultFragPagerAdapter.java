package com.example.administrator.myfondapp.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.myfondapp.fragment.DailyFragment;
import com.example.administrator.myfondapp.fragment.HappyFragment;

/**
 * Created by Administrator on 2018\5\8 0008.
 */

public class ConsultFragPagerAdapter extends FragmentPagerAdapter {
    private final int pageCount = 2;
    private String [] titles;
    private DailyFragment dailyFragment;
    private HappyFragment happyFragment;

    public ConsultFragPagerAdapter(FragmentManager fm,
                                   Context mContext,
                                   DailyFragment dailyFragment,
                                   HappyFragment happyFragment) {
        super(fm);
        titles = new String[]{"日常新闻","娱乐头条"};
        this.dailyFragment = dailyFragment;
        this.happyFragment = happyFragment;
    }

    @Override
    public Fragment getItem(int position) {
       if (position == 0){
           return dailyFragment;
       }else {
           return happyFragment;
       }
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
