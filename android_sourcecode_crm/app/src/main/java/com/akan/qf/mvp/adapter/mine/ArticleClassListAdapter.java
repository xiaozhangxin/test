package com.akan.qf.mvp.adapter.mine;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.ContributeClassBean;
import com.akan.qf.bean.ContributeClassBeansBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/11/26.
 */

public class ArticleClassListAdapter extends RecyclerArrayAdapter<ContributeClassBean> {

    private ArrayList<ContributeClassBean> list;
    private ArticleClassChildAdapter childAdapter;

    public ArticleClassListAdapter(Context context, List<ContributeClassBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ContributeClassBean> {
        private TextView tvName, ivImg, ivImgDown;
        private RecyclerView recycleViewChild;
        private RelativeLayout item;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_article_class);
            tvName = $(R.id.tvName);
            ivImg = $(R.id.ivImg);
            ivImgDown = $(R.id.ivImgDown);
            item = $(R.id.item);
            recycleViewChild = $(R.id.recycleViewChild);
            recycleViewChild.setLayoutManager(new LinearLayoutManager(getContext()));
            list = new ArrayList<>();
            childAdapter = new ArticleClassChildAdapter(getContext(), list);
            recycleViewChild.setAdapter(childAdapter);

        }

        @Override
        public void setData(final ContributeClassBean data) {
            super.setData(data);
            tvName.setText(data.getClass_name());
            childAdapter.setOnOpenClick(new ArticleClassChildAdapter.OnChildOpenClick() {
                @Override
                public void onOpenClick(int position) {
                    childAdapter.notifyItemChanged(position);
                }

                @Override
                public void onAddClick(String class_ids,String class_id,String className) {
                    onOpenClick.onAddClick(data.getClass_id()+","+class_ids,class_id,className);
                }
            });
            if (data.getContributeClassBeans().size() > 0) {
                if (data.isShow()) {
                    childAdapter.clear();
                    childAdapter.addAll(data.getContributeClassBeans());
                    childAdapter.notifyDataSetChanged();
                    ivImg.setVisibility(View.GONE);
                    ivImgDown.setVisibility(View.VISIBLE);
                } else {
                    childAdapter.clear();
                    childAdapter.notifyDataSetChanged();
                    ivImg.setVisibility(View.VISIBLE);
                    ivImgDown.setVisibility(View.GONE);
                }
                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (data.isShow()) {
                            data.setShow(false);
                        } else {
                            data.setShow(true);
                        }
                        onOpenClick.onOpenClick(getDataPosition());
                    }
                });
            } else {
                ivImg.setVisibility(View.INVISIBLE);
                ivImgDown.setVisibility(View.INVISIBLE);
                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOpenClick.onAddClick(data.getClass_id(),data.getClass_id(),data.getClass_name());
                    }
                });
            }
        }
    }

    public interface OnOpenClick {

        void onOpenClick(int position);
        void onAddClick(String class_ids,String class_id,String name);
    }

    private OnOpenClick onOpenClick;

    public void setOnOpenClick(OnOpenClick onOpenClick) {
        this.onOpenClick = onOpenClick;
    }
}
