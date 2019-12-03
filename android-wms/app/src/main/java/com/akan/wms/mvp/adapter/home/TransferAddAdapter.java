package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.ItemWhQohBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class TransferAddAdapter extends RecyclerArrayAdapter<ItemWhQohBean> {
    public TransferAddAdapter(Context context, List<ItemWhQohBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new TransferAddAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ItemWhQohBean> {
        private TextView tvOne, tvTwo, tvMfc;
        private EditText tvThree;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_transfer);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvMfc = $(R.id.tvMfc);

        }

        @Override
        public void setData(final ItemWhQohBean data) {
            super.setData(data);
            tvOne.setText(data.getItem_name());
            if (TextUtils.isEmpty(data.getItem_spec())) {
                tvTwo.setText("暂无");
            } else {
                tvTwo.setText(data.getItem_spec());
            }
            tvThree.setText(data.getNum() + "");
            tvMfc.setText(data.getSupplier_name());

            tvMfc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStockListener.onChooseMfc(getDataPosition(), data.getItem_id());
                }
            });
        }
    }

    public interface OnSelectClickListener {


        void onChooseMfc(int position, String itemId);


    }

    private OnSelectClickListener onStockListener;

    public void setOnStockListener(OnSelectClickListener onStockListener) {
        this.onStockListener = onStockListener;
    }
}
