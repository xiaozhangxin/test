package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.WareHouseBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class DeportBaseAdapter extends RecyclerArrayAdapter<WareHouseBean> {
    public DeportBaseAdapter(Context context, List<WareHouseBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new DeportBaseAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<WareHouseBean> {
        private TextView tvNo,tvOne,tvTwo,tvThree;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_deport_list);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
        }

        @Override
        public void setData(WareHouseBean data) {
            super.setData(data);
            tvOne.setText(data.getWarehouse_code());
            tvThree.setText(data.getWarehouse_name());
        }
    }

}
