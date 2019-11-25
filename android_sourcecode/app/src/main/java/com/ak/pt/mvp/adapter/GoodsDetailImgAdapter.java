package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.DetailImgBean;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/5/14.
 */

public class GoodsDetailImgAdapter extends RecyclerArrayAdapter<DetailImgBean> {
    public GoodsDetailImgAdapter(Context context, List<DetailImgBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<DetailImgBean> {


        private ImageView img;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_detail_img);
            img = $(R.id.img);

        }

        @Override
        public void setData(DetailImgBean data) {
            super.setData(data);
            Glide.with(getContext()).load(Constants.BASE_URL+data.getSrc())
                    .error(R.drawable.error_img).into(img);


        }
    }

}

