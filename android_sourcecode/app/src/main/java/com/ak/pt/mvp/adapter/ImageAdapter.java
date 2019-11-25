package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
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
 * Created by lenove on 2017/7/24.
 */

public class ImageAdapter extends RecyclerArrayAdapter<String> {


    public ImageAdapter(Context context, List<String> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_image_show);
    }

    public class MyImageHolder extends BaseViewHolder<String> {

        private ImageView img, delete;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            img = $(R.id.item_image);
            delete = $(R.id.delete);

        }

        @Override
        public void setData(final String data) {
            if (TextUtils.isEmpty(data)) {
                delete.setVisibility(View.GONE);
            } else {
                delete.setVisibility(View.VISIBLE);
            }
            if (data.contains("images")) {
                Glide.with(getContext())
                        .load(Constants.BASE_URL+data)
                        .error(R.drawable.camera_h).transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                        .into(img);
            } else {
                Glide.with(getContext())
                        .load(data)
                        .error(R.drawable.camera_h).transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                        .into(img);
            }

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onDeleteClick.onDeleteClick(getDataPosition());
                }
            });
        }
    }

    public interface OnDeleteClick {

        void onDeleteClick(int data);
    }

    private OnDeleteClick onDeleteClick;

    public void setOnDeleteClick(OnDeleteClick onDeleteClick) {
        this.onDeleteClick = onDeleteClick;
    }

}
