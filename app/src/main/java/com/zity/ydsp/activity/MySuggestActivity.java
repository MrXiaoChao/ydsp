package com.zity.ydsp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.blankj.utilcode.utils.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.FieldAttributes;
import com.google.gson.reflect.TypeToken;
import com.zity.ydsp.R;
import com.zity.ydsp.adapter.CaseAdapter;
import com.zity.ydsp.adapter.MySuggestAdapter;
import com.zity.ydsp.app.App;
import com.zity.ydsp.base.BaseActivity;
import com.zity.ydsp.bean.MySuggest;
import com.zity.ydsp.bean.Progress;
import com.zity.ydsp.http.GsonRequest;
import com.zity.ydsp.http.UrlPath;
import com.zity.ydsp.utils.SPUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/9/14.
 * 意见建议
 */

public class MySuggestActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.tv_right_save)
    TextView tvRightSave;
    @BindView(R.id.rv_mysuggest)
    RecyclerView rvMysuggest;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    private String userid;
    private MySuggestAdapter adapter;

    private int page = 1;
    private boolean isLoadMore;//是否有上啦加载更多
    private List<MySuggest.ListBean> list = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_mysuggest;
    }

    @Override
    protected void initData() {
        //获取userid
        userid = (String) SPUtils.get(MySuggestActivity.this, "userid", "");
        tvTooltarTitle.setText("我的建议");
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        rvMysuggest.setLayoutManager(layoutManager);
        swipeLayout.setOnRefreshListener(this);
        getDataFromService(userid, page, "8");

    }


    @OnClick({R.id.iv_toolbar_back, R.id.tv_right_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.tv_right_save:
                Intent intent =new Intent(MySuggestActivity.this,MakeSuggestActivity.class);
                startActivity(intent);
                break;
        }
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        page = 1;
        isLoadMore = false;
        getDataFromService(userid, page, "8");
        //停止刷新
        swipeLayout.setRefreshing(false);
    }

    //上啦加载更多
    @Override
    public void onLoadMoreRequested() {
        isLoadMore = true;
//        rvMysuggest.setEnabled(false);
        adapter.setEnableLoadMore(true);
        page++;
        getDataFromService(userid, page, "8");
    }

    //从服务器获取数据
    private void getDataFromService(String apply_id, int page, String rows) {
        TypeToken type = new TypeToken<List<MySuggest>>() {
        };
        Map<String, String> map = new HashMap<>();
        map.put("apply_id", apply_id);
        map.put("page", String.valueOf(page));
        map.put("rows", rows);
        GsonRequest<List<MySuggest>> request = new GsonRequest<List<MySuggest>>(Request.Method.POST, map, UrlPath.MYSUGGEST, type, new Response.Listener<List<MySuggest>>() {
            @Override
            public void onResponse(List<MySuggest> response) {
                if (isLoadMore) {
                    if (response != null && response.get(0).getList().size() > 0) {
                        if (list != null && list.size() > 0) {
                            list.addAll(response.get(0).getList());
                            adapter = new MySuggestAdapter(R.layout.item_case, list);
                            rvMysuggest.setAdapter(adapter);
                            adapter.loadMoreComplete();
                            adapter.setOnLoadMoreListener(MySuggestActivity.this, rvMysuggest);
                        } else {
                            ToastUtils.showShortToast("网络出错啦");
                        }
                    } else {
                        adapter.loadMoreEnd();
                    }
                } else {
                    if (response != null && response.get(0).getList().size() > 0) {
                        list = response.get(0).getList();
                        adapter = new MySuggestAdapter(R.layout.item_case, response.get(0).getList());
                        rvMysuggest.setAdapter(adapter);
                        adapter.setOnLoadMoreListener(MySuggestActivity.this, rvMysuggest);
                    } else {
                        ToastUtils.showShortToast("该用户无数据");
                    }
                }

                //recyclerviview的点击事件
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent =new Intent(MySuggestActivity.this,SuggestXqActivity.class);
                        intent.putExtra("id",list.get(position).getJianyi_id());
                        startActivity(intent);
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }
}
