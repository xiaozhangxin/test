package com.ak.pt.mvp.adapter.water;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.FixFileBean;
import com.ak.pt.util.GlideRoundTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/5/23.
 */

public class ImageFixAdapter extends RecyclerArrayAdapter<FixFileBean> {


    public ImageFixAdapter(Context context, List<FixFileBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_image_show);
    }

    public class MyImageHolder extends BaseViewHolder<FixFileBean> {

        private ImageView img, delete;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            img = $(R.id.item_image);
            delete = $(R.id.delete);

        }

        @Override
        public void setData(final FixFileBean data) {
            if (TextUtils.isEmpty(data.getFile_id())) {
                if (TextUtils.isEmpty(data.getFile_url())) {
                    delete.setVisibility(View.GONE);
                    Glide.with(getContext())
                            .load(R.drawable.camera_h)
                            .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                            .into(img);
                } else {
                    delete.setVisibility(View.VISIBLE);
                    Glide.with(getContext())
                            .load(data.getFile_url()).error(R.drawable.error_img)
                            .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                            .into(img);
                }
            } else {
                delete.setVisibility(View.VISIBLE);
                String upUrl = data.getFile_url();
                if (upUrl.endsWith(".jpg") | upUrl.endsWith(".png")|upUrl.endsWith(".jpeg")| upUrl.endsWith(".JPEG")|upUrl.endsWith(".PNG")|upUrl.endsWith(".JPG")) {
                    Glide.with(getContext())
                            .load(Constants.BASE_URL + data.getFile_url())
                            .error(R.drawable.error_img).transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                            .into(img);
                } else if (upUrl.endsWith(".txt")) {
                    Glide.with(getContext())
                            .load(R.drawable.file_txt_new)
                            .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                            .into(img);
                } else if (upUrl.endsWith(".docx") | upUrl.endsWith(".doc")) {
                    Glide.with(getContext())
                            .load(R.drawable.file_word_new)
                            .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                            .into(img);
                } else if (upUrl.endsWith(".xlsx") | upUrl.endsWith(".xls")) {
                    Glide.with(getContext())
                            .load(R.drawable.file_xls_new)
                            .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                            .into(img);
                } else if (upUrl.endsWith(".html")) {
                    Glide.with(getContext())
                            .load(R.drawable.file_html_new)
                            .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                            .into(img);
                } else if (upUrl.endsWith(".pdf")) {
                    Glide.with(getContext())
                            .load(R.drawable.file_pdf_new)
                            .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                            .into(img);
                }else if (upUrl.endsWith(".zip")|upUrl.endsWith(".rar")) {
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
