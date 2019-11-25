package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.GoodsBean;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/5/5.
 */

public class MallListAdapter extends RecyclerArrayAdapter<GoodsBean> {

    public static enum ITEM_TYPE {
        ITEM_TYPE_Theme,
        ITEM_TYPE_Video
    }
    public MallListAdapter(Context context, List<GoodsBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<GoodsBean> {
        private TextView tvName, tvIntegral, tvNum;
        private ImageView ivImg, ivImgTop;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_mall_list);
            ivImg = $(R.id.ivImg);
            tvIntegral = $(R.id.tvIntegral);
            tvName = $(R.id.tvName);
            tvNum = $(R.id.tvNum);
            ivImgTop = $(R.id.ivImgTop);

        }

        @Override
        public void setData(GoodsBean data) {
            super.setData(data);
            if (data.getGoods_stock() == 0) {
                ivImgTop.setVisibility(View.VISIBLE);
            } else {
                ivImgTop.setVisibility(View.GONE);
            }

            tvName.setText(data.getGoods_name());
            Glide.with(getContext())
                    .load(Constants.BASE_URL + data.getGoods_img())
                    .placeholder(R.drawable.error_img)
                    .error(R.drawable.error_img).into(ivImg);
            tvIntegral.setText(data.getGoods_now_price());
            tvNum.setText(data.getGoods_stock() + "");


        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == ITEM_TYPE.ITEM_TYPE_Theme.ordinal() ? 1 : gridManager.getSpanCount();
                }
            });
        }
    }
}
