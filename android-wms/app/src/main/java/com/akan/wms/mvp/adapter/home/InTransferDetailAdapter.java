package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.TransferInBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class InTransferDetailAdapter extends RecyclerArrayAdapter<TransferInBean.LineBeanListBean> {
    public InTransferDetailAdapter(Context context, List<TransferInBean.LineBeanListBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new InTransferDetailAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<TransferInBean.LineBeanListBean> {
        private TextView tvOne, tvTwo, tvThree, tvFour;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_detail_four);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvFour = $(R.id.tvFour);


        }

        @Override
        public void setData(TransferInBean.LineBeanListBean bean) {
            super.setData(bean);
            if (TextUtils.isEmpty(bean.getItem_spec())) {
                tvOne.setText(bean.getItem_name());
            } else {
                tvOne.setText(bean.getItem_name() + "/" + bean.getItem_spec());
            }

            tvTwo.setText(bean.getOut_qty() + "");
            tvThree.setText(bean.getQty() + "");
            tvFour.setText(bean.getWh_name() + "");
        }
    }


}
