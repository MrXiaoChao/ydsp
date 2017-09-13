package com.zity.ydsp.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zity.ydsp.R;
import com.zity.ydsp.adapter.TablayoutAdapter;
import com.zity.ydsp.base.BaseActivity;
import com.zity.ydsp.fragment.CorporationRegisterFragment;
import com.zity.ydsp.fragment.PersonallyRegisterFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/9/12.
 * 注册页面
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.tlMain)
    TabLayout tlMain;
    @BindView(R.id.vpMain)
    ViewPager vpMain;
    private ArrayList<String> titleList = new ArrayList<String>() {{
        add("个人注册");
        add("法人注册");
    }};

    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>() {{
        add(new PersonallyRegisterFragment());
        add(new CorporationRegisterFragment());
    }};

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        tvTooltarTitle.setText("注册");
        vpMain.setOffscreenPageLimit(2);
        TablayoutAdapter adapter = new TablayoutAdapter(getSupportFragmentManager(), titleList, fragmentList);
        vpMain.setAdapter(adapter);
        tlMain.setupWithViewPager(vpMain, true);
        tlMain.setTabsFromPagerAdapter(adapter);
        //设置tablayout下划线的宽度
        tlMain.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tlMain, 20, 20);
            }
        });
    }

    //设置tablayout下划线的宽度的方法
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }


    @OnClick(R.id.iv_toolbar_back)
    public void onClick() {
        onBackPressedSupport();
    }
}
