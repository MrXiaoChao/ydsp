package com.zity.ydsp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.EmptyUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.zity.ydsp.R;
import com.zity.ydsp.activity.LoginActivity;
import com.zity.ydsp.activity.MyAppealActivity;
import com.zity.ydsp.activity.MyCaseActivity;
import com.zity.ydsp.activity.MySuggestActivity;
import com.zity.ydsp.activity.PersonalInfoActivity;
import com.zity.ydsp.base.BaseFragment;
import com.zity.ydsp.utils.SPUtils;
import com.zity.ydsp.widegt.MyDialog;

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
    @BindView(R.id.rl_mycase)
    RelativeLayout rlMycase;
    Unbinder unbinder;
    @BindView(R.id.rl_mysugess)
    RelativeLayout rlMysugess;
    @BindView(R.id.rl_relogin)
    RelativeLayout rlRelogin;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.iv_touxiang)
    ImageView ivTouxiang;
    @BindView(R.id.rl_myappeal)
    RelativeLayout rlMyappeal;
    private String name;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initData() {
        name = (String) SPUtils.get(getActivity(), "name", "");
        String username = (String) SPUtils.get(getActivity(), "person_flag", "");

        //判断是否登录了``没有登录就去登录
        if (EmptyUtils.isNotEmpty(name)) {
            tvName.setText(name);
        } else {
            ivTouxiang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showExitDialog("请您去登录");
                }
            });
        }

        if (EmptyUtils.isNotEmpty(username)) {
            if (StringUtils.equals("0", username)) {
                tvUsername.setText("个人用户");
            } else {
                tvUsername.setText("法人用户");
            }
        } else {
            tvUsername.setVisibility(View.INVISIBLE);
        }

        ivToolbarBack.setVisibility(View.GONE);
        tvTooltarTitle.setText("个人中心");

    }


    @OnClick({R.id.iv_toolbar_back, R.id.rl_personalinfo, R.id.rl_mycase, R.id.rl_mysugess, R.id.rl_relogin,R.id.rl_myappeal})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.rl_personalinfo: //个人信息
                if (EmptyUtils.isNotEmpty(name)) {
                    Intent intent_personalinfo = new Intent(getActivity(), PersonalInfoActivity.class);
                    startActivity(intent_personalinfo);
                }else {
                    showExitDialog("请您去登录");
                }

                break;
            case R.id.rl_mycase://我的办件
                if (EmptyUtils.isNotEmpty(name)) {
                    Intent intent_mycase = new Intent(mContext, MyCaseActivity.class);
                    startActivity(intent_mycase);
                }else {
                    showExitDialog("请您去登录");
                }

                break;

            case R.id.rl_mysugess://建议意见
                if (EmptyUtils.isNotEmpty(name)) {
                    Intent intent_mysugess = new Intent(mContext, MySuggestActivity.class);
                    startActivity(intent_mysugess);
                }else {
                    showExitDialog("请您去登录");
                }
                break;
            case R.id.rl_relogin://注销登录
                if (EmptyUtils.isNotEmpty(name)) {
                    showExitDialog1("注销登录");
                }else {
                    showExitDialog("请您去登录");
                }
                break;
            case R.id.rl_myappeal:
                if (EmptyUtils.isNotEmpty(name)) {
                    Intent intent =new Intent(getActivity(), MyAppealActivity.class);
                    startActivity(intent);
                }else {
                    showExitDialog("请您去登录");
                }

                break;
        }
    }

    private void showExitDialog(String msg) {
        final MyDialog dialog = new MyDialog(getActivity());
        dialog.setTitle("提示");
        dialog.setMessage(msg);
        dialog.setYesOnclickListener("确定", new MyDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                Intent intent_login = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent_login);
                dialog.dismiss();
                getActivity().finish();
            }
        });
        dialog.setNoOnclickListener("取消", new MyDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private void showExitDialog1(String msg) {
        final MyDialog dialog = new MyDialog(getActivity());
        dialog.setTitle("提示");
        dialog.setMessage(msg);
        dialog.setYesOnclickListener("确定", new MyDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                if (SPUtils.contains(getActivity(), "username")) {
                    SPUtils.remove(getActivity(), "username");
                }
                if (SPUtils.contains(getActivity(), "password")) {
                    SPUtils.remove(getActivity(), "password");
                }
                if (SPUtils.contains(getActivity(), "phone")) {
                    SPUtils.remove(getActivity(), "phone");
                }
                if (SPUtils.contains(getActivity(), "name")) {
                    SPUtils.remove(getActivity(), "name");
                }
                if (SPUtils.contains(getActivity(), "person_flag")) {
                    SPUtils.remove(getActivity(), "person_flag");
                }
                Intent intent_login = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent_login);
                dialog.dismiss();
                getActivity().finish();
            }
        });
        dialog.setNoOnclickListener("取消", new MyDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
