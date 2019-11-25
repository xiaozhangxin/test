package com.ak.pt.mvp.adapter.water;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.FilterReplaceBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/5/22.
 */

public class FilterReplaceListAdapter extends RecyclerArrayAdapter<FilterReplaceBean> {
    public FilterReplaceListAdapter(Context context, List<FilterReplaceBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent,viewType);
    }

    public  class  ViewHolder extends BaseViewHolder<FilterReplaceBean>{
        private TextView tvName,tvNum,tvState,tvTime;
        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_filter_replace);
            tvName = $(R.id.tvName);
            tvNum = $(R.id.tvNo);
            tvState = $(R.id.tvState);
            tvTime = $(R.id.tvTime);

        }

        @Override
        public void setData(FilterReplaceBean data) {
            super.setData(data);

            tvNum.setText(data.getFilter_no());
            tvName.setText(data.getStaff_name());
            tvTime.setText(data.getCreate_time());
           // tvState.setText(data.getDaily_state_show());

        }
    }

}

