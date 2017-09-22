package com.zity.ydsp.fragment;

import android.os.CountDownTimer;
import android.support.annotation.IdRes;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.zity.ydsp.base.BaseFragment;
import com.zity.ydsp.bean.Success;
import com.zity.ydsp.http.GsonRequest;
import com.zity.ydsp.http.UrlPath;
import com.zity.ydsp.widegt.BanCNandEmpty;
import com.zity.ydsp.widegt.ClearEditText;
import com.zity.ydsp.widegt.PasswordEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by luochao on 2017/9/12.
 * 个人注册页面
 */

public class PersonallyRegisterFragment extends BaseFragment {
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    PasswordEditText etPassword;
    @BindView(R.id.et_confirm_password)
    PasswordEditText etConfirmPassword;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.rb_man)
    RadioButton rbMan;
    @BindView(R.id.rb_woman)
    RadioButton rbWoman;
    @BindView(R.id.radioGroupID)
    RadioGroup radioGroupID;
    @BindView(R.id.et_idcard)
    ClearEditText etIdcard;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_phonenumber)
    EditText etPhonenumber;
    @BindView(R.id.et_confirm_code)
    EditText etConfirmCode;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.btn_security_code)
    Button btnSecurityCode;

    Unbinder unbinder;

    private String gender;
    private CountDownTimer timer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            btnSecurityCode.setEnabled(false);
            btnSecurityCode.setText((millisUntilFinished / 1000) + "秒后重发");
        }

        @Override
        public void onFinish() {
            btnSecurityCode.setEnabled(true);
            btnSecurityCode.setText("获取验证码");
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal_register;
    }

    @Override
    protected void initData() {
        //防止虚拟键盘把所有布局给推上去
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        BanCNandEmpty.newInstance().banCNandEmpty(etPassword);
        //禁止输入中文跟空格
        BanCNandEmpty.newInstance().banCNandEmpty(etConfirmPassword);
        BanCNandEmpty.newInstance().banCNandEmpty(etUsername);
        BanCNandEmpty.newInstance().banCNandEmpty(etEmail);
        radioGroupID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (rbMan.getId() == checkedId) {
                    gender = "0";
                } else {
                    gender = "1";
                }
            }
        });
    }


    //从服务器获取数据
    private void getDataFromService(String username, String password, String repassword, String name, String gender,
                                    String id_num, String email, String address, String phone, String phone_yzm) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("repassword", repassword);
        map.put("name", name);
        map.put("gender", gender);
        map.put("id_num", id_num);
        map.put("email", email);
        map.put("address", address);
        map.put("phone", phone);
        map.put("phone_yzm", phone_yzm);
        GsonRequest<Success> request = new GsonRequest<Success>(Request.Method.POST, map, UrlPath.PERSION_REGISTER, Success.class, new Response.Listener<Success>() {
            @Override
            public void onResponse(Success response) {
                if (response.getFlag().equals("true")) {
                    ToastUtils.showShortToast("注册成功");
                    mActivity.finish();
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


    @OnClick({R.id.btn_register, R.id.btn_security_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_security_code:
                timer.start();
                break;
            case R.id.btn_register:
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String repassword = etConfirmPassword.getText().toString().trim();
                String name = etName.getText().toString().trim();
                String id_num = etIdcard.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String address = etAddress.getText().toString().trim();
                String phone = etPhonenumber.getText().toString().trim();
                String phone_yzm = etConfirmCode.getText().toString().trim();

                if (EmptyUtils.isEmpty(username)) {
                    ToastUtils.showShortToast("用户名不能为空");
                    etUsername.requestFocus();
                    return;
                }
                if (EmptyUtils.isEmpty(password)) {
                    ToastUtils.showShortToast("密码不能为空");
                    etPassword.requestFocus();
                    return;
                }
                if (EmptyUtils.isEmpty(repassword)) {
                    ToastUtils.showShortToast("密码不能为空");
                    etConfirmPassword.requestFocus();
                    return;
                }
                if (!StringUtils.equals(password, repassword)) {
                    ToastUtils.showShortToast("两次输入密码不一致");
                    etConfirmPassword.requestFocus();
                    return;
                }
                if (EmptyUtils.isEmpty(name)) {
                    ToastUtils.showShortToast("姓名不能为空");
                    etName.requestFocus();
                    return;
                }
                if (EmptyUtils.isEmpty(gender)) {
                    ToastUtils.showShortToast("请选择性别");
                    return;
                }
                if (EmptyUtils.isEmpty(id_num)) {
                    ToastUtils.showShortToast("身份证号不能为空");
                    etIdcard.requestFocus();
                    return;
                }
                if (!RegexUtils.isIDCard18(id_num)) {
                    ToastUtils.showShortToast("身份证号不合法");
                    etIdcard.requestFocus();
                    return;
                }
                if (EmptyUtils.isEmpty(email)) {
                    ToastUtils.showShortToast("邮箱不能为空");
                    etEmail.requestFocus();
                    return;
                }
                if (!RegexUtils.isEmail(email)) {
                    ToastUtils.showShortToast("邮件不合法");
                    etEmail.requestFocus();
                    return;
                }
                if (EmptyUtils.isEmpty(address)) {
                    ToastUtils.showShortToast("地址不能为空");
                    etAddress.requestFocus();
                    return;
                }
                if (EmptyUtils.isEmpty(phone)) {
                    ToastUtils.showShortToast("手机号不能为空");
                    etPhonenumber.requestFocus();
                    return;
                }
                if (!RegexUtils.isMobileExact(phone)) {
                    ToastUtils.showShortToast("手机号不合法");
                    etPhonenumber.requestFocus();
                    return;
                }
                if (EmptyUtils.isEmpty(phone_yzm)) {
                    ToastUtils.showShortToast("验证码不能为空");
                    etConfirmCode.requestFocus();
                    return;
                }
                getDataFromService(username, password, repassword, name, gender, id_num, email, address, phone, phone_yzm);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timer.cancel();
    }
}
