package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.StoragingProListBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class FinishListtAdapter extends RecyclerArrayAdapter<StoragingProListBean> {
    public FinishListtAdapter(Context context, List<StoragingProListBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new FinishListtAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<StoragingProListBean> {
        private TextView tvNo, tvState, tvOne, tvTwo, tvThree;
        private ImageView ivInvalid;
        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_puschase_return);
            tvNo = $(R.id.tvNo);
            tvState = $(R.id.tvState);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            ivInvalid = $(R.id.ivInvalid);
        }

        @Override
        public void setData(StoragingProListBean data) {
            super.setData(data);
            tvNo.setText(data.getDoc_no());
            tvOne.setText("单据类型：" + data.getReport_name());
            tvTwo.setText("制单人：" + data.getCreator_name());
            tvThree.setText(data.getCreate_time());
            tvState.setText(data.getStatus_name());
            if ("1".equals(data.getIs_valid())) {
                ivInvalid.setVisibility(View.VISIBLE);
            } else {
                ivInvalid.setVisibility(View.GONE);
            }
        }
    }

}
