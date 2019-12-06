package com.akan.qf.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.TextListBean;
import com.akan.qf.util.CashierInputFilter;
import com.akan.qf.util.ToastUtil;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2018/10/29.
 */

public class AddReimburseAdapter extends RecyclerArrayAdapter<TextListBean> {
    private boolean isHideDelete = true;

    public void setHideDelete(boolean s) {
        this.isHideDelete = s;

    }

    public AddReimburseAdapter(Context context, List<TextListBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        MyImageHolder mallGoodsHolder = new MyImageHolder(parent, viewType);
        mallGoodsHolder.setIsRecyclable(false);
        return mallGoodsHolder;
    }

    public class MyImageHolder extends BaseViewHolder<TextListBean> {

        private TextView tvDelete, tvTop, jian, jia;
        private EditText tvNum, tvSubject, tvPrice, tvContent;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_add_reimburse);
            tvContent = $(R.id.tvContent);
            tvDelete = $(R.id.tvDelete);
            tvSubject = $(R.id.tvSubject);
            tvPrice = $(R.id.tvPrice);
            tvNum = $(R.id.num);
            tvTop = $(R.id.tvTop);
            jian = $(R.id.jian);
            jia = $(R.id.jia);
            InputFilter[] filters = {new CashierInputFilter(2)};
            tvPrice.setFilters(filters);

        }

        @Override
        public void setData(final TextListBean data) {
            if (getDataPosition()==0){
                tvDelete.setVisibility(View.GONE);
            }else {
                tvDelete.setVisibility(View.VISIBLE);
                tvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClick.onDeleteClick(getDataPosition());
                    }
                });
            }
            tvTop.setText("报销明细(" + (getDataPosition() + 1) + ")");
            if (!TextUtils.isEmpty(data.getText_subject())) {
                tvSubject.setText(data.getText_subject());
                tvPrice.setText(data.getText_price());
                tvNum.setText(data.getText_number());
                tvContent.setText(data.getText_info());
            }

            data.setText_subject(tvSubject.getText().toString());
            data.setText_price(tvPrice.getText().toString());
            data.setText_number(tvNum.getText().toString());
            data.setText_info(tvContent.getText().toString());


            jia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int goods_num = Integer.parseInt(tvNum.getText().toString());
                    goods_num++;
                    tvNum.setText(String.valueOf(goods_num));
                    data.setText_number(tvNum.getText().toString());
                    onClick.onSubPriceClick();

                }
            });
            jian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int goods_num = Integer.parseInt(tvNum.getText().toString());
                    if (goods_num <= 1) {
                        ToastUtil.showToast(getContext().getApplicationContext(), "数量不能小于1");
                        return;
                    }
                    goods_num--;
                    tvNum.setText(String.valueOf(goods_num));
                    data.setText_number(tvNum.getText().toString());
                    onClick.onSubPriceClick();
                }
            });


            tvPrice.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String s1 = s.toString();
                    if (TextUtils.isEmpty(s1)) {
                        data.setText_price("0");
                        onClick.onSubPriceClick();
                        return;
                    }
                    if (s1.endsWith(".")) {
                        s1 = s1.substring(0, s1.length() - 1);
                    }
                    data.setText_price(s1);
                    onClick.onSubPriceClick();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            tvSubject.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    data.setText_subject(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            tvContent.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    data.setText_info(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


        }
    }

    public interface OnAddorReduceClick {

        void onDeleteClick(int data);

        void onSubPriceClick();

    }

    private OnAddorReduceClick onClick;

    public void setOnDeleteClick(OnAddorReduceClick onClick) {
        this.onClick = onClick;
    }

}
