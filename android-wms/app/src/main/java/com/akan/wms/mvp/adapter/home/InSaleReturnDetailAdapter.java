package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.RevLinesBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class InSaleReturnDetailAdapter extends RecyclerArrayAdapter<RevLinesBean> {
    public InSaleReturnDetailAdapter(Context context, List<RevLinesBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new InSaleReturnDetailAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<RevLinesBean> {
        private TextView tvOne, tvTwo, tvThree, tvFour;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_in_sale_return);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvFour = $(R.id.tvFour);
        }

        @Override
        public void setData(RevLinesBean data) {
            super.setData(data);
            if (TextUtils.isEmpty(data.getItem_spec())) {
                tvOne.setText(data.getItem_name());
            } else {
                tvOne.setText(data.getItem_name() + "/" + data.getItem_spec());
            }
            tvTwo.setText(data.getArrive_qty() + "");
            tvThree.setText(data.getArrive_qty() + "");
            tvFour.setText(data.getWh_name());
        }
    }


}
