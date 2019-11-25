package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.IntegralBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/3/26.
 */

public class IntegralAdapter extends RecyclerArrayAdapter<IntegralBean> {
    public IntegralAdapter(Context context, List<IntegralBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<IntegralBean> {


        private TextView tvTittle, tvTime, tvIntegral;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_integral);
            tvTittle = $(R.id.tvTittle);
            tvTime = $(R.id.tvTime);
            tvIntegral = $(R.id.tvIntegral);

        }

        @Override
        public void setData(IntegralBean data) {
            super.setData(data);
            if ("积分兑换".equals(data.getIntegral_type())) {
                tvIntegral.setTextColor(getContext().getResources().getColor(R.color.img_color));
                tvIntegral.setText(data.getIntegral_score());
            } else {
                tvIntegral.setTextColor(getContext().getResources().getColor(R.color.colorPrimaryNew));
                tvIntegral.setText("+"+data.getIntegral_score());
            }
            tvTittle.setText(data.getIntegral_type());
            tvTime.setText(data.getIntegral_create_time());



        }
    }

}