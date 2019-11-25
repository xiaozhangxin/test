package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.StateBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/4/16.
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
                tvName.setBackground(getContext().getResources().getDrawable(R.drawable.setbar_cp15));
                tvName.setTextColor(getContext().getResources().getColor(R.color.white));
            }else {
                tvName.setBackground(getContext().getResources().getDrawable(R.drawable.setbar_filter_gray_two));
                tvName.setTextColor(getContext().getResources().getColor(R.color.colorTextG3));
            }
        }
    }

}
