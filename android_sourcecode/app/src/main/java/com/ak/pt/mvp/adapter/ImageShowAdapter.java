package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.util.GlideRoundTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/3/20.
 */

public class ImageShowAdapter extends RecyclerArrayAdapter<String> {


    public ImageShowAdapter(Context context, List<String> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_image_show);
    }

    public class MyImageHolder extends BaseViewHolder<String> {

        private ImageView img;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            img = $(R.id.item_image);

        }

        @Override
        public void setData(final String data) {
            Glide.with(getContext())
                    .load(Constants.BASE_URL+data)
                    .error(R.drawable.error_img).transform(new CenterCrop(getContext()),new GlideRoundTransform(getContext(),12))
                    .into(img);


        }
    }


}
