package com.akan.qf.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.ContractApplyBean;
import com.akan.qf.bean.StaffSignUnionBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.List;

/**
 * Created by admin on 2018/11/28.
 */

public class CustomerContractAdapter extends RecyclerArrayAdapter<ContractApplyBean> {
    public CustomerContractAdapter(Context context, List<ContractApplyBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ContractApplyBean> {
        private TextView tvName, tvNum, tvState, tvTime;
        private RecyclerView recyclerView;
        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_leave_list);
            tvName = $(R.id.tvName);
            tvNum = $(R.id.tvNo);
            tvState = $(R.id.tvState);
            tvTime = $(R.id.tvTime);
            recyclerView = $(R.id.recyclerView);
        }

        @Override
        public void setData(ContractApplyBean data) {
            super.setData(data);
            tvState.setTextColor(getContext().getResources().getColor(R.color.audit_two));
            tvNum.setText(data.getApply_no());
            tvName.setText(data.getStaff_name());
            tvTime.setText(data.getApply_create_time());
            tvState.setText("详情");
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(RecyclerView.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(new CommonAdapter<StaffSignUnionBean>(getContext(),R.layout.sign_detail_name,data.getStaffSignUnionList()) {

                @Override
                protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, StaffSignUnionBean bean, int position) {
                    TextView tvType = holder.getView(R.id.tvType);
                    tvType.setText(bean.getSign_name());
                }

            });
        }
    }

}
