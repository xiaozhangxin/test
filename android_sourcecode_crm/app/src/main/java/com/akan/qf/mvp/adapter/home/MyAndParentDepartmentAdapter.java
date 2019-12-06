package com.akan.qf.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.MeParentBean;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admin on 2018/10/23.
 */

public class MyAndParentDepartmentAdapter extends RecyclerArrayAdapter<MeParentBean> {
    public MyAndParentDepartmentAdapter(Context context, List<MeParentBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<MeParentBean> {
        private TextView tvName, tvJob;
        private CircleImageView img;

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
