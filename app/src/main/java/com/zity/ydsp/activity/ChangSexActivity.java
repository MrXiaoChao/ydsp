package com.zity.ydsp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.zity.ydsp.R;
import com.zity.ydsp.base.BaseActivity;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/9/14.
 * 修改性别
 */

public class ChangSexActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.tv_right_save)
    TextView tvRightSave;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.rl_changsex)
    RelativeLayout rlChangsex;

    @Override
    protected int getLayout() {
        return R.layout.activity_changsex;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("修改性别");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_toolbar_back, R.id.tv_right_save, R.id.rl_changsex})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
            case R.id.tv_right_save:
                break;
            case R.id.rl_changsex:
                OptionsPickerView pickerView = new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        tvSex.setText(getResources().getStringArray(R.array.gender)[options1]);
                    }
                }).setTitleText("性别").build();
                pickerView.setPicker(Arrays.asList(getResources().getStringArray(R.array.gender)));
                pickerView.show();
                break;
        }
    }
}
