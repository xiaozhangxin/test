package com.akan.qf.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.mvp.fragment.qifei.StaffBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2018/11/29.
 */

public class PersonInfoAdapter extends RecyclerArrayAdapter<StaffBean> {
    public PersonInfoAdapter(Context context, List<StaffBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<StaffBean> {


        private TextView tvOne, tvTwo, tvThree, tvFour, tvState;
        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_person_info);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvFour = $(R.id.tvFour);
            tvState = $(R.id.tvState);
        }

        @Override
        public void setData(StaffBean data) {
            super.setData(data);
            tvOne.setText(data.getStaff_name());
            tvTwo.setText(data.getDepartment_name());
            tvThree.setText(data.getJob_name());
            tvFour.setText(data.getPhone());
            //tvState.setText( data.getYear());
            if ("0".equals(data.getIs_departure())) {
                tvState.setText("在职");
                tvState.setTextColor(getContext().getResources().getColor(R.color.colorPrimaryNew));

            } else {
                tvState.setText("离职");
                tvState.setTextColor(getContext().getResources().getColor(R.color.name_one));

            }

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(RecyclerView.HORIZONTAL);



        }
    }

}
