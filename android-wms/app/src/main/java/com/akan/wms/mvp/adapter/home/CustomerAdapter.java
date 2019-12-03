package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.CustomBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class CustomerAdapter extends RecyclerArrayAdapter<CustomBean> {
    public CustomerAdapter(Context context, List<CustomBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomerAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<CustomBean> {
        private TextView tvNo,tvOne,tvTwo,tvThree;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_customer);
            tvNo = $(R.id.tvNo);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
        }

        @Override
        public void setData(CustomBean data) {
            super.setData(data);
            tvNo.setText(data.getCus_code());
            tvOne.setText(data.getCus_name());
        }
    }

}
