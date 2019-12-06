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
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/11/26.
 */

public class ArticleClassChildAdapter extends RecyclerArrayAdapter<ContributeClassBean> {
    private ArrayList<ContributeClassBean> list;
    private ArticleClassChilTwodAdapter childAdapter;

    public ArticleClassChildAdapter(Context context, List<ContributeClassBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ContributeClassBean> {
        private TextView tvName, ivImgDown, ivImg;
        private RecyclerView recycleViewChild;
        private RelativeLayout item;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_article_class);
            tvName = $(R.id.tvName);
            ivImg = $(R.id.ivImg);
            item = $(R.id.item);ivImgDown = $(R.id.ivImgDown);

            recycleViewChild = $(R.id.recycleViewChild);
            recycleViewChild.setLayoutManager(new LinearLayoutManager(getContext()));
            list = new ArrayList<>();
            childAdapter = new ArticleClassChilTwodAdapter(getContext(), list);
            recycleViewChild.setAdapter(childAdapter);

        }

        @Override
        public void setData(final ContributeClassBean data) {
            super.setData(data);
            tvName.setText(data.getClass_name());
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
                        onChildOpenClick.onOpenClick(getDataPosition());
                    }
                });
            } else {
                ivImg.setVisibility(View.GONE);
                ivImgDown.setVisibility(View.GONE);
                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onChildOpenClick.onAddClick(data.getClass_id(),data.getClass_id(),data.getClass_name());
                    }
                });
            }
            childAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    onChildOpenClick.onAddClick(data.getClass_id()+","+childAdapter.getItem(position).getClass_id(),childAdapter.getItem(position).getClass_id(),childAdapter.getItem(position).getClass_name());
                }
            });



        }


    }

    public interface OnChildOpenClick {

        void onOpenClick(int position);
        void onAddClick(String  class_ids,String class_id,String className);
    }

    private OnChildOpenClick onChildOpenClick;

    public void setOnOpenClick(OnChildOpenClick onChildOpenClick) {
        this.onChildOpenClick = onChildOpenClick;
    }
}