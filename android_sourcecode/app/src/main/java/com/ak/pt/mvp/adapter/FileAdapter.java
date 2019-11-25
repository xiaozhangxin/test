package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.FileBean;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/3/27.
 */

public class FileAdapter extends RecyclerArrayAdapter<FileBean> {
    public FileAdapter(Context context, List<FileBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<FileBean> {


        private TextView tvName, tvOffice, tvTime;
        private ImageView img;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_office_list);
            tvName = $(R.id.tvName);
            tvTime = $(R.id.tvTime);
            img = $(R.id.ivImg);
            tvOffice = $(R.id.tvoffice);
        }

        @Override
        public void setData(FileBean data) {
            super.setData(data);
            //wait_audit:未审核 auditing:审核中 accept:接受 refuse:拒绝
            tvName.setText(data.getStaff_name());
            tvOffice.setText(data.getPaper_name());
            tvTime.setText(data.getPaper_create_time());

            String upUrl = data.getPaper_url();
            if (upUrl.endsWith(".jpg") | upUrl.endsWith(".png")|upUrl.endsWith(".jpeg")) {
                Glide.with(getContext())
                        .load(R.drawable.file_png)
                        .into(img);
            } else if (upUrl.endsWith(".txt")) {
                Glide.with(getContext())
                        .load(R.drawable.file_txt)
                        .into(img);
            } else if (upUrl.endsWith(".docx") | upUrl.endsWith(".doc")) {
                Glide.with(getContext())
                        .load(R.drawable.file_word)
                        .into(img);
            } else if (upUrl.endsWith(".xlsx") | upUrl.endsWith(".xls")) {
                Glide.with(getContext())
                        .load(R.drawable.file_xlsx)
                        .into(img);
            } else if (upUrl.endsWith(".html")) {
                Glide.with(getContext())
                        .load(R.drawable.file_html)
                        .into(img);
            } else if (upUrl.endsWith(".pdf")) {
                Glide.with(getContext())
                        .load(R.drawable.file_pdf)
                        .into(img);
            } else if (upUrl.endsWith(".zip") | upUrl.endsWith(".rar")) {
                Glide.with(getContext())
                        .load(R.drawable.file_zip)
                        .into(img);
            } else {
                Glide.with(getContext())
                        .load(R.drawable.file_unknow)
                        .into(img);
            }

        }

    }


}

