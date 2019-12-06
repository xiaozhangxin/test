package com.akan.qf.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.FinancialBean;
import com.akan.qf.bean.StaffSignUnionBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.List;

/**
 * Created by admin on 2019/2/27.
 */

public class CostStatisticsAdapter extends RecyclerArrayAdapter<FinancialBean> {
    public CostStatisticsAdapter(Context context, List<FinancialBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<FinancialBean> {


        private TextView tvName, tvNum, tvState, tvTime, tvType,tvDepartment;
        private RecyclerView recyclerView;
        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_work_list_new);
            tvName = $(R.id.tvName);
            tvNum = $(R.id.tvNo);
            tvState = $(R.id.tvState);
            tvTime = $(R.id.tvTime);
            tvType = $(R.id.tvType);
            tvDepartment = $(R.id.tvDepartment);
            recyclerView = $(R.id.recyclerView);
        }

        @Override
        public void setData(FinancialBean data) {
            super.setData(data);
            //wait_audit:未审核 auditing:审核中 accept:接受 refuse:拒绝
            tvType.setVisibility(View.GONE);
            tvNum.setText(data.getApply_no());
            tvName.setText(data.getStaff_name());
            tvTime.setText(data.getApply_create_time());
            tvDepartment.setText(data.getApply_department());
            tvState.setTextColor(getContext().getResources().getColor(R.color.audit_two));
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
