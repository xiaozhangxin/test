package com.ak.pt.mvp.adapter.area;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.PeopleAddBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/5/29.
 */

public class PeopleInfoAdapter extends RecyclerArrayAdapter<PeopleAddBean> {


    public PeopleInfoAdapter(Context context, List<PeopleAddBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_people_show);
    }

    public class MyImageHolder extends BaseViewHolder<PeopleAddBean> {

        private TextView tvOne, tvTwo, tvTittle;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);

            tvTittle = $(R.id.tvTittle);


        }

        @Override
        public void setData(final PeopleAddBean data) {
            switch (data.getType()) {
                case "pressure":
                    tvTittle.setText("试压工信息");
                    break;
                case "clerk":
                    tvTittle.setText("文员信息");
                    break;
            }

            tvOne.setText(data.getInfo_name());
            tvTwo.setText(data.getInfo_tel());


        }
    }


}
