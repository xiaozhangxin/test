package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.mvp.fragment.statistics.PressurePageBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/1/17.
 */

public class OrderAcceptAdapter extends RecyclerArrayAdapter<PressurePageBean> {
    public OrderAcceptAdapter(Context context, List<PressurePageBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<PressurePageBean> {


        private TextView tvName, tvNum, tvState, tvDetail, tvNo, tvMake;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_order_accept);
            tvName = $(R.id.tvOne);
            tvNum = $(R.id.tvTwo);
            tvState = $(R.id.tvThree);
            tvDetail = $(R.id.tvDetail);
            tvNo = $(R.id.tvNo);
            tvMake = $(R.id.tvMake);
        }

        @Override
        public void setData(PressurePageBean data) {
            super.setData(data);

            switch (data.getFlow_state()) {
                case "order"://未发出
                    tvDetail.setTextColor(getContext().getResources().getColor(R.color.audit_one));
                    break;
                case "accept": //待接单
                    tvDetail.setTextColor(getContext().getResources().getColor(R.color.audit_one));
                    break;
                case "plan"://已接单
                    tvDetail.setTextColor(getContext().getResources().getColor(R.color.audit_one));
                    break;
                case "tested"://已试压
                    tvDetail.setTextColor(getContext().getResources().getColor(R.color.audit_one));
                    break;
                case "success"://待审核
                    tvDetail.setTextColor(getContext().getResources().getColor(R.color.audit_one));
                    break;
                case "audit"://已完成
                    tvDetail.setTextColor(getContext().getResources().getColor(R.color.audit_two));
                    break;
                case "result"://已拒绝
                    tvDetail.setTextColor(getContext().getResources().getColor(R.color.audit_three));
                    break;
                case "delete"://已删除
                    tvDetail.setTextColor(getContext().getResources().getColor(R.color.audit_three));
                    break;

                case "cancel"://已作废
                    tvDetail.setTextColor(getContext().getResources().getColor(R.color.audit_three));
                    break;

            }
            tvName.setText(data.getAddress());
            tvNum.setText(data.getTrn_date());
            tvState.setText(data.getPhoto_count());
            tvDetail.setText(data.getFlow_state_show());
            tvNo.setText("单据编号 " + data.getDoc_no());
            tvMake.setText("制单人 " + data.getStaff_name());


        }
    }

}

