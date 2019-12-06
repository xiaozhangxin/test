package com.akan.qf.mvp.adapter.message;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.akan.qf.R;
import com.akan.qf.bean.CkeckBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2018/11/15.
 */

public class OneAdapter extends RecyclerArrayAdapter<CkeckBean> {

    private  Boolean  isAll;
    public OneAdapter(Context context, List<CkeckBean> list, Boolean isAll) {
        super(context, list);
        this.isAll=isAll;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<CkeckBean> {
        private CheckBox checkbox;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_one);
            checkbox = $(R.id.checkbox);


        }

        @Override
        public void setData(final CkeckBean data) {
            super.setData(data);
            if (data.isCkeck()) {
                checkbox.setChecked(true);
            } else {
                checkbox.setChecked(false);
            }
            checkbox.setText(data.getName());
            checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isAll) {
                        data.setCkeck(!data.isCkeck());
                    }else {
                        onDeleteClick.onDeleteClick(getDataPosition(),data.isCkeck());


                    }
                }
            });

        }
    }

    public interface OnDeleteClick {

        void onDeleteClick(int position,boolean isCheck);
    }

    private OnDeleteClick onDeleteClick;

    public void setOnDeleteClick(OnDeleteClick onDeleteClick) {
        this.onDeleteClick = onDeleteClick;
    }

}
