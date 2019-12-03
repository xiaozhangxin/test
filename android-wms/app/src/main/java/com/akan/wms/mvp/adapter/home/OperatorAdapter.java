package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.OperatorStaffBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class OperatorAdapter extends RecyclerArrayAdapter<OperatorStaffBean> {
    public OperatorAdapter(Context context, List<OperatorStaffBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new OperatorAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<OperatorStaffBean> {
        private TextView tvName;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_text_view);
            tvName = $(R.id.tvName);

        }

        @Override
        public void setData(OperatorStaffBean data) {
            super.setData(data);
            tvName.setText((getDataPosition()+1)+"．"+data.getStaff_name());

        }
    }

}
