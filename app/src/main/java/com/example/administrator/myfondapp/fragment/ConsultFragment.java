package com.example.administrator.myfondapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myfondapp.R;
import com.example.administrator.myfondapp.adapter.ConsultFragPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    private DailyFragment dailyFragment;
    private HappyFragment happyFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_consult,container,false);
        ButterKnife.bind(this,view);
        initEvents();
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
    }
}
