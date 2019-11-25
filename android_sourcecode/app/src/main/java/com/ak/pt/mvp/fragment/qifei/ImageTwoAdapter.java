package com.ak.pt.mvp.fragment.qifei;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ak.pt.R;
import com.ak.pt.util.GlideRoundTransform;
import com.ak.pt.util.img.ShowPictureTwoActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/12/3.
 */

public class ImageTwoAdapter extends RecyclerArrayAdapter<PressurePageBean.PiptbBaseTypeBean> {
    public ImageTwoAdapter(Context context, List<PressurePageBean.PiptbBaseTypeBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<PressurePageBean.PiptbBaseTypeBean> {


        private TextView tvType;
        private RecyclerView recyclerView;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_done_img);
            tvType = $(R.id.tvType);
            recyclerView = $(R.id.recyclerView);
        }

        @Override
        public void setData(PressurePageBean.PiptbBaseTypeBean data) {
            super.setData(data);
            tvType.setText(data.getType_name());
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
            final CommonAdapter<PressurePageBean.PiptbBaseTypeBean.Photos> commonAdapter = new CommonAdapter<PressurePageBean.PiptbBaseTypeBean.Photos>(getContext(), R.layout.item_one_img, data.getPhotos()) {
                @Override
                protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, PressurePageBean.PiptbBaseTypeBean.Photos Photos, int position) {
                    Glide.with(mContext)
                            .load("http://qifei.akan.com.cn:8088/AKMaster/" + Photos.getImage_url())
                            .transform(new CenterCrop(mContext), new GlideRoundTransform(mContext, 12))
                            .placeholder(R.drawable.error_img)
                            .into((ImageView) holder.getView(R.id.img));
                }


            };
            recyclerView.setAdapter(commonAdapter);
            commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    Intent intent = new Intent(getContext(), ShowPictureTwoActivity.class);
                    List<PressurePageBean.PiptbBaseTypeBean.Photos> datas = commonAdapter.getDatas();
                    List<String> list=new ArrayList<String>();
                    for (int i=0;i<datas.size();i++){
                        list.add(datas.get(i).getImage_url());
                    }
                    intent.putExtra("imagelist", (Serializable) list);
                    intent.putExtra("position", position);
                    getContext().startActivity(intent);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
        }
    }

}
