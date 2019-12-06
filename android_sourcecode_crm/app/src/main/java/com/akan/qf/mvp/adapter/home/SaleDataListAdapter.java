package com.akan.qf.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.SaleDataBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by admin on 2018/11/20.
 */

public class SaleDataListAdapter extends RecyclerArrayAdapter<SaleDataBean> {
    public SaleDataListAdapter(Context context, List<SaleDataBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<SaleDataBean> {


        private TextView tvName, tvNum, tvState, tvTime, tvThreeTittle;

        private LinearLayout ll;
        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_dispatch);
            tvName = $(R.id.tvOne);
            tvNum = $(R.id.tvTwo);
            tvState = $(R.id.tvThree);
            tvTime = $(R.id.tvFour);
            tvThreeTittle = $(R.id.tvThreeTittle);
            ll = $(R.id.ll);
        }

        @Override
        public void setData(SaleDataBean data) {
            super.setData(data);
            switch (data.getSearch_type()) {
                case "area":
                case "area_all":
                    tvName.setText(data.getArea());
                    break;
                case "region":
                case "region_all":
                    tvName.setText(data.getRegion());
                    break;
                case "big_area":
                case "big_area_all":
                    tvName.setText(data.getBig_area());
                    break;
            }
            tvThreeTittle.setText("销售量");
            if (TextUtils.isEmpty(data.getClass_name())) {
                ll.setVisibility(View.GONE);
            } else {
                ll.setVisibility(View.VISIBLE);
                tvNum.setText(data.getClass_name());
            }
            tvState.setText(addComma(data.getSum_money() + "")+"元");
            tvTime.setText(data.getYear()+"年");


        }
    }

    public static String addComma(String str) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(Double.parseDouble(str));
    }
}
