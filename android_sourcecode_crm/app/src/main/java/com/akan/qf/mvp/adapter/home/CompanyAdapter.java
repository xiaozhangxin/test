package com.akan.qf.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.CustomerInfoBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2018/11/8.
 */

public class CompanyAdapter extends RecyclerArrayAdapter<CustomerInfoBean> {
    public CompanyAdapter(Context context, List<CustomerInfoBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<CustomerInfoBean> {
        private TextView tvName, tvNum, tvState, tvTime;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_leave_list);
            tvName = $(R.id.tvName);
            tvNum = $(R.id.tvNo);
            tvState = $(R.id.tvState);
            tvTime = $(R.id.tvTime);

        }

        @Override
        public void setData(CustomerInfoBean data) {
            super.setData(data);
            switch (data.getInfo_state()) {
                case "wait_audit":
                    tvState.setTextColor(getContext().getResources().getColor(R.color.audit_one));
                    break;
                case "auditing":
                    tvState.setTextColor(getContext().getResources().getColor(R.color.audit_one));
                    break;
                case "accept":
                    tvState.setTextColor(getContext().getResources().getColor(R.color.audit_two));
                    break;
                case "refuse":
                    tvState.setTextColor(getContext().getResources().getColor(R.color.red));
                    break;
                default:
                    tvState.setTextColor(getContext().getResources().getColor(R.color.colorTextG3));
                    break;
            }

            tvNum.setText(data.getCustomer_no());
            tvName.setText(data.getStaff_name());
            tvTime.setText(data.getInfo_create_time());
            tvState.setText(data.getInfo_state_show());

        }
    }

}
