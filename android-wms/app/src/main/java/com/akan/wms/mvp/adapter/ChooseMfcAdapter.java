package com.akan.wms.mvp.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.MfcBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class ChooseMfcAdapter extends RecyclerArrayAdapter<MfcBean> {
    public ChooseMfcAdapter(Context context, List<MfcBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChooseMfcAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<MfcBean> {
        private TextView tvName;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_text_view);
            tvName = $(R.id.tvName);


        }

        @Override
        public void setData(MfcBean data) {
            super.setData(data);
            tvName.setText((getDataPosition() + 1) + "." + data.getMfc_name() + " (库存:" + data.getQty() + ")");

        }
    }

}
