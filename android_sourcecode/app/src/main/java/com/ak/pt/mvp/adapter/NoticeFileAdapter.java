package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.NoticeBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/1/17.
 */

public class NoticeFileAdapter extends RecyclerArrayAdapter<NoticeBean.NoticeFileBeans> {
    private String fileName;
    public NoticeFileAdapter(Context context, List<NoticeBean.NoticeFileBeans> list,String fileName) {
        super(context, list);
        this.fileName=fileName;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<NoticeBean.NoticeFileBeans> {
        private TextView tvName, tvNum, tvState, tvTime;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_notice_file);
            tvName = $(R.id.tvName);
            tvNum = $(R.id.tvNo);
            tvState = $(R.id.tvState);
            tvTime = $(R.id.tvTime);

        }

        @Override
        public void setData(NoticeBean.NoticeFileBeans data) {
            super.setData(data);

            tvNum.setText(data.getFile_name());
            tvName.setText(fileName);
            tvTime.setText(data.getCreate_time());
            tvState.setText(data.getFile_note());

        }
    }

}
