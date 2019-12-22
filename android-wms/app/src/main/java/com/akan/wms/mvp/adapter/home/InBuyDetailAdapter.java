package com.akan.wms.mvp.adapter.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.PurchasesBean;
import com.akan.wms.bean.SupplierReceivesBeanDetail;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.List;

public class InBuyDetailAdapter extends RecyclerArrayAdapter<PurchasesBean> {

    private String mState = "0";
    private boolean isEdit = false;

    public void setState(String state, boolean isEdit) {
        this.mState = state;
        this.isEdit = isEdit;

    }

    public InBuyDetailAdapter(Context context, List<PurchasesBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new InBuyDetailAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<PurchasesBean> {
        private TextView tvTittle;
        private ImageView tvDelete;

        private RecyclerView recyclerView;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_send_add);

            tvTittle = $(R.id.tvTittle);
            tvDelete = $(R.id.tvDelete);
            recyclerView = $(R.id.recyclerView);

        }

        @Override
        public void setData(final PurchasesBean data) {
            super.setData(data);
            if ("1".equals(mState) && isEdit) {
                tvDelete.setImageResource(R.drawable.scan_detail);
                tvDelete.setVisibility(View.VISIBLE);
                tvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onStockListener.onScan(getDataPosition(), mState);
                    }
                });
            } else if ("3".equals(mState)) {
                tvDelete.setImageResource(R.drawable.scan_detail);
                tvDelete.setVisibility(View.VISIBLE);
                tvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onStockListener.onScan(getDataPosition(), mState);
                    }
                });
            } else {
                tvDelete.setVisibility(View.GONE);
            }

            tvTittle.setText("采购单" + (getDataPosition() + 1) + "   " + data.getDoc_no());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new CommonAdapter<SupplierReceivesBeanDetail>(getContext(), R.layout.item_send_add_child_detail, data.getSupplier_receives()) {

                @Override
                protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, final SupplierReceivesBeanDetail bean, final int position) {
                    TextView tvDeport = holder.getView(R.id.tvDeport);
                    tvDeport.setText(bean.getWh_name());
                    TextView tvWhManager = holder.getView(R.id.tvWhManager);
                    tvWhManager.setText(bean.getWh_staff_name());

                    TextView tvMfc = holder.getView(R.id.tvMfc);
                    tvMfc.setText(bean.getMfc_name());

                    holder.setText(R.id.tvName, bean.getItem_name() + "\n" + bean.getItem_spec());
                    holder.setText(R.id.tvNum, bean.getPur_qty() + "");
                    holder.setText(R.id.tvSend, bean.getSend_qty() + "");

                    RelativeLayout rlActual = holder.getView(R.id.rlActual);
                    RelativeLayout rlPass = holder.getView(R.id.rlPass);
                    RelativeLayout rlNotPass = holder.getView(R.id.rlNotPass);

                    EditText tvActual = holder.getView(R.id.tvActual);
                    EditText tvPass = holder.getView(R.id.tvPass);
                    final EditText tvNotPass = holder.getView(R.id.tvNotPass);

                    tvActual.setText(bean.getArrive_qty() + "");
                    tvPass.setText(bean.getQualified_qty() + "");
                    tvNotPass.setText(bean.getUnqualified_qty() + "");
                    switch (mState) {
                        case "1"://点收
                            if (isEdit) {
                                tvDeport.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        onStockListener.onChooseDeport(getDataPosition(), position);
                                    }
                                });
                                tvWhManager.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        onStockListener.onChooseWhManager(getDataPosition(), position);
                                    }
                                });
                            }

                            rlActual.setVisibility(View.VISIBLE);
                            break;
                        case "2"://质检
                            tvActual.setEnabled(false);
                            if (isEdit) {
                                tvDeport.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        onStockListener.onChooseDeport(getDataPosition(), position);
                                    }
                                });
                                tvWhManager.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        onStockListener.onChooseWhManager(getDataPosition(), position);
                                    }
                                });
                                tvPass.setEnabled(true);
                                tvNotPass.setEnabled(true);
                            } else {
                                tvPass.setEnabled(false);
                                tvNotPass.setEnabled(false);
                            }
                            rlActual.setVisibility(View.VISIBLE);
                            rlPass.setVisibility(View.VISIBLE);
                            rlNotPass.setVisibility(View.VISIBLE);
                            break;
                        case "3":
                            tvPass.setEnabled(false);
                            tvNotPass.setEnabled(false);
                            rlActual.setVisibility(View.VISIBLE);
                            rlPass.setVisibility(View.VISIBLE);
                            rlNotPass.setVisibility(View.VISIBLE);
                            break;
                    }

/*                    tvActual.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            String toString = s.toString();
                            if (TextUtils.isEmpty(toString)) {
                                bean.setArrive_qty(0);
                            } else {
                                bean.setArrive_qty(Integer.parseInt(toString));
                            }

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });*/
                    tvPass.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            String toString = s.toString();
                            if (TextUtils.isEmpty(toString)) {
                                bean.setQualified_qty(0);
                            } else {
                                int totalInt = bean.getArrive_qty();
                                int passInt = Integer.parseInt(toString);
                                bean.setQualified_qty(passInt);
                                if (passInt>totalInt){
                                    tvNotPass.setText("0");

                                }else {
                                    tvNotPass.setText(String.valueOf(totalInt-passInt));
                                }


                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    tvNotPass.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            String toString = s.toString();
                            if (TextUtils.isEmpty(toString)) {
                                bean.setUnqualified_qty(0);
                            } else {
                                bean.setUnqualified_qty(Integer.parseInt(toString));
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });


                }
            });


        }
    }


    public interface onSelectStockClickListener {

        void onScan(int position, String state);

        void onChooseDeport(int position, int childPosition);

        void onChooseWhManager(int position, int childPosition);
    }

    private onSelectStockClickListener onStockListener;

    public void setOnStockListener(onSelectStockClickListener onStockListener) {
        this.onStockListener = onStockListener;
    }
}
