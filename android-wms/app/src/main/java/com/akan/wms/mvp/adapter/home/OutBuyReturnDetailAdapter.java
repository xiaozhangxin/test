package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.RtnedLinesBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.List;

public class OutBuyReturnDetailAdapter extends RecyclerArrayAdapter<RtnedLinesBean> {
    public OutBuyReturnDetailAdapter(Context context, List<RtnedLinesBean> list) {
        super(context, list);
    }
    private String mState = "0";

    public void setState(String state) {
        this.mState = state;

    }
    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<RtnedLinesBean> {
        private TextView tvNo, tvDelete;
        private RecyclerView childRecyclerView;
        private ImageView tvScan;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_out_buy_return_detail);


            childRecyclerView = $(R.id.recyclerView);
            tvNo = $(R.id.tvNo);
            tvScan = $(R.id.tvScan);


        }

        @Override
        public void setData(final RtnedLinesBean data) {
            super.setData(data);
            if ("1".equals(mState)){
                tvScan.setVisibility(View.VISIBLE);
            }else {tvScan.setVisibility(View.GONE);}
            tvNo.setText("退货申请单  " +data.getDoc_no());
            childRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            childRecyclerView.setAdapter(new CommonAdapter<RtnedLinesBean.RtnedGodsLinesBean>(getContext(), R.layout.item_child_goods_detail_public, data.getRtned_gods_lines()) {
                @Override
                protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, RtnedLinesBean.RtnedGodsLinesBean bean, final int position) {
                    if (TextUtils.isEmpty(bean.getItem_spec())) {
                        holder.<TextView>getView(R.id.tvOne).setText(bean.getItem_name());
                    } else {
                        holder.<TextView>getView(R.id.tvOne).setText(bean.getItem_name() + "/" + bean.getItem_spec());
                    }
                    holder.<TextView>getView(R.id.tvTwo).setText(bean.getRtn_qty() + "");
                    holder.<TextView>getView(R.id.tvThree).setText(bean.getAlloc_qty() + "");
                    holder.<TextView>getView(R.id.tvFour).setText(bean.getWh_name().toString() );
                    holder.<TextView>getView(R.id.tvMfc).setText(bean.getMfc_name());

                }
            });
            tvScan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 onStockListener.onScan(getDataPosition());
                }
            });


        }
    }

    public interface onSelectStockClickListener {
        void onScan(int position);
    }

    private onSelectStockClickListener onStockListener;

    public void setOnStockListener(onSelectStockClickListener onStockListener) {
        this.onStockListener = onStockListener;
    }
}
