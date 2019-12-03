package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.SaleRcvBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class InSaleReturnListAdapter extends RecyclerArrayAdapter<SaleRcvBean> {
    public InSaleReturnListAdapter(Context context, List<SaleRcvBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<SaleRcvBean> {
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
        public void setData(SaleRcvBean data) {
            super.setData(data);
            tvState.setText(data.getStatus_show());
            tvNo.setText(data.getDoc_no());
            tvThree.setText(data.getBusiness_date());
            tvOne.setText("退货类型：" + data.getDoc_type_name());
            tvTwo.setText("退货客户：" + data.getCustomer_name());

        }
    }

}
