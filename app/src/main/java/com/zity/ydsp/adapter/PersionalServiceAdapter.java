package com.zity.ydsp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zity.ydsp.R;
import com.zity.ydsp.bean.ClassImager;
import com.zity.ydsp.bean.HomePageImageUrl;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by luochao on 2017/9/19.
 * 个人办事 法人办事 适配器
 */

public class PersionalServiceAdapter extends RecyclerView.Adapter<PersionalServiceAdapter.ViewHolder> {
    private Context context;
    private List<HomePageImageUrl.ListBean> list;
    private HomePageAdapter.OnItemClickListener clickListener;

    public PersionalServiceAdapter(Context context, List<HomePageImageUrl.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_case_them, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvPersionalThem.setText(list.get(position).getName());
        Glide.with(context).load("").error(R.drawable.hjsf).into(holder.ivPersionalThem);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView ivPersionalThem;
        private final TextView tvPersionalThem;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPersionalThem = (ImageView) itemView.findViewById(R.id.iv_persional_them);
            tvPersionalThem = (TextView) itemView.findViewById(R.id.tv_persional_them);
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
    public void setClickListener(HomePageAdapter.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public static interface OnItemClickListener {
        void onClick(View view, int position);
    }

}
