package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.StaffMessageBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2018/10/23.
 */

public class MessageAdapter extends RecyclerArrayAdapter<StaffMessageBean> {
    public MessageAdapter(Context context, List<StaffMessageBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<StaffMessageBean> {


        private TextView tvName, tvContent, tvTime, tvRed;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_message);
            tvName = $(R.id.tvTittle);
            tvTime = $(R.id.tvTime);
            tvContent = $(R.id.tvContent);
            tvRed = $(R.id.tvRed);


        }

        @Override
        public void setData(final StaffMessageBean data) {
            super.setData(data);
            if ("0".equals(data.getIs_read())) {
                tvRed.setVisibility(View.VISIBLE);
            } else {
                tvRed.setVisibility(View.INVISIBLE);
            }
            tvName.setText(data.getMsg_title());
            tvContent.setText(data.getMsg_content());
            tvTime.setText(data.getCreate_time());


        }
    }


}
