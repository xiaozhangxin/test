package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.SupplierBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class SupplierListAdapter extends RecyclerArrayAdapter<SupplierBean> {
    public SupplierListAdapter(Context context, List<SupplierBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new SupplierListAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<SupplierBean> {
        private TextView tvNo,tvOne,tvTwo,tvThree;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_supplier_list);
            tvNo = $(R.id.tvNo);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
        }

        @Override
        public void setData(SupplierBean data) {
            super.setData(data);
            tvNo.setText(data.getCode());
            tvOne.setText(data.getName());
        }
    }

}
