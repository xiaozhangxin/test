package com.akan.wms.mvp.adapter.part;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.PartBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class PartListAdapter extends RecyclerArrayAdapter<PartBean> {
    public PartListAdapter(Context context, List<PartBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<PartBean> {
        private TextView tvTime;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_part);
            tvTime = $(R.id.tvTime);

        }

        @Override
        public void setData(PartBean data) {
            super.setData(data);

        }
    }

}
