package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.ProductionOrderBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class FinishAddAdapter extends RecyclerArrayAdapter<ProductionOrderBean> {
    public FinishAddAdapter(Context context, List<ProductionOrderBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ProductionOrderBean> {
        private TextView tvTittle, tvNo, tvOne, tvTwo, tvFour;
        private ImageView tvDelete, tvScan;
        private EditText tvThree;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_add_finish);
            tvTittle = $(R.id.tvTittle);
            tvDelete = $(R.id.tvDelete);
            tvScan = $(R.id.tvScan);
            tvNo = $(R.id.tvNo);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvFour = $(R.id.tvFour);


        }

        @Override
        public void setData(final ProductionOrderBean data) {
            super.setData(data);
            tvTittle.setText("成品入库信息" + (getDataPosition() + 1));
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
            tvNo.setText(data.getDoc_no());
            tvOne.setText(data.getItem_name() + "/" + data.getItem_spec());
            tvTwo.setText(data.getComplete_qty()+"");
            tvThree.setText(data.getSend_qty()+"");//扫码数量
         /*   tvThree.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    data.setSend_qty(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });*/
            tvFour.setText(data.getWh_name());
            tvFour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStockListener.onChooseDeport(getDataPosition());
                }
            });


        }
    }


    public interface OnSelectClickListener {

        void onScan(int position);

        void onChooseDeport(int position);

        void onDelete(int position);

    }

    private OnSelectClickListener onStockListener;

    public void setOnStockListener(OnSelectClickListener onStockListener) {
        this.onStockListener = onStockListener;
    }
}
