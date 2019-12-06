package com.akan.qf.mvp.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.qf.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2018/7/13.
 */

public class ChooseTypeAdapter extends RecyclerArrayAdapter<String> {
    public ChooseTypeAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<String> {


        private TextView tvName;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_choose_type);
            tvName = $(R.id.tvName);
        }

        @Override
        public void setData(String data) {
            super.setData(data);
            tvName.setText(data);
        }
    }

}

