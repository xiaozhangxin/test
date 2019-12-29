package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class HomeScanChildListAdapter extends RecyclerArrayAdapter<String> {
    public HomeScanChildListAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeScanChildListAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<String> {
        private TextView tvNo;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_home_scan_child);
            tvNo = $(R.id.tvNo);

        }

        @Override
        public void setData(String data) {
            super.setData(data);
            tvNo.setText(data);


        }
    }

}
