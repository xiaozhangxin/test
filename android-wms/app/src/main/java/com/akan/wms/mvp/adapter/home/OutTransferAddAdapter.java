package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.TransferUnCompleteBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.List;

public class OutTransferAddAdapter extends RecyclerArrayAdapter<TransferUnCompleteBean> {
    public OutTransferAddAdapter(Context context, List<TransferUnCompleteBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new OutTransferAddAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<TransferUnCompleteBean> {
        private TextView tvTittle,tvTwoTittle,tvThreeTittle;
        private ImageView tvDelete, tvScan;
        private RecyclerView recyclerView;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_out_transfer_add);
            tvTittle = $(R.id.tvTittle);
            tvDelete = $(R.id.tvDelete);
            tvScan = $(R.id.tvScan);
            tvTwoTittle = $(R.id.tvTwoTittle);
            tvThreeTittle = $(R.id.tvThreeTittle);
            recyclerView = $(R.id.recyclerView);


        }

        @Override
        public void setData(TransferUnCompleteBean data) {
            super.setData(data);
            tvTittle.setText("调拨申请单  " + data.getDoc_no());
            tvTwoTittle.setText("已调拨数量/调拨数量");
            tvThreeTittle.setText("配货数量");
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


            CommonAdapter<TransferUnCompleteBean.LineBeanListBean> commonAdapter = new CommonAdapter<TransferUnCompleteBean.LineBeanListBean>(getContext(), R.layout.item_out_sale_child, data.getLineBeanList()) {
                @Override
                protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, final TransferUnCompleteBean.LineBeanListBean bean, final int position) {

                    if (TextUtils.isEmpty(bean.getItem_spec())) {
                        holder.setText(R.id.tvOne, bean.getItem_name() );
                    } else {
                        holder.setText(R.id.tvOne, bean.getItem_name() + "/" + bean.getItem_spec());
                    }

                    holder.setText(R.id.tvTwo, bean.getOut_qty()+ "/"+bean.getApply_qty() );
                    holder.setText(R.id.tvFive, bean.getSupplier_name() );
                    EditText tvSend = holder.getView(R.id.tvThree);
                    tvSend.setText(bean.getPoint_qty() + "");
                    TextView tvDeport = holder.getView(R.id.tvFour);
                    tvDeport.setText(bean.getOut_wh_name());
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

    private OutSaleAddAdapter.onSelectStockClickListener onStockListener;

    public void setOnStockListener(OutSaleAddAdapter.onSelectStockClickListener onStockListener) {
        this.onStockListener = onStockListener;
    }
}
