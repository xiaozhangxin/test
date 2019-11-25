package com.ak.pt.mvp.adapter.water;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

public class ImageShowNameAdapter extends RecyclerArrayAdapter<WarrantyFileBean> {


    public ImageShowNameAdapter(Context context, List<WarrantyFileBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_image_show_name);
    }

    public class MyImageHolder extends BaseViewHolder<WarrantyFileBean> {

        private ImageView img;
        private TextView tvType;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            img = $(R.id.item_image);
            tvType = $(R.id.tvType);

        }

        @Override
        public void setData(final WarrantyFileBean data) {
            // * img_type : 产品安装完成(success),保修单图片(bill)其他图片(other)
            switch (data.getImg_type()) {
                case "success":
                    tvType.setText("产品安装照片");
                    break;
                case "bill":
                    tvType.setText("保修单凭证照片");
                    break;
                case "other":
                    tvType.setText("测试照片");
                    break;
            }
            Glide.with(getContext())
                    .load(Constants.BASE_URL + data.getImg_url())
                    .error(R.drawable.error_img).transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                    .into(img);


        }
    }


}
