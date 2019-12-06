package com.akan.qf.mvp.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.FileListNewBean;
import com.akan.qf.util.GlideRoundTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/2/22.
 */

public class ImageFileAdapter extends RecyclerArrayAdapter<FileListNewBean> {


    private String type;

    public ImageFileAdapter(Context context, List<FileListNewBean> objects, String type) {
        super(context, objects);
        this.type = type;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_image_show);
    }

    public class MyImageHolder extends BaseViewHolder<FileListNewBean> {

        private ImageView img, delete;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            img = $(R.id.item_image);
            delete = $(R.id.delete);

        }

        @Override
        public void setData(final FileListNewBean data) {
            if (TextUtils.isEmpty(data.getFile_url())) {
                delete.setVisibility(View.GONE);
                Glide.with(getContext())
                        .load(Constants.BASE_URL + data.getFile_url())
                        .error(R.drawable.camera_h).transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                        .into(img);
            } else {
                delete.setVisibility(View.VISIBLE);
                if (data.is_up()) {
                    Glide.with(getContext())
                            .load(data.getFile_url())
                            .error(R.drawable.error_img).transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                            .into(img);
                } else {
                    Glide.with(getContext())
                            .load(Constants.BASE_URL + data.getFile_url())
                            .error(R.drawable.error_img).transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                            .into(img);
                }
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

