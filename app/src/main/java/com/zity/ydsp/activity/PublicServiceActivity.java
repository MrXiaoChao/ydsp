package com.zity.ydsp.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.StringUtils;
import com.zity.ydsp.R;
import com.zity.ydsp.adapter.TablayoutAdapter;
import com.zity.ydsp.base.BaseActivity;
import com.zity.ydsp.fragment.JiaoTongFragment;
import com.zity.ydsp.fragment.NongYeFragment;
import com.zity.ydsp.fragment.YiLiaoFragment;
import com.zity.ydsp.fragment.ZhuFangFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 公共服务
 * Created by luochao on 2017/7/18.
 */

public class PublicServiceActivity extends BaseActivity {

    @BindView(R.id.tlMain)
    TabLayout tlMain;
    @BindView(R.id.vpMain)
    ViewPager vpMain;
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;

    private ArrayList<String> titleList = new ArrayList<String>() {{
        add("医疗");
        add("交通");
        add("农业");
        add("住房");
    }};
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>() {{
        add(new YiLiaoFragment());
        add(new JiaoTongFragment());
        add(new NongYeFragment());
        add(new ZhuFangFragment());
    }};


    @Override
    protected int getLayout() {
        return R.layout.activity_public_service;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("公共服务");
        vpMain.setOffscreenPageLimit(4);
        TablayoutAdapter adapter = new TablayoutAdapter(getSupportFragmentManager(), titleList, fragmentList);
        vpMain.setAdapter(adapter);
        tlMain.setupWithViewPager(vpMain, true);
        tlMain.setTabsFromPagerAdapter(adapter);
        String flag = getIntent().getStringExtra("flag");
        onMoonEvent(flag);
    }


    public void onMoonEvent(String s) {
        if (StringUtils.equals("0", s)) {
            tlMain.getTabAt(0).select();
        } else if (StringUtils.equals("1", s)) {
            tlMain.getTabAt(1).select();
        } else if (StringUtils.equals("2", s)) {
            tlMain.getTabAt(2).select();
        } else if (StringUtils.equals("3", s)) {
            tlMain.getTabAt(3).select();
        }
    }


    @OnClick(R.id.iv_toolbar_back)
    public void onClick() {
        onBackPressedSupport();
    }
}
