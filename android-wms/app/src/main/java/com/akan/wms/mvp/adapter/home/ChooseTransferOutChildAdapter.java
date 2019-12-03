package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.TransferOutBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class ChooseTransferOutChildAdapter extends RecyclerArrayAdapter<TransferOutBean.LineBeanListBean> {
    public ChooseTransferOutChildAdapter(Context context, List<TransferOutBean.LineBeanListBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChooseTransferOutChildAdapter.ViewHolder(parent, viewType);
    }



    public class ViewHolder extends BaseViewHolder<TransferOutBean.LineBeanListBean> {
        private TextView tvName,tvType,tvNo;
        private CheckBox ckTop;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_sale_return_child);
            tvName = $(R.id.tvName);
            tvType = $(R.id.tvType);
            tvNo = $(R.id.tvNo);
            ckTop = $(R.id.ckTop);

        }

        @Override
        public void setData(TransferOutBean.LineBeanListBean data) {
            super.setData(data);
            if (data.isCheck()) {
                ckTop.setChecked(true);
            } else {
                ckTop.setChecked(false);
            }
            tvName.setText(data.getItem_name());
            tvType.setText(data.getItem_spec());
            tvNo.setText(data.getQty()+"");

        }
    }

}
