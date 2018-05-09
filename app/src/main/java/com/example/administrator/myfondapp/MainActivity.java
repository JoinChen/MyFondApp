package com.example.administrator.myfondapp;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.administrator.myfondapp.fragment.AbilityFragment;
import com.example.administrator.myfondapp.fragment.ConsultFragment;
import com.example.administrator.myfondapp.fragment.MineFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{
    @BindView(R.id.bottom_nav)
    BottomNavigationBar bottom_nav;
    @BindView(R.id.fl_main)
    FrameLayout fl_main;
    private ArrayList<Fragment> fragmentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainui);
        ButterKnife.bind(this);
        initBottomNav();
    }

    @SuppressLint("ResourceAsColor")
    private void initBottomNav() {
        //没有水波效果
        bottom_nav.setMode(BottomNavigationBar.MODE_DEFAULT);
        bottom_nav.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        //设置选中的字体图片及其背景颜色
        bottom_nav.setActiveColor(R.color.colorAccent);
        bottom_nav.setInActiveColor(R.color.colorPrimaryDark);
        bottom_nav.setBarBackgroundColor(R.color.colorPrimary);

        bottom_nav
                .addItem(new BottomNavigationItem(R.mipmap.icon_zixun,"咨询"))
                .addItem(new BottomNavigationItem(R.mipmap.icon_rencai, "人才"))
                .addItem(new BottomNavigationItem(R.mipmap.icon_wode,"我的"))
                .setFirstSelectedPosition(0)//默认选中第一个bottom
                .initialise();//所有的设置需在调用该方法前完成;

        getFragmentArrayList();
        setDefaultFragment();
        bottom_nav.setTabSelectedListener(this);

    }

    /***
     * 底部按钮选择回调方法
     * @param position
     */
    @Override
    public void onTabSelected(int position) {

       if (fragmentArrayList != null){
           FragmentManager manager = getSupportFragmentManager();
           FragmentTransaction transaction = manager.beginTransaction();
           Fragment from = manager.findFragmentById(R.id.fl_main);
           Fragment fragment = fragmentArrayList.get(position);
           if (position < fragmentArrayList.size()){
               if (fragment.isAdded()){
                   transaction.hide(from).show(fragment);
               }else {
                   transaction.hide(from).add(R.id.fl_main, fragment);
                   if (fragment.isHidden()) {
                       transaction.show(fragment);
                   }
               }
               transaction.commitNowAllowingStateLoss();
           }
       }
    }

    @Override
    public void onTabUnselected(int position) {
        if (fragmentArrayList != null){
            if (position < fragmentArrayList.size()){
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = fragmentArrayList.get(position);
                transaction.hide(fragment);
                transaction.commitNowAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }

    /*
    * 添加fragment到集合中
    * */
    private ArrayList<Fragment> getFragmentArrayList(){
        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new ConsultFragment());
        fragmentArrayList.add(new AbilityFragment());
        fragmentArrayList.add(new MineFragment());
        return fragmentArrayList;
    }

    /*设置默认的fragment*/
    private void setDefaultFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fl_main,new ConsultFragment());
        transaction.commitNowAllowingStateLoss();
    }
}
