package com.zity.ydsp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.zity.ydsp.R;
import com.zity.ydsp.app.App;
import com.zity.ydsp.base.BaseActivity;
import com.zity.ydsp.bean.PersionalInfo;
import com.zity.ydsp.http.GsonRequest;
import com.zity.ydsp.http.UrlPath;
import com.zity.ydsp.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
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
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.tv_idcard)
    TextView tvIdcard;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @Override
    protected int getLayout() {
        return R.layout.activity_personalinfo;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("个人信息");
        String userid = (String) SPUtils.get(mContext,"userid","");
        String person_flag= (String) SPUtils.get(mContext,"person_flag","");
        getPersionalInfo(userid,person_flag);
    }


    @OnClick({R.id.iv_toolbar_back, R.id.rl_changsex, R.id.rl_changemail, R.id.rl_changaddress, R.id.rl_changphone, R.id.rl_changpassword})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.rl_changsex:
//                Intent intent_changsex =new Intent(mContext,ChangSexActivity.class);
//                startActivity(intent_changsex);
                break;
            case R.id.rl_changemail:
//                Intent intent_changemail =new Intent(mContext,ChangEmailActivity.class);
//                startActivity(intent_changemail);
                break;
            case R.id.rl_changaddress:
//                Intent intent_changaddress =new Intent(mContext,ChangAddressActivity.class);
//                startActivity(intent_changaddress);
                break;
            case R.id.rl_changphone:
                Intent intent_changphone = new Intent(PersonalInfoActivity.this, ChangPhoneOneActivity.class);
                startActivity(intent_changphone);
                break;
            case R.id.rl_changpassword:
                Intent intent_changpassword = new Intent(mContext, ChangPasswordActivity.class);
                startActivity(intent_changpassword);
                break;
        }
    }

    //获取个人信息
    private void getPersionalInfo(String personuuid, String person_flag) {
        TypeToken type = new TypeToken<List<PersionalInfo>>() {
        };
        Map<String, String> map = new HashMap<>();
        map.put("personuuid", personuuid);
        map.put("person_flag", person_flag);
        GsonRequest<List<PersionalInfo>> request = new GsonRequest<List<PersionalInfo>>(Request.Method.POST, map, UrlPath.PERSIONAL_INFO, type, new Response.Listener<List<PersionalInfo>>() {
            @Override
            public void onResponse(List<PersionalInfo> response) {
                if (response.get(0) != null && response.size() > 0) {
                    tvPhone.setText(response.get(0).getPhone());
                    tvUsername.setText(response.get(0).getUsername());
                    tvIdcard.setText(response.get(0).getIdNum());
                    tvAddress.setText(response.get(0).getAddress());
                    tvEmail.setText(response.get(0).getEmail());
                    tvName.setText(response.get(0).getName());
                    tvGender.setText(response.get(0).getGender());
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