package com.example.administrator.myfondapp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.myfondapp.R;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018\5\8 0008.
 */

public class DailyFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    private View view;
    @BindView(R.id.rv_daily)
    RecyclerView rv_daily;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private PullToRefreshAdapter adapter;
    private static final int TOTAL_COUNTER = 1000;
    private static final int PAGE_SIZE = 6;//用于判断起始数据的条目为填充屏幕是的情形
    private int mCurrentCounter = 0;
    private boolean mLoadMoreEndGone = false;
    private boolean isErr = false;
    private List<String> mDatas = new ArrayList<String>();
    private int n = 100;

    public DailyFragment() {
    }

    public static DailyFragment newInstance() {
        return new DailyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        initDate();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv_daily.setLayoutManager(linearLayoutManager);

        swipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        swipeRefreshLayout.setOnRefreshListener(this);//下拉刷新监听
        adapter = new PullToRefreshAdapter();
        adapter.setOnLoadMoreListener(this, rv_daily);//加载更多添加监听
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        adapter.isFirstOnly(false);
//        adapter.setPreLoadNumber(3);//预加载
        rv_daily.setAdapter(adapter);
        mCurrentCounter = adapter.getData().size();

        initListener();
    }

    /*
    * recycleview的点击事件
    * */
    private void initListener() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
               if (view.getId() == R.id.iv_test){
                   Toast.makeText(getActivity(), "图片点击事件"+position , Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

    /***
     * 刷新数据
     */
    @Override
    public void onRefresh() {
        adapter.setEnableLoadMore(false);//禁止加载
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                n = 100;
                mDatas.clear();
                initDate();
                adapter.setNewData(mDatas);
                isErr = false;
                mCurrentCounter = PAGE_SIZE;
                swipeRefreshLayout.setRefreshing(false);
                adapter.setEnableLoadMore(true);//启用加载
            }
        }, 2000);
    }

    /***
     * 加载更多数据
     */
    @Override
    public void onLoadMoreRequested() {
        swipeRefreshLayout.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapter.getData().size() < PAGE_SIZE) {
                    adapter.loadMoreEnd(true);
                } else {
                    if (mCurrentCounter >= TOTAL_COUNTER) {
                        adapter.loadMoreEnd(mLoadMoreEndGone);//true is gone,false is visible
                    } else {
                        if (!isErr) {
                            ++n;
                            initDate();
                            adapter.setNewData(mDatas);
                            mCurrentCounter = adapter.getData().size();
                            adapter.loadMoreComplete();
                        } else {
                            isErr = false;
                            Toast.makeText(getActivity(), "错误", Toast.LENGTH_LONG).show();
                            adapter.loadMoreFail();
                        }
                    }
                    swipeRefreshLayout.setEnabled(true);
                }
            }
        }, 5000);
    }

    /*
    * 模拟数据加载
    * */
    private void initDate() {
        for (int i = 0; i < n; i++) {
            mDatas.add("" + i);
        }
    }

    /***
     * 此处的BaseQuickAdapter中的泛型String 可以为所需要的实体类
     */
    class PullToRefreshAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public PullToRefreshAdapter() {
            super(R.layout.item_recycle_daily, mDatas);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv1, item)
                    .setImageResource(R.id.iv_test, R.drawable.ic_launcher_background)
            .addOnClickListener(R.id.iv_test);
        }
    }
}
