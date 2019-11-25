package com.ak.pt.mvp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

public class NoticeAdapter extends RecyclerArrayAdapter<NoticeBean> {
    public NoticeAdapter(Context context, List<NoticeBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<NoticeBean> {


        private TextView tvTittle, tvContent, tvTime, tvRed;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_notice);
            tvTittle = $(R.id.tvTittle);
            tvTime = $(R.id.tvTime);
            tvContent = $(R.id.tvContent);


        }

        @Override
        public void setData(final NoticeBean data) {
            super.setData(data);
            if ("0".equals(data.getIs_read())) {
                Drawable nav_up = getContext().getResources().getDrawable(R.drawable.notice_new);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                tvTittle.setCompoundDrawables(null, null, nav_up, null);

            } else {
                tvTittle.setCompoundDrawables(null, null, null, null);
            }
            tvContent.setText(data.getNotice_content());
            tvTime.setText(data.getCreate_time());
            tvTittle.setText(data.getNotice_title());

        }
    }


}
