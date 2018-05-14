package com.example.administrator.myfondapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myfondapp.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018\5\7 0007.
 */

public class AbilityFragment extends Fragment {
    private View view;

    public AbilityFragment() {
    }

    public static AbilityFragment newInstance(){
        return new AbilityFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_abilityperson,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
}
