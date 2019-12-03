package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.InforListBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class ProduceReceiveDetailAdapter extends RecyclerArrayAdapter<InforListBean> {
    public ProduceReceiveDetailAdapter(Context context, List<InforListBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProduceReceiveDetailAdapter.ViewHolder(parent, viewType);
    }

    private boolean isShow;
    private String state;

    public void setShowThree(boolean isShow, String state) {
        this.isShow = isShow;
        this.state = state;
    }

    public class ViewHolder extends BaseViewHolder<InforListBean> {
        private TextView tvOne, tvTwo, tvThree, tvFour, tvMfc;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_preduce_receive_detail);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvFour = $(R.id.tvFour);
            tvMfc = $(R.id.tvMfc);

        }

        @Override
        public void setData(final InforListBean data) {
            super.setData(data);
            if (isShow) {
                switch (state) {
                    case "1":
                        tvThree.setVisibility(View.VISIBLE);
                        tvThree.setText(data.getScan_num() + "");
                        break;
                    case "2":
                        tvThree.setVisibility(View.VISIBLE);
                        tvThree.setText(data.getInfo_num() + "");
                        break;
                }

            } else {
                tvThree.setVisibility(View.GONE);

            }
            if (TextUtils.isEmpty(data.getInfo_spec())) {
                tvOne.setText(data.getInfo_name());
            } else {
                tvOne.setText(data.getInfo_name() + "/" + data.getInfo_spec());
            }
            if (TextUtils.isEmpty(data.getMark_name())) {
                tvMfc.setText("无");
            } else {
                tvMfc.setText(data.getMark_name());
            }
            tvTwo.setText(data.getNumber() + "");
            tvFour.setText(data.getInfo_wh_name());


        }
    }


    public interface OnSelectClickListener {
        void onScan(int position);
    }

    private OnSelectClickListener onStockListener;

    public void setOnStockListener(OnSelectClickListener onStockListener) {
        this.onStockListener = onStockListener;
    }
}
