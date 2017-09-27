package com.zity.ydsp.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.blankj.utilcode.utils.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zity.ydsp.R;
import com.zity.ydsp.bean.Grbs;

import java.util.List;

/**
 * Created by luochao on 2017/9/26.
 * 个人办事 适配器
 */

public class GrbsAdapter extends BaseQuickAdapter<Grbs.ListBean, BaseViewHolder> {
    private String type;

    public GrbsAdapter(@LayoutRes int layoutResId, @Nullable List<Grbs.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Grbs.ListBean item) {

        if (StringUtils.equals("1", item.getType())) {
            type = "审批事项";
        } else if (StringUtils.equals("2", item.getType())) {
            type = "便民事项";
        } else if (StringUtils.equals("3", item.getType())) {
            type = "对接事项";
        }
        helper.setText(R.id.tv_title, item.getName())
                .setText(R.id.tv_orgname, item.getOrgname())
                .setText(R.id.tv_type, type);
    }
}
