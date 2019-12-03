package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.PlanLineBeanDetail;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class OutSaleDetailAdapter extends RecyclerArrayAdapter<PlanLineBeanDetail> {
    public OutSaleDetailAdapter(Context context, List<PlanLineBeanDetail> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<PlanLineBeanDetail> {
        private TextView tvOne, tvTwo, tvThree, tvFour;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_out_sale_detail);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvFour = $(R.id.tvFour);
        }

        @Override
        public void setData(PlanLineBeanDetail bean) {
            super.setData(bean);
            if (TextUtils.isEmpty(bean.getItem_spec())) {
                tvOne.setText(bean.getItem_name());
            } else {
                tvOne.setText(bean.getItem_name() + "/" + bean.getItem_spec());
            }
            tvTwo.setText(bean.getPlan_qty() + "");
            tvThree.setText(bean.getQty() + "");
            tvFour.setText(bean.getWh_name());


        }
    }


}
