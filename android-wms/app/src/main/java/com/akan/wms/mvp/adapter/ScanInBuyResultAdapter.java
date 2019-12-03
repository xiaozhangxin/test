package com.akan.wms.mvp.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.ScanInfoBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class ScanInBuyResultAdapter extends RecyclerArrayAdapter<ScanInfoBean> {
    private String type;

    public ScanInBuyResultAdapter(Context context, List<ScanInfoBean> list, String type) {
        super(context, list);
        this.type = type;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ScanInBuyResultAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ScanInfoBean> {
        private TextView tvOne, tvTwo, tvThree, tvFour;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_scan_top);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvFour = $(R.id.tvFour);

        }

        @Override
        public void setData(ScanInfoBean data) {
            super.setData(data);
            tvOne.setText(data.getItem_name());
            if (TextUtils.isEmpty(data.getItem_spec())) {
                tvTwo.setText("无规格");
            } else {
                tvTwo.setText(data.getItem_spec());
            }

            switch (type) {
                case "in_buy_agree"://采购入库同意入库
                    tvThree.setText(data.getArrive_qty() + "");
                    tvFour.setText(data.getIn_qty() + "");
                    break;
                case "in_buy_point"://采购入库点收
                case "finish_check"://成品入库(审核)
                case "finish_add"://成品入库(添加)
                case "pro_return_point"://生产退料(点收)
                case "pro_return_in"://生产退料(点收)
                case "pro_receive_point"://生产领料(点收)
                case "pro_receive_out"://生产领料(出库)
                case "out_buy_add"://采购退货(新增)
                case "out_buy_detail"://采购退货(核定)
                case "out_sale_add"://销售出库(新增)
                case "in_transfer_add"://调拨入库(新增)
                case "out_transfer_add"://调拨出库(新增)
                    tvThree.setText(data.getSend_qty() + "");
                    tvFour.setText(data.getArrive_qty() + "");
                    break;
                case "check_add"://盘点新增
                case "transfer_add":
                    tvThree.setText(data.getArrive_qty() + "");
                    tvFour.setVisibility(View.GONE);
                    break;
            }


        }
    }

}
