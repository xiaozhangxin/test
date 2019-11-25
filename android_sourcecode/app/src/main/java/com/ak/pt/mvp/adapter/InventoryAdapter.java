package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.UserBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import com.uniquext.android.widget.view.CornerImageView;

/**
 * Created by admin on 2019/4/16.
 */

public class InventoryAdapter extends RecyclerArrayAdapter<UserBean> {


    public InventoryAdapter(Context context, List<UserBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_account);
    }

    public class MyImageHolder extends BaseViewHolder<UserBean> {


        private TextView tvName;
        private ImageView ivNow;
        private CornerImageView ivAvatar;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            ivAvatar = $(R.id.ivAvatar);
            tvName = $(R.id.tvName);
            ivNow = $(R.id.ivNow);

        }

        @Override
        public void setData(final UserBean data) {

            tvName.setText(data.getStaff_name());
            Glide.with(getContext()).load(Constants.BASE_URL + data.getHead_img()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.error_img).into(ivAvatar);

            if (data.isNow()) {
                ivNow.setVisibility(View.VISIBLE);
            } else {
                ivNow.setVisibility(View.GONE);
            }
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
