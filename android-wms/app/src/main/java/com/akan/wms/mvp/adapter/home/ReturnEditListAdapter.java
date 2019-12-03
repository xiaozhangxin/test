package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.ReturnNumBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class ReturnEditListAdapter extends RecyclerArrayAdapter<ReturnNumBean> {
    public ReturnEditListAdapter(Context context, List<ReturnNumBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ReturnNumBean> {
        private TextView tvOne, tvTwo, tvThree;
        private EditText tvFour;
        private LinearLayout llbg;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_return_edit);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvFour = $(R.id.tvFour);
            llbg = $(R.id.llbg);

        }

        @Override
        public void setData(final ReturnNumBean data) {
            super.setData(data);
            if (data.isIsred()) {
                llbg.setBackgroundColor(Color.parseColor("#33FF4949"));
            } else {
                llbg.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
            tvOne.setText(data.getOne());
            tvTwo.setText(data.getTwo());
            tvThree.setText(data.getThree());
            tvFour.setText(data.getFour());
            changeTextColor(data.getThree(), data.getFour());
            tvFour.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    String sFour = s.toString();
                    if (!TextUtils.isEmpty(sFour)) {
                        data.setFour(sFour);
                    }
                    changeTextColor(data.getThree(), sFour);

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        }

        private void changeTextColor(String sThree, String sFour) {
            if (TextUtils.isEmpty(sThree) || TextUtils.isEmpty(sFour)) {
                return;
            }
            int intFour = Integer.parseInt(sFour);
            int intThree = Integer.parseInt(sThree);
            if (intFour > intThree) {
                tvFour.setTextColor(Color.parseColor("#FF4816"));
            } else if (intFour < intThree) {
                tvFour.setTextColor(Color.parseColor("#FCAC3B"));
                llbg.setBackgroundColor(Color.parseColor("#FFFFFF"));
            } else {
                tvFour.setTextColor(Color.parseColor("#8BE9DE"));
                llbg.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        }

    }


}

