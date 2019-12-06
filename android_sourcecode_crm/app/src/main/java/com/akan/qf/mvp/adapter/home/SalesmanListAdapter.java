package com.akan.qf.mvp.adapter.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.AppHomeMenuBean;
import com.akan.qf.bean.AppHomeMenuTreeBean;
import com.akan.qf.util.GlideRoundTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by admin on 2018/10/19.
 */

public class SalesmanListAdapter extends RecyclerArrayAdapter<AppHomeMenuTreeBean> {
    public SalesmanListAdapter(Context context, List<AppHomeMenuTreeBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }


    public class ViewHolder extends BaseViewHolder<AppHomeMenuTreeBean> {


        private TextView tvTittle;

        private RecyclerView recycleView;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_app_home);
            tvTittle = $(R.id.tvTittle);
            recycleView = $(R.id.recycleView);
        }

        @Override
        public void setData(final AppHomeMenuTreeBean data) {
            super.setData(data);
            tvTittle.setText(data.getMenu_title());
            recycleView.setNestedScrollingEnabled(false);
            recycleView.setLayoutManager(new GridLayoutManager(getContext(), 4));
            final CommonAdapter<AppHomeMenuBean> commonAdapter = new CommonAdapter<AppHomeMenuBean>(getContext(), R.layout.item_app_home_child, data.getAppHomeMenuBeans()) {
                @Override
                protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, AppHomeMenuBean appHomeMenuBeansBean, int position) {
                    switch (appHomeMenuBeansBean.getMenu_key()) {
                        case "Recruitment"://招人申请
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审批");
                            Glide.with(getContext())
                                    .load(R.drawable.people_one)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "NewcomerJoin"://新人入职
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审批");
                            Glide.with(getContext())
                                    .load(R.drawable.people_two)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "ResignationLetter"://离职申请
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审批");
                            Glide.with(getContext())
                                    .load(R.drawable.people_three)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "Sign":
                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
                            Glide.with(getContext())
                                    .load(R.drawable.menu_one)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "AskLeave":
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审批");
                            Glide.with(getContext())
                                    .load(R.drawable.menu_two)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "Daily":
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "日志");
                            Glide.with(getContext())
                                    .load(R.drawable.menu_four)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "Week":
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "日志");
                            Glide.with(getContext())
                                    .load(R.drawable.menu_five)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "Month":
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "日志");
                            Glide.with(getContext())
                                    .load(R.drawable.menu_six)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "ExpenseReimbursement":
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审批");
                            Glide.with(getContext())
                                    .load(R.drawable.menu_s_four)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "TemporarySupport":
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审批");
                            Glide.with(getContext())
                                    .load(R.drawable.menu_s_two)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "PayApply":
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审批");
                            Glide.with(getContext())
                                    .load(R.drawable.menu_s_three)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "PolicyApply":
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审批");
                            Glide.with(getContext())
                                    .load(R.drawable.menu_s_one)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "NewApply":
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审阅");
                            Glide.with(getContext())
                                    .load(R.drawable.menu_t_three)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "GoodsApply":
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审批");
                            Glide.with(getContext())
                                    .load(R.drawable.menu_t_two)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "VisitorApply":
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审批");
                            Glide.with(getContext())
                                    .load(R.drawable.menu_t_five)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "ProjectApply":
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审批");
                            Glide.with(getContext())
                                    .load(R.drawable.menu_t_one)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "DebtApply":
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审批");
                            Glide.with(getContext())
                                    .load(R.drawable.menu_s_five)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "Staff"://人员信息
                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
                            Glide.with(getContext())
                                    .load(R.drawable.menu_t_six)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "AreaContribute"://区域投稿
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审阅");
                            Glide.with(getContext())
                                    .load(R.drawable.menu_y_two)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;

                        case "ChannelCustomer"://家装公司
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审阅");
                            Glide.with(getContext())
                                    .load(R.drawable.menu_f_one)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "NuantongCompany"://暖通公司
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审阅");
                            Glide.with(getContext())
                                    .load(R.drawable.menu_f_four)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "ChannelWater"://工长信息
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审阅");
                            Glide.with(getContext())
                                    .load(R.drawable.menu_f_two)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "ChannelDistribution"://分销网点
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审阅");
                            Glide.with(getContext())
                                    .load(R.drawable.menu_f_three)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "project"://工程项目
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审阅");
                            Glide.with(getContext())
                                    .load(R.drawable.project)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;

                        case "ArchivesApply":
                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
                            Glide.with(getContext())
                                    .load(R.drawable.menu_f_nine)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "ContractApply":
                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
                            Glide.with(getContext())
                                    .load(R.drawable.menu_f_eleven)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "SaleTask":
                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
                            Glide.with(getContext())
                                    .load(R.drawable.menu_three)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "SaleDataSum":
                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
                            Glide.with(getContext())
                                    .load(R.drawable.menu_m_two)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "SaleDataContrast":
                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
                            Glide.with(getContext())
                                    .load(R.drawable.menu_m_three)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "PressurePage":
                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
                            Glide.with(getContext())
                                    .load(R.drawable.menu_f_seven)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "AreaPressurePage":
                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
                            Glide.with(getContext())
                                    .load(R.drawable.menu_f_eight)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "BigAreaPressurePage":
                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
                            Glide.with(getContext())
                                    .load(R.drawable.menu_f_six)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;

                        case "ShopAdv"://店招广告
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审阅");
                            Glide.with(getContext())
                                    .load(R.drawable.ad_one)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "ImageAdv"://形象广告
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审阅");
                            Glide.with(getContext())
                                    .load(R.drawable.ad_two)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "PromotionAdv"://推广广告
                            holder.getView(R.id.tvAudit).setVisibility(View.VISIBLE);
                            holder.setText(R.id.tvAudit, "审阅");
                            Glide.with(getContext())
                                    .load(R.drawable.ad_three)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "AnnouncementNotice"://公告通知
                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
                            Glide.with(getContext())
                                    .load(R.drawable.menu_y_one)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "OfficialDocument"://公文
                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
                            Glide.with(getContext())
                                    .load(R.drawable.menu_y_three)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "CostStatistics"://费用统计
                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
                            Glide.with(getContext())
                                    .load(R.drawable.menu_y_four)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "SaleForecast":
                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
                            Glide.with(getContext())
                                    .load(R.drawable.sales)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "ProfitReport":
                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
                            Glide.with(getContext())
                                    .load(R.drawable.profit)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "Contact":
                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
                            Glide.with(getContext())
                                    .load(R.drawable.contact)
                                    .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 16))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;

