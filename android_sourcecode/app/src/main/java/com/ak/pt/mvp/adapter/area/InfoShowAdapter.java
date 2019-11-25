package com.ak.pt.mvp.adapter.area;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.SummaryInfoBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/5/28.
 */

public class InfoShowAdapter extends RecyclerArrayAdapter<SummaryInfoBean> {


    public InfoShowAdapter(Context context, List<SummaryInfoBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_table_show);
    }

    public class MyImageHolder extends BaseViewHolder<SummaryInfoBean> {

        private TextView tvOne, tvTwo, tvThree, tvFour, tvFive, tvSix, tvTittle;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvFour = $(R.id.tvFour);
            tvFive = $(R.id.tvFive);
            tvSix = $(R.id.tvSix);
            tvTittle = $(R.id.tvTittle);


        }

        @Override
        public void setData(final SummaryInfoBean data) {
            tvTittle.setText("月度数量表" + (getDataPosition() + 1));
            tvOne.setText(data.getMonth_department());
            tvTwo.setText(data.getMonth_name());
            tvThree.setText(data.getMonth_test() + "");
            tvFour.setText(data.getMonth_finish() + "");
            tvFive.setText(data.getFinish_rate());
            tvSix.setText(data.getRemark());

        }
    }


}
