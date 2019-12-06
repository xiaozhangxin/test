package com.akan.qf.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.TaskBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by admin on 2018/11/16.
 */

public class DispatchListAdapter extends RecyclerArrayAdapter<TaskBean> {
    public DispatchListAdapter(Context context, List<TaskBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<TaskBean> {


        private TextView tvName, tvNum, tvState, tvTime,tvbB;
        LinearLayout ll;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_dispatch);
            tvName = $(R.id.tvOne);
            tvNum = $(R.id.tvTwo);
            tvState = $(R.id.tvThree);
            tvTime = $(R.id.tvFour);
            tvbB = $(R.id.tvbB);
            ll = $(R.id.ll);
        }

        @Override
        public void setData(TaskBean data) {
            super.setData(data);
            switch (data.getTask_type()){
                case "department":
                case "":
                    tvbB.setText("部门");
                    tvName.setText( data.getDepartment_name());
                    break;
                case "staff":
                    tvbB.setText("个人");
                    tvName.setText( data.getStaff_name());
                    break;

            }

            if (TextUtils.isEmpty(data.getClass_name())){
                ll.setVisibility(View.GONE);
            }else {
                ll.setVisibility(View.VISIBLE);
                tvNum.setText( data.getClass_name());
            }


            DecimalFormat df = new DecimalFormat("#0");
            tvState.setText(addComma(data.getAll_sale()+"")+"元");

            tvTime.setText( data.getYear());



        }
    }
    public static String addComma(String str) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(Double.parseDouble(str));
    }
}
