package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.NumOldBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/6/11.
 */

public class NumOldAdapter extends RecyclerArrayAdapter<NumOldBean> {

    public NumOldAdapter(Context context, List<NumOldBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<NumOldBean> {
        private LinearLayout llBg;
        private TextView num;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_num_old);
            llBg = $(R.id.llBg);
            num = $(R.id.num);
        }

        @Override
        public void setData(NumOldBean data) {
            super.setData(data);

            if (data.isCheck()) {
                llBg.setBackgroundResource(R.drawable.shape_old_yuan_one);
                llBg.setScaleX(1.2f);
                llBg.setScaleY(1.2f);

            } else {
                llBg.setBackgroundResource(R.drawable.shape_old_yuan_two);
                llBg.setScaleX(1f);
                llBg.setScaleY(1f);
            }
            num.setText(data.getNum());


        }
    }
}

