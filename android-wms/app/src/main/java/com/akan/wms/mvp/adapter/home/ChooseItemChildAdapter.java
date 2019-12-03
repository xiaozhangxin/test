package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.ItemWhQohBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class ChooseItemChildAdapter extends RecyclerArrayAdapter<ItemWhQohBean> {
    public ChooseItemChildAdapter(Context context, List<ItemWhQohBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChooseItemChildAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ItemWhQohBean> {
        private TextView tvName, tvDetail;
        private ImageView tvDelete;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_choose_check_child);
            tvName = $(R.id.tvName);
            tvDetail = $(R.id.tvDetail);
            tvDelete = $(R.id.tvDelete);


        }

        @Override
        public void setData(final ItemWhQohBean data) {
            super.setData(data);
            tvName.setText(data.getItem_name());
            tvDetail.setText(data.getItem_spec());


            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChangeClickListener.delete(getDataPosition());

                }
            });


        }
    }

    public interface OnChangeClickListener {
        void delete(int position);
    }

    private OnChangeClickListener onChangeClickListener;

    public void setOnChangeClickListener(OnChangeClickListener onChangeClickListener) {
        this.onChangeClickListener = onChangeClickListener;
    }
}
