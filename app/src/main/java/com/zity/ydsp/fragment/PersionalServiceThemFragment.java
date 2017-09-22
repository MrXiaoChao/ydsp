package com.zity.ydsp.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.zity.ydsp.R;
import com.zity.ydsp.adapter.HomePageAdapter;
import com.zity.ydsp.adapter.PersionalServiceAdapter;
import com.zity.ydsp.app.App;
import com.zity.ydsp.base.BaseFragment;
import com.zity.ydsp.bean.HomePageImageUrl;
import com.zity.ydsp.http.GsonRequest;
import com.zity.ydsp.http.UrlPath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by luochao on 2017/9/19.
 * 个人办事  按主题
 */

public class PersionalServiceThemFragment extends BaseFragment {
    @BindView(R.id.rv_persionalservice_them)
    RecyclerView rvPersionalserviceThem;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_persionservice_them;
    }

    @Override
    protected void initData() {
        GridLayoutManager layoutManager =new GridLayoutManager(getActivity(),2);
        rvPersionalserviceThem.setLayoutManager(layoutManager);
        getPersionalImageFromService();
    }

    //从服务器获取个人办事图标及标题
    private void getPersionalImageFromService() {
        TypeToken type = new TypeToken<List<HomePageImageUrl>>() {
        };
        Map<String, String> map = new HashMap<>();
        map.put("flag", "1");
        final GsonRequest<List<HomePageImageUrl>> request = new GsonRequest<List<HomePageImageUrl>>(Request.Method.POST, map, UrlPath.PERSION_MORE, type, new Response.Listener<List<HomePageImageUrl>>() {
            @Override
            public void onResponse(List<HomePageImageUrl> response) {
                if (response.get(0).getList().size() > 0) {
                    PersionalServiceAdapter adapter = new PersionalServiceAdapter(getActivity(), response.get(0).getList());
                    rvPersionalserviceThem.setAdapter(adapter);
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
