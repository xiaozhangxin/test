package com.ak.pt.mvp.adapter.mall;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.GoodsBean;

import java.util.List;

/**
 * Created by Crazyfzw on 2016/4/16.
 */
public class RecyclerCardviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static enum ITEM_TYPE {
        ITEM_TYPE_Theme,
        ITEM_TYPE_Video
    }
    //数据集
    public List<GoodsBean> mdatas;
    private TextView themeTitle;

    public RecyclerCardviewAdapter(List<GoodsBean> datas){
        super();
        this.mdatas = datas;
    }


    public static interface  OnItemClickListener{
        void onItemClick(View view, int positon);
    }


    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE.ITEM_TYPE_Theme.ordinal()){

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mall_top,parent,false);

            return new ThemeVideoHolder(view);

        }else if(viewType == ITEM_TYPE.ITEM_TYPE_Video.ordinal()){

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mall_list,parent,false);
            return new VideoViewHolder(view);

        }
          return null;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (holder instanceof ThemeVideoHolder){

           themeTitle.setText("励志");

        }else if (holder instanceof VideoViewHolder){
            ((VideoViewHolder)holder).videologo.setImageResource(R.drawable.jilu);


            if (mOnItemClickListener!=null){
                ((VideoViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(((VideoViewHolder)holder).videologo, position);
                    }
                });
            }
        }

    }


    public int getItemViewType(int position){

        return position % 5 == 0 ? ITEM_TYPE.ITEM_TYPE_Theme.ordinal() : ITEM_TYPE.ITEM_TYPE_Video.ordinal();
    }




    @Override
    public int getItemCount() {
        return mdatas.size();
    }


    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == ITEM_TYPE.ITEM_TYPE_Theme.ordinal()
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }


    public class ThemeVideoHolder extends RecyclerView.ViewHolder{

        public ThemeVideoHolder(View itemView) {
            super(itemView);
            themeTitle = (TextView) itemView.findViewById(R.id.tvAllOrder);
        }
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        public ImageView videologo;


        public VideoViewHolder(View itemView) {
            super(itemView);
            videologo = (ImageView) itemView.findViewById(R.id.ivImgTop);

        }
    }
}

