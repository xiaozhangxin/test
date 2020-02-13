package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.TransferOutBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class ChooseTransferOutAdapter extends RecyclerArrayAdapter<TransferOutBean> {
    public ChooseTransferOutAdapter(Context context, List<TransferOutBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChooseTransferOutAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<TransferOutBean> {
        private TextView tvtittle,tvNo;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_stock);
            tvtittle = $(R.id.tvtittle);
            tvNo = $(R.id.tvNo);

        }

        @Override
        public void setData(TransferOutBean data) {
            super.setData(data);
            tvNo.setText("调出组织:"+data.getOut_org_name());
            tvtittle.setText(data.getDoc_no());
        }
    }

}

