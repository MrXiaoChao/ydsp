package com.zity.ydsp.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zity.ydsp.R;
import com.zity.ydsp.activity.PublicService1Activity;
import com.zity.ydsp.adapter.PublicServiceAdapter;
import com.zity.ydsp.app.App;
import com.zity.ydsp.base.BaseFragment;
import com.zity.ydsp.bean.PublicService;
import com.zity.ydsp.http.GsonRequest;
import com.zity.ydsp.http.UrlPath;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by luochao on 2017/7/26.
 * 交通
 */

public class JiaoTongFragment extends BaseFragment {
    @BindView(R.id.rv_yiliao)
    RecyclerView rvYiliao;
    private List<PublicService.ListBean> list;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_yiliao;
    }

    @Override
    protected void initData() {
        getDataFromService("2");
        LinearLayoutManager manager= new LinearLayoutManager(getActivity());
        rvYiliao.setLayoutManager(manager);
        rvYiliao.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent =new Intent(getActivity(), PublicService1Activity.class);
                intent.putExtra("id",list.get(position).getPublicId());
                intent.putExtra("flag","交通");
                startActivity(intent);
            }
        });
    }
    //获取数据
    private void getDataFromService(String type){
        Map<String,String> map =new HashMap<>();
        map.put("type",type);
        GsonRequest<PublicService> request =new GsonRequest<PublicService>(Request.Method.POST, map, UrlPath.YILIAO, PublicService.class, new Response.Listener<PublicService>() {
            @Override
            public void onResponse(PublicService response) {
                if (response.getList()!=null && response.getList().size()>0){
                    list=response.getList();
                    PublicServiceAdapter adapter =new PublicServiceAdapter(R.layout.item_public_service,response.getList());
                    rvYiliao.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().getHttpQueue().add(request);
    }
}
