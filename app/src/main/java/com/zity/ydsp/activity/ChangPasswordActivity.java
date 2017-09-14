package com.zity.ydsp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zity.ydsp.R;
import com.zity.ydsp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/9/14.
 * 修改密码
 */

public class ChangPasswordActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;

    @Override
    protected int getLayout() {
        return R.layout.activity_changpassword;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("修改密码");
    }


}
