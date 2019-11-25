package com.ak.pt.mvp.adapter.water;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.FilterTypeBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/5/31.
 */

public class WarranyTableListAdapter extends RecyclerArrayAdapter<FilterTypeBean> {


    public WarranyTableListAdapter(Context context, List<FilterTypeBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_table_warrany);
    }

    public class MyImageHolder extends BaseViewHolder<FilterTypeBean> {

        private EditText tvNum;
        private TextView tvOne,  tvDelete, tvTop;
        private ImageView ivSCAN;
        private LinearLayout llChange;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            tvDelete = $(R.id.tvDelete);
            tvOne = $(R.id.tvOne);
            ivSCAN = $(R.id.ivSCAN);
            tvTop = $(R.id.tvTop);
            tvNum = $(R.id.tvNum);
            llChange = $(R.id.llChange);


        }

        @Override
        public void setData(final FilterTypeBean data) {
            if (0 == getDataPosition()) {
                tvDelete.setVisibility(View.GONE);
            } else {
                tvDelete.setVisibility(View.VISIBLE);
            }
            llChange.setVisibility(View.GONE);


            tvTop.setText("产品" + (getDataPosition() + 1));
            if (!TextUtils.isEmpty(data.getProduct_soft())) {
                tvOne.setText(data.getProduct_soft() + data.getProduct_type());
            }
            tvNum.setText(data.getProduct_no());

            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteClick.onDeleteClick(getDataPosition());
                }
            });
            tvOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteClick.onChoosType(getDataPosition());

                }
            });
            ivSCAN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteClick.onCScan(getDataPosition());

                }
            });


            tvNum.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    String string = s.toString();
                    if (!TextUtils.isEmpty(string)) {
                        data.setProduct_no(string);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public interface OnDeleteClick {

        void onDeleteClick(int data);

        void onChoosType(int data);

        void onCScan(int data);

    }

    private OnDeleteClick onDeleteClick;

    public void setOnDeleteClick(OnDeleteClick onDeleteClick) {
        this.onDeleteClick = onDeleteClick;
    }


}
