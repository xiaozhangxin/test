package com.akan.qf.mvp.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
 * Created by admin on 2019/2/28.
 */

public class CopyAdapter extends RecyclerArrayAdapter<MeParentBean> {


    public CopyAdapter(Context context, List<MeParentBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_copy);
    }

    public class MyImageHolder extends BaseViewHolder<MeParentBean> {


        private TextView tvCheckPersonName;
        private ImageView ivCheckDelete;
        private CircleImageView circleImageVIew;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            tvCheckPersonName = $(R.id.tvCheckPersonName);
            circleImageVIew = $(R.id.circleImageVIew);
            ivCheckDelete = $(R.id.ivCheckDelete);
        }

        @Override
        public void setData(final MeParentBean data) {
            if (TextUtils.isEmpty(data.getStaff_id())) {
                ivCheckDelete.setVisibility(View.GONE);
                tvCheckPersonName.setText("");
                Glide.with(getContext()).load(R.drawable.check_img).into(circleImageVIew);
            } else {
                ivCheckDelete.setVisibility(View.VISIBLE);
                tvCheckPersonName.setText(data.getStaff_name());
                Glide.with(getContext()).load(Constants.BASE_URL + data.getHead_img()).error(R.drawable.error_img).into(circleImageVIew);
            }

            ivCheckDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteClickListener.delete(getDataPosition());
                }
            });
        }
    }


    public interface OnDeleteClickListener {
        void delete(int position);
    }

    private OnDeleteClickListener deleteClickListener;

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.deleteClickListener = onDeleteClickListener;
    }

}
