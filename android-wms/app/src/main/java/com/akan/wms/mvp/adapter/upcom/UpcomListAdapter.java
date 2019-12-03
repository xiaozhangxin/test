package com.akan.wms.mvp.adapter.upcom;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.ItemInfoBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class UpcomListAdapter extends RecyclerArrayAdapter<ItemInfoBean> {
    public UpcomListAdapter(Context context, List<ItemInfoBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ItemInfoBean> {
        private TextView tvNo,tvOne,tvTwo,tvThree;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_upcom);
            tvNo = $(R.id.tvNo);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);

        }

        @Override
        public void setData(ItemInfoBean data) {
            super.setData(data);
            tvNo.setText(data.getItem_code());
            tvOne.setText(data.getItem_name());
            String warehouseName = data.getItem_spec();
            if (TextUtils.isEmpty(warehouseName)){
                tvTwo.setVisibility(View.GONE);
            }else {
                tvTwo.setVisibility(View.VISIBLE);
                tvTwo.setText(data.getItem_spec());
            }
        }
    }

}
