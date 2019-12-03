package com.akan.wms.mvp.adapter.mix;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.RcvBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class ReceiveMixListAdapter extends RecyclerArrayAdapter<RcvBean> {
    public ReceiveMixListAdapter(Context context, List<RcvBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReceiveMixListAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<RcvBean> {
        private TextView tvNo, tvState, tvOne, tvTwo, tvThree;

        private View line2;
        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_puschase_return);
            tvNo = $(R.id.tvNo);
            tvState = $(R.id.tvState);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            line2 = $(R.id.line2);
            tvThree = $(R.id.tvThree);

        }

        @Override
        public void setData(RcvBean data) {
            super.setData(data);
            tvNo.setText(data.getDoc_no() + "");
            tvState.setText(data.getStatus_show());
            tvThree.setText(data.getUpdate_time());
            line2.setVisibility(View.GONE);
            tvOne.setVisibility(View.GONE);
            //tvOne.setText("制单人：" + data.getWh_man_name());
            tvTwo.setText("单据类型：" + data.getDoc_type_name());
        }
    }

}
