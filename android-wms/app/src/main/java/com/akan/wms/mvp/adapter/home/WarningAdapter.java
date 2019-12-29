package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.WarnTwoBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class WarningAdapter extends RecyclerArrayAdapter<WarnTwoBean> {
    public WarningAdapter(Context context, List<WarnTwoBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<WarnTwoBean> {
        private TextView tvOne, tvTwo, tvThree, tvFour, tvSix, tvSeven, tvEight;
        private LinearLayout llbg;
        private SeekBar tvFive;
        private View line;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_warining);

            llbg = $(R.id.llbg);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvFour = $(R.id.tvFour);
            tvFive = $(R.id.tvFive);
            tvSix = $(R.id.tvSix);
            tvSeven = $(R.id.tvSeven);
            tvEight = $(R.id.tvEight);
            line = $(R.id.line);
            //禁止seekBar滑动点击
            tvFive.setClickable(false);
            tvFive.setEnabled(false);
            tvFive.setSelected(false);
            tvFive.setFocusable(false);

        }

        @Override
        public void setData(WarnTwoBean data) {
            super.setData(data);
            if (0 == getDataPosition()) {
                line.setVisibility(View.GONE);
            } else {
                line.setVisibility(View.VISIBLE);
            }

            //状态 1过低 2过高
            switch (data.getStatus()) {
                case "1":
                    if (data.getQty()==0){
                        tvFive.setProgress(0);
                    }else {
                        tvFive.setProgress(30);
                    }

                    tvSeven.setBackgroundResource(R.drawable.setbar_home_three);
                    tvEight.setBackgroundResource(R.drawable.setbar_home_two);
                    tvFive.setProgressDrawable(getContext().getResources().getDrawable(R.drawable.setbar_seekbar_two));
                    break;
                case "2":
                    tvFive.setProgress(100);
                    tvEight.setBackgroundResource(R.drawable.setbar_home_four);
                    tvFive.setProgressDrawable(getContext().getResources().getDrawable(R.drawable.setbar_seekbar_one));
                    break;
            }

            //呆滞状态 0正常 1过低 2过高
/*            switch (data.getRemain_status()) {
                case "0":
                    tvSeven.setVisibility(View.GONE);
                    break;
                case "1":
                case "2":
                    tvSeven.setVisibility(View.VISIBLE);
                    break;

            }*/

            tvOne.setText(data.getWh_name());
            tvTwo.setText(data.getItem_name());
            tvThree.setText(data.getItem_spec());
            tvFour.setText(data.getQty() + "/" + data.getCeiling_qty());
/*            int i = new Double(data.getQty()).intValue();
            tvFive.setProgress( i / data.getCeiling_qty());*/
            tvSix.setText(data.getWarning_time());
            tvEight.setText(data.getStatus_name());


        }
    }

}
