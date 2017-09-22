package com.zity.ydsp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.EmptyUtils;
import com.blankj.utilcode.utils.RegexUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.zity.ydsp.R;
import com.zity.ydsp.base.BaseActivity;
import com.zity.ydsp.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/9/14.
 * 修改手机号码第一步
 */

public class ChangPhoneOneActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.btn_yzm)
    TextView btnYzm;
    @BindView(R.id.et_duanxin)
    EditText etDuanxin;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    private Animation shake;
    private CountDownTimer timer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            btnYzm.setEnabled(false);
            btnYzm.setText((millisUntilFinished / 1000) + "秒后重发");
        }

        @Override
        public void onFinish() {
            btnYzm.setEnabled(true);
            btnYzm.setText("获取验证码");
        }
    };

    @Override
    protected int getLayout() {
        return R.layout.activity_changphone_one;
    }

    @Override
    protected void initData() {
        String phone = (String) SPUtils.get(mContext, "phone", "");
        tvPhone.setText(phone);
        tvTooltarTitle.setText("修改手机号");
        shake = AnimationUtils.loadAnimation(mContext, R.anim.shake);
    }


    @OnClick({R.id.iv_toolbar_back, R.id.btn_login,R.id.btn_yzm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.btn_login:
                String yzm =etDuanxin.getText().toString().trim();
                String phone =etPhone.getText().toString().trim();
                if (EmptyUtils.isEmpty(phone)){
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.setText("手机号不能为空");
                    tvMessage.startAnimation(shake);
                    etPhone.requestFocus();
                    return;
                }
                if (!RegexUtils.isMobileExact(phone)){
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.setText("手机号不合法");
                    tvMessage.startAnimation(shake);
                    etPhone.requestFocus();
                    return;
                }
                if (EmptyUtils.isEmpty(yzm)){
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.setText("验证码不能为空");
                    tvMessage.startAnimation(shake);
                    etDuanxin.requestFocus();
                    return;
                }
                Intent intent = new Intent(mContext, ChangPhoneTwoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_yzm:
                String phone1 =etPhone.getText().toString().trim();
                if (EmptyUtils.isEmpty(phone1)){
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.setText("手机号不能为空");
                    tvMessage.startAnimation(shake);
                    etPhone.requestFocus();
                    return;
                }

                if (!RegexUtils.isMobileExact(phone1)){
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.setText("手机号不合法");
                    tvMessage.startAnimation(shake);
                    etPhone.requestFocus();
                    return;
                }
                timer.start();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
