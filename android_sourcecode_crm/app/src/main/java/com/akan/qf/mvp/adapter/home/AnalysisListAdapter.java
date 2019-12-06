package com.akan.qf.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.SaleDataContrastBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.text.DecimalFormat;
import java.util.List;

import static com.akan.qf.R.id.tvOne;

/**
 * Created by admin on 2018/11/20.
 */

public class AnalysisListAdapter extends RecyclerArrayAdapter<SaleDataContrastBean> {
    public AnalysisListAdapter(Context context, List<SaleDataContrastBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<SaleDataContrastBean> {


        private TextView tvName, tvNum, tvState, tvTime, tvThreeTittle, tvTittleFour;
        private LinearLayout ll;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_dispatch);
            tvName = $(tvOne);
            tvNum = $(R.id.tvTwo);
            tvState = $(R.id.tvThree);
            tvTime = $(R.id.tvFour);
            tvThreeTittle = $(R.id.tvThreeTittle);
            tvTittleFour = $(R.id.tvTittleFour);
            ll = $(R.id.ll);
        }

        @Override
        public void setData(SaleDataContrastBean data) {
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
            tvThreeTittle.setText("任务量");
            tvTittleFour.setText("已完成量");
            if (TextUtils.isEmpty(data.getClass_name())) {
                ll.setVisibility(View.GONE);
            } else {
                ll.setVisibility(View.VISIBLE);
                tvNum.setText(data.getClass_name());
            }
            tvState.setText(addComma(data.getAll_task() + "")+"元");
            tvTime.setText(addComma(data.getThis_year_sum() + "")+"元");


        }
    }

    public static String addComma(String str) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(Double.parseDouble(str));
    }
}
