package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.SaleShipBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class OutSaleListAdapter extends RecyclerArrayAdapter<SaleShipBean> {
    public OutSaleListAdapter(Context context, List<SaleShipBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<SaleShipBean> {
        private TextView tvNo, tvState, tvOne, tvTwo, tvThree;
        private ImageView ivInvalid;
        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_puschase_return);
            tvNo = $(R.id.tvNo);
            tvState = $(R.id.tvState);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            ivInvalid = $(R.id.ivInvalid);
        }

        @Override
        public void setData(SaleShipBean data) {
            super.setData(data);
            if ("1".equals(data.getIs_valid())) {
                ivInvalid.setVisibility(View.VISIBLE);
            } else {
                ivInvalid.setVisibility(View.GONE);
            }
            tvState.setText(data.getStatus_show());
            tvNo.setText(data.getDoc_no());
            tvThree.setText(data.getCreate_time());
            tvOne.setText("制单人：" + data.getCreate_name());
            tvTwo.setText("单据类型：" + data.getDoc_type_name());

        }
    }

}
