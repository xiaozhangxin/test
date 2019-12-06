package com.akan.qf.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.StaffSignListBean;
import com.akan.qf.mvp.adapter.PostAdapter;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class LableAdapter extends RecyclerArrayAdapter<StaffSignListBean> {
    public LableAdapter(Context context, List<StaffSignListBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new LableAdapter.ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<StaffSignListBean> {


        private TextView tvName;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_post);
            tvName = $(R.id.tvName);
        }

        @Override
        public void setData(StaffSignListBean data) {
            super.setData(data);
            tvName.setText(data.getSign_name());
            if (data.isCheck()){
                tvName.setBackground(getContext().getResources().getDrawable(R.drawable.setbar_filter_check));
                tvName.setTextColor(getContext().getResources().getColor(R.color.white));
            }else {
                tvName.setBackground(getContext().getResources().getDrawable(R.drawable.setbar_filter_gray_two));
                tvName.setTextColor(getContext().getResources().getColor(R.color.colorTextG6));
            }
        }
    }

}
