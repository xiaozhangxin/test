package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.ItemInfoBean;
import com.akan.wms.bean.WhListBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class ProduceReturnAdapter extends RecyclerArrayAdapter<ItemInfoBean> {
    public ProduceReturnAdapter(Context context, List<ItemInfoBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProduceReturnAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ItemInfoBean> {
        private TextView tvOne, tvTwo, tvThree, tvFour, tvMfc;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_add_produce_add);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvFour = $(R.id.tvFour);
            tvMfc = $(R.id.tvMfc);

        }

        @Override
        public void setData(final ItemInfoBean data) {
            super.setData(data);
            tvOne.setText(data.getItem_name());
            String itemSpec = data.getItem_spec();
            if (TextUtils.isEmpty(itemSpec)) {
                tvTwo.setText("暂无");
            } else {
                tvTwo.setText(itemSpec);
            }
            tvThree.setText(data.getNum());

            List<WhListBean> whList = data.getItemWhBean().getWhList();
            //初始设置默认仓库
            if (TextUtils.isEmpty(data.getWarehouse_id())) {
                if (whList.size() > 0) {
                    data.setWarehouse_name(whList.get(0).getWarehouse_name());
                    data.setWarehouse_id(whList.get(0).getWarehouse_id());
                }
            }

            tvThree.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    data.setNum(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            tvFour.setText(data.getWarehouse_name());
            tvFour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStockListener.onChooseDeport(getDataPosition());
                }
            });
            tvMfc.setText(data.getTrade_mark_name());
            tvMfc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStockListener.onChooseMfc(getDataPosition(),data.getItem_id());
                }
            });
        }
    }


    public interface OnSelectClickListener {

        void onChooseDeport(int position);

        void onChooseMfc(int position,String itemId);


    }

    private OnSelectClickListener onStockListener;

    public void setOnStockListener(OnSelectClickListener onStockListener) {
        this.onStockListener = onStockListener;
    }
}
