package com.akan.qf.mvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.SignBean;
import com.akan.qf.bean.StaffSignUnionBean;
import com.akan.qf.view.img.ShowPictureActivity;
import com.akan.qf.util.GlideRoundTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;

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
        private RecyclerView recyclerView;
        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_sign_record);
            tvName = $(R.id.tvName);
            tvAddress = $(R.id.tvAddress);
            tvSignNum = $(R.id.tvSignNum);
            tvDate = $(R.id.tvDate);
            ivImg = $(R.id.ivImg);
            recyclerView = $(R.id.recyclerView);
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

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(RecyclerView.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(new CommonAdapter<StaffSignUnionBean>(getContext(),R.layout.sign_detail_name,data.getStaffSignUnionList()) {

                @Override
                protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, StaffSignUnionBean bean, int position) {
                    TextView tvType = holder.getView(R.id.tvType);
                    tvType.setText(bean.getSign_name());
                }

            });

        }
    }

}
