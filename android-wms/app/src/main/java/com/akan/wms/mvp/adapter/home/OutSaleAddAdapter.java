package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.PlanLineBean;
import com.akan.wms.bean.ShipPlanBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.List;

public class OutSaleAddAdapter extends RecyclerArrayAdapter<ShipPlanBean> {
    public OutSaleAddAdapter(Context context, List<ShipPlanBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ShipPlanBean> {
        private TextView tvTittle;
        private ImageView tvDelete, tvScan;
        private RecyclerView recyclerView;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_out_sale_add);
            tvTittle = $(R.id.tvTittle);
            tvDelete = $(R.id.tvDelete);
            tvScan = $(R.id.tvScan);
            recyclerView = $(R.id.recyclerView);


        }

        @Override
        public void setData(ShipPlanBean data) {
            super.setData(data);
            tvTittle.setText("出货计划单  " + data.getDoc_no());
            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStockListener.onDelete(getDataPosition());
                }
            });
            tvScan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStockListener.onScan(getDataPosition());
                }
            });
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


            CommonAdapter<PlanLineBean> commonAdapter = new CommonAdapter<PlanLineBean>(getContext(), R.layout.item_out_sale_child, data.getPlanLineBeans()) {
                @Override
                protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, final PlanLineBean bean, final int position) {
                    holder.setText(R.id.tvOne, bean.getItem_name() + "/" + bean.getItem_spec());
                    holder.setText(R.id.tvTwo, bean.getPlan_qty() + "");
                    EditText tvSend = holder.getView(R.id.tvThree);
                    tvSend.setText(bean.getAdd_qty() + "");
/*                    tvSend.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            String toString = s.toString();
                            if (toString == null || TextUtils.isEmpty(toString) || "0".equals(toString)) {
                                bean.setDis_qty(0);
                            } else {
                                bean.setDis_qty(Integer.parseInt(toString));
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });*/
                    TextView tvDeport = holder.getView(R.id.tvFour);
                    tvDeport.setText(bean.getDefault_wh_name());
                    tvDeport.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            onStockListener.onSelect(getDataPosition(), position, bean.getItem_id() + "");
                        }
                    });

                }
            };
            recyclerView.setAdapter(commonAdapter);


        }
    }


    public interface onSelectStockClickListener {
        void onSelect(int position, int childPosition, String itemId);

        void onDelete(int position);

        void onScan(int position);
    }

    private onSelectStockClickListener onStockListener;

    public void setOnStockListener(onSelectStockClickListener onStockListener) {
        this.onStockListener = onStockListener;
    }
}
