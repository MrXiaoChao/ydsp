package com.zity.ydsp.activity;

import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.zity.ydsp.R;
import com.zity.ydsp.app.App;
import com.zity.ydsp.base.BaseActivity;
import com.zity.ydsp.fragment.HomePageFragment;
import com.zity.ydsp.fragment.PersonalFragment;
import com.zity.ydsp.fragment.MessageFragment;


import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_homepage)
    TextView tvHomePage;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.tv_personal)
    TextView tvPersonal;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    private long backtime=0;
    private HomePageFragment homePageFragment;
    private MessageFragment messageFragment;
    private PersonalFragment personalFragment;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;

    }

    @Override
    protected void initData() {
        defaultSelected();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    //默认选中
    private void defaultSelected(){
        selected();
        tvHomePage.setSelected(true);
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (homePageFragment == null) {
            homePageFragment = new HomePageFragment();
            transaction.add(R.id.fragment_container, homePageFragment);
        } else {
            transaction.show(homePageFragment);
        }
        transaction.commit();
    }
    //重置所有文本的选中状态
    public void selected() {
        tvHomePage.setSelected(false);
        tvMessage.setSelected(false);
        tvPersonal.setSelected(false);
    }

    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction) {
        if (homePageFragment != null) {
            transaction.hide(homePageFragment);
        }
        if (messageFragment != null) {
            transaction.hide(messageFragment);
        }
        if (personalFragment != null) {
            transaction.hide(personalFragment);
        }

    }

    @OnClick({R.id.tv_homepage,R.id.tv_message, R.id.tv_personal})
    public void onClick(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (view.getId()) {
            case R.id.tv_homepage:
                selected();
                tvHomePage.setSelected(true);
                if (homePageFragment == null) {
                    homePageFragment = new HomePageFragment();
                    transaction.add(R.id.fragment_container, homePageFragment);
                } else {
                    transaction.show(homePageFragment);
                }
                break;

            case R.id.tv_message:
                selected();
                tvMessage.setSelected(true);
                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                    transaction.add(R.id.fragment_container, messageFragment);
                } else {
                    transaction.show(messageFragment);
                }
                break;
            case R.id.tv_personal:
                selected();
                tvPersonal.setSelected(true);
                if (personalFragment == null) {
                    personalFragment = new PersonalFragment();
                    transaction.add(R.id.fragment_container, personalFragment);
                } else {
                    transaction.show(personalFragment);
                }
                break;
        }
        transaction.commit();
    }

    //是否退出客户端
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long t = System.currentTimeMillis();
            if (t - backtime > 3000) {
                ToastUtils.showShortToast("再按一次退出应用");
                backtime = t;
                return true;
            }
            App.getInstance().exitApp();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }



}
