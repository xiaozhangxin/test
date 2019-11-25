package com.ak.pt.mvp.adapter.water;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.WarrantyBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/5/23.
 */

public class WarrantyListAdapter extends RecyclerArrayAdapter<WarrantyBean> {
    public WarrantyListAdapter(Context context, List<WarrantyBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent,viewType);
    }

    public  class  ViewHolder extends BaseViewHolder<WarrantyBean>{
        private TextView tvName,tvNum,tvState,tvTime;
        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_filter_replace);
            tvName = $(R.id.tvName);
            tvNum = $(R.id.tvNo);
            tvState = $(R.id.tvState);
            tvTime = $(R.id.tvTime);

        }

        @Override
        public void setData(WarrantyBean data) {
            super.setData(data);

            tvNum.setText(data.getCard_no());
            tvName.setText(data.getStaff_name());
            tvTime.setText(data.getCreate_time());
            // tvState.setText(data.getDaily_state_show());

        }
    }

}

