package com.zity.ydsp.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zity.ydsp.R;
import com.zity.ydsp.bean.MySuggest;

import java.util.List;

/**
 * Created by luochao on 2017/9/20.
 * 我的建议
 */

public class MySuggestAdapter extends BaseQuickAdapter<MySuggest.ListBean,BaseViewHolder>{
    public MySuggestAdapter(@LayoutRes int layoutResId, @Nullable List<MySuggest.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MySuggest.ListBean item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_time, item.getCreatedate())
                .setText(R.id.tv_state,item.getIsreplay());
    }
}
