package com.akan.qf.mvp.adapter.mine;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.qf.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/2/18.
 */

public class NoticeFileTwoAdapter extends RecyclerArrayAdapter<String> {
    public NoticeFileTwoAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<String> {
        private TextView tvName, tvNum, tvState, tvTime,tvFour;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_notice_file);
            tvName = $(R.id.tvName);
            tvNum = $(R.id.tvNo);
            tvState = $(R.id.tvState);
            tvTime = $(R.id.tvTime);
            tvFour = $(R.id.tvFour);

        }

        @Override
        public void setData(String data) {
            super.setData(data);
            tvFour.setText("备注");
            tvNum.setText("附件"+(getDataPosition()+1));
            tvName.setText(data);

        }
    }

}