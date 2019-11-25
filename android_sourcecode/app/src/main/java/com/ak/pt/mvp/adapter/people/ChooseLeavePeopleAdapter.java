package com.ak.pt.mvp.adapter.people;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.StaffInfoLeaveBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class ChooseLeavePeopleAdapter extends RecyclerArrayAdapter<StaffInfoLeaveBean> {
    public ChooseLeavePeopleAdapter(Context context, List<StaffInfoLeaveBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChooseLeavePeopleAdapter.ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<StaffInfoLeaveBean> {
        private TextView tvName;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_choose_leave_people);
            tvName = $(R.id.tvName);

        }

        @Override
        public void setData(final StaffInfoLeaveBean data) {
            super.setData(data);
           tvName.setText(""+(getDataPosition()+1)+"."+data.getStaff_name());



        }
    }


}
