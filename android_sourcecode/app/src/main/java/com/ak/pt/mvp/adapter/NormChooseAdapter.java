package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.GoodsSpecificationBeans;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/5/6.
 */

public class NormChooseAdapter extends RecyclerArrayAdapter<GoodsSpecificationBeans> {
    public NormChooseAdapter(Context context, List<GoodsSpecificationBeans> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<GoodsSpecificationBeans> {


        private TextView tvName;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_norm);
            tvName = $(R.id.tvName);
        }

        @Override
        public void setData(final GoodsSpecificationBeans data) {
            super.setData(data);
            if (data.getSpecification_stock() == 0) {
                tvName.setBackgroundResource(R.drawable.setbar_gray);
                tvName.setTextColor(getContext().getResources().getColor(R.color.colorTextG9));
                tvName.setEnabled(false);
            }else {
                if (data.isCheck()) {
                    tvName.setBackgroundResource(R.drawable.setbar_cp5);
                    tvName.setTextColor(getContext().getResources().getColor(R.color.white));
                } else {
                    tvName.setBackgroundResource(R.drawable.setbar_g_line3_black);
                    tvName.setTextColor(getContext().getResources().getColor(R.color.colorTextG6));
                }
            }


            tvName.setText(data.getSpecification_names());

        }
    }


    private SelectListener selectListener;

    public interface SelectListener {
        void OnSelectListener(int position);
    }

    public void setSelectListener(SelectListener selectListener) {
        this.selectListener = selectListener;
    }
}
