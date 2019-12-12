package com.ak.pt.mvp.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import androidx.annotation.LayoutRes;

public class SearchHistoryAdapter extends RecyclerArrayAdapter<String> {
    public SearchHistoryAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchHistoryAdapter.ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<String> {


        private TextView tvName;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_history);
            tvName = $(R.id.tvHistory);
        }

        @Override
        public void setData(String data) {
            super.setData(data);
            tvName.setText(data);


        }
    }

}

