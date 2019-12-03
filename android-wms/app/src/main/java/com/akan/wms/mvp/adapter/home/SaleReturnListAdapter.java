package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.MiscRcvBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class SaleReturnListAdapter extends RecyclerArrayAdapter<MiscRcvBean> {
    public SaleReturnListAdapter(Context context, List<MiscRcvBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new SaleReturnListAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<MiscRcvBean> {
        private TextView tvNo,tvState,tvOne,tvTwo,tvThree;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_puschase_return);
            tvNo = $(R.id.tvNo);
            tvState = $(R.id.tvState);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);

        }

        @Override
        public void setData(MiscRcvBean data) {
            super.setData(data);
            if ("3".equals(data.getRcv_status())) {
                tvState.setTextColor(getContext().getResources().getColor(R.color.red));
            } else {
                tvState.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));

            }
            tvNo.setText(data.getRcv_no());
            tvState.setText(data.getRcv_status_show());
            tvThree.setText(data.getCreate_time());
            tvOne.setText("制单人：" + data.getStaff_name());
            tvTwo.setText("配货人：" + data.getDistribution_name());

        }
    }

}
