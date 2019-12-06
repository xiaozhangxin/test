package com.akan.qf.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.TextListBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2018/10/29.
 */

public class ReimburseChildAdapter extends RecyclerArrayAdapter<TextListBean> {


    public ReimburseChildAdapter(Context context, List<TextListBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_reimburse_child);
    }

    public class MyImageHolder extends BaseViewHolder<TextListBean> {

        private TextView tvPrice, tvContent, tvSubject, tvNum, tvTop;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            tvPrice = $(R.id.tvPrice);
            tvContent = $(R.id.tvContent);
            tvSubject = $(R.id.tvSubject);
            tvNum = $(R.id.tvNum);
            tvTop = $(R.id.tvTop);
        }

        @Override
        public void setData(final TextListBean data) {
            tvPrice.setText(data.getText_price());
            tvContent.setText(data.getText_info());
            tvSubject.setText(data.getText_subject());
            tvNum.setText(data.getText_number());
            tvTop.setText("费用明细("+(getDataPosition()+1)+")");


        }
    }


}
