package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.StaffGroupBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class ProduceFiveAdapter extends RecyclerArrayAdapter<StaffGroupBean> {
    public ProduceFiveAdapter(Context context, List<StaffGroupBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<StaffGroupBean> {
        private TextView tvName;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_text_view);
            tvName = $(R.id.tvName);

        }

        @Override
        public void setData(StaffGroupBean data) {
            super.setData(data);
            tvName.setText(data.getGroup_name());
            tvName.setText((getDataPosition()+1)+"．"+data.getGroup_name());

        }
    }

}
