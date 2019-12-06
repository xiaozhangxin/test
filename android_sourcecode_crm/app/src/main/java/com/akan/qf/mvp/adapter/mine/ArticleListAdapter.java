package com.akan.qf.mvp.adapter.mine;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.ContributeBean;
import com.akan.qf.bean.StaffSignUnionBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.List;

/**
 * Created by admin on 2018/11/26.
 */

public class ArticleListAdapter extends RecyclerArrayAdapter<ContributeBean> {
    private String type;

    public ArticleListAdapter(Context context, List<ContributeBean> list, String type) {
        super(context, list);
        this.type = type;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ContributeBean> {
        private TextView tvTime, tvTittle, tvState, tvName;
        private RecyclerView recyclerView;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_article);
            tvTime = $(R.id.tvTime);
            tvTittle = $(R.id.tvTittle);
            tvState = $(R.id.tvState);
            tvName = $(R.id.tvName);
            recyclerView = $(R.id.recyclerView);
        }

        @Override
        public void setData(ContributeBean data) {
            super.setData(data);
            tvName.setText(data.getStaff_name());
            tvTime.setText(data.getCreate_time());
            tvTittle.setText(data.getTitle());
            switch (data.getState()) {
                case "accept":
                    tvState.setText("已审阅");
                    tvState.setTextColor(getContext().getResources().getColor(R.color.audit_two));
                    break;
                case "wait_audit":
                    tvState.setText("未审阅");
                    tvState.setTextColor(getContext().getResources().getColor(R.color.audit_one));
                    break;
            }
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
