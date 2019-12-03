package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.PurchaseBean;
import com.akan.wms.bean.PurchaseLinesBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.List;

public class SendAddAdapter extends RecyclerArrayAdapter<PurchaseBean> {
    public SendAddAdapter(Context context, List<PurchaseBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<PurchaseBean> {
        private TextView tvTittle;
        private ImageView tvDelete;
        private RecyclerView recyclerView;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_send_add);

            tvTittle = $(R.id.tvTittle);
            tvDelete = $(R.id.tvDelete);
            recyclerView = $(R.id.recyclerView);

        }

        @Override
        public void setData(PurchaseBean data) {
            super.setData(data);
            tvTittle.setText("采购单" + (getDataPosition() + 1) + "   " + data.getDoc_no());
            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStockListener.onDelete(getDataPosition());
                }
            });
            List<PurchaseLinesBean> linesBeans = data.getPurchase_lines();

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new CommonAdapter<PurchaseLinesBean>(getContext(), R.layout.item_send_add_child, linesBeans) {

                @Override
                protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, final PurchaseLinesBean bean, final int position) {
                    holder.setText(R.id.tvName, bean.getItem_name() + "\n" + bean.getItem_spec());
                    holder.setText(R.id.tvNum, bean.getPur_qty() + "");
                    holder.setText(R.id.tvUnit, "");
                    TextView tvSend = holder.getView(R.id.tvSend);


                    tvSend.setText(bean.getSend_qty() + "");
                    tvSend.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            String toString = s.toString();
                            if (toString == null || TextUtils.isEmpty(toString) || "0".equals(toString)) {
                                bean.setSend_qty(0);
                            } else {
                                bean.setSend_qty(Integer.parseInt(toString));
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    TextView tvWhManager = holder.getView(R.id.tvWhManager);
                    tvWhManager.setText(bean.getWh_manager());
                    tvWhManager.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onStockListener.onWhManager(getDataPosition(), position);
                        }
                    });

                    TextView tvDeport = holder.getView(R.id.tvDeport);
                    tvDeport.setText(bean.getWh_name());
                    tvDeport.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onStockListener.onSelect(getDataPosition(), position, bean.getItem_id());
                        }
                    });

                    TextView tvMfc = holder.getView(R.id.tvMfc);
                    tvMfc.setText(bean.getMfc_name());
                    tvMfc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onStockListener.onMfc(getDataPosition(), position, bean.getItem_id());
                        }
                    });

                }
            });


        }
    }


    public interface onSelectStockClickListener {
        void onSelect(int position, int childPosition, String itemId);
        void onMfc(int position, int childPosition, String itemId);

        void onDelete(int position);
        void onWhManager(int position, int childPosition);
    }

    private onSelectStockClickListener onStockListener;

    public void setOnStockListener(onSelectStockClickListener onStockListener) {
        this.onStockListener = onStockListener;
    }
}
