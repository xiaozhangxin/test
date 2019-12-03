package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.SendBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.List;

public class ProduceReturnDetailAdapter extends RecyclerArrayAdapter<SendBean> {
    public ProduceReturnDetailAdapter(Context context, List<SendBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<SendBean> {
        private TextView tvOne, tvDelete, tvTittle, tvOneTittle, tvFirst, tvSecond, tvThird, tvForth;
        private RecyclerView childRecyclerView;
        private LinearLayout llbg;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_adapter_detail_public);
            tvOne = $(R.id.tvOne);
            tvTittle = $(R.id.tvTittle);
            tvOneTittle = $(R.id.tvOneTittle);
            tvDelete = $(R.id.tvDelete);
            tvFirst = $(R.id.tvFirst);
            tvSecond = $(R.id.tvSecond);
            tvThird = $(R.id.tvThird);
            tvForth = $(R.id.tvForth);
            childRecyclerView = $(R.id.childRecyclerView);
            childRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        }

        @Override
        public void setData(SendBean data) {
            super.setData(data);
            tvTittle.setText("完工申报信息");
            tvOneTittle.setText("生产订单");
            tvOneTittle.setText("生产订单");
            tvFirst.setText("品名");
            tvSecond.setText("规格");
            tvThird.setText("数量");
            tvForth.setText("核定数量");
            childRecyclerView.setAdapter(new CommonAdapter<String>(getContext(), R.layout.item_child_goods_detail_public, data.getList()) {
                @Override
                protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, String s, final int position) {
                    holder.<TextView>getView(R.id.tvOne).setText(s);
                }
            });


        }
    }


}
