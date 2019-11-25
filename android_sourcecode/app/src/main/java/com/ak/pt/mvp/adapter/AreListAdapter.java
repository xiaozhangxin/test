package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AreaPressureBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2018/12/3.
 */

public class AreListAdapter extends RecyclerArrayAdapter<AreaPressureBean> {
    public AreListAdapter(Context context, List<AreaPressureBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<AreaPressureBean> {
        private TextView tvName, tvNum, tvState;
        private ConstraintLayout bgView;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_area);
            tvName = $(R.id.one);
            tvNum = $(R.id.two);
            tvState = $(R.id.three);
            bgView = $(R.id.bgView);
        }

        @Override
        public void setData(AreaPressureBean data) {
            super.setData(data);
            if (0 == getDataPosition()) {
                bgView.setBackgroundResource(R.drawable.rank_one);
                tvName.setBackgroundResource(R.drawable.num_one);
                tvName.setText("");
                tvNum.setTextColor(getContext().getResources().getColor(R.color.white));
                tvState.setTextColor(getContext().getResources().getColor(R.color.white));
            } else if (1 == getDataPosition()) {
                bgView.setBackgroundResource(R.drawable.rank_two);
                tvName.setBackgroundResource(R.drawable.num_two);
                tvName.setText("");
                tvNum.setTextColor(getContext().getResources().getColor(R.color.white));
                tvState.setTextColor(getContext().getResources().getColor(R.color.white));
            } else if (2 == getDataPosition()) {
                bgView.setBackgroundResource(R.drawable.rank_three);
                tvName.setBackgroundResource(R.drawable.num_three);
                tvName.setText("");
                tvNum.setTextColor(getContext().getResources().getColor(R.color.white));
                tvState.setTextColor(getContext().getResources().getColor(R.color.white));
            } else {
                bgView.setBackground(null);tvName.setBackground(null);
                tvName.setVisibility(View.VISIBLE);
                tvName.setText((getDataPosition() + 1) + "");
                tvNum.setTextColor(getContext().getResources().getColor(R.color.colorTextG3));
                tvState.setTextColor(getContext().getResources().getColor(R.color.colorTextG3));
            }
            tvNum.setText(data.getGroup_name());
            tvState.setText(data.getCount());
        }
    }

}

