package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.BookBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/1/17.
 */

public class BookListAdapter extends RecyclerArrayAdapter<BookBean> {
    public BookListAdapter(Context context, List<BookBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<BookBean> {
        private TextView tv, tvPhone, tvJob;
        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_book_child);
            tv = $(R.id.tv);
            tvPhone = $(R.id.tvPhone);
            tvJob = $(R.id.tvJob);
        }

        @Override
        public void setData(BookBean data) {
            super.setData(data);
            if ("1".equals(data.getIs_all_data())){
                tvJob.setBackgroundResource(R.drawable.setbar_m3);
            }else {
                tvJob.setBackgroundResource(R.drawable.setbar_cp3);
            }
            tv.setText(data.getStaff_name());
            tvPhone.setText(data.getPhone());
            tvJob.setText(data.getJob_name());
        }
    }

}
