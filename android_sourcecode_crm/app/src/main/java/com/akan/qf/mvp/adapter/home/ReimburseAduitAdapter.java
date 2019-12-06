package com.akan.qf.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.RecordListBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2018/10/29.
 */

public class ReimburseAduitAdapter extends RecyclerArrayAdapter<RecordListBean> {


    public ReimburseAduitAdapter(Context context, List<RecordListBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_leave_audit);
    }

    public class MyImageHolder extends BaseViewHolder<RecordListBean> {

        private TextView tvName, tvTime, tvState, tvContent;
        private ImageView img;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            tvName = $(R.id.tvName);
            tvTime = $(R.id.tvTime);
            tvState = $(R.id.tvState);
            tvContent = $(R.id.tvContent);
            img = $(R.id.img);
        }

        @Override
        public void setData(final RecordListBean data) {
            if (getDataPosition() == 0) {
                img.setVisibility(View.GONE);
            } else {
                img.setVisibility(View.VISIBLE);
            }
            //wait_audit:未审核 auditing:审核中 accept:接受 refuse:拒绝
            switch (data.getRecord_state()) {
                case "wait_audit":
                     tvState.setTextColor(getContext().getResources().getColor(R.color.colorSendName4));
                    break;
                case "unauditing":
                    tvState.setTextColor(getContext().getResources().getColor(R.color.colorSendName4));
                    break;
                case "accept":
                    tvState.setTextColor(getContext().getResources().getColor(R.color.colorSendName2));
                    break;
                case "refuse":
                    tvState.setTextColor(getContext().getResources().getColor(R.color.red));
                    break;
                case "auditing":
                    tvState.setTextColor(getContext().getResources().getColor(R.color.colorSendName2));
                    break;
            }
            tvName.setText(data.getRecord_name());
            tvTime.setText(data.getRecord_create_time());
            tvState.setText(data.getRecord_state_show());
            if (TextUtils.isEmpty(data.getRecord_remark())) {
                tvContent.setVisibility(View.GONE);
            } else {
                tvContent.setVisibility(View.VISIBLE);
                tvContent.setText(data.getRecord_remark());
            }

        }
    }


}
