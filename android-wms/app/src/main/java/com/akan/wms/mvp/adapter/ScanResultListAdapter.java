package com.akan.wms.mvp.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.BarBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class ScanResultListAdapter extends RecyclerArrayAdapter<BarBean> {
    public ScanResultListAdapter(Context context, List<BarBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<BarBean> {
        private TextView tvNo, tvName, tvType, tvScanNum, tvPosition;
        private ImageView tvDelete;
        private EditText tvNum;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_scan);
            tvNo = $(R.id.tvNo);
            tvDelete = $(R.id.tvDelete);
            tvName = $(R.id.tvName);
            tvType = $(R.id.tvType);
            tvNum = $(R.id.tvNum);
            tvScanNum = $(R.id.tvScanNum);
            tvPosition = $(R.id.tvPosition);


        }

        @Override
        public void setData(final BarBean data) {
            super.setData(data);
            tvNo.setText(data.getItem_code());
            tvName.setText(data.getItem_name());
            tvType.setText(data.getItem_spec());
            tvNum.setText(data.getQty() + "");
            tvScanNum.setText(data.getOld_num() + "");
            tvPosition.setText((getDataPosition() + 1) + "");
            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCustomClickListener.onDeldte(getDataPosition(), data.getItem_name());
                }
            });

            tvNum.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String toString = s.toString();
                    if (TextUtils.isEmpty(toString)){
                        data.setQty(0);
                        return;
                    }
                    data.setQty(Integer.parseInt(toString));
                    onCustomClickListener.onChange(getDataPosition(),data.getItem_code());

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public interface OnCustomClickListener {
        void onDeldte(int position, String name);
        void onChange(int position, String name);
    }

    private OnCustomClickListener onCustomClickListener;

    public void setOnCustomClickListener(OnCustomClickListener onCustomClickListener) {
        this.onCustomClickListener = onCustomClickListener;
    }

}
