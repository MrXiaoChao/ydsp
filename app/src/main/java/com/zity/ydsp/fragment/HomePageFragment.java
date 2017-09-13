package com.zity.ydsp.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zity.ydsp.R;
import com.zity.ydsp.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 首页
 * Created by luochao on 2017/7/18.
 */

public class HomePageFragment extends BaseFragment {


    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initData() {
        ivToolbarBack.setVisibility(View.GONE);
        tvTooltarTitle.setText("移动审批系统");
    }

}
