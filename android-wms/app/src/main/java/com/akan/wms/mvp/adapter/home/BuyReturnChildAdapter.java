package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.RtnLinesBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class BuyReturnChildAdapter extends RecyclerArrayAdapter<RtnLinesBean> {
    private String inReturnChildType;
    public BuyReturnChildAdapter(Context context, List<RtnLinesBean> list,String inReturnChildType) {
        super(context, list);
        this.inReturnChildType=inReturnChildType;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<RtnLinesBean> {
        private TextView tvName, tvType, tvNo;
        private CheckBox ckTop;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_sale_return_child);
            tvName = $(R.id.tvName);
            tvType = $(R.id.tvType);
            ckTop = $(R.id.ckTop);
            tvNo = $(R.id.tvNo);

        }

        @Override
        public void setData(RtnLinesBean data) {
            super.setData(data);
            if ("home".equals(inReturnChildType)){
                ckTop.setVisibility(View.GONE);
            }
            if (data.isCheck()) {
                ckTop.setChecked(true);
            } else {
                ckTop.setChecked(false);
            }


            tvName.setText(data.getItem_name());
            tvType.setText(data.getItem_spec());
            tvNo.setText(data.getRtn_qty() + "");

        }
    }

}
