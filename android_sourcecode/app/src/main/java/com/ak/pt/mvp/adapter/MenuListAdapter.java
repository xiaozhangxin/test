package com.ak.pt.mvp.adapter;

import android.content.Context;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AppHomeMenuBeansBean;
import com.ak.pt.bean.AppHomeMenuTreeBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by admin on 2019/3/15.
 */

public class MenuListAdapter extends RecyclerArrayAdapter<AppHomeMenuTreeBean> {
    public MenuListAdapter(Context context, List<AppHomeMenuTreeBean> list) {
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
            recycleView.setLayoutManager(new GridLayoutManager(getContext(), 4));
            final CommonAdapter<AppHomeMenuBeansBean> commonAdapter = new CommonAdapter<AppHomeMenuBeansBean>(getContext(), R.layout.item_app_home_child, data.getAppHomeMenuBeans()) {
                @Override
                protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, AppHomeMenuBeansBean homeMenuChildBean, int position) {
                    switch (homeMenuChildBean.getMenu_key()) {
                        case "AddressBook"://通讯录
                            Glide.with(getContext())
                                    .load(R.drawable.menu_three)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "PipelineFigure"://管路图
                            Glide.with(getContext())
                                    .load(R.drawable.menu_two)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "SecurityCheck"://防伪查询
                            Glide.with(getContext())
                                    .load(R.drawable.menu_two)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "PressureTestAdd"://试压报单
                            Glide.with(getContext())
                                    .load(R.drawable.menu_one)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "Sign"://签到
                            Glide.with(getContext())
                                    .load(R.drawable.menu_s_one)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "Daily"://日报
                            Glide.with(getContext())
                                    .load(R.drawable.menu_s_two)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "Week":
                            Glide.with(getContext())
                                    .load(R.drawable.menu_s_three)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "Month":
                            Glide.with(getContext())
                                    .load(R.drawable.menu_s_four)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "AnnouncementNotice"://公告

                            Glide.with(getContext())
                                    .load(R.drawable.menu_s_five)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "PressurePage"://试压记录
                            Glide.with(getContext())
                                    .load(R.drawable.menu_y_one)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "AreaPressurePage"://区域
                            Glide.with(getContext())
                                    .load(R.drawable.menu_y_two)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "BigAreaPressurePage"://大区
                            Glide.with(getContext())
                                    .load(R.drawable.menu_y_three)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "OfficialDocument"://公文

                            Glide.with(getContext())
                                    .load(R.drawable.menu_five)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "IntegralView"://积分查询
                            Glide.with(getContext())
                                    .load(R.drawable.menu_y_four)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "Document"://文档
                            Glide.with(getContext())
                                    .load(R.drawable.menu_six)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "IntegralMall"://积分商城
                            Glide.with(getContext())
                                    .load(R.drawable.mall)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "FilterRpReceipt"://滤芯更换
                            Glide.with(getContext())
                                    .load(R.drawable.water_one)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "RepairRecords"://维修记录
                            Glide.with(getContext())
                                    .load(R.drawable.water_two)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "ProductQFeedback"://电子保修卡
                            Glide.with(getContext())
                                    .load(R.drawable.water_three)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "WarrantyCard"://产品质量
                            Glide.with(getContext())
                                    .load(R.drawable.water_four)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "MonthlySummary":

                            Glide.with(getContext())
                                    .load(R.drawable.new_one)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "RegionTraining":

                            Glide.with(getContext())
                                    .load(R.drawable.new_two)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "RegionVisit":

                            Glide.with(getContext())
                                    .load(R.drawable.new_three)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "AddPeople":

                            Glide.with(getContext())
                                    .load(R.drawable.new_four)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "PressureTesterReturn":

                            Glide.with(getContext())
                                    .load(R.drawable.new_five)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "Table":
                            Glide.with(getContext())
                                    .load(R.drawable.new_six)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;

                        case "EntryApplication"://入职申请
                            Glide.with(getContext())
                                    .load(R.drawable.people_two)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "LeaveApplication"://离职申请
                            Glide.with(getContext())
                                    .load(R.drawable.people_three)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;
                        case "DealerClose"://经销商关闭
                            Glide.with(getContext())
                                    .load(R.drawable.people_one)
                                    .placeholder(R.drawable.error_img)
                                    .transform(new CenterCrop(getContext()))
                                    .into((ImageView) holder.getView(R.id.ivImg));
                            break;



                        case "QF_PressureRecord":   //启飞试压记录
                            ((ImageView) holder.getView(R.id.ivImg)).setImageResource(R.mipmap.icon_qf_pressure_record);
                            break;
                        case "QF_AreaPressurePage": //  区域试压排行
                            ((ImageView) holder.getView(R.id.ivImg)).setImageResource(R.mipmap.icon_qf_pressure_area);
                            break;
                        case "QF_BigPressurePage":  //  大区试压排行
                            ((ImageView) holder.getView(R.id.ivImg)).setImageResource(R.mipmap.icon_qf_pressure_region);
                            break;




                    }
                    holder.setText(R.id.tvName, homeMenuChildBean.getMenu_title());
                }
            };
            recycleView.setAdapter(commonAdapter);
            commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    //is_disable 0 有权限
                    onClickImgListener.OnClick(data.getAppHomeMenuBeans().get(position).getMenu_key(),getDataPosition(),position);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });

        }
    }


    public interface OnClickImgListener {
        void OnClick(String tittle,int position,int childPosition);
    }

    private OnClickImgListener onClickImgListener;

    public void setOnClickImgListener(OnClickImgListener onClickImgListener) {
        this.onClickImgListener = onClickImgListener;
    }

}
