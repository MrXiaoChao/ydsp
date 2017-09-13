package com.zity.ydsp.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zity.ydsp.R;
import com.zity.ydsp.activity.ForgetPasswordActivity;
import com.zity.ydsp.activity.MainActivity;
import com.zity.ydsp.activity.RegisterActivity;
import com.zity.ydsp.base.BaseFragment;
import com.zity.ydsp.widegt.ClearEditText;
import com.zity.ydsp.widegt.PasswordEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/9/11.
 * 个人登录页面
 */

public class PersonallyLoginFragment extends BaseFragment {
    @BindView(R.id.et_username)
    ClearEditText etUsername;
    @BindView(R.id.et_password)
    PasswordEditText etPassword;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forgetpassword)
    TextView tvForgetpassword;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal_login;
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.btn_login, R.id.tv_register, R.id.tv_forgetpassword})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                Intent intent_login =new Intent(getActivity(), MainActivity.class);
                startActivity(intent_login);
                break;
            case R.id.tv_register:
                Intent intent =new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_forgetpassword:
                Intent intent_password =new Intent(mContext, ForgetPasswordActivity.class);
                startActivity(intent_password);
                break;
        }
    }
}
