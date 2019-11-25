package com.ak.pt.mvp.adapter.people;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.LeavePerpleBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class LeavePeopleInfoAdapter extends RecyclerArrayAdapter<LeavePerpleBean> {


    public LeavePeopleInfoAdapter(Context context, List<LeavePerpleBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new LeavePeopleInfoAdapter.MyImageHolder(parent, R.layout.item_people_show);
    }

    public class MyImageHolder extends BaseViewHolder<LeavePerpleBean> {

        private TextView tvOne, tvTwo, tvTittle;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvTittle = $(R.id.tvTittle);


        }

        @Override
        public void setData(final LeavePerpleBean data) {
            switch (data.getInfo_sign()) {
                case "1":
                    tvTittle.setText("试压工信息");
                    break;
                case "0":
                    tvTittle.setText("文员信息");
                    break;
            }

            tvOne.setText(data.getStaff_name());
            tvTwo.setText(data.getTel());


        }
    }


}
