package com.ak.pt.mvp.adapter.water;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.WarrantyFileBean;
import com.ak.pt.util.GlideRoundTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/5/24.
 */

public class ImageWarrantyAdapter extends RecyclerArrayAdapter<WarrantyFileBean> {


    public ImageWarrantyAdapter(Context context, List<WarrantyFileBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_image_show);
    }

    public class MyImageHolder extends BaseViewHolder<WarrantyFileBean> {

        private ImageView img, delete;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            img = $(R.id.item_image);
            delete = $(R.id.delete);

        }

        @Override
        public void setData(final WarrantyFileBean data) {
            if (TextUtils.isEmpty(data.getImg_id())) {
                if (TextUtils.isEmpty(data.getImg_url())) {
                    delete.setVisibility(View.GONE);
                    Glide.with(getContext())
                            .load(R.drawable.camera_h)
                            .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                            .into(img);
                } else {
                    delete.setVisibility(View.VISIBLE);
                    Glide.with(getContext())
                            .load(data.getImg_url()).error(R.drawable.error_img)
                            .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                            .into(img);
                }
            } else {
                delete.setVisibility(View.VISIBLE);
                Glide.with(getContext())
                        .load(Constants.BASE_URL + data.getImg_url())
                        .error(R.drawable.error_img).transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
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
