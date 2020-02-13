package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.PurchaseBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class StockistAdapter extends RecyclerArrayAdapter<PurchaseBean> {
    public StockistAdapter(Context context, List<PurchaseBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<PurchaseBean> {
        private TextView tvtittle,tvNo;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_stock);
            tvtittle = $(R.id.tvtittle);
            tvNo = $(R.id.tvNo);

        }

        @Override
        public void setData(PurchaseBean data) {
            super.setData(data);
            tvtittle.setText(data.getDoc_no());
            tvNo.setText("供应商："+data.getSupplier_name());

        }
    }

}
