package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.DeliverGoodsBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class InBuyListAdapter extends RecyclerArrayAdapter<DeliverGoodsBean> {
    public InBuyListAdapter(Context context, List<DeliverGoodsBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<DeliverGoodsBean> {
        private TextView tvNo, tvState, tvOne, tvTwo, tvThree;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_puschase_return);
            tvNo = $(R.id.tvNo);
            tvState = $(R.id.tvState);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);

        }

        @Override
        public void setData(DeliverGoodsBean data) {
            super.setData(data);
            tvNo.setText(data.getDoc_no());
            tvState.setText(data.getStatus_name());
            tvThree.setText(data.getSend_time());
            tvOne.setText("供应商：" + data.getSupplier_name());
            tvTwo.setText("制单人：" + data.getCreator_name());
        }
    }

}
