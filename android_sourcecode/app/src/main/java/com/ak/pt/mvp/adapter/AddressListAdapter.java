package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AddressBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/5/7.
 */

public class AddressListAdapter extends RecyclerArrayAdapter<AddressBean> {
    public AddressListAdapter(Context context, List<AddressBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<AddressBean> {
        private TextView tvName, tvType, tvAddress, tvPhone, tvFlag;
        private ImageView edit;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_mall_address);
            tvName = $(R.id.tvName);
            tvPhone = $(R.id.tvPhone);
            tvType = $(R.id.tvType);
            tvAddress = $(R.id.tvAddress);
            edit = $(R.id.edit);
            tvFlag = $(R.id.tvFlag);
        }

        @Override
        public void setData(AddressBean data) {
            super.setData(data);
            tvName.setText(data.getAddress_name());
            tvPhone.setText(data.getAddress_mobile());
            if (TextUtils.isEmpty(data.getAddress_flag())) {
                tvFlag.setVisibility(View.GONE);
            } else {
                tvFlag.setVisibility(View.VISIBLE);
                tvFlag.setText(data.getAddress_flag());
            }
            tvAddress.setText(data.getAddress_province() + data.getAddress_city() + data.getAddress_district() + data.getAddress_detail());
            if ("1".equals(data.getIs_default())) {
                tvType.setVisibility(View.VISIBLE);
            } else {
                tvType.setVisibility(View.GONE);
            }

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onEditClickListener.edit(getDataPosition());
                }
            });

        }
    }


    private OnEditClickListener onEditClickListener;

    public interface OnEditClickListener {
        void edit(int position);
    }

    public void setOnOnEditClickListener(OnEditClickListener onEditClickListener) {
        this.onEditClickListener = onEditClickListener;
    }
}

