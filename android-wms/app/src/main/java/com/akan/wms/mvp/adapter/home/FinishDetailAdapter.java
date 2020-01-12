package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
        private TextView tvNo, tvOne, tvTwo, tvThree, tvFour, tvFive, tvPass, tvNotPass;
        private ImageView tvScan;
        private RelativeLayout rlPass, rlNotPass;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_finish_detail_new);
            tvScan = $(R.id.tvScan);
            tvNo = $(R.id.tvNo);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvFour = $(R.id.tvFour);
            tvFive = $(R.id.tvFive);
            rlPass = $(R.id.rlPass);
            tvPass = $(R.id.tvPass);
            rlNotPass = $(R.id.rlNotPass);
            tvNotPass = $(R.id.tvNotPass);


        }

        @Override
        public void setData(final StoragingProBean.StoragingProLinesBean data) {
            super.setData(data);

            switch (mState) {
                case "0":
                    tvScan.setVisibility(View.GONE);
                    rlNotPass.setVisibility(View.GONE);
                    rlPass.setVisibility(View.GONE);
                    break;
                case "1":
                    tvScan.setVisibility(View.GONE);
                    rlNotPass.setVisibility(View.VISIBLE);
                    rlPass.setVisibility(View.VISIBLE);
                    tvPass.setEnabled(true);
                    tvFive.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                    tvFive.setEnabled(true);
                    tvFive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onStockListener.onChooseWh(getDataPosition(), data.getItem_id());
                        }
                    });
                    break;
                case "2":
                    tvPass.setEnabled(false);
                    tvScan.setVisibility(View.VISIBLE);
                    rlNotPass.setVisibility(View.VISIBLE);
                    rlPass.setVisibility(View.VISIBLE);
                    tvFive.setTextColor(getContext().getResources().getColor(R.color.colorTextG3));
                    tvFive.setClickable(false);
                    tvFive.setEnabled(false);
                    break;
                case "3":
                    tvScan.setVisibility(View.GONE);
                    rlNotPass.setVisibility(View.VISIBLE);
                    rlPass.setVisibility(View.VISIBLE);
                    break;
            }

            tvNo.setText(data.getDoc_no());
            tvOne.setText(data.getItem_name());
            tvTwo.setText(data.getItem_spec());
            tvThree.setText(data.getQualified_qty());
            tvFour.setText(String.valueOf(data.getWh_qty()));
            tvFive.setText(data.getWh_name());

            tvScan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStockListener.onScan(getDataPosition(), mState);
                }
            });

            tvPass.setText(String.valueOf(data.getQualify_qty()));
            tvNotPass.setText(String.valueOf(data.getUn_qualify_qty()));
            tvPass.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String toString = s.toString();
                    if (TextUtils.isEmpty(toString)) {
                        data.setQualify_qty(0);
                    } else {
                        int totalInt = data.getWh_qty();
                        int passInt = Integer.parseInt(toString);
                        data.setQualify_qty(passInt);
                        if (passInt>totalInt){
                            tvNotPass.setText("0");
                            data.setUn_qualify_qty(0);
                        }else {
                            int num = totalInt - passInt;
                            tvNotPass.setText(String.valueOf(num));
                            data.setUn_qualify_qty(num);
                        }


                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


        }
    }


    public interface onSelectStockClickListener {
        void onScan(int position, String state);

        void onChooseWh(int position, String state);
    }

    private onSelectStockClickListener onStockListener;

    public void setOnStockListener(onSelectStockClickListener onStockListener) {
        this.onStockListener = onStockListener;
    }

}
