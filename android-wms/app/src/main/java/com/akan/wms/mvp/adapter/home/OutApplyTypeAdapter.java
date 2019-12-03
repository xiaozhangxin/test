package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.OutTypeLBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class OutApplyTypeAdapter extends RecyclerArrayAdapter<OutTypeLBean> {
    public OutApplyTypeAdapter(Context context, List<OutTypeLBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new OutApplyTypeAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<OutTypeLBean> {
        private TextView tvName;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_choose_deport);
            tvName = $(R.id.tvName);

        }

        @Override
        public void setData(OutTypeLBean data) {
            super.setData(data);
            tvName.setText((getDataPosition()+1)+". "+data.getName());
        }
    }

}
