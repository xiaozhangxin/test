package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.FakeBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/3/26.
 */

public class FakeAdapter extends RecyclerArrayAdapter<FakeBean> {
    public FakeAdapter(Context context, List<FakeBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<FakeBean> {


        private TextView tvName, tvNo, tvTime,tvResult,tvDepartment;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_fake);
            tvName = $(R.id.tvName);
            tvNo = $(R.id.tvNo);
            tvTime = $(R.id.tvTime);
            tvResult = $(R.id.tvResult);
            tvDepartment = $(R.id.tvDepartment);

        }

        @Override
        public void setData(FakeBean data) {
            super.setData(data);
            tvName.setText(data.getFake_name());
            tvNo.setText(data.getFake_no());
            tvTime.setText(data.getFake_create_time());
            String is_true = data.getIs_true();
            if ("正确".equals(is_true)){
                tvResult.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
            }else {
                tvResult.setTextColor(getContext().getResources().getColor(R.color.audit_three));
            }
            tvResult.setText(is_true);
            tvDepartment.setText(data.getFake_department());
            




        }
    }

}