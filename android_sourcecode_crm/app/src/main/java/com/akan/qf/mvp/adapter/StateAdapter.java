package com.akan.qf.mvp.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.StateBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/4/1.
 */

public class StateAdapter extends RecyclerArrayAdapter<StateBean> {
    public StateAdapter(Context context, List<StateBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<StateBean> {


        private TextView tvName;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_post);
            tvName = $(R.id.tvName);
        }

        @Override
        public void setData(StateBean data) {
            super.setData(data);
            tvName.setText(data.getName());
            if (data.isCheck()){
                tvName.setBackground(getContext().getResources().getDrawable(R.drawable.setbar_filter_check));
                tvName.setTextColor(getContext().getResources().getColor(R.color.white));
            }else {
                tvName.setBackground(getContext().getResources().getDrawable(R.drawable.setbar_filter_gray_two));
                tvName.setTextColor(getContext().getResources().getColor(R.color.colorTextG6));
            }
        }
    }

}
