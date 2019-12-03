package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.ReturnNumBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class ReturnListAdapter extends RecyclerArrayAdapter<ReturnNumBean> {
    public ReturnListAdapter(Context context, List<ReturnNumBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReturnListAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ReturnNumBean> {
        private TextView tvTime;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_return);
            tvTime = $(R.id.tvTime);

        }

        @Override
        public void setData(ReturnNumBean data) {
            super.setData(data);

        }
    }

}
