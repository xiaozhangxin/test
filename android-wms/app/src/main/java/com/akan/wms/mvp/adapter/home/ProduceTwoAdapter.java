package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.OperatorBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class ProduceTwoAdapter extends RecyclerArrayAdapter<OperatorBean> {
    public ProduceTwoAdapter(Context context, List<OperatorBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProduceTwoAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<OperatorBean> {
        private TextView tvName;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_text_view);
            tvName = $(R.id.tvName);

        }

        @Override
        public void setData(OperatorBean data) {
            super.setData(data);
            tvName.setText(data.getOperator_name());
            tvName.setText((getDataPosition()+1)+"．"+data.getOperator_name());
        }
    }

}
