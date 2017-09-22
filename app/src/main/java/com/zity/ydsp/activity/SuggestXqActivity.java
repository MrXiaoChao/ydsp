package com.zity.ydsp.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.blankj.utilcode.utils.EmptyUtils;
import com.google.gson.reflect.TypeToken;
import com.zity.ydsp.R;
import com.zity.ydsp.app.App;
import com.zity.ydsp.base.BaseActivity;
import com.zity.ydsp.bean.SuggestXQ;
import com.zity.ydsp.http.GsonRequest;
import com.zity.ydsp.http.UrlPath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/9/21.
 * 建议详情
 */

public class SuggestXqActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_creattime)
    TextView tvCreattime;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phonenumber)
    TextView tvPhonenumber;
    @BindView(R.id.tv_suggest_content)
    TextView tvSuggestContent;
    @BindView(R.id.tv_reply_content)
    TextView tvReplyContent;
    @BindView(R.id.tv_reply_time)
    TextView tvReplyTime;
    private String id;

    @Override
    protected int getLayout() {
        return R.layout.activity_suggestxq;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("建议详情");
        id = getIntent().getStringExtra("id");
        getDataFromService();
    }


    @OnClick(R.id.iv_toolbar_back)
    public void onClick() {
        onBackPressedSupport();
    }

    //从服务器获取数据
    private void getDataFromService() {
        TypeToken type = new TypeToken<List<SuggestXQ>>() {
        };
        Map<String, String> map = new HashMap<>();
        map.put("jianyi_id", id);
        GsonRequest<List<SuggestXQ>> request = new GsonRequest<List<SuggestXQ>>(Request.Method.POST, map, UrlPath.SUGGESTXQ, type, new Response.Listener<List<SuggestXQ>>() {
            @Override
            public void onResponse(List<SuggestXQ> response) {
                if (response != null && response.size() > 0) {
                    tvTitle.setText(response.get(0).getTitle());
                    tvName.setText(response.get(0).getName());
                    tvPhonenumber.setText(response.get(0).getPhone());
                    tvCreattime.setText("提交时间：" + response.get(0).getCreatedate());
                    tvSuggestContent.setText(response.get(0).getContent());
                    tvReplyContent.setText(response.get(0).getReplycontent());
                    if (EmptyUtils.isNotEmpty(response.get(0).getReplydate())) {
                        tvReplyTime.setText("答复时间：" + response.get(0).getReplydate());
                    }
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
