package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.WarnTwoBean;
import com.akan.wms.bean.WarnTwoBean.MfcStockBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class NumListtAdapter extends RecyclerArrayAdapter<WarnTwoBean.MfcStockBean> {
    public NumListtAdapter(Context context, List<WarnTwoBean.MfcStockBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new NumListtAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<WarnTwoBean.MfcStockBean> {
        private TextView tvOne, tvTwo;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_num_stock);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);

        }

        @Override
        public void setData(WarnTwoBean.MfcStockBean data) {
            super.setData(data);
            tvOne.setText(data.getMfc_name());
            tvTwo.setText(data.getQtyX());

        }
    }

}
