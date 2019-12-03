package com.akan.wms.mvp.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.Constants;
import com.akan.wms.R;
import com.akan.wms.bean.RecordsBean;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecordAdapter extends RecyclerArrayAdapter<RecordsBean> {
    public RecordAdapter(Context context, List<RecordsBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecordAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<RecordsBean> {
        private TextView tvType, tvName, tvTime, line;
        private CircleImageView ivImg;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_record);
            ivImg = $(R.id.ivImg);
            tvType = $(R.id.tvType);
            tvName = $(R.id.tvName);
            tvTime = $(R.id.tvTime);
            line = $(R.id.line);
        }

        @Override
        public void setData(RecordsBean bean) {
            super.setData(bean);
            if (0 == getDataPosition()) {
                line.setVisibility(View.GONE);
            } else {
                line.setVisibility(View.VISIBLE);
            }
            Glide.with(getContext())
                    .load(Constants.BASE_URL + bean.getHead_img())
                    .error(R.drawable.error_img)
                    .into(ivImg);
            tvType.setText(bean.getRemark());
            tvName.setText(bean.getStaff_name());
            tvTime.setText(bean.getCreate_time());


        }
    }


}
