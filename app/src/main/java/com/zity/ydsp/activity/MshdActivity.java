package com.zity.ydsp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zity.ydsp.R;
import com.zity.ydsp.app.App;
import com.zity.ydsp.base.BaseActivity;
import com.zity.ydsp.bean.HomePageBanner;
import com.zity.ydsp.bean.MessageEvent;
import com.zity.ydsp.http.GsonRequest;
import com.zity.ydsp.http.UrlPath;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by luochao on 2017/9/25.
 * 民生互动模块
 */

public class MshdActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_tooltar_title)
    TextView tvTooltarTitle;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_zixun)
    TextView tvZixun;
    @BindView(R.id.tv_jianyi)
    TextView tvJianyi;
    @BindView(R.id.tv_tousu)
    TextView tvTousu;
    @BindView(R.id.tv_qiuzhu)
    TextView tvQiuzhu;
    @BindView(R.id.tv_yijian)
    TextView tvYijian;
    @BindView(R.id.tv_jubao)
    TextView tvJubao;
    @BindView(R.id.tv_bszn)
    TextView tvBszn;
    @BindView(R.id.tv_zcfg)
    TextView tvZcfg;
    @BindView(R.id.tv_tzgg)
    TextView tvTzgg;
    @BindView(R.id.tv_yiliao)
    TextView tvYiliao;
    @BindView(R.id.tv_jiaotong)
    TextView tvJiaotong;
    @BindView(R.id.tv_nongye)
    TextView tvNongye;
    @BindView(R.id.tv_zhufang)
    TextView tvZhufang;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    List<String> images = new ArrayList<>();
    private List<HomePageBanner.ListBean> list;
    private ProgressDialog progressDialog;
    @Override
    protected int getLayout() {
        return R.layout.activity_mshd;
    }

    @Override
    protected void initData() {
        setProgress();
        tvTooltarTitle.setText("群众服务平台");
        getImagerFromService();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(MshdActivity.this, WebViewActivity.class);
                intent.putExtra("URL", "http://211.151.183.170:9081/views/app/appImgDetail.jsp?id=" + list.get(position).getImgId());
                startActivity(intent);
            }
        });
        //下拉刷新
        swipeLayout.setOnRefreshListener(this);
    }

    @OnClick({R.id.tv_zixun, R.id.tv_jianyi, R.id.tv_tousu, R.id.tv_qiuzhu, R.id.tv_yijian, R.id.tv_jubao, R.id.tv_bszn, R.id.tv_zcfg, R.id.tv_tzgg, R.id.tv_yiliao, R.id.tv_jiaotong, R.id.tv_nongye, R.id.tv_zhufang,R.id.iv_toolbar_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_zixun://咨询
                Intent intent_zixun = new Intent(mContext, AppealActivity.class);
                intent_zixun.putExtra("title", "咨询");
                startActivity(intent_zixun);
                break;
            case R.id.tv_jianyi://建议
                Intent intent_jianyi = new Intent(mContext, AppealActivity.class);
                intent_jianyi.putExtra("title", "建议");
                startActivity(intent_jianyi);
                break;
            case R.id.tv_tousu://投诉
                Intent intent_tousu = new Intent(mContext, AppealActivity.class);
                intent_tousu.putExtra("title", "投诉");
                startActivity(intent_tousu);
                break;
            case R.id.tv_qiuzhu://求助
                Intent intent_qiuzhu = new Intent(mContext, AppealActivity.class);
                intent_qiuzhu.putExtra("title", "求助");
                startActivity(intent_qiuzhu);
                break;
            case R.id.tv_yijian://意见
                Intent intent_yijian = new Intent(mContext, AppealActivity.class);
                intent_yijian.putExtra("title", "意见");
                startActivity(intent_yijian);
                break;
            case R.id.tv_jubao://举报
                Intent intent_jubao = new Intent(mContext, AppealActivity.class);
                intent_jubao.putExtra("title", "举报");
                startActivity(intent_jubao);
                break;
            case R.id.tv_bszn://办事指南
                Intent intent_bszn = new Intent(mContext, CommonActivity.class);
                intent_bszn.putExtra("title", "办事指南");
                startActivity(intent_bszn);
                break;
            case R.id.tv_zcfg://政策法规
                Intent intent_zcfg = new Intent(mContext, CommonActivity.class);
                intent_zcfg.putExtra("title", "政策法规");
                startActivity(intent_zcfg);
                break;
            case R.id.tv_tzgg://通知公告
                Intent intent_txgg = new Intent(mContext, CommonActivity.class);
                intent_txgg.putExtra("title", "通知公告");
                startActivity(intent_txgg);
                break;
            case R.id.tv_yiliao://医疗
                Intent intent =new Intent(MshdActivity.this,PublicServiceActivity.class);
                intent.putExtra("flag","0");
                startActivity(intent);
                break;
            case R.id.tv_jiaotong://交通
                EventBus.getDefault().post(new MessageEvent("1"));
                Intent intent1 =new Intent(MshdActivity.this,PublicServiceActivity.class);
                intent1.putExtra("flag","1");
                startActivity(intent1);
                break;
            case R.id.tv_nongye://农业
                EventBus.getDefault().post(new MessageEvent("2"));
                Intent intent2 =new Intent(MshdActivity.this,PublicServiceActivity.class);
                intent2.putExtra("flag","2");
                startActivity(intent2);
                break;
            case R.id.tv_zhufang://住房
                EventBus.getDefault().post(new MessageEvent("3"));
                Intent intent3 =new Intent(MshdActivity.this,PublicServiceActivity.class);
                intent3.putExtra("flag","3");
                startActivity(intent3);
                break;
            case R.id.iv_toolbar_back:
                onBackPressedSupport();
                break;
        }
    }

    private boolean isRefresh = false;//是否刷新中

    @Override
    public void onRefresh() {
        if (!isRefresh) {
            getImagerFromService();
            isRefresh = true;
        }
    }


    //加载图片
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }

    }

    //获取banner图片
    private void getImagerFromService() {
        GsonRequest<HomePageBanner> request = new GsonRequest<HomePageBanner>(UrlPath.BANNER_IMAGE, HomePageBanner.class, new Response.Listener<HomePageBanner>() {
            @Override
            public void onResponse(HomePageBanner response) {
                //防止刷新的时候重复获取数据   因为list 跟 images 用的都是全局变量
                if (list != null && list.size() > 0) {
                    list.clear();
                }
                if (images != null && images.size() > 0) {
                    images.clear();
                }

                if (response.getList() != null && response.getList().size() > 0) {
                    list = response.getList();
                    //设置图片加载器
                    for (int i = 0; i < response.getList().size(); i++) {
                        images.add("http://211.151.183.170:9081" + response.getList().get(i).getUrl());
                    }
                    banner.setImageLoader(new GlideImageLoader());
                    //设置图片集合
                    banner.setImages(images);
                    banner.setDelayTime(3600);
                    //banner设置方法全部调用完毕时最后调用
                    banner.start();
                    swipeLayout.setRefreshing(false);
                    isRefresh = false;
                    cancelProgress();
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
