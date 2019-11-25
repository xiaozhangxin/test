package com.ak.pt.mvp.adapter.area;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.PeopleBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/5/29.
 */

public class PeopleListAdapter extends RecyclerArrayAdapter<PeopleBean> {
    public PeopleListAdapter(Context context, List<PeopleBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<PeopleBean> {
        private TextView tvName, tvNum, tvState, tvTime;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_leave_list);
            tvName = $(R.id.tvName);
            tvNum = $(R.id.tvNo);
            tvState = $(R.id.tvState);
            tvTime = $(R.id.tvTime);


        }

        @Override
        public void setData(PeopleBean data) {
            super.setData(data);
            switch (data.getAdd_state()) {
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

            tvNum.setText(data.getAdd_no());
            tvName.setText(data.getStaff_name());
            tvTime.setText(data.getCreate_time());
            tvState.setText(data.getAdd_state_show());


        }
    }

}

