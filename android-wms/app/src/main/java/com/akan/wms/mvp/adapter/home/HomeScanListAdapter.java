package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.BarMsgBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class HomeScanListAdapter extends RecyclerArrayAdapter<BarMsgBean.LogisticsBean> {
    public HomeScanListAdapter(Context context, List<BarMsgBean.LogisticsBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeScanListAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<BarMsgBean.LogisticsBean> {
        private TextView tvOne, tvTwo, tvThree, tvAddOne, tvAddTwo, tvType;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_home_scan);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvAddOne = $(R.id.tvAddOne);
            tvAddTwo = $(R.id.tvAddTwo);
            tvType = $(R.id.tvType);

        }

        @Override
        public void setData(BarMsgBean.LogisticsBean data) {
            super.setData(data);
            if ("0".equals(data.getIn_out_type())) {
                tvType.setText("入");
            } else {
                tvType.setText("出");
            }

            if ("XSCK".equals(data.getInventory_type())) {
                tvAddTwo.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));

                tvAddTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onDetailListener.onDetail();
                    }
                });
            }else {
                tvAddTwo.setTextColor(getContext().getResources().getColor(R.color.colorTextG9));
            }

            tvOne.setText(data.getOrg_name());
            tvTwo.setText(data.getWh_name());
            tvThree.setText(data.getCreate_time());
            tvAddOne.setText(data.getWh_name());
            tvAddTwo.setText(data.getInventory_type_name());


        }
    }
    public interface OnDetailListener{
        void onDetail();

    }
    private OnDetailListener onDetailListener;

    public void setOnDetailListener(OnDetailListener onDetailListener){
        this.onDetailListener=onDetailListener;
    }
}
