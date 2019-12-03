package com.akan.wms.mvp.adapter.mix;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.ShipBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class SendMixListAdapter extends RecyclerArrayAdapter<ShipBean> {
public SendMixListAdapter(Context context, List<ShipBean> list) {
        super(context, list);
        }

@Override
public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new SendMixListAdapter.ViewHolder(parent, viewType);
        }

public class ViewHolder extends BaseViewHolder<ShipBean> {
    private TextView tvNo, tvState, tvOne, tvTwo, tvThree;
    private View line2;
    public ViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, R.layout.item_puschase_return);
        tvNo = $(R.id.tvNo);
        tvState = $(R.id.tvState);
        tvOne = $(R.id.tvOne);
        tvTwo = $(R.id.tvTwo);
        tvThree = $(R.id.tvThree);
        line2 = $(R.id.line2);

    }

    @Override
    public void setData(ShipBean data) {
        super.setData(data);
        tvNo.setText(data.getDoc_no() + "");
        tvState.setText(data.getStatus_show());
        tvThree.setText(data.getUpdate_time());
        tvOne.setVisibility(View.GONE);
        line2.setVisibility(View.GONE);
       //tvOne.setText("制单人：" + data.getWh_man_name());
        tvTwo.setText("单据类型：" + data.getDoc_type_name());
    }
}

}
