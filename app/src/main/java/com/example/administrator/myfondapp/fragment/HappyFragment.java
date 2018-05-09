package com.example.administrator.myfondapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myfondapp.R;

/**
 * Created by Administrator on 2018\5\8 0008.
 */

public class HappyFragment extends Fragment {
    private View view;
    public HappyFragment() {
    }

    public static HappyFragment newInstance(){
        return new HappyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine,container,false);
        return view;
    }
}
