package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.AllFunctionBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class AllFunctionListAdapter extends RecyclerArrayAdapter<AllFunctionBean> {
    public AllFunctionListAdapter(Context context, List<AllFunctionBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<AllFunctionBean> {
        private TextView tvtittle;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_function_left);
            tvtittle = $(R.id.tvtittle);

        }

        @Override
        public void setData(AllFunctionBean data) {
            super.setData(data);
            tvtittle.setText(data.getType());
            if (data.isCkeck()) {
                tvtittle.setBackgroundColor(Color.WHITE);
                Drawable drawableLeft = getContext().getResources().getDrawable(R.drawable.setbar_color_left10);
                drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
                tvtittle.setCompoundDrawables(drawableLeft, null, null, null);
                tvtittle.setScaleX(1.1f);
                tvtittle.setScaleY(1.1f);
                tvtittle.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
            } else {
                tvtittle.setBackgroundColor(getContext().getResources().getColor(R.color.colorActivityBackground));
                tvtittle.setTextColor(getContext().getResources().getColor(R.color.colorTextG3));
                tvtittle.setCompoundDrawables(null, null, null, null);
                tvtittle.setScaleX(1f);
                tvtittle.setScaleY(1f);
            }
        }
    }

}
