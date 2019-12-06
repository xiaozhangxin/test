package com.akan.qf.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.MonthBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by admin on 2018/11/19.
 */

public class DispatchAdapter extends RecyclerArrayAdapter<MonthBean> {
    public DispatchAdapter(Context context, List<MonthBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<MonthBean> {


        private TextView month, number;
        private LinearLayout bgView;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_dispatch_detail);
            month = $(R.id.month);
            number = $(R.id.number);
            bgView = $(R.id.bgView);

        }

        @Override
        public void setData(MonthBean data) {
            super.setData(data);
            switch (getDataPosition()) {
                case 0:
                case 1:
                    bgView.setBackgroundColor(getContext().getResources().getColor(R.color.colorTextE5));
                    month.setTextColor(getContext().getResources().getColor(R.color.colorTextG3));
                    month.setBackgroundResource(R.color.colorTextE5);
                    break;
                case 2:
                case 3:
                case 6:
                case 7:
                case 10:

                case 11:
                    month.setTextColor(getContext().getResources().getColor(R.color.white));
                    bgView.setBackgroundColor(getContext().getResources().getColor(R.color.white));
                    month.setBackgroundResource(R.drawable.yuan_two);

                    break;
                case 4:
                case 5:
                case 8:
                case 9:
                case 12:
                case 13:
                    month.setTextColor(getContext().getResources().getColor(R.color.white));
                    month.setBackgroundResource(R.drawable.yuan_one);
                    bgView.setBackgroundColor(getContext().getResources().getColor(R.color.colorTextF8));
                    break;
            }
            month.setText(data.getMonth());
            if ("分派目标量(元)".equals(data.getNumber())|"实际完成量(元)".equals(data.getNumber())|"销售预测(元)".equals(data.getNumber())) {
                number.setText(data.getNumber());
            } else {
                number.setText(addComma(data.getNumber()));
            }
        }
    }

    public static String addComma(String str) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(Double.parseDouble(str));
    }
}
