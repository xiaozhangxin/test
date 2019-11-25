package com.ak.pt.mvp.adapter.water;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.TwoChooseChildBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/5/23.
 */

public class TwoChooseChildAdapter extends RecyclerArrayAdapter<TwoChooseChildBean> {
    public TwoChooseChildAdapter(Context context, List<TwoChooseChildBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<TwoChooseChildBean> {


        private TextView tvName;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_two_choose);
            tvName = $(R.id.tvName);

        }

        @Override
        public void setData(TwoChooseChildBean data) {
            super.setData(data);
            if (data.isCheck()){
                tvName.setBackgroundResource(R.color.colorPrimaryLight);
                tvName.setTextColor(getContext().getResources().getColor(R.color.white));
            }else {
                tvName.setBackgroundResource(R.color.colorActivityBackground);
                tvName.setTextColor(getContext().getResources().getColor(R.color.colorTextG3));
            }
            tvName.setText(data.getName());
        }
    }

}
