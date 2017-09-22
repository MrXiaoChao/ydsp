package com.zity.ydsp.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zity.ydsp.R;
import com.zity.ydsp.adapter.CaseAdapter;
import com.zity.ydsp.base.BaseFragment;
import com.zity.ydsp.bean.Progress;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by luochao on 2017/9/14.
 * 全部案件
 */

public class AllCaseFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.rv_allcase)
    RecyclerView rvAllcase;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    Unbinder unbinder;
    private CaseAdapter adapter;
    List<Progress.ListBean> list =new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_allcase;
    }

    @Override
    protected void initData() {
        Progress.ListBean listBean =new Progress.ListBean();
        listBean.setCreatedate("2019-09-21");
        listBean.setState(1);
        listBean.setTitle("结案是肯定阖家安康的");
        for (int i =0;i<5;i++){
            list.add(listBean);
        }
        swipeLayout.setOnRefreshListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvAllcase.setLayoutManager(layoutManager);

        adapter = new CaseAdapter(R.layout.item_case,list);
        rvAllcase.setAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        swipeLayout.setRefreshing(false);
    }
}
