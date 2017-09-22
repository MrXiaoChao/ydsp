package com.zity.ydsp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.blankj.utilcode.utils.EmptyUtils;
import com.blankj.utilcode.utils.RegexUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.zity.ydsp.R;
import com.zity.ydsp.app.App;
import com.zity.ydsp.base.BaseActivity;
import com.zity.ydsp.bean.SuggestSuccess;
import com.zity.ydsp.http.GsonRequest;
import com.zity.ydsp.http.UrlPath;
import com.zity.ydsp.utils.SPUtils;
import com.zity.ydsp.widegt.BanCNandEmpty;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/9/21.
 * 提建议
 */

public class MakeSuggestActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private String phone1;

    @Override
    protected int getLayout() {
        return R.layout.activity_make_suggest;
    }

    @Override
    protected void initData() {
        //防止虚拟键盘把所有布局给推上去
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        phone1 = (String) SPUtils.get(MakeSuggestActivity.this, "phone", "");
        tvTooltarTitle.setText("填写建议");
    }


    @OnClick({R.id.iv_toolbar_back, R.id.btn_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.btn_submit:
                String title = etTitle.getText().toString().trim();
                String content = etContent.getText().toString().trim();
                String username = etName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                if (EmptyUtils.isEmpty(title)) {
                    ToastUtils.showShortToast("请输入标题");
                    etTitle.requestFocus();
                    return;
                }
                if (EmptyUtils.isEmpty(content)) {
                    ToastUtils.showShortToast("请输入内容");
                    etContent.requestFocus();
                    return;
                }
                if (EmptyUtils.isEmpty(username)) {
                    ToastUtils.showShortToast("请输入姓名");
                    etName.requestFocus();
                    return;
                }
                if (EmptyUtils.isEmpty(phone)) {
                    ToastUtils.showShortToast("请输入电话号码");
                    etPhone.requestFocus();
                    return;
                }
                if (!RegexUtils.isMobileExact(phone)) {
                    ToastUtils.showShortToast("电话号码不合法");
                    etPhone.requestFocus();
                    return;
                }
                submitSuggest(title, content, username, phone);
                break;
        }
    }

    //提交建议到服务器
    private void submitSuggest(String title, String content, String username, String phone) {
        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("content", content);
        map.put("username", username);
        map.put("phone", phone);
        GsonRequest<SuggestSuccess> request = new GsonRequest<SuggestSuccess>(Request.Method.POST, map, UrlPath.SUBMIT_SUGGEST, SuggestSuccess.class, new Response.Listener<SuggestSuccess>() {
            @Override
            public void onResponse(SuggestSuccess response) {
                if (response.getSave_flag().equals("true")) {
                    ToastUtils.showShortToast(response.getMsg());
                    finish();
                } else {
                    ToastUtils.showShortToast(response.getMsg());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String a ="a";
            }
        });
        App.getInstance().getHttpQueue().add(request);
    }
}
