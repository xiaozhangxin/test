package com.akan.qf.mvp.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.util.GlideRoundTransform;
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
                Glide.with(getContext())
                        .load(R.drawable.camera_h)
                        .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                        .into(img);

            } else {
                delete.setVisibility(View.VISIBLE);
                if (data.endsWith(".jpg") | data.endsWith(".png")| data.endsWith(".jpeg")) {
                    if (data.contains("storage/")) {
                        Glide.with(getContext())
                                .load(data)
                                .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                                .into(img);
                    } else {
                        Glide.with(getContext())
                                .load(Constants.BASE_URL + data)
                                .error(R.drawable.camera_h).transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                                .into(img);
                    }
                } else if (data.endsWith(".txt")) {
                    Glide.with(getContext())
                            .load(R.drawable.file_txt_new)
                            .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                            .into(img);
                } else if (data.endsWith(".docx") | data.endsWith(".doc")) {
                    Glide.with(getContext())
                            .load(R.drawable.file_word_new)
                            .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                            .into(img);
                } else if (data.endsWith(".xlsx") | data.endsWith(".xls")) {
                    Glide.with(getContext())
                            .load(R.drawable.file_xls_new)
                            .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                            .into(img);
                } else if (data.endsWith(".html")) {
                    Glide.with(getContext())
                            .load(R.drawable.file_html_new)
                            .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                            .into(img);
                } else if (data.endsWith(".pdf")) {
                    Glide.with(getContext())
                            .load(R.drawable.file_pdf_new)
                            .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                            .into(img);
                }else if (data.endsWith(".zip")|data.endsWith(".rar")) {
                    Glide.with(getContext())
                            .load(R.drawable.file_rar_new)
                            .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                            .into(img);
                } else {
                    Glide.with(getContext())
                            .load(R.drawable.file_new)
                            .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
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
