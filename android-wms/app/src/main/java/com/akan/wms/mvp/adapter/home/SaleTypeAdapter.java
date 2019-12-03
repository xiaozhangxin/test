package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.SaleShipTypeBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class SaleTypeAdapter extends RecyclerArrayAdapter<SaleShipTypeBean> {
    public SaleTypeAdapter(Context context, List<SaleShipTypeBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new SaleTypeAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<SaleShipTypeBean> {
        private TextView tvName;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_supplier);
            tvName = $(R.id.tvName);
        }

        @Override
        public void setData(SaleShipTypeBean data) {
            super.setData(data);
            tvName.setText((getDataPosition() + 1) + "." + data.getName());
        }
    }

}
