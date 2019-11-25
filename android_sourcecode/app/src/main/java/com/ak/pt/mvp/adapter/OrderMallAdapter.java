package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.OrderBean;
import com.ak.pt.util.GlideRoundTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/5/7.
 */

public class OrderMallAdapter extends RecyclerArrayAdapter<OrderBean> {
    public OrderMallAdapter(Context context, List<OrderBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<OrderBean> {


        private TextView shopName, orderState, goodsName, goodsPrice, goodsNorm, goodsNum, totalNum, totalPrice, tvOne, tvTwo, tvThree;
        private ImageView goodsImg;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_order_mall);
            shopName = $(R.id.shopName);
            orderState = $(R.id.orderState);
            goodsImg = $(R.id.goodsImg);
            goodsName = $(R.id.goodsName);
            goodsPrice = $(R.id.goodsPrice);
            goodsNorm = $(R.id.goodsNorm);
            goodsNum = $(R.id.goodsNum);
            totalNum = $(R.id.totalNum);
            totalPrice = $(R.id.totalPrice);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
        }

        @Override
        public void setData(OrderBean data) {
            super.setData(data);
            orderState.setText(data.getOrder_state_show());
            Glide.with(getContext()).load(Constants.BASE_URL+data.getGoods_img())
                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 6))
                    .error(R.drawable.error_img).into(goodsImg);
            goodsName.setText(data.getGoods_name());
            goodsNorm.setText(data.getSpecification_names());
            goodsPrice.setText(data.getSpecification_price());
            totalPrice.setText(data.getOrder_actual_price());
            goodsNum.setText("x"+data.getGoods_num());
            totalNum.setText("共"+data.getGoods_num()+"件商品");
            shopName.setText(data.getOrder_no());

        }
    }

}

