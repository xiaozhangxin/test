package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.WarnBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class FlowListAdapter extends RecyclerArrayAdapter<WarnBean> {
    public FlowListAdapter(Context context, List<WarnBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new FlowListAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<WarnBean> {
        private TextView tvType, tvFour, tvOne, tvTwo, tvThree, tvNum;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_flow);
            tvType = $(R.id.tvType);
            tvNum = $(R.id.tvNum);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvFour = $(R.id.tvFour);


        }

        @Override
        public void setData(WarnBean data) {
            super.setData(data);

            tvOne.setText(data.getItem_name());

            if (TextUtils.isEmpty(data.getItem_spec())) {
                tvTwo.setText(data.getItem_id() + "");
            } else {
                tvTwo.setText("无规格");
            }


            tvType.setText(data.getInventory_type_name());
            switch (data.getIn_out_type()) {
                case 0:
                    tvNum.setText("+" + data.getQty());
                    tvNum.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                    tvType.setBackgroundResource(R.drawable.setbar_color);
                    tvThree.setText("入库仓库   " + data.getWh_name());
                    tvFour.setText("入库时间   " + data.getCreate_time());
                    break;
                case 1:
                    tvNum.setText("-" + data.getQty());
                    tvType.setBackgroundResource(R.drawable.setbar_color_yellow);
                    tvNum.setTextColor(getContext().getResources().getColor(R.color.color_yellow));
                    tvThree.setText("出库仓库   " + data.getWh_name());
                    tvFour.setText("出库时间   " + data.getCreate_time());
                    break;
            }


        }
    }

}
