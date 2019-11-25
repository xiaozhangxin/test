package com.ak.pt.mvp.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.SignBean;
import com.ak.pt.util.img.ShowPictureActivity;
import com.ak.pt.util.GlideRoundTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2018/7/2.
 */

public class SignRecordAdapter extends RecyclerArrayAdapter<SignBean> {
    private List mImgList;

    public SignRecordAdapter(Context context, List<SignBean> list, List<String> imgList) {
        super(context, list);
        mImgList = imgList;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<SignBean> {


        private TextView tvName, tvAddress, tvSignNum, tvDate;
        private ImageView ivImg;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_sign);
            tvName = $(R.id.tvName);
            tvAddress = $(R.id.tvAddress);
            tvSignNum = $(R.id.tvSignNum);
            tvDate = $(R.id.tvDate);
            ivImg = $(R.id.ivImg);
        }

        @Override
        public void setData(final SignBean data) {
            super.setData(data);
            tvName.setText( data.getStaff_name());
            tvAddress.setText(data.getSign_address());
            tvSignNum.setText( data.getSign_number());
            tvDate.setText(data.getCreate_time());
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
