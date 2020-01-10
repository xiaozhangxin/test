package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.PurchaseLinesBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class StockChildAdapter extends RecyclerArrayAdapter<PurchaseLinesBean> {
    private String childType;

    public StockChildAdapter(Context context, List<PurchaseLinesBean> list, String childType) {
        super(context, list);
        this.childType = childType;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<PurchaseLinesBean> {
        private TextView tvName, tvType, tvNo;
        private CheckBox ckTop;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_stock_child);
            tvName = $(R.id.tvName);
            tvType = $(R.id.tvType);
            tvNo = $(R.id.tvNo);
            ckTop = $(R.id.ckTop);

        }

        @Override
        public void setData(PurchaseLinesBean data) {
            super.setData(data);
            if ("home".equals(childType)) {
                ckTop.setVisibility(View.GONE);
            }
            if (data.isCheck()) {
                ckTop.setChecked(true);
            } else {
                ckTop.setChecked(false);
            }
            tvName.setText(data.getItem_name());
            tvType.setText(data.getItem_spec());
            tvNo.setText(data.getPerOfOvertopQty() + "");

        }
    }

}
