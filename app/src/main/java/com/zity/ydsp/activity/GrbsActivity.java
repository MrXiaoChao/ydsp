package com.zity.ydsp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.blankj.utilcode.utils.KeyboardUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.reflect.TypeToken;
import com.zity.ydsp.R;
import com.zity.ydsp.adapter.GrbsAdapter;
import com.zity.ydsp.app.App;
import com.zity.ydsp.base.BaseActivity;
import com.zity.ydsp.bean.Grbs;
import com.zity.ydsp.http.GsonRequest;
import com.zity.ydsp.http.UrlPath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/9/26.
 * 个人办事列表
 */

public class GrbsActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv_grbs)
    RecyclerView rvGrbs;
    @BindView(R.id.view_div)
    View viewDiv;
    private String flag;
    private String title;
    private String titleId;

    @Override
    protected int getLayout() {
        return R.layout.activity_grbs;
    }

    @Override
    protected void initData() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        rvGrbs.setLayoutManager(manager);
        Intent intent = getIntent();
        titleId = intent.getStringExtra("titleId");
        flag = intent.getStringExtra("flag");
        title = intent.getStringExtra("title");
        if (StringUtils.equals("部门", title)) {
            tvTooltarTitle.setText("部门办事");
            if (StringUtils.equals("个人", flag)) {
                getClassList(titleId, "", "", "1");
            } else {
                getClassList(titleId, "", "", "0");
            }

        } else {
            if (StringUtils.equals("法人", flag)) {
                tvTooltarTitle.setText("法人办事");
            } else {
                tvTooltarTitle.setText("个人办事");
            }
            if (StringUtils.equals("个人", flag)) {
                getPersionalList("", titleId, "1");
            } else {
                getPersionalList("", titleId, "0");
            }
        }

        KeyboardUtils.hideSoftInput(this);

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 当按了搜索之后关闭软键盘
                    ((InputMethodManager) etSearch.getContext().getSystemService(
                            Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            GrbsActivity.this.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);

                    //获取要搜索的值
                    String searchtitle = etSearch.getText().toString().trim();
                    if (StringUtils.equals("部门", title)) {
                        if (StringUtils.equals("个人", flag)) {
                            getClassList(titleId, searchtitle, "", "1");
                        } else {
                            getClassList(titleId, searchtitle, "", "0");
                        }

                    } else {
                        if (StringUtils.equals("个人", flag)) {
                            getPersionalList(searchtitle, titleId, "1");
                        } else {
                            getPersionalList(searchtitle, titleId, "0");
                        }
                    }
                    return true;
                }
                return false;
            }
        });

    }


    //从服务器取数据 主题
    private void getPersionalList(String name, String titleId, String beanTo) {
        TypeToken typeToken = new TypeToken<List<Grbs>>() {
        };
        Map<String, String> map = new HashMap<>();
        map.put("id", "");
        map.put("name", name);
        map.put("titleId", titleId);
        map.put("online", "");
        map.put("beanTo", beanTo);
        map.put("page", "1");
        map.put("rows", "10");
        GsonRequest<List<Grbs>> request = new GsonRequest<List<Grbs>>(Request.Method.POST, map, UrlPath.GRBS, typeToken, new Response.Listener<List<Grbs>>() {
            @Override
            public void onResponse(List<Grbs> response) {
                if (response.get(0).getList() != null && response.get(0).getList().size() > 0) {
                    viewDiv.setVisibility(View.VISIBLE);
                    rvGrbs.setAdapter(new GrbsAdapter(R.layout.item_grbs, response.get(0).getList()));
                } else {

                    ToastUtils.showShortToast("该用户无数据");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }


    //从服务器取数据 部门
    private void getClassList(String id, String name, String titleId, String beanTo) {
        TypeToken typeToken = new TypeToken<List<Grbs>>() {
        };
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("titleId", titleId);
        map.put("online", "");
        map.put("beanTo", beanTo);
        map.put("page", "1");
        map.put("rows", "10");
        GsonRequest<List<Grbs>> request = new GsonRequest<List<Grbs>>(Request.Method.POST, map, UrlPath.BMBS, typeToken, new Response.Listener<List<Grbs>>() {
            @Override
            public void onResponse(List<Grbs> response) {
                if (response.get(0).getList() != null && response.get(0).getList().size() > 0) {
                    viewDiv.setVisibility(View.VISIBLE);
                    rvGrbs.setAdapter(new GrbsAdapter(R.layout.item_grbs, response.get(0).getList()));
                } else {
                    ToastUtils.showShortToast("该用户无数据");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }


    @OnClick(R.id.iv_toolbar_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
        }
    }


}
