package com.zity.ydsp.base;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.blankj.utilcode.utils.Utils;
import com.zity.ydsp.R;
import com.zity.ydsp.app.App;
import com.zity.ydsp.widegt.ConnectionChangeReceiver;
import com.zity.ydsp.widegt.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;


/**
 * Created by luochao on 2017/3/15.
 * Activity的基类
 */

public abstract class BaseActivity extends SupportActivity{
    protected Activity mContext;
    private Unbinder mUnBinder;
    private ConnectionChangeReceiver myReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        //沉浸式状态栏
        StatusBarUtil.setColor(mContext,getResources().getColor(R.color.blue),1);
        App.getInstance().addActivity(this);
        initData();
        //初始化工具类
        Utils.init(mContext);
        registerReceiver();
    }

    //顶部同意样式栏
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        mUnBinder.unbind();
        unregisterReceiver(myReceiver);
    }

    protected abstract int getLayout();
    protected abstract void initData();

    private  void registerReceiver(){
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        myReceiver = new ConnectionChangeReceiver();
        this.registerReceiver(myReceiver, filter);
    }
}
