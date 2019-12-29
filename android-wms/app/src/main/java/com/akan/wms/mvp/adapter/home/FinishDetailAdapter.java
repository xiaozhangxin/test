package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.StoragingProBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class FinishDetailAdapter extends RecyclerArrayAdapter<StoragingProBean.StoragingProLinesBean> {
    public FinishDetailAdapter(Context context, List<StoragingProBean.StoragingProLinesBean> list) {
        super(context, list);
    }
    private String mState = "0";
    public void setState(String state) {
        this.mState = state;

    }
    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new FinishDetailAdapter.ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<StoragingProBean.StoragingProLinesBean> {
        private TextView tvOne, tvTwo, tvThree, tvFour,tvNo,tvTittle;
        private ImageView tvScan;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_finish_detail);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvFour = $(R.id.tvFour);
            tvNo = $(R.id.tvNo);
            tvTittle = $(R.id.tvTittle);
            tvScan = $(R.id.tvScan);

        }

        @Override
        public void setData(final StoragingProBean.StoragingProLinesBean data) {
            super.setData(data);
           if ("1".equals(mState)) {
               tvScan.setVisibility(View.VISIBLE);
               tvScan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onStockListener.onScan(getDataPosition(),mState);
                    }
                });
               tvFour.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
               tvFour.setEnabled(true);
               tvFour.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       onStockListener.onChooseWh(getDataPosition(),data.getItem_id());
                   }
               });
            } else {
               tvScan.setVisibility(View.GONE);
               tvFour.setTextColor(getContext().getResources().getColor(R.color.colorTextG6));
               tvFour.setEnabled(false);
            }
            tvOne.setText(data.getItem_name()+"/"+data.getItem_spec());
            tvTwo.setText(data.getComplete_qty());
            tvThree.setText(data.getWh_qty()+"");
            tvFour.setText(data.getWh_name());
            tvNo.setText(data.getDoc_no());
            tvTittle.setText("成品入库信息"+(getDataPosition()+1));

        }
    }


    public interface onSelectStockClickListener {
        void onScan(int position,String state);
        void onChooseWh(int position,String state);
    }

    private onSelectStockClickListener onStockListener;

    public void setOnStockListener(onSelectStockClickListener onStockListener) {
        this.onStockListener = onStockListener;
    }

}
