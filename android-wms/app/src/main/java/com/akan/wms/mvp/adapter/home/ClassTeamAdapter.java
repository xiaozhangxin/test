package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.ClassTeamBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class ClassTeamAdapter extends RecyclerArrayAdapter<ClassTeamBean> {
    public ClassTeamAdapter(Context context, List<ClassTeamBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ClassTeamAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ClassTeamBean> {
        private TextView tvName;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_text_view);
            tvName = $(R.id.tvName);

        }

        @Override
        public void setData(ClassTeamBean data) {
            super.setData(data);
            tvName.setText((getDataPosition() + 1) + "．" + data.getName());

        }
    }

}
