package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class AllFunctionChildAdapter extends RecyclerArrayAdapter<String> {
    public AllFunctionChildAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<String> {
        private TextView tvName;
        private ImageView ivImg;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_function_right);
            tvName = $(R.id.tvName);
            ivImg = $(R.id.ivImg);

        }

        @Override
        public void setData(String data) {
            super.setData(data);
            tvName.setText(data);
            switch (data) {
                case "采购入库":
                    Glide.with(getContext()).load(R.drawable.in_one).into(ivImg);
                    break;
                case "销售退货":
                    Glide.with(getContext()).load(R.drawable.in_two).into(ivImg);
                    break;
                case "完工申报":
                    Glide.with(getContext()).load(R.drawable.in_three).into(ivImg);
                    break;
                case "生产退料":
                    Glide.with(getContext()).load(R.drawable.in_four).into(ivImg);
                    break;
                case "成品入库":
                    Glide.with(getContext()).load(R.drawable.in_five).into(ivImg);
                    break;
                case "采购退货":
                    Glide.with(getContext()).load(R.drawable.out_one).into(ivImg);
                    break;
                case "销售出库":
                    Glide.with(getContext()).load(R.drawable.out_two).into(ivImg);
                    break;
                case "生产领料":
                    Glide.with(getContext()).load(R.drawable.out_three).into(ivImg);
                    break;
                case "供应商":
                    Glide.with(getContext()).load(R.drawable.base_one).into(ivImg);
                    break;
                case "仓库信息":
                    Glide.with(getContext()).load(R.drawable.base_two).into(ivImg);
                    break;
                case "设备信息":
                    Glide.with(getContext()).load(R.drawable.base_three).into(ivImg);
                    break;
                case "客户管理":
                    Glide.with(getContext()).load(R.drawable.base_four).into(ivImg);
                    break;
                case "盘点":
                    Glide.with(getContext()).load(R.drawable.deport_one).into(ivImg);
                    break;
                case "调拨":
                    Glide.with(getContext()).load(R.drawable.deport_two).into(ivImg);
                    break;
                case "统计":
                    Glide.with(getContext()).load(R.drawable.deport_three).into(ivImg);
                    break;
                case "库存查询":
                    Glide.with(getContext()).load(R.drawable.deport_four).into(ivImg);
                    break;
                case "调拨入库":
                    Glide.with(getContext()).load(R.drawable.deport_five).into(ivImg);
                    break;
                case "调拨出库":
                    Glide.with(getContext()).load(R.drawable.deport_six).into(ivImg);
                    break;
                case "出入库流水":
                    Glide.with(getContext()).load(R.drawable.total_one).into(ivImg);
                    break;
                case "出货计划":
                    Glide.with(getContext()).load(R.drawable.out_plan).into(ivImg);
                    break;
                case "采购退货申请":
                    Glide.with(getContext()).load(R.drawable.return_apply).into(ivImg);
                    break;
                case "生产订单":
                    Glide.with(getContext()).load(R.drawable.produce_order).into(ivImg);
                    break;
                case "调拨申请":
                    Glide.with(getContext()).load(R.drawable.transfer_apply).into(ivImg);
                    break;
                case "采购申请":
                    Glide.with(getContext()).load(R.drawable.buy_apply).into(ivImg);
                    break;
            }

        }
    }

}
