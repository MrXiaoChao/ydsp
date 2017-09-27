package com.zity.ydsp.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.zity.ydsp.R;
import com.zity.ydsp.activity.GrbsActivity;
import com.zity.ydsp.adapter.HomePageAdapter;
import com.zity.ydsp.adapter.PersionalServiceAdapter;
import com.zity.ydsp.adapter.PersionalServiceAdapter1;
import com.zity.ydsp.app.App;
import com.zity.ydsp.base.BaseFragment;
import com.zity.ydsp.bean.ClassImager;
import com.zity.ydsp.bean.HomePageImageUrl;
import com.zity.ydsp.http.GsonRequest;
import com.zity.ydsp.http.UrlPath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by luochao on 2017/9/19.
 * 个人办事  按部门
 */

public class PersionalServiceClassFragment extends BaseFragment{
    @BindView(R.id.rv_persionalservice_them)
    RecyclerView rvPersionalserviceThem;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_persionservice_class;
    }

    @Override
    protected void initData() {
        GridLayoutManager layoutManager =new GridLayoutManager(getActivity(),2);
        rvPersionalserviceThem.setLayoutManager(layoutManager);
        getPersionalImageFromService();
    }

    //从服务器获取部门办事图标及标题
    private void getPersionalImageFromService() {
        TypeToken type = new TypeToken<List<ClassImager>>() {
        };
        GsonRequest<List<ClassImager>> request = new GsonRequest<List<ClassImager>>(UrlPath.CORPORATION_CLASS, type, new Response.Listener<List<ClassImager>>() {
            @Override
            public void onResponse(final List<ClassImager> response) {
                if (response.get(0).getList().size() > 0) {
                    PersionalServiceAdapter1 adapter = new PersionalServiceAdapter1(getActivity(), response.get(0).getList());
                    rvPersionalserviceThem.setAdapter(adapter);


                    adapter.setClickListener(new HomePageAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(View view, int position) {
                            Intent intent = new Intent(getActivity(), GrbsActivity.class);
                            intent.putExtra("titleId", response.get(0).getList().get(position).getOrgId());
                            intent.putExtra("flag", "个人");
                            intent.putExtra("title","部门");
                            startActivity(intent);
                        }
                    });
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
