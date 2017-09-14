package com.zity.ydsp.fragment;


import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.zity.ydsp.R;
import com.zity.ydsp.base.BaseFragment;
import com.zity.ydsp.widegt.VpSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 首页
 * Created by luochao on 2017/7/18.
 * 布局可以用recyclervie的网格布局来写避免各种滑动冲突，
 * 布局什么方法简单就用什么方法
 */

public class HomePageFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.swipeLayout)
    VpSwipeRefreshLayout swipeLayout;
    List<String> images = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initData() {
        images.add("http://pic6.nipic.com/20100422/3017209_194429223521_2.jpg");
        images.add("http://image.tianjimedia.com/uploadImages/2015/131/41/5CI8TD94WZ10.jpg");
        images.add("http://image.fvideo.cn/uploadfile/2015/05/25/img37533071189339.jpg");
        images.add("http://image.l99.com/ad8/1437453022715_5swgd5.jpg");
        images.add("http://4493bz.1985t.com/uploads/allimg/160713/5-160G3143G1.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505280814398&di=37220423ca811a9d9cd582f3d38a1578&imgtype=0&src=http%3A%2F%2Fpic7.nipic.com%2F20100508%2F3017209_105824190694_2.jpg");
        ivToolbarBack.setVisibility(View.GONE);
        tvTooltarTitle.setText("移动审批系统");
        //下拉刷新
        swipeLayout.setOnRefreshListener(this);

        banner.setImageLoader(new HomePageFragment.GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        banner.setDelayTime(3600);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        swipeLayout.setRefreshing(false);
    }

    //加载图片
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }

    }
}
