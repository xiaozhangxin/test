package com.akan.wms.mvp.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.InforListBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class ScanResultTopAdapter extends RecyclerArrayAdapter<InforListBean> {
    public ScanResultTopAdapter(Context context, List<InforListBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<InforListBean> {
        private TextView tvOne, tvTwo, tvThree, tvFour;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_scan_top);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvFour = $(R.id.tvFour);

        }

        @Override
        public void setData(InforListBean data) {
            super.setData(data);
            tvOne.setText(data.getInfo_name());
            tvTwo.setText(data.getInfo_spec());
            tvThree.setText(data.getNumber()+"");
            tvFour.setText(data.getScan_num()+"");


        }
    }

}
