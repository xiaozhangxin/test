package com.akan.qf.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.FileListBean;
import com.akan.qf.util.GlideRoundTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2018/10/29.
 */

public class ImageReimburseAdapter extends RecyclerArrayAdapter<FileListBean> {


    public ImageReimburseAdapter(Context context, List<FileListBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_file);
    }

    public class MyImageHolder extends BaseViewHolder<FileListBean> {

        private ImageView img;
        private TextView tvName;
        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            img = $(R.id.item_image);
            tvName = $(R.id.tvName);
        }

        @Override
        public void setData(final FileListBean data) {
            if (TextUtils.isEmpty(data.getFile_name())) {
                tvName.setText("未命名");
            } else {
                tvName.setText(data.getFile_name());
            }

            String upUrl = data.getFile_url();
            if (upUrl.endsWith(".jpg") | upUrl.endsWith(".png")|upUrl.endsWith(".jpeg")) {
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

    }
}

