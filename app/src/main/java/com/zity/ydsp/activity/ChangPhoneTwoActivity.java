package com.zity.ydsp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.blankj.utilcode.utils.EmptyUtils;
import com.blankj.utilcode.utils.RegexUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.zity.ydsp.R;
import com.zity.ydsp.app.App;
import com.zity.ydsp.base.BaseActivity;
import com.zity.ydsp.bean.ChangPassword;
import com.zity.ydsp.http.GsonRequest;
import com.zity.ydsp.http.UrlPath;
import com.zity.ydsp.utils.SPUtils;
import com.zity.ydsp.widegt.MyDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/9/14.
 * 修改手机号码第二步
 */

public class ChangPhoneTwoActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_confirm_phone)
    EditText etConfirmPhone;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    private Animation shake;

    @Override
    protected int getLayout() {
        return R.layout.activity_changphone_two;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("修改手机号");
        shake = AnimationUtils.loadAnimation(mContext, R.anim.shake);
    }


    @OnClick({R.id.iv_toolbar_back, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.btn_login:
                String userid = (String) SPUtils.get(mContext,"userid","");
                String phone = etPhone.getText().toString().trim();
                String confimphone = etConfirmPhone.getText().toString().trim();
                if (EmptyUtils.isEmpty(phone)) {
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.setText("手机号不能为空");
                    tvMessage.startAnimation(shake);
                    etPhone.requestFocus();
                    return;
                }
                if (!RegexUtils.isMobileExact(phone)) {
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.setText("手机号不合法");
                    tvMessage.startAnimation(shake);
                    etPhone.requestFocus();
                    return;
                }
                if (EmptyUtils.isEmpty(confimphone)) {
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.setText("手机号不能为空");
                    tvMessage.startAnimation(shake);
                    etConfirmPhone.requestFocus();
                    return;
                }
                if (!RegexUtils.isMobileExact(confimphone)) {
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.setText("手机号不合法");
                    tvMessage.startAnimation(shake);
                    etConfirmPhone.requestFocus();
                    return;
                }
                if (!StringUtils.equals(phone, confimphone)) {
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.setText("新手机号不一致");
                    tvMessage.startAnimation(shake);
                    etPhone.requestFocus();
                    return;
                }
                changPhoneNumber(userid,phone,"12313");
                break;
        }
    }

    //修改手机号码
    private void changPhoneNumber(String personuuid, String phone, String phone_yzm) {
        final Map<String, String> map = new HashMap<>();
        map.put("personuuid", personuuid);
        map.put("phone", phone);
        map.put("phone_yzm", phone_yzm);
        GsonRequest<ChangPassword> request = new GsonRequest<ChangPassword>(Request.Method.POST, map, UrlPath.CHANG_PHONE, ChangPassword.class, new Response.Listener<ChangPassword>() {
            @Override
            public void onResponse(ChangPassword response) {
                if (response.getUpdate_flag().equals("true")) {
                    showExitDialog1("手机号修改成功，请重新登录");
                } else {
                    ToastUtils.showShortToast(response.getMsg());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }

    private void showExitDialog1(final String msg) {
        final MyDialog dialog = new MyDialog(mContext);
        dialog.setTitle("提示");
        dialog.setMessage(msg);
        dialog.setYesOnclickListener("确定", new MyDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                if (SPUtils.contains(mContext, "username")) {
                    SPUtils.remove(mContext, "username");
                }
                if (SPUtils.contains(mContext, "password")) {
                    SPUtils.remove(mContext, "password");
                }
                if (SPUtils.contains(mContext, "phone")) {
                    SPUtils.remove(mContext, "phone");
                }
                if (SPUtils.contains(mContext, "name")) {
                    SPUtils.remove(mContext, "name");
                }
                if (SPUtils.contains(mContext, "person_flag")) {
                    SPUtils.remove(mContext, "person_flag");
                }
                Intent intent_login = new Intent(mContext, LoginActivity.class);
                startActivity(intent_login);
                dialog.dismiss();
                mContext.finish();
            }
        });
        dialog.setNoOnclickListener("取消", new MyDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
            }
        });
        dialog.show();
    }
}
