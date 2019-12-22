package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.ProductionOrderBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class CompleteAddAdapter extends RecyclerArrayAdapter<ProductionOrderBean> {
    public CompleteAddAdapter(Context context, List<ProductionOrderBean> list) {
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

    public class ViewHolder extends BaseViewHolder<ProductionOrderBean> {
        private TextView tvTittle, tvNo, tvItemNo, tvName, tvOperate, tvGroup,
                tvDeport, tvOne;
        private ImageView tvDelete;
        private LinearLayout llBottom, llOne, llTwo, llThree;
        private View lineOne, lineTwo, lineThree;
        private EditText tvCompleteNum, tvDeviceId, tvPassNum, tvNotPassNum, tvNotPassKg,
                tvEveryKg, tvSecond, tvPeopleTime, tvPrepareTime, tvWorkTime, tvThree,
                tvTwo, tvFour, tvFive, tvSix, tvSeven, tvEight, tvNine;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_add_complete);
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
            tvFive = $(R.id.tvFive);
            tvSix = $(R.id.tvSix);
            tvSeven = $(R.id.tvSeven);
            tvEight = $(R.id.tvEight);
            tvNine = $(R.id.tvNine);


        }

        @Override
        public void setData(final ProductionOrderBean data) {
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
                case "2":
                    llBottom.setVisibility(View.VISIBLE);
                    llOne.setVisibility(View.GONE);
                    llTwo.setVisibility(View.GONE);
                    lineOne.setVisibility(View.GONE);
                    lineTwo.setVisibility(View.GONE);
                    break;
            }
            tvTittle.setText("完工申报信息" + (getDataPosition() + 1));

            tvNo.setText(data.getDoc_no());
            tvItemNo.setText(data.getItem_code());
            tvName.setText(data.getItem_name() + "/" + data.getItem_spec());
            tvOperate.setText(data.getOperator_Name());
            tvGroup.setText(data.getClass_team_name());
            tvOne.setText(data.getMod_name());
            tvDeport.setText(data.getWh_name());


            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStockListener.onDelete(getDataPosition());
                }
            });
            tvOperate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStockListener.onOperate(getDataPosition());
                }
            });
            tvGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStockListener.onGroup(getDataPosition());
                }
            });
            tvDeport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStockListener.onDeport(getDataPosition());
                }
            });
            tvOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStockListener.onCode(getDataPosition());
                }
            });

            tvDeviceId.setText(data.getMac_code());//设备编号
            tvDeviceId.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    data.setMac_code(s.toString());

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


            tvCompleteNum.setText(String.valueOf(data.getApply_qty()));//完工数量
            tvPassNum.setText(String.valueOf(data.getQualified_qty()));//合格数量
            tvNotPassNum.setText(String.valueOf(data.getScrap_qty()));//报废数量

            tvPassNum.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String passS = s.toString();
                    Double mPass = 0.0;
                    if (TextUtils.isEmpty(passS)){
                        data.setQualified_qty(0);
                    }else {
                         mPass = Double.valueOf(passS);
                        data.setQualified_qty(mPass);
                    }
                    double total = data.getScrap_qty() + mPass;
                    data.setApply_qty(total);
                    tvCompleteNum.setText(total+"");
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            tvNotPassNum.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String notPass = s.toString();
                    Double mNotPass=0.0;
                    if (TextUtils.isEmpty(notPass)){
                        data.setScrap_qty(0);
                    }else {
                        mNotPass = Double.valueOf(notPass);
                        data.setScrap_qty(mNotPass);


                    }
                    double total = data.getQualified_qty() + mNotPass;
                    data.setApply_qty(total);
                    tvCompleteNum.setText(total+"");
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            tvNotPassKg.setText(data.getScrap_weight());//报废重量
            tvNotPassKg.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    data.setScrap_weight(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


            tvEveryKg.setText(data.getEve_weight());
            tvEveryKg.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    data.setEve_weight(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


            tvSecond.setText(data.getSpeed());
            tvSecond.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    data.setSpeed(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


            tvPeopleTime.setText(data.getLabor_hours());
            tvPeopleTime.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    data.setLabor_hours(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            tvPrepareTime.setText(data.getMac_pre_hours());
            tvPrepareTime.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    data.setMac_pre_hours(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            tvWorkTime.setText(data.getMac_hours());
            tvWorkTime.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    data.setMac_hours(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


            tvTwo.setText(data.getMod_weight());
            tvTwo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    data.setMod_weight(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            tvThree.setText(data.getEle_weight());
            tvThree.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    data.setEle_weight(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


            tvFour.setText(data.getMod_outline_weight());
            tvFour.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    data.setMod_outline_weight(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            tvSix.setText(data.getMod_inserts_weight());
            tvSix.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    data.setMod_inserts_weight(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            tvSeven.setText(data.getBush_weight());
            tvSeven.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    data.setBush_weight(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            tvEight.setText(data.getMod_amount());
            tvEight.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    data.setMod_amount(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            tvNine.setText(data.getEle_amount());
            tvNine.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    data.setEle_amount(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


        }
    }


    public interface onSelectStockClickListener {


        void onDelete(int position);

        void onOperate(int position);

        void onGroup(int position);

        void onDeport(int position);

        void onCode(int position);

    }

    private onSelectStockClickListener onStockListener;

    public void setOnStockListener(onSelectStockClickListener onStockListener) {
        this.onStockListener = onStockListener;
    }
}