//                        case "CostPayment":    //  费用付款
//                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
//                            ((ImageView) holder.getView(R.id.ivImg)).setImageResource(R.mipmap.icon_cost_payment);
//                            break;
//                        case "CustomerDiscount":    //  客户折扣
//                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
//                            ((ImageView) holder.getView(R.id.ivImg)).setImageResource(R.mipmap.icon_discount);
//                            break;
//                        case "DisableMaterial":    //  禁用物料
//                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
//                            ((ImageView) holder.getView(R.id.ivImg)).setImageResource(R.mipmap.icon_material);
//                            break;
//                        case "SalesInvoice":    //  销项发票
//                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
//                            ((ImageView) holder.getView(R.id.ivImg)).setImageResource(R.mipmap.icon_sales_invoice);
//                            break;
//                        case "SalesInvoiceRefund":    //  销项发票退票
//                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
//                            ((ImageView) holder.getView(R.id.ivImg)).setImageResource(R.mipmap.icon_refund);
//                            break;
//                        case "EnterInvoice":    //  进项发票
//                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
//                            ((ImageView) holder.getView(R.id.ivImg)).setImageResource(R.mipmap.icon_enter_invoice);
//                            break;

                        case "New_PressurePage":    //  试压记录
                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
                            ((ImageView) holder.getView(R.id.ivImg)).setImageResource(R.mipmap.icon_pressure_record);
                            break;
                        case "New_AreaPressurePage":    //  区域试压排行
                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
                            ((ImageView) holder.getView(R.id.ivImg)).setImageResource(R.mipmap.icon_area_pressure);
                            break;
                        case "New_BigPressurePage": //  大区试压排行
                            holder.getView(R.id.tvAudit).setVisibility(View.GONE);
                            ((ImageView) holder.getView(R.id.ivImg)).setImageResource(R.mipmap.icon_big_area_pressure);
                            break;

                    }

                    holder.setText(R.id.tvName, appHomeMenuBeansBean.getMenu_title());

                }


            };
            recycleView.setAdapter(commonAdapter);
            commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                    //is_disable 0 有权限
                    //   recyclerViewClickListener.OnRecyclerViewClickListener(person.getOrder_id());
                    onClickImgListener.OnClick(data.getAppHomeMenuBeans().get(position).getMenu_key(), getDataPosition(), position);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });

        }
    }

    public Bitmap stringToBitmap(String string) {
        // 将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
                    bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    public interface OnClickImgListener {
        void OnClick(String tittle, int position, int childPosition);
    }

    private OnClickImgListener onClickImgListener;

    public void setOnClickImgListener(OnClickImgListener onClickImgListener) {
        this.onClickImgListener = onClickImgListener;
    }

}
