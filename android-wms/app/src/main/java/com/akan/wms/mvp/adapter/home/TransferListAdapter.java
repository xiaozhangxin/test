package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.TransferBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class TransferListAdapter extends RecyclerArrayAdapter<TransferBean> {
    public TransferListAdapter(Context context, List<TransferBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new TransferListAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<TransferBean> {
        private TextView tvNo,tvState,tvOne,tvTwo,tvThree;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_puschase_return);
            tvNo = $(R.id.tvNo);
            tvState = $(R.id.tvState);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);

        }

        @Override
        public void setData(TransferBean data) {
            super.setData(data);
            tvState.setText(data.getStatus_show());
            tvNo.setText(data.getDoc_no());
            tvOne.setText("调出仓库：" + data.getOut_wh_name());
            tvTwo.setText("调入仓库："+data.getIn_wh_name());
            tvThree.setText(data.getCreate_time());
        }
    }

}
