package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.WarnTwoBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class StockFindAdapter extends RecyclerArrayAdapter<WarnTwoBean> {
    public StockFindAdapter(Context context, List<WarnTwoBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new StockFindAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<WarnTwoBean> {
        private TextView tvOne, tvTwo, tvThree, tvFour, tvFive,tvSix;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_stock_find);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvFour = $(R.id.tvFour);
            tvFive = $(R.id.tvFive);
            tvSix = $(R.id.tvSix);

        }

        @Override
        public void setData(WarnTwoBean data) {
            super.setData(data);
            switch (data.getStatus()) {
                case "0":
                    tvTwo.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                    break;
                case "1":
                    tvTwo.setTextColor(getContext().getResources().getColor(R.color.color_yellow));

                    break;
                case "2":
                    tvTwo.setTextColor(getContext().getResources().getColor(R.color.red));
                    break;
            }
            tvOne.setText(data.getItem_code());
            tvTwo.setText(data.getStatus_name());
            tvThree.setText(data.getItem_name());
            tvFour.setText(data.getItem_spec());
            tvFive.setText(data.getQty()+"");
            tvSix.setText(data.getWh_name());
        }
    }

}
