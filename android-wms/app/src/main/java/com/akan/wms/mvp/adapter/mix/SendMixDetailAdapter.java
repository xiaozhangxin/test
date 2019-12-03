package com.akan.wms.mvp.adapter.mix;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.ShipBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class SendMixDetailAdapter extends RecyclerArrayAdapter<ShipBean.LineBeanListBean> {
    public SendMixDetailAdapter(Context context, List<ShipBean.LineBeanListBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new SendMixDetailAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ShipBean.LineBeanListBean> {
        private TextView tvOne, tvTwo, tvThree, tvFour,tvFive;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_send_mix_detail);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvFour = $(R.id.tvFour);
            tvFive = $(R.id.tvFive);

        }

        @Override
        public void setData(final ShipBean.LineBeanListBean data) {
            super.setData(data);
            if (TextUtils.isEmpty(data.getItem_spec())) {
                tvOne.setText(data.getItem_name());
            } else {
                tvOne.setText(data.getItem_name() + "/" + data.getItem_spec());
            }
            tvTwo.setText(data.getQty() + "");
            tvThree.setText(data.getQty() + "");
            tvFour.setText(data.getWh_name());
            tvFive.setText(data.getBenefit_dep_name());

        }
    }


}
