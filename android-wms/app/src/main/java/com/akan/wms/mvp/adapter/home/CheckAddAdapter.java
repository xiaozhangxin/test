package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.ItemWhQohBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class CheckAddAdapter extends RecyclerArrayAdapter<ItemWhQohBean> {
    public CheckAddAdapter(Context context, List<ItemWhQohBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CheckAddAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ItemWhQohBean> {
        private TextView tvOne, tvTwo, tvThree;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_add_check);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);

        }

        @Override
        public void setData(final ItemWhQohBean data) {
            super.setData(data);
            tvOne.setText(data.getItem_name());

            if (TextUtils.isEmpty(data.getItem_spec())){
                tvTwo.setText("暂无");
            }else {
                tvTwo.setText(data.getItem_spec());
            }

            tvThree.setText(data.getNum()+"");
/*            tvThree.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String toString = s.toString();
                    if (TextUtils.isEmpty(toString) && toString == null) {
                        return;
                    }
                    data.setNum(toString);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });*/

        }
    }

}
