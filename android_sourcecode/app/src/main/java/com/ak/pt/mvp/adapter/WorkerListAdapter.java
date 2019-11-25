package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.WorkerBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/1/16.
 */

public class WorkerListAdapter extends RecyclerArrayAdapter<WorkerBean> {
    public WorkerListAdapter(Context context, List<WorkerBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<WorkerBean> {


        private TextView tvName, tvPhone;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_worker);
            tvName = $(R.id.tvName);
            tvPhone = $(R.id.tvPhone);

        }

        @Override
        public void setData(final WorkerBean data) {
            super.setData(data);
            tvName.setText(data.getStaff_name());
            tvPhone.setText(data.getPhone());



        }
    }


}