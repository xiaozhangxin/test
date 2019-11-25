package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.PressureDropBean;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;


/**
 * Created by admin on 2019/1/18.
 */

public class NumAdapter extends RecyclerArrayAdapter<PressureDropBean> {
    private String type;
    public NumAdapter(Context context, List<PressureDropBean> list,String type) {
        super(context, list);
        this.type=type;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<PressureDropBean> {
        private TextView tvName;
        private ImageView iv_img;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_num);
            tvName = $(R.id.num);
            iv_img = $(R.id.iv_img);

        }

        @Override
        public void setData(PressureDropBean data) {
            super.setData(data);
            if ("1".equals(type)){
                tvName.setTextColor(getContext().getResources().getColor(R.color.white));
            }else {
                tvName.setTextColor(getContext().getResources().getColor(R.color.colorTextG6));
            }
            if (data.isCheck()) {
                Glide.with(getContext()).load(R.drawable.choose_yes).into(iv_img);
            } else {
                Glide.with(getContext()).load(R.drawable.choose_no).into(iv_img);
            }

           // int kgInt = Integer.valueOf(data.getKgAll(), 16);
            tvName.setText(data.getDrop_num() + "kg");

        }
    }

}