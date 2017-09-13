package com.zity.ydsp.fragment;

import android.view.WindowManager;

import com.zity.ydsp.R;
import com.zity.ydsp.base.BaseFragment;

/**
 * Created by luochao on 2017/9/12.
 * 个人注册页面
 */

public class PersonallyRegisterFragment extends BaseFragment{
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal_register;
    }

    @Override
    protected void initData() {
       getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }
}
