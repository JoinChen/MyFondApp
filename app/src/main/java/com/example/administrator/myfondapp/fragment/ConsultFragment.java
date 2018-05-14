package com.example.administrator.myfondapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myfondapp.R;
import com.example.administrator.myfondapp.adapter.ConsultFragPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018\5\7 0007.
 */

public class ConsultFragment extends Fragment {
    private View view;
    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.fab_consult)
    FloatingActionButton fab_consult;
    private DailyFragment dailyFragment;
    private HappyFragment happyFragment;

    public ConsultFragment() {
    }

    public static ConsultFragment newInstance(){
        return new ConsultFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_consult,container,false);
        ButterKnife.bind(this,view);
        initEvents();
        /*floatingActionButton的点击事件*/
        fab_consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dailyFragment.showCanladarDialog();
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fragmentManager = getFragmentManager();
        if (savedInstanceState != null){
            dailyFragment = (DailyFragment) fragmentManager.getFragment(savedInstanceState,DailyFragment.class.getSimpleName());
            happyFragment = (HappyFragment) fragmentManager.getFragment(savedInstanceState,HappyFragment.class.getSimpleName());
        }else {
            dailyFragment = DailyFragment.newInstance();
            happyFragment = HappyFragment.newInstance();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        FragmentManager fragmentManager = getFragmentManager();
        if (dailyFragment.isAdded()){
            fragmentManager.putFragment(outState,DailyFragment.class.getSimpleName(),dailyFragment);
        }

        if (happyFragment.isAdded()){
            fragmentManager.putFragment(outState,HappyFragment.class.getSimpleName(),happyFragment);
        }
    }

    private void initEvents(){
        viewPager.setAdapter(new ConsultFragPagerAdapter(getChildFragmentManager(),
                getActivity(),
                dailyFragment,
                happyFragment));
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
        //设置tablayout切换时floatingActionButton的显示与隐藏
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    fab_consult.show();
                }else {
                    fab_consult.hide();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
