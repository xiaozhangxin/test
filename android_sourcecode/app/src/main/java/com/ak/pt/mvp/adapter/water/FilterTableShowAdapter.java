package com.ak.pt.mvp.adapter.water;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.FilterTypeBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/5/31.
 */

public class FilterTableShowAdapter extends RecyclerArrayAdapter<FilterTypeBean> {


    public FilterTableShowAdapter(Context context, List<FilterTypeBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_table_filter_show);
    }

    public class MyImageHolder extends BaseViewHolder<FilterTypeBean> {

        private TextView tvOne, tvTwo, tvThree, tvTittle;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);

            tvTittle = $(R.id.tvTittle);


        }

        @Override
        public void setData(final FilterTypeBean data) {
            tvTittle.setText("产品" + (getDataPosition() + 1));
            tvOne.setText(data.getProduct_soft()+data.getProduct_type());
            tvTwo.setText(data.getProduct_no());
            tvThree.setText(data.getProduct_name());


        }
    }


}
