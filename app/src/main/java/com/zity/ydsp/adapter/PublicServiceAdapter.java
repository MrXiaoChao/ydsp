package com.zity.ydsp.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zity.ydsp.R;
import com.zity.ydsp.bean.PublicService;
import com.zity.ydsp.http.UrlPath;


import java.util.List;

/**
 * Created by luochao on 2017/7/28.
 */

public class PublicServiceAdapter extends BaseQuickAdapter<PublicService.ListBean,BaseViewHolder>{

    public PublicServiceAdapter(@LayoutRes int layoutResId, @Nullable List<PublicService.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PublicService.ListBean item) {
            helper.setText(R.id.item_tv,item.getTitle());
            Glide.with(mContext).load(UrlPath.BaseUrl1+item.getPicurl()).centerCrop().into((ImageView) helper.getView(R.id.item_iv));
    }
}
