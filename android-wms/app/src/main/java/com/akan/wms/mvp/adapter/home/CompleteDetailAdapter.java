package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.CompleteDecBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class CompleteDetailAdapter extends RecyclerArrayAdapter<CompleteDecBean.CompleteDecLinesBean> {
    public CompleteDetailAdapter(Context context, List<CompleteDecBean.CompleteDecLinesBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    private String mType;

    public void setType(String type) {
        mType = type;
    }

    public class ViewHolder extends BaseViewHolder<CompleteDecBean.CompleteDecLinesBean> {
        private TextView tvTittle, tvNo, tvItemNo, tvName, tvOperate, tvGroup,
                tvDeport, tvOne, tvCompleteNum, tvDeviceId, tvPassNum, tvNotPassNum, tvNotPassKg,
                tvEveryKg, tvSecond, tvPeopleTime, tvPrepareTime, tvWorkTime, tvTwo, tvThree, tvFour, tvSix, tvSeven, tvEight, tvNine;
        private ImageView tvDelete;
        private LinearLayout llBottom, llOne, llTwo, llThree;
        private View lineOne, lineTwo, lineThree;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_complete_detail);
            tvTittle = $(R.id.tvTittle);
            tvNo = $(R.id.tvNo);
            tvDelete = $(R.id.tvDelete);
            tvItemNo = $(R.id.tvItemNo);
            tvName = $(R.id.tvName);
            tvOperate = $(R.id.tvOperate);
            tvGroup = $(R.id.tvGroup);
            tvDeviceId = $(R.id.tvDeviceId);
            tvCompleteNum = $(R.id.tvCompleteNum);
            tvPassNum = $(R.id.tvPassNum);
            tvNotPassNum = $(R.id.tvNotPassNum);
            tvNotPassKg = $(R.id.tvNotPassKg);
            tvEveryKg = $(R.id.tvEveryKg);
            tvSecond = $(R.id.tvSecond);
            tvPeopleTime = $(R.id.tvPeopleTime);
            tvPrepareTime = $(R.id.tvPrepareTime);
            tvWorkTime = $(R.id.tvWorkTime);
            tvDeport = $(R.id.tvDeport);
            llBottom = $(R.id.llBottom);
            llOne = $(R.id.llOne);
            llTwo = $(R.id.llTwo);
            llThree = $(R.id.llThree);
            lineOne = $(R.id.lineOne);
            lineTwo = $(R.id.lineTwo);
            lineThree = $(R.id.lineThree);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            tvFour = $(R.id.tvFour);
            tvSix = $(R.id.tvSix);
            tvSeven = $(R.id.tvSeven);
            tvEight = $(R.id.tvEight);
            tvNine = $(R.id.tvNine);


        }

        @Override
        public void setData(CompleteDecBean.CompleteDecLinesBean data) {
            super.setData(data);

            switch (mType) {
                case "1":
                    llBottom.setVisibility(View.GONE);
                    llOne.setVisibility(View.VISIBLE);
                    llTwo.setVisibility(View.VISIBLE);
                    llThree.setVisibility(View.VISIBLE);
                    lineOne.setVisibility(View.VISIBLE);
                    lineTwo.setVisibility(View.VISIBLE);
                    lineThree.setVisibility(View.VISIBLE);

                    break;
                case "0":
                    llBottom.setVisibility(View.VISIBLE);
                    llOne.setVisibility(View.GONE);
                    llTwo.setVisibility(View.GONE);
                    lineOne.setVisibility(View.GONE);
                    lineTwo.setVisibility(View.GONE);
                    break;
            }
            tvTittle.setText("完工申报信息" + (getDataPosition() + 1));

            tvNo.setText(data.getDoc_no() + "");
            tvItemNo.setText(data.getItem_code());
            tvName.setText(data.getItem_name() + "/" + data.getItem_spec());
            tvOperate.setText(data.getOperator_name());
            tvGroup.setText(data.getClass_team_name());


            tvDeviceId.setText(data.getMac_code());
            tvCompleteNum.setText(data.getComplete_qty());
            tvPassNum.setText(data.getQualified_qty());
            tvNotPassNum.setText(data.getScrap_qty());
            tvNotPassKg.setText(data.getScrap_weight());

            tvEveryKg.setText(data.getEve_weight());
            tvSecond.setText(data.getSpeed());
            tvPeopleTime.setText(data.getLabor_hours());
            tvPrepareTime.setText(data.getMac_hours());
            tvWorkTime.setText(data.getMac_pre_hours());
            tvDeport.setText(data.getWh_name());

            tvOne.setText(data.getMod_code());
            tvTwo.setText(data.getMod_weight());
            tvThree.setText(data.getEle_weight());
            tvFour.setText(data.getMod_outline_weight());
            tvSix.setText(data.getMod_inserts_weight());
            tvSeven.setText(data.getBush_weight());
            tvEight.setText(data.getMod_amount());
            tvNine.setText(data.getEle_amount());


        }
    }


}
