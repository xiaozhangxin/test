package com.ak.pt.mvp.adapter.people;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.RecordListBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class EntryAduitAdapter extends RecyclerArrayAdapter<RecordListBean> {


    public EntryAduitAdapter(Context context, List<RecordListBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new EntryAduitAdapter.MyImageHolder(parent, R.layout.item_people_audit);
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
                case "unAuditing":
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
                tvContent.setText("备注："+data.getRecord_remark());
            }

        }
    }


}
