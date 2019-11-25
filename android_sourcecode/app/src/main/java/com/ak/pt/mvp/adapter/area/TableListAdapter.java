package com.ak.pt.mvp.adapter.area;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.SummaryInfoBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/5/27.
 */

public class TableListAdapter extends RecyclerArrayAdapter<SummaryInfoBean> {


    public TableListAdapter(Context context, List<SummaryInfoBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_table);
    }

    public class MyImageHolder extends BaseViewHolder<SummaryInfoBean> {

        private EditText tvTwo, tvThree, tvFour, tvFive, tvSix;
        private TextView tvOne, tvDelete, tvTop;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            tvDelete = $(R.id.tvDelete);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvFour = $(R.id.tvFour);
            tvFive = $(R.id.tvFive);
            tvSix = $(R.id.tvSix);
            tvTop = $(R.id.tvTop);


        }

        @Override
        public void setData(final SummaryInfoBean data) {
            if (0==getDataPosition()){
                tvDelete.setVisibility(View.GONE);
            }
            tvTop.setText("月度总结表" + (getDataPosition() + 1));
            tvOne.setText(data.getMonth_department());
            tvTwo.setText(data.getMonth_name());
            int month_test = data.getMonth_test();
            if (month_test!=0){
                tvThree.setText(month_test+ "");
            }
            int month_finish = data.getMonth_finish();
            if (month_finish!=0){
                tvFour.setText(month_finish + "");
            }

            tvSix.setText(data.getRemark());
            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteClick.onDeleteClick(getDataPosition());
                }
            });
            tvOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteClick.onChoosDepartment(getDataPosition());

                }
            });
            tvTwo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    String string = s.toString();
                    if (!TextUtils.isEmpty(string)) {
                        data.setMonth_name(string);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            tvThree.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String string = s.toString();
                    if (!TextUtils.isEmpty(string)) {
                        data.setMonth_test(Integer.parseInt(string));
                    }


                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            tvFour.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String string = s.toString();
                    if (!TextUtils.isEmpty(string)) {
                        data.setMonth_finish(Integer.parseInt(string));
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            tvSix.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    String string = s.toString();
                    if (!TextUtils.isEmpty(string)) {
                        data.setRemark(string);
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
        void onChoosDepartment(int data);
    }

    private OnDeleteClick onDeleteClick;

    public void setOnDeleteClick(OnDeleteClick onDeleteClick) {
        this.onDeleteClick = onDeleteClick;
    }


}
