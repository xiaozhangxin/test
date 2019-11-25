package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.ImgTypeBean;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.List;

/**
 * Created by admin on 2019/1/24.
 */

public class ImgTypeAdapter extends RecyclerArrayAdapter<ImgTypeBean> {

    private CommonAdapter<String> commonAdapter;

    public ImgTypeAdapter(Context context, List<ImgTypeBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ImgTypeBean> {
        private RecyclerView recycleView;
        private TextView tvTittle;
        private TextView tvDelete;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_img);
            tvTittle = $(R.id.tvTittle);
            tvDelete = $(R.id.tvDelete);
            recycleView = $(R.id.recycleView);

        }

        @Override
        public void setData(ImgTypeBean data) {
            super.setData(data);
            tvTittle.setText(data.getTittle());
            recycleView.setLayoutManager(new GridLayoutManager(getContext(), 3));
            commonAdapter = new CommonAdapter<String>(getContext(), R.layout.item_child_img, data.getUrlList()) {


                @Override
                protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, String s, final int position) {
                    if (TextUtils.isEmpty(s)) {
                        Glide.with(mContext)
                                .load(R.drawable.camera_h)
                                .into((ImageView) holder.getView(R.id.item_image));

                        holder.getView(R.id.delete).setVisibility(View.GONE);
                        holder.getView(R.id.item_image).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onImageClickListener.onAdd(getDataPosition());
                            }
                        });
                    } else {
                        Glide.with(mContext)
                                .load(s)
                                .error(R.drawable.error_img)
                                .into((ImageView) holder.getView(R.id.item_image));
                        holder.getView(R.id.delete).setVisibility(View.VISIBLE);
                        holder.getView(R.id.delete).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onImageClickListener.onChildDelete(getDataPosition(),position);
                            }
                        });
                    }


                }
            };

            recycleView.setAdapter(commonAdapter);
            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onImageClickListener.onDeleteClick(getDataPosition());
                }
            });


        }
    }


    public interface OnImageClickListener {

        void onDeleteClick(int data);

        void onAdd(int data);

        void onChildDelete(int position,int childPosition);
    }

    private OnImageClickListener onImageClickListener;

    public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }
}