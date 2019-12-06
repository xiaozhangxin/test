package com.akan.qf.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.NewApplyBean;
import com.akan.qf.bean.StaffSignUnionBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.List;

/**
 * Created by admin on 2018/11/13.
 */

public class ProblemListAdapter extends RecyclerArrayAdapter<NewApplyBean> {
    public ProblemListAdapter(Context context, List<NewApplyBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<NewApplyBean> {
        private TextView tvName, tvNum, tvState, tvTime,tvType;

        private ImageView imgState;
        private RecyclerView recyclerView;
        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_list_public);
            tvName = $(R.id.tvName);
            tvNum = $(R.id.tvNo);
            tvState = $(R.id.tvState);
            tvTime = $(R.id.tvTime);
            imgState = $(R.id.imgState);
            tvType = $(R.id.tvType);
            recyclerView = $(R.id.recyclerView);
        }

        @Override
        public void setData(NewApplyBean data) {
            super.setData(data);
            //wait_audit:未审核 auditing:审核中 accept:接受 refuse:拒绝
            switch (data.getApply_state()) {
                case "wait_audit":
                    tvState.setTextColor(getContext().getResources().getColor(R.color.audit_one));
                    tvState.setBackgroundResource(R.drawable.public_state_one);
                    imgState.setImageResource(R.drawable.list_three_audit);
                    break;
                case "auditing":
                    tvState.setTextColor(getContext().getResources().getColor(R.color.audit_one));
                    tvState.setBackgroundResource(R.drawable.public_state_one);
                    imgState.setImageResource(R.drawable.list_three_audit);
                    break;
                case "accept":
                    tvState.setTextColor(getContext().getResources().getColor(R.color.audit_two));
                    tvState.setBackgroundResource(R.drawable.public_state_two);
                    imgState.setImageResource(R.drawable.list_three_point);
                    break;
                case "refuse":
                    tvState.setTextColor(getContext().getResources().getColor(R.color.red));
                    tvState.setBackgroundResource(R.drawable.public_state_three);
                    imgState.setImageResource(R.drawable.list_three_point);
                    break;
                default:
                    tvState.setTextColor(getContext().getResources().getColor(R.color.colorTextG3));
                    break;
            }

            tvNum.setText(data.getApply_no());
            tvName.setText(data.getStaff_name());
            tvTime.setText(data.getApply_create_time());
            tvState.setText(data.getApply_state_show());
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
