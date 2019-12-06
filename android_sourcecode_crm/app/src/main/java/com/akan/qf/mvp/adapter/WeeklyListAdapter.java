package com.akan.qf.mvp.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.DailyBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2018/7/4.
 */

public class WeeklyListAdapter extends RecyclerArrayAdapter<DailyBean> {
    public WeeklyListAdapter(Context context, List<DailyBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent,viewType);
    }


    public  class  ViewHolder extends BaseViewHolder<DailyBean>{

        private TextView tvName, tvNum, tvState, tvTime,tvType;

        private ImageView imgState;
        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_list_public);
            tvName = $(R.id.tvName);
            tvNum = $(R.id.tvNo);
            tvState = $(R.id.tvState);
            tvTime = $(R.id.tvTime);
            imgState = $(R.id.imgState);
            tvType = $(R.id.tvType);
        }

        @Override
        public void setData(DailyBean data) {
            super.setData(data);
            switch (data.getDaily_state()) {
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

            tvNum.setText(data.getDaily_no());
            tvName.setText(data.getStaff_name());
            tvTime.setText(data.getCreate_time());
            tvState.setText(data.getDaily_state_show());
            if (TextUtils.isEmpty(data.getStaff_sign_name())){
                tvType.setText("无");
            }else {
                tvType.setText(data.getStaff_sign_name());
            }

        }
    }

}
