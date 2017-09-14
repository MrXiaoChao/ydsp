package com.zity.ydsp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zity.ydsp.R;
import com.zity.ydsp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/9/14.
 * 个人信息
 */

public class PersonalInfoActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.rl_changsex)
    RelativeLayout rlChangsex;
    @BindView(R.id.rl_changemail)
    RelativeLayout rlChangemail;
    @BindView(R.id.rl_changaddress)
    RelativeLayout rlChangaddress;
    @BindView(R.id.rl_changphone)
    RelativeLayout rlChangphone;
    @BindView(R.id.rl_changpassword)
    RelativeLayout rlChangpassword;

    @Override
    protected int getLayout() {
        return R.layout.activity_personalinfo;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("个人信息");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_toolbar_back, R.id.rl_changsex, R.id.rl_changemail, R.id.rl_changaddress, R.id.rl_changphone, R.id.rl_changpassword})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                break;
            case R.id.rl_changsex:
                Intent intent_changsex =new Intent(mContext,ChangSexActivity.class);
                startActivity(intent_changsex);
                break;
            case R.id.rl_changemail:
                Intent intent_changemail =new Intent(mContext,ChangEmailActivity.class);
                startActivity(intent_changemail);
                break;
            case R.id.rl_changaddress:
                Intent intent_changaddress =new Intent(mContext,ChangAddressActivity.class);
                startActivity(intent_changaddress);
                break;
            case R.id.rl_changphone:
                Intent intent_changphone =new Intent(PersonalInfoActivity.this,ChangPhoneOneActivity.class);
                startActivity(intent_changphone);
                break;
            case R.id.rl_changpassword:
                Intent intent_changpassword= new Intent(mContext,ChangPasswordActivity.class);
                startActivity(intent_changpassword);
                break;
        }
    }
}
