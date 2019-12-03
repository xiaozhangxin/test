package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.ItemWhQohBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class ChooseItemAdapter extends RecyclerArrayAdapter<ItemWhQohBean> {
    public ChooseItemAdapter(Context context, List<ItemWhQohBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChooseItemAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ItemWhQohBean> {
        private TextView tvName, tvDetail;
        private CheckBox ckTop;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_choose_check);
            tvName = $(R.id.tvName);
            tvDetail = $(R.id.tvDetail);
            ckTop = $(R.id.ckTop);

        }

        @Override
        public void setData(final ItemWhQohBean data) {
            super.setData(data);
            tvName.setText(data.getItem_name());
            tvDetail.setText(data.getItem_spec());
            if (data.isCheck()) {
                ckTop.setChecked(true);
            } else {
                ckTop.setChecked(false);
            }
        }
    }

}
