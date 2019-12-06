package com.akan.qf.mvp.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.ProfitBean;
import com.akan.qf.bean.ProfitBean;
import com.akan.qf.mvp.adapter.home.AnalysisListAdapter;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.text.DecimalFormat;
import java.util.List;

import static com.akan.qf.R.id.tvOne;

public class ProfitListAdapter extends RecyclerArrayAdapter<ProfitBean> {
    public ProfitListAdapter(Context context, List<ProfitBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProfitListAdapter.ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<ProfitBean> {


        private TextView tvName, tvNum, tvState, tvTime, tvThreeTittle, tvTittleFour, tvTwoTittle;
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
            tvTwoTittle = $(R.id.tvTwoTittle);
        }

        @Override
        public void setData(ProfitBean data) {
            super.setData(data);
            tvTwoTittle.setText("营业费用");
            tvThreeTittle.setText("净利润");
            tvTittleFour.setText("年份");
            tvName.setText(data.getGroup_name());
            tvNum.setText(data.getBusiness_total_price()+"");
            tvState.setText(data.getNet_price()+"");

            tvTime.setText(data.getYear());
         /*   switch (data.getArea_type()) {
                case "area":
                    tvName.setText(data.geta());
                    break;
                case "sale_center":
                    tvName.setText(data.getRegion());
                    break;
                case "big_area":
                    tvName.setText(data.getBig_area());
                    break;
            }

            if (TextUtils.isEmpty(data.getClass_name())) {
                ll.setVisibility(View.GONE);
            } else {
                ll.setVisibility(View.VISIBLE);
                tvNum.setText(data.getClass_name());
            }*/


        }
    }

    public static String addComma(String str) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(Double.parseDouble(str));
    }
}
