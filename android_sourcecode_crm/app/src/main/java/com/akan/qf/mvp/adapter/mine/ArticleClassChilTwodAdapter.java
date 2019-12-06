package com.akan.qf.mvp.adapter.mine;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.ContributeClassBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/11/26.
 */

public class ArticleClassChilTwodAdapter extends RecyclerArrayAdapter<ContributeClassBean> {
    private ArrayList<ContributeClassBean> list;
    private ArticleClassChildAdapter childAdapter;

    public ArticleClassChilTwodAdapter(Context context, List<ContributeClassBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ContributeClassBean> {
        private TextView tvName;
        private LinearLayout item;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_article_class_child);
            tvName = $(R.id.tvName);
        }

        @Override
        public void setData(final ContributeClassBean data) {
            super.setData(data);
            tvName.setText(data.getClass_name());
           /* item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChildOpenClick.onAddClick(data.getClass_id(),data.getClass_id());
                }
            });*/

        }


    }

    public interface OnChildOpenClick {

        void onAddClick(String  class_ids,String class_id);
    }

    private OnChildOpenClick onChildOpenClick;

    public void setOnOpenClick(OnChildOpenClick onChildOpenClick) {
        this.onChildOpenClick = onChildOpenClick;
    }
}
