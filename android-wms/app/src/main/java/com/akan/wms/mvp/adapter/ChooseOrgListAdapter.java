package com.akan.wms.mvp.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.StaffOrgsBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class ChooseOrgListAdapter extends RecyclerArrayAdapter<StaffOrgsBean> {
    private String orgName;
    public ChooseOrgListAdapter(Context context, List<StaffOrgsBean> list,String orgName) {
        super(context, list);
        this.orgName=orgName;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChooseOrgListAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<StaffOrgsBean> {
        private TextView tvName,tvChange;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_org);
            tvName = $(R.id.tvName);
            tvChange = $(R.id.tvChange);

        }

        @Override
        public void setData(StaffOrgsBean data) {
            super.setData(data);
            if (orgName.equals(data.getName())){
                tvChange.setVisibility(View.GONE);
            }else {
                tvChange.setVisibility(View.VISIBLE);
            }

            tvName.setText( data.getName());

        tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStockListener.onChange(getDataPosition());
            }
        });
        }
    }
    public interface OnChangeClickListener {

        void onChange(int position);
    }

    private OnChangeClickListener onStockListener;

    public void setOnStockListener(OnChangeClickListener onStockListener) {
        this.onStockListener = onStockListener;
    }
}
