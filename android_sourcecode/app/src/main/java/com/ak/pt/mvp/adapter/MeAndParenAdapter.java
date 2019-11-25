package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.MeParentBean;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import com.uniquext.android.widget.view.CornerImageView;

/**
 * Created by admin on 2019/5/29.
 */

public class MeAndParenAdapter extends RecyclerArrayAdapter<MeParentBean> {
    public MeAndParenAdapter(Context context, List<MeParentBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<MeParentBean> {
        private TextView tvName, tvJob;
        private CornerImageView img;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_choose_checkperson);
            tvName = $(R.id.tvName);
            tvJob = $(R.id.tvJob);
            img = $(R.id.img);

        }

        @Override
        public void setData(final MeParentBean data) {
            super.setData(data);
            tvName.setText(data.getStaff_name());
            tvJob.setText(data.getJob_name());
            Glide.with(getContext())
                    .load(Constants.BASE_URL + data.getHead_img())
                    .error(R.drawable.error_img)
                    .into(img);


        }
    }


}
