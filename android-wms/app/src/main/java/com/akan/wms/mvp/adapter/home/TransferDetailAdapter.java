package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.LineBeanListBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class TransferDetailAdapter extends RecyclerArrayAdapter<LineBeanListBean> {
    public TransferDetailAdapter(Context context, List<LineBeanListBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new TransferDetailAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<LineBeanListBean> {
        private TextView tvOne, tvTwo, tvThree,tvMfc;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_transfer_detail);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvMfc = $(R.id.tvMfc);


        }

        @Override
        public void setData(LineBeanListBean bean) {
            super.setData(bean);
            tvOne.setText(bean.getItem_name());
            if (TextUtils.isEmpty(bean.getItem_spec())) {
                tvTwo.setText("暂无");
            } else {
                tvTwo.setText(bean.getItem_spec());
            }
            tvThree.setText(bean.getQty() + "");
            tvMfc.setText(bean.getSupplier_name() + "");
        }
    }


}
