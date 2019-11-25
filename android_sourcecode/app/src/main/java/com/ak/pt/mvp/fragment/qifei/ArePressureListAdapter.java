package com.ak.pt.mvp.fragment.qifei;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.LayoutRes;

import com.ak.pt.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2018/11/23.
 */

public class ArePressureListAdapter extends RecyclerArrayAdapter<PressurePageBean> {
    public ArePressureListAdapter(Context context, List<PressurePageBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<PressurePageBean> {


        private TextView tvName, tvNum, tvState;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_area_pressure);
            tvName = $(R.id.tvOne);
            tvNum = $(R.id.tvTwo);
            tvState = $(R.id.tvThree);
        }

        @Override
        public void setData(PressurePageBean data) {
            super.setData(data);
            tvName.setText( data.getAddress());
            tvNum.setText( data.getTrn_date());
            tvState.setText( data.getPhoto_count()+"张");



        }
    }

}
