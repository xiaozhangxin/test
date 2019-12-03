package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.ItemInfoBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class ChooseGoodsMoreAdapter extends RecyclerArrayAdapter<ItemInfoBean> {
    public ChooseGoodsMoreAdapter(Context context, List<ItemInfoBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChooseGoodsMoreAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ItemInfoBean> {
        private TextView tvName, tvDetail, tvNum;
        private ImageView tvAdd, tvReduce;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_choose_more_goods);
            tvName = $(R.id.tvName);
            tvDetail = $(R.id.tvDetail);
            tvAdd = $(R.id.tvAdd);
            tvReduce = $(R.id.tvReduce);
            tvNum = $(R.id.tvNum);

        }

        @Override
        public void setData(final ItemInfoBean data) {
            super.setData(data);
            tvName.setText(data.getItem_name());

                tvDetail.setText(data.getItem_code()+"/"+data.getItem_spec());

            tvNum.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String num = s.toString();
                    if (TextUtils.isEmpty(num)) {
                        return;
                    }
                    data.setNum(Integer.parseInt(num) + "");
                    onChangeClickListener.jiaOrJian(getDataPosition());
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            tvAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = tvNum.getText().toString();
                    if (TextUtils.isEmpty(s)) {
                        return;
                    }
                    int i = Integer.parseInt(s);
                    i++;
                      data.setNum(i + "");
                    tvNum.setText(i + "");
                    onChangeClickListener.jiaOrJian(getDataPosition());

                }
            });
            tvReduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = tvNum.getText().toString();
                    if (TextUtils.isEmpty(s) || "0".equals(s)) {
                        return;
                    }
                    int i = Integer.parseInt(s);
                    i--;
                    data.setNum(i + "");
                    tvNum.setText(i + "");
                    onChangeClickListener.jiaOrJian(getDataPosition());
                }
            });

        }
    }

    public interface OnChangeClickListener {
        void jiaOrJian(int position);
    }

    private OnChangeClickListener onChangeClickListener;

    public void setOnChangeClickListener(OnChangeClickListener onChangeClickListener) {
        this.onChangeClickListener = onChangeClickListener;
    }
}
