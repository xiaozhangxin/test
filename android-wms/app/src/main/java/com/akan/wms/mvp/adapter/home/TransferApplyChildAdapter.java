package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.TransferUnCompleteBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class TransferApplyChildAdapter extends RecyclerArrayAdapter<TransferUnCompleteBean.LineBeanListBean> {
    private String mType;
    public TransferApplyChildAdapter(Context context, List<TransferUnCompleteBean.LineBeanListBean> list,String type) {
        super(context, list);
        this.mType=type;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new TransferApplyChildAdapter.ViewHolder(parent, viewType);
    }



    public class ViewHolder extends BaseViewHolder<TransferUnCompleteBean.LineBeanListBean> {
        private TextView tvName,tvType,tvNo;
        private CheckBox ckTop;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_sale_return_child);
            tvName = $(R.id.tvName);
            tvType = $(R.id.tvType);
            tvNo = $(R.id.tvNo);
            ckTop = $(R.id.ckTop);

        }

        @Override
        public void setData(TransferUnCompleteBean.LineBeanListBean data) {
            super.setData(data);
            if ("home".equals(mType)){
                ckTop.setVisibility(ViewGroup.GONE);
            }else {
                ckTop.setVisibility(ViewGroup.VISIBLE);
            }
            if (data.isCheck()) {
                ckTop.setChecked(true);
            } else {
                ckTop.setChecked(false);
            }
            tvName.setText(data.getItem_name());
            tvType.setText(data.getItem_spec());
            tvNo.setText(data.getApply_qty()+"");

        }
    }

}
