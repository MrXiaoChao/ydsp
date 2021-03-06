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
 * Created by luochao on 2017/9/12.
 * 忘记密码
 */

public class ForgetPasswordActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected int getLayout() {
        return R.layout.activity_forgetpassword;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("找回密码");
    }



    @OnClick({R.id.iv_toolbar_back, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.btn_login:
                Intent intent =new Intent(ForgetPasswordActivity.this,ForgetPasswordTwoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
