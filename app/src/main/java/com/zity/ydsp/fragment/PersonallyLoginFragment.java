package com.zity.ydsp.fragment;

import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.blankj.utilcode.utils.EmptyUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.reflect.TypeToken;
import com.zity.ydsp.R;
import com.zity.ydsp.activity.ForgetPasswordActivity;
import com.zity.ydsp.activity.MainActivity;
import com.zity.ydsp.activity.RegisterActivity;
import com.zity.ydsp.app.App;
import com.zity.ydsp.base.BaseFragment;
import com.zity.ydsp.bean.MsUserid;
import com.zity.ydsp.bean.User;
import com.zity.ydsp.http.GsonRequest;
import com.zity.ydsp.http.UrlPath;
import com.zity.ydsp.utils.SPUtils;
import com.zity.ydsp.widegt.ClearEditText;
import com.zity.ydsp.widegt.PasswordEditText;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Animation shake;
    private String password;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal_login;
    }

    @Override
    protected void initData() {
        shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        if (SPUtils.contains(getActivity(),"username")){
            etUsername.setText(String.valueOf(SPUtils.get(getActivity(),"username","")));
            etPassword.setText(String.valueOf(SPUtils.get(getActivity(),"password","")));
        }
    }


    @OnClick({R.id.btn_login, R.id.tv_register, R.id.tv_forgetpassword})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String username = etUsername.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                if (EmptyUtils.isEmpty(username)) {
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.setText("用户名不能为空");
                    tvMessage.startAnimation(shake);
                    etUsername.requestFocus();
                    return;
                }
                if (EmptyUtils.isEmpty(password)) {
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.setText("密码不能为空");
                    tvMessage.startAnimation(shake);
                    etPassword.requestFocus();
                    return;
                }
                persionalLogin(username, password);

                break;
            case R.id.tv_register:
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_forgetpassword:
                Intent intent_password = new Intent(mContext, ForgetPasswordActivity.class);
                startActivity(intent_password);
                break;
        }
    }

    //登录
    private void persionalLogin(String username, final String password) {
        TypeToken type = new TypeToken<List<User>>() {
        };
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        GsonRequest<List<User>> request = new GsonRequest<List<User>>(Request.Method.POST, map, UrlPath.PERSION_LOGIN, type, new Response.Listener<List<User>>() {
            @Override
            public void onResponse(List<User> response) {
                if (response.get(0).getLogin_flag().equals("1")) {
                    String userid=response.get(0).getPersonuuid();
                    SPUtils.put(getActivity(),"userid",userid);
                    //记住密码
                    SPUtils.put(getActivity(),"username",response.get(0).getUsername());
                    SPUtils.put(getActivity(),"password",password);
                    SPUtils.put(getActivity(),"phone",response.get(0).getPhone());
                    SPUtils.put(getActivity(),"name",response.get(0).getName());
                    SPUtils.put(getActivity(),"person_flag",response.get(0).getPerson_flag());
                    //获取民生互动id
                    getMshdId(response.get(0).getPhone());

                    Intent intent_login = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent_login);
                    getActivity().finish();
                } else {
                    ToastUtils.showShortToast("账号密码错误");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }

    //获取民生网用户id
    private void getMshdId(String phone){
        Map<String,String> map =new HashMap<>();
        map.put("phone",phone);
        GsonRequest<MsUserid> request =new GsonRequest<MsUserid>(Request.Method.POST, map, UrlPath.MSHD, MsUserid.class, new Response.Listener<MsUserid>() {
            @Override
            public void onResponse(MsUserid response) {
                if (response!=null){
                    String mshdid = response.getUser_id();
                    SPUtils.put(getActivity(),"mshdid",mshdid);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }
}

