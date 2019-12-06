package com.akan.qf.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.AnalysisBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by admin on 2018/11/20.
 */

public class AnalysisAdapter extends RecyclerArrayAdapter<AnalysisBean> {
    public AnalysisAdapter(Context context, List<AnalysisBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<AnalysisBean> {


        private TextView one, two, three, four;
        private LinearLayout bgView;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_analysis_detail);
            one = $(R.id.one);
            two = $(R.id.two);
            three = $(R.id.three);
            four = $(R.id.four);
            bgView = $(R.id.bgView);


        }

        @Override
        public void setData(AnalysisBean data) {
            super.setData(data);
            switch (getDataPosition()) {
                case 0:
                    bgView.setBackgroundColor(getContext().getResources().getColor(R.color.colorTextE5));
                    one.setTextColor(getContext().getResources().getColor(R.color.colorTextG3));
                    one.setBackgroundResource(R.color.colorTextE5);
                    break;
                case 1:
                case 3:
                case 5:
                case 7:
                case 9:
                case 11:
                    one.setTextColor(getContext().getResources().getColor(R.color.white));
                    bgView.setBackgroundColor(getContext().getResources().getColor(R.color.white));
                    one.setBackgroundResource(R.drawable.yuan_two);

                    break;
                case 2:
                case 4:
                case 6:
                case 8:
                case 10:
                case 12:
                    one.setTextColor(getContext().getResources().getColor(R.color.white));
                    one.setBackgroundResource(R.drawable.yuan_one);
                    bgView.setBackgroundColor(getContext().getResources().getColor(R.color.colorTextF8));
                    break;
            }
            one.setText(data.getMonth());
            if ("销售量(元)".equals(data.getSale())) {
                two.setText(data.getSale());
            } else {
                two.setText(addComma(data.getSale()));
            }

            if ("NaN%".equals(data.getComplete())) {
                three.setText("0%");
            } else if ("Infinity%".equals(data.getComplete())) {
                three.setText("100%");
            } else {
                three.setText(data.getComplete());
            }

            four.setText(data.getUp());


        }
    }

    public static String addComma(String str) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(Double.parseDouble(str));
    }
}
