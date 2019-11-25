package com.ak.pt.mvp.adapter.area;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.InstallFormBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/6/3.
 */

public class TableAllTypeAdapter extends RecyclerArrayAdapter<InstallFormBean> {
    public TableAllTypeAdapter(Context context, List<InstallFormBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    private String type;
    public void  setType(String type){
        this.type=type;
    }
    public class ViewHolder extends BaseViewHolder<InstallFormBean> {
        private TextView tvNum, tvName, tvData, tvAll, tvChild;
        private ImageView tvImg;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_table_type);
            tvNum = $(R.id.tvNum);
            tvImg = $(R.id.tvImg);
            tvName = $(R.id.tvName);
            tvData = $(R.id.tvData);
            tvAll = $(R.id.tvAll);
            tvChild = $(R.id.tvChild);
        }

        @Override
        public void setData(InstallFormBean data) {
            super.setData(data);
            if (0 == getDataPosition()) {
                tvNum.setVisibility(View.GONE);
                tvImg.setVisibility(View.VISIBLE);
                tvImg.setImageResource(R.drawable.num_one);

            } else if (1 == getDataPosition()) {
                tvNum.setVisibility(View.GONE);
                tvImg.setVisibility(View.VISIBLE);
                tvImg.setImageResource(R.drawable.num_two);

            } else if (2 == getDataPosition()) {
                tvNum.setVisibility(View.GONE);
                tvImg.setVisibility(View.VISIBLE);
                tvImg.setImageResource(R.drawable.num_three);

            } else {
                tvNum.setVisibility(View.VISIBLE);
                tvImg.setVisibility(View.GONE);
            }

            if (data.getType().contains("率")) {
                tvData.setText(data.getPercent());
            } else if (data.getType().contains("电话")) {
                tvData.setText(data.getTel());
            } else if (data.getType().contains("姓名")|data.getType().contains("名称")) {
                tvData.setText(data.getName());
            } else {
                tvData.setText(data.getNumber());
            }
            tvNum.setText((getDataPosition() + 1) + "");
            tvName.setText(data.getDepartment_name());
            tvAll.setText(data.getAll_number());
            tvChild.setText("（" + data.getG_number() + "，" + data.getN_number() + "）");
        }
    }

}

