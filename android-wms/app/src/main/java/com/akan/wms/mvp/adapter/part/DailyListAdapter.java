package com.akan.wms.mvp.adapter.part;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.NoticeBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/3/20.
 */

public class DailyListAdapter extends RecyclerArrayAdapter<NoticeBean> {
    public DailyListAdapter(Context context, List<NoticeBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent,viewType);
    }

    public  class  ViewHolder extends BaseViewHolder<NoticeBean> {
        private TextView tvName,tvNum,tvState,tvTime;
        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_leave_list);
            tvName = $(R.id.tvName);
            tvNum = $(R.id.tvNo);
            tvState = $(R.id.tvState);
            tvTime = $(R.id.tvTime);

        }

        @Override
        public void setData(NoticeBean data) {
            super.setData(data);
            tvTime.setText(data.getCreate_time());


        }
    }

}
