package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.TransferUnCompleteBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class TransferApplyListAdapter extends RecyclerArrayAdapter<TransferUnCompleteBean> {
    public TransferApplyListAdapter(Context context, List<TransferUnCompleteBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new TransferApplyListAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<TransferUnCompleteBean> {
        private TextView tvtittle,tvNo;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_stock);
            tvtittle = $(R.id.tvtittle);
            tvNo = $(R.id.tvNo);

        }

        @Override
        public void setData(TransferUnCompleteBean data) {
            super.setData(data);

            tvtittle.setText(data.getDoc_no());
            tvNo.setText("调入组织:"+data.getIn_org_name()+"\n调出组织:"+data.getOut_org_name());
        }
    }

}

