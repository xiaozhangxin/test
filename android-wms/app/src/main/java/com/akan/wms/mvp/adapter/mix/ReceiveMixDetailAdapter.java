package com.akan.wms.mvp.adapter.mix;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.RcvBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class ReceiveMixDetailAdapter  extends RecyclerArrayAdapter<RcvBean.LineBeanListBean> {
    public ReceiveMixDetailAdapter(Context context, List<RcvBean.LineBeanListBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReceiveMixDetailAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<RcvBean.LineBeanListBean> {
        private TextView tvOne, tvTwo, tvThree, tvFour;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_send_mix_detail);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvFour = $(R.id.tvFour);

        }

        @Override
        public void setData(final RcvBean.LineBeanListBean data) {
            super.setData(data);
            if (TextUtils.isEmpty(data.getItem_spec())) {
                tvOne.setText(data.getItem_name());
            } else {
                tvOne.setText(data.getItem_name() + "/" + data.getItem_spec());
            }
            tvTwo.setText(data.getQty() + "");
            tvThree.setText(data.getQty() + "");
            tvFour.setText(data.getWh_name());

        }
    }


}
