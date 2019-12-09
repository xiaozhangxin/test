package com.ak.pt.mvp.fragment.statistics;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.PhotoListBean;
import com.ak.pt.util.img.ShowPictureActivity;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2019/3/18.
 */

public class ImgTwoAdapter extends RecyclerArrayAdapter<PhotoListBean> {

    private CommonAdapter<PhotoListBean.PhotosBean> commonAdapter;
    private List mImgList;

    public ImgTwoAdapter(Context context, List<PhotoListBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<PhotoListBean> {
        private RecyclerView recycleView;
        private TextView tvTittle;
        private TextView tvDelete;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_img);
            tvTittle = $(R.id.tvTittle);
            tvDelete = $(R.id.tvDelete);
            recycleView = $(R.id.recycleView);

        }

        @Override
        public void setData(final PhotoListBean data) {
            super.setData(data);
            tvTittle.setText(data.getType_name());
            tvDelete.setVisibility(View.GONE);
            recycleView.setLayoutManager(new GridLayoutManager(getContext(), 3));
            commonAdapter = new CommonAdapter<PhotoListBean.PhotosBean>(getContext(), R.layout.item_child_img, data.getPhotos()) {
                @Override
                protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, final PhotoListBean.PhotosBean photosBean, int position) {
                    Glide.with(mContext)
                            .load(Constants.BASE_URL + photosBean.getImage_url())
                            .thumbnail( 0.1f )
                            .error(R.drawable.error_img)
                            .into((ImageView) holder.getView(R.id.item_image));

                }
            };
            commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    mImgList = new ArrayList();
                    mImgList.clear();
                    for (int i = 0; i < data.getPhotos().size(); i++) {
                        mImgList.add(data.getPhotos().get(i).getImage_url());
                    }
                    Intent intent = new Intent(getContext(), ShowPictureActivity.class);
                    intent.putExtra("imagelist", (Serializable) mImgList);
                    intent.putExtra("position", position);
                    getContext().startActivity(intent);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {

                    onLongTimeDeleteClickListener.onLongDelete(getDataPosition(),position);
                    return false;
                }
            });


            recycleView.setAdapter(commonAdapter);
        }
    }


    public interface OnLongTimeDeleteClickListener {
        void onLongDelete(int position, int childPosition);
    }

    private OnLongTimeDeleteClickListener onLongTimeDeleteClickListener;

    public void setOnLongTimeDeleteClickListener(OnLongTimeDeleteClickListener onLongTimeDeleteClickListener) {
        this.onLongTimeDeleteClickListener = onLongTimeDeleteClickListener;
    }
}