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
import com.akan.wms.bean.OutSaleRtuBean;
import com.akan.wms.bean.RtnLinesBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.List;

public class OutBuyReturnAddAdapter extends RecyclerArrayAdapter<OutSaleRtuBean> {
    public OutBuyReturnAddAdapter(Context context, List<OutSaleRtuBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<OutSaleRtuBean> {
        private TextView tvTittle;
        private RecyclerView childRecyclerView;
        private ImageView tvDelete, tvScan;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_out_buy_return_add);
            tvTittle = $(R.id.tvTittle);
            tvDelete = $(R.id.tvDelete);
            tvScan = $(R.id.tvScan);
            childRecyclerView = $(R.id.recyclerView);


        }

        @Override
        public void setData(final OutSaleRtuBean data) {
            super.setData(data);
            tvTittle.setText("退货申请单 " + data.getDoc_no());

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


            childRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            childRecyclerView.setAdapter(new CommonAdapter<RtnLinesBean>(getContext(), R.layout.item_out_buy_return_add_child, data.getRtn_lines()) {


                @Override
                protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, final RtnLinesBean bean, final int position) {
                    if (TextUtils.isEmpty(bean.getItem_spec())) {
                        holder.<TextView>getView(R.id.tvOne).setText(bean.getItem_name());
                    } else {
                        holder.<TextView>getView(R.id.tvOne).setText(bean.getItem_name() + "/" + bean.getItem_spec());
                    }
                    holder.<TextView>getView(R.id.tvTwo).setText(bean.getRtn_qty() + "");
                    EditText tvThree = holder.getView(R.id.tvThree);
                    tvThree.setText(bean.getSend_qty() + "");
/*                    tvThree.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            bean.setRtn_ar_qty(s.toString());
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });*/
                    holder.<TextView>getView(R.id.tvFour).setText(bean.getWh_name() + "");
                    holder.<TextView>getView(R.id.tvFour).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onStockListener.onChooseDeport(getDataPosition(), position);
                        }
                    });
                    holder.<TextView>getView(R.id.tvMfc).setText(bean.getMfc_name() + "");
                    holder.<TextView>getView(R.id.tvMfc).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onStockListener.onChooseMfc(getDataPosition(), position, bean.getItem_id());
                        }
                    });
                    holder.<TextView>getView(R.id.tvPeople).setText(bean.getWh_manager() + "");
                    holder.<TextView>getView(R.id.tvPeople).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onStockListener.onChooseWhMAnager(getDataPosition(), position);
                        }
                    });

                }
            });


        }
    }


    public interface onSelectStockClickListener {

        void onScan(int position);

        void onChooseDeport(int position, int childPositon);

        void onChooseWhMAnager(int position, int childPositon);

        void onChooseMfc(int position, int childPositon, String item_id);

        void onDelete(int position);


    }

    private onSelectStockClickListener onStockListener;

    public void setOnStockListener(onSelectStockClickListener onStockListener) {
        this.onStockListener = onStockListener;
    }
}
