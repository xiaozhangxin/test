package com.akan.qf.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.LeaveBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2018/10/24.
 */

public class LeaveAduitAdapter extends RecyclerArrayAdapter<LeaveBean.AskLeaveAuditBeansBean> {


    public LeaveAduitAdapter(Context context, List<LeaveBean.AskLeaveAuditBeansBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_leave_audit);
    }

    public class MyImageHolder extends BaseViewHolder<LeaveBean.AskLeaveAuditBeansBean> {

        private TextView tvName, tvTime, tvState, tvContent,tvCopy;
        private ImageView img;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            tvName = $(R.id.tvName);
            tvTime = $(R.id.tvTime);
            tvState = $(R.id.tvState);
            tvContent = $(R.id.tvContent);
            img = $(R.id.img);
            tvCopy = $(R.id.tvCopy);

        }

        @Override
        public void setData(final LeaveBean.AskLeaveAuditBeansBean data) {
            if (getDataPosition() == 0) {
                img.setVisibility(View.GONE);
            } else {
                img.setVisibility(View.VISIBLE);
            }
            //wait_audit:未审核 auditing:审核中 accept:接受 refuse:拒绝
            switch (data.getAudit_state()) {
                case "wait_audit":
                   // tvState.setTextColor(getContext().getResources().getColor(R.color.indicator_color));
                    break;
                case "auditing":
                    tvState.setTextColor(getContext().getResources().getColor(R.color.colorSendName4));
                    break;
                case "pass":
                    tvState.setTextColor(getContext().getResources().getColor(R.color.colorSendName2));
                    break;
                case "not_pass":
                    tvState.setTextColor(getContext().getResources().getColor(R.color.red));
                    break;
            }
            if (TextUtils.isEmpty(data.getAudit_content())) {
                tvContent.setVisibility(View.GONE);
            } else {
                tvContent.setVisibility(View.VISIBLE);
                tvContent.setText("备注："+data.getAudit_content());
            }
            if (TextUtils.isEmpty(data.getCc_person_name())) {
                tvCopy.setVisibility(View.GONE);
            }else {
                tvCopy.setVisibility(View.VISIBLE);
                tvCopy.setText("抄送人："+data.getCc_person_name());
            }
            tvName.setText(data.getStaff_name());
            tvTime.setText(data.getCreate_time());
            tvState.setText(data.getAudit_state_show());


        }
    }


}
