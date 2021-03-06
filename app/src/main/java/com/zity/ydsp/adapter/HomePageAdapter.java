package com.zity.ydsp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zity.ydsp.R;
import com.zity.ydsp.bean.HomePageImageUrl;

import java.util.List;

/**
 * Created by luochao on 2017/9/19.
 */

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.ViewHolder> {

    private Context context;
    private List<HomePageImageUrl.ListBean> list;
    private OnItemClickListener clickListener;

    public HomePageAdapter(Context context, List<HomePageImageUrl.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_homepageimage, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvHomepageTitle.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getUrl()).error(R.drawable.hjsf).into(holder.ivHomePageUrl);
    }

    @Override
    public int getItemCount() {
        return list.size() != 0 ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvHomepageTitle;
        private ImageView ivHomePageUrl;

        public ViewHolder(View itemView) {
            super(itemView);
            tvHomepageTitle = (TextView) itemView.findViewById(R.id.tv_homepage_title);
            ivHomePageUrl = (ImageView) itemView.findViewById(R.id.iv_homepage_url);
            LinearLayout ll_image = (LinearLayout) itemView.findViewById(R.id.ll_image);
            ll_image.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onClick(itemView, getAdapterPosition());
            }
        }
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public static interface OnItemClickListener {
        void onClick(View view, int position);
    }

}
