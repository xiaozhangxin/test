package com.akan.qf.mvp.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.SignBean;
import com.akan.qf.util.GlideRoundTransform;
import com.akan.qf.view.img.ShowPictureActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/2/13.
 */

public class SignRecordChildAdapter extends RecyclerArrayAdapter<SignBean> {
    private List mImgList;

    public SignRecordChildAdapter(Context context, List<SignBean> list, List<String> imgList) {
        super(context, list);
        mImgList = imgList;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<SignBean> {


        private TextView tvTIme, tvAddress, tvContent;
        private ImageView ivImg;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_sign_new);
            tvTIme = $(R.id.tvTIme);
            tvAddress = $(R.id.tvAddress);
            tvContent = $(R.id.tvContent);
            ivImg = $(R.id.ivImg);
        }

        @Override
        public void setData(final SignBean data) {
            super.setData(data);
            tvTIme.setText(data.getCreate_time().substring(10, 16));
            tvAddress.setText(data.getSign_address());
            tvContent.setText(data.getSign_content());

            if (TextUtils.isEmpty(data.getSign_image())) {
                ivImg.setVisibility(View.GONE);
            } else {
                ivImg.setVisibility(View.VISIBLE);
                Glide.with(getContext())
                        .load(Constants.BASE_URL + data.getSign_image())
                        .error(R.drawable.error_img).transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                        .into(ivImg);

                ivImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mImgList.clear();
                        mImgList.add(data.getSign_image());
                        Intent intent = new Intent(getContext(), ShowPictureActivity.class);
                        intent.putExtra("imagelist", (Serializable) mImgList);
                        intent.putExtra("position", 0);
                        getContext().startActivity(intent);
                    }
                });

            }


        }
    }

}
