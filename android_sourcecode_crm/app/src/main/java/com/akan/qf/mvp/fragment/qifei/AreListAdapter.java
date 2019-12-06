package com.akan.qf.mvp.fragment.qifei;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.qf.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2018/12/3.
 */

public class AreListAdapter<T extends AreaPressureBean> extends RecyclerArrayAdapter<T> {
    public AreListAdapter(Context context, List<T> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<T> {


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
        public void setData(T data) {
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
                bgView.setBackground(null);
                tvName.setBackground(null);
                tvName.setVisibility(View.VISIBLE);
                tvName.setText((getDataPosition() + 1) + "");
                tvNum.setTextColor(getContext().getResources().getColor(R.color.colorTextG3));
                tvState.setTextColor(getContext().getResources().getColor(R.color.colorTextG3));
            }

            tvNum.setText(getArea(data));
            tvState.setText(data.getCount());


        }
    }


    private String getArea(T data){
        if (data instanceof BigAreaBean) {
            return ((BigAreaBean) data).getGroup_name();
        } else {
            return data.getFullArea();
        }
    }

}

