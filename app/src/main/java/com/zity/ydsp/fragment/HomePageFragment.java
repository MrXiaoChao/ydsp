package com.zity.ydsp.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.zity.ydsp.R;
import com.zity.ydsp.activity.CorporationServiceActivity;
import com.zity.ydsp.activity.MshdActivity;
import com.zity.ydsp.activity.PersionalServiceActivity;
import com.zity.ydsp.adapter.HomePageAdapter;
import com.zity.ydsp.app.App;
import com.zity.ydsp.base.BaseFragment;
import com.zity.ydsp.bean.HomePageImageUrl;
import com.zity.ydsp.http.GsonRequest;
import com.zity.ydsp.http.UrlPath;
import com.zity.ydsp.widegt.VpSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.rv_persional)
    RecyclerView rvPersional;
    @BindView(R.id.rv_corporation)
    RecyclerView rvCorporation;
    List<String> images = new ArrayList<>();
    @BindView(R.id.tv_persional_more)
    TextView tvPersionalMore;
    @BindView(R.id.tv_corporation_more)
    TextView tvCorporationMore;
    @BindView(R.id.iv_mshd)
    ImageView ivMshd;
    private ProgressDialog progressDialog;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initData() {
        setProgress();
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

        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        banner.setDelayTime(3600);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        //从服务器获取图片
        getPersionalImageFromService();
        getCorporationImageFromService();


        //初始化Recycleview
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 4);
        rvPersional.setLayoutManager(mLayoutManager);
        //处理滑动冲突
        rvPersional.setNestedScrollingEnabled(false);

        //初始化Recycleview
        GridLayoutManager mLayoutManager1 = new GridLayoutManager(getActivity(), 4);
        rvCorporation.setLayoutManager(mLayoutManager1);
        //处理滑动冲突
        rvCorporation.setNestedScrollingEnabled(false);

    }

    //下拉刷新
    @Override
    public void onRefresh() {
        swipeLayout.setRefreshing(false);
    }


    @OnClick({R.id.tv_persional_more, R.id.tv_corporation_more, R.id.iv_mshd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_persional_more:
                Intent intent_persional = new Intent(getActivity(), PersionalServiceActivity.class);
                startActivity(intent_persional);
                break;
            case R.id.tv_corporation_more:
                Intent intent_corporation = new Intent(getActivity(), CorporationServiceActivity.class);
                startActivity(intent_corporation);
                break;
            case R.id.iv_mshd:
                Intent intent_mshd =new Intent(getActivity(), MshdActivity.class);
                startActivity(intent_mshd);
                break;
        }
    }

    //加载图片
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }

    }

    //从服务器获取个人办事图标及标题
    private void getPersionalImageFromService() {
        TypeToken type = new TypeToken<List<HomePageImageUrl>>() {
        };
        Map<String, String> map = new HashMap<>();
        map.put("flag", "1");
        final GsonRequest<List<HomePageImageUrl>> request = new GsonRequest<List<HomePageImageUrl>>(Request.Method.POST, map, UrlPath.PERSION_THEM, type, new Response.Listener<List<HomePageImageUrl>>() {
            @Override
            public void onResponse(List<HomePageImageUrl> response) {
                if (response.get(0).getList().size() > 0) {
                    HomePageAdapter adapter = new HomePageAdapter(getActivity(), response.get(0).getList());
                    rvPersional.setAdapter(adapter);
                }else {

                }
                cancelProgress();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }

    //从服务器获取法人办事图标及标题
    private void getCorporationImageFromService() {
        TypeToken type = new TypeToken<List<HomePageImageUrl>>() {
        };
        Map<String, String> map = new HashMap<>();
        map.put("flag", "0");
        final GsonRequest<List<HomePageImageUrl>> request = new GsonRequest<List<HomePageImageUrl>>(Request.Method.POST, map, UrlPath.PERSION_THEM, type, new Response.Listener<List<HomePageImageUrl>>() {
            @Override
            public void onResponse(List<HomePageImageUrl> response) {
                if (response.get(0).getList().size() > 0) {
                    HomePageAdapter adapter = new HomePageAdapter(getActivity(), response.get(0).getList());
                    rvCorporation.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }

    private void setProgress() {
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("正在加载中...");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    private void cancelProgress() {
        progressDialog.dismiss();
    }
}
