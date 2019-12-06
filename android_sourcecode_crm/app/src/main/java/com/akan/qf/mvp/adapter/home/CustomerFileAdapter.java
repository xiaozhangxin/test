package com.akan.qf.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.ArchivesApplyBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2018/11/28.
 */

public class CustomerFileAdapter extends RecyclerArrayAdapter<ArchivesApplyBean> {
    public CustomerFileAdapter(Context context, List<ArchivesApplyBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ArchivesApplyBean> {
        private TextView tvName, tvNum, tvState, tvTime;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_leave_list);
            tvName = $(R.id.tvName);
            tvNum = $(R.id.tvNo);
            tvState = $(R.id.tvState);
            tvTime = $(R.id.tvTime);

        }

        @Override
        public void setData(ArchivesApplyBean data) {
            super.setData(data);
            tvState.setTextColor(getContext().getResources().getColor(R.color.audit_two));
            tvNum.setText(data.getApply_no());
            tvName.setText(data.getStaff_name());
            tvTime.setText(data.getApply_create_time());
            tvState.setText("详情");

        }
    }

}
