package com.akan.qf.mvp.adapter.message;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.BookBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admin on 2018/10/23.
 */

public class BookAdapter extends RecyclerArrayAdapter<BookBean> {
    public BookAdapter(Context context, List<BookBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<BookBean> {


        private TextView tvName, tvContent, tvTime;
        private CircleImageView img;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_message);
            tvName = $(R.id.tvTittle);
            tvTime = $(R.id.tvTime);
            tvContent = $(R.id.tvContent);

        }

        @Override
        public void setData(final BookBean data) {
            super.setData(data);
//            tvName.setText(data.getMsg_title());
//            tvContent.setText(data.getMsg_content());
//            tvName.setText(data.getCreate_time_simple());




        }
    }


}
