package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.SaleReturnBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class SaleReturnAdapter extends RecyclerArrayAdapter<SaleReturnBean> {
    public SaleReturnAdapter(Context context, List<SaleReturnBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<SaleReturnBean> {
        private TextView tvtittle,tvNo;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_stock);
            tvtittle = $(R.id.tvtittle);
            tvNo = $(R.id.tvNo);

        }

        @Override
        public void setData(SaleReturnBean data) {
            super.setData(data);
            tvNo.setText(data.getDoc_no());
            tvtittle.setText(data.getDoc_type_name());
        }
    }

}
