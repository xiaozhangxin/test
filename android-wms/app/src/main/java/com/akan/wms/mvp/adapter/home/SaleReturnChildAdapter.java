package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.ReturnLineBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class SaleReturnChildAdapter extends RecyclerArrayAdapter<ReturnLineBean> {
    public SaleReturnChildAdapter(Context context, List<ReturnLineBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }



    public class ViewHolder extends BaseViewHolder<ReturnLineBean> {
        private TextView tvName, tvType, tvNum, tvUnit,tvNo;
        private CheckBox ckTop;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_sale_return_child);
            tvName = $(R.id.tvName);
            tvType = $(R.id.tvType);
            tvNum = $(R.id.tvNum);
            tvUnit = $(R.id.tvUnit);
            ckTop = $(R.id.ckTop);
            tvNo = $(R.id.tvNo);

        }

        @Override
        public void setData(ReturnLineBean data) {
            super.setData(data);
            if (data.isCheck()) {
                ckTop.setChecked(true);
            } else {
                ckTop.setChecked(false);
            }
            tvName.setText(data.getItem_name());
            tvType.setText(data.getItem_spec());
            tvNo.setText(data.getReturn_qty()+"");

        }
    }

}
