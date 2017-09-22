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
 * 修改密码
 */

public class ChangPasswordActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.et_old_password)
    EditText etOldPassword;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private Animation shake;
    private String id;

    @Override
    protected int getLayout() {
        return R.layout.activity_changpassword;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("修改密码");
        id = (String) SPUtils.get(ChangPasswordActivity.this, "userid", "");
        shake = AnimationUtils.loadAnimation(mContext, R.anim.shake);
    }


    @OnClick({R.id.iv_toolbar_back, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.btn_login:
                String oldpassword =etOldPassword.getText().toString().trim();
                String newpassword =etNewPassword.getText().toString().trim();
                String newpassword1=etConfirmPassword.getText().toString().trim();
                if (EmptyUtils.isEmpty(oldpassword)){
                    tvMessage.setText("请输入原密码");
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.startAnimation(shake);
                    etOldPassword.requestFocus();
                    return;
                }
                if (EmptyUtils.isEmpty(newpassword)){
                    tvMessage.setText("请输入新密码");
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.startAnimation(shake);
                    etNewPassword.requestFocus();
                    return;
                }
                if (EmptyUtils.isEmpty(newpassword1)){
                    tvMessage.setText("请输入新密码");
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.startAnimation(shake);
                    etConfirmPassword.requestFocus();
                    return;
                }
                if (!StringUtils.equals(newpassword,newpassword1)){
                    tvMessage.setText("两次输入密码不一致");
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.startAnimation(shake);
                    etConfirmPassword.requestFocus();
                    return;
                }
                changPassword(id,oldpassword,newpassword,newpassword1);
                break;
        }
    }

    //修改密码
    private void changPassword(String personuuid, String oldPass, String newPass, final String reNewPass) {
        Map<String, String> map = new HashMap<>();
        map.put("personuuid", personuuid);
        map.put("oldPass", oldPass);
        map.put("newPass", newPass);
        map.put("reNewPass", reNewPass);
        GsonRequest<ChangPassword> request = new GsonRequest<ChangPassword>(Request.Method.POST, map, UrlPath.CHANG_PASSWORD, ChangPassword.class, new Response.Listener<ChangPassword>() {
            @Override
            public void onResponse(ChangPassword response) {
                if (response.getUpdate_flag().equals("true")) {
                    ToastUtils.showShortToast(response.getMsg());
                    finish();
//                    showExitDialog("密码已修改请重新登录");
                } else {
                    tvMessage.setText(response.getMsg());
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.startAnimation(shake);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }

    private void showExitDialog(String msg) {
        final MyDialog dialog = new MyDialog(mContext);
        dialog.setTitle("提示");
        dialog.setMessage(msg);
        dialog.setYesOnclickListener("确定", new MyDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                Intent intent_login = new Intent(mContext, LoginActivity.class);
                startActivity(intent_login);
                dialog.dismiss();
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
