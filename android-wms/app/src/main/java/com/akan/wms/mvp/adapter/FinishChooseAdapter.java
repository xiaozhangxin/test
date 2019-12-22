package com.akan.wms.mvp.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.ProductionOrderBean;
import com.akan.wms.mvp.adapter.home.CompleteChooseAdapter;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class FinishChooseAdapter extends RecyclerArrayAdapter<ProductionOrderBean> {
    public FinishChooseAdapter(Context context, List<ProductionOrderBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new FinishChooseAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ProductionOrderBean> {
        private TextView tvNo, tvName, tvSpe, tvNum, tvChoose, tvCode, tvUnit, tvCar,tvNumType;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_production);
            tvNo = $(R.id.tvNo);
            tvName = $(R.id.tvName);
            tvSpe = $(R.id.tvSpe);
            tvNum = $(R.id.tvNum);
            tvChoose = $(R.id.tvChoose);
            tvCode = $(R.id.tvCode);
            tvUnit = $(R.id.tvUnit);
            tvCar = $(R.id.tvCar);
            tvNumType = $(R.id.tvNumType);

        }

        @Override
        public void setData(ProductionOrderBean data) {
            super.setData(data);
            tvNo.setText(data.getDoc_type_name() + "(" + data.getDoc_no() + ")");
            tvName.setText(data.getItem_name());
            tvSpe.setText(data.getItem_spec());
            tvNum.setText(String.valueOf(data.getWh_qty()));
            tvNumType.setText("(wh_qty)");
            tvCode.setText(String.valueOf(data.getItem_code()));
            tvUnit.setText(data.getItem_sku() + "(单位)");
            tvCar.setText("生产车间：" + data.getU9_department_name() + "");
        }
    }

}

