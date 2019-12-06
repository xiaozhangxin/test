package com.akan.qf.mvp.fragment.statistics;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;

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

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_img);
            tvTittle = $(R.id.tvTittle);
            recycleView = $(R.id.recycleView);

        }

        @Override
        public void setData(final PhotoListBean data) {
            super.setData(data);
            tvTittle.setText(data.getType_name());
            recycleView.setLayoutManager(new GridLayoutManager(getContext(), 3));
            final CommonAdapter<PhotoListBean.PhotosBean> commonAdapter = new CommonAdapter<PhotoListBean.PhotosBean>(getContext(), R.layout.item_child_img, data.getPhotos()) {
                @Override
                protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, final PhotoListBean.PhotosBean photosBean, int position) {
                    Glide.with(mContext)
                            .load("http://pressure.akan.com.cn/" + photosBean.getImage_url())
                            .placeholder(R.drawable.default_logo)
                            .error(R.drawable.error_img)
                            .into((ImageView) holder.getView(R.id.item_image));
                }
            };
            recycleView.setAdapter(commonAdapter);
//            commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//                    mImgList = new ArrayList();
//                    mImgList.clear();
//                    for (int i = 0; i < data.getPhotos().size(); i++) {
//                        mImgList.add(data.getPhotos().get(i).getImage_url());
//                    }
//                    Intent intent = new Intent(getContext(), ShowPictureActivity.class);
//                    intent.putExtra("imagelist", (Serializable) mImgList);
//                    intent.putExtra("position", position);
//                    getContext().startActivity(intent);
//                }
//
//                @Override
//                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
//                    return false;
//                }
//            });
//
//
//            recycleView.setAdapter(commonAdapter);
        }
    }

}