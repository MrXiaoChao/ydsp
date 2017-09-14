package com.zity.ydsp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zity.ydsp.R;
import com.zity.ydsp.activity.PersonalInfoActivity;
import com.zity.ydsp.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 个人中心
 * Created by luochao on 2017/9/18.
 */

public class PersonalFragment extends BaseFragment {


    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.rl_personalinfo)
    RelativeLayout rlPersonalinfo;
    Unbinder unbinder;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initData() {
        ivToolbarBack.setVisibility(View.GONE);
        tvTooltarTitle.setText("个人中心");

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

    @OnClick({R.id.iv_toolbar_back, R.id.rl_personalinfo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.rl_personalinfo:
                Intent intent_personalinfo =new Intent(getActivity(), PersonalInfoActivity.class);
                startActivity(intent_personalinfo);
                break;
        }
    }
}
