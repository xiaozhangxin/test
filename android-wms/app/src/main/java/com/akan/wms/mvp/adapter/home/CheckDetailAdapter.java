package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.InfoBeanList;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class CheckDetailAdapter extends RecyclerArrayAdapter<InfoBeanList> {
    public CheckDetailAdapter(Context context, List<InfoBeanList> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CheckDetailAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<InfoBeanList> {
        private TextView tvOne, tvTwo, tvThree;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_check_detail);

            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
        }

        @Override
        public void setData(InfoBeanList data) {
            super.setData(data);
            tvOne.setText(data.getItem_name());
            if (TextUtils.isEmpty(data.getItem_spec())) {
                tvTwo.setText("暂无");
            } else {
                tvTwo.setText(data.getItem_spec());
            }
            switch (data.getInfo_status()) {
                case "low":
                    tvThree.setTextColor(getContext().getResources().getColor(R.color.color_yellow));
                    break;
                case "height":
                    tvThree.setTextColor(getContext().getResources().getColor(R.color.red));
                    break;
                case "equals":
                    tvThree.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                    break;
            }
            tvThree.setText(data.getInfo_num());


        }
    }


}