package com.akan.qf.mvp.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.AppHomeMenuTreeBean;
import com.akan.qf.bean.BannerBean;
import com.akan.qf.bean.LableBean;
import com.akan.qf.bean.NoticeBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.StaffSignListBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.activity.DoubleContentActivity;
import com.akan.qf.mvp.adapter.home.SalesmanListAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.fragment.fsales.CustomerFileActivity;
import com.akan.qf.mvp.fragment.fsales.SaleForecastActivity;
import com.akan.qf.mvp.presenter.home.HomePresenter;
import com.akan.qf.mvp.view.home.IHomeView;
import com.akan.qf.util.SpSingleInstance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/10/19.
 */

public class CustomerFragment extends BaseFragment<IHomeView, HomePresenter> implements IHomeView {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    Unbinder unbinder;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private List<AppHomeMenuTreeBean> list;
    private SalesmanListAdapter adapter;
    private List<LableBean> signList = new ArrayList<>();//标签列表

    public static CustomerFragment newInstance() {
        Bundle args = new Bundle();
        CustomerFragment fragment = new CustomerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_recycleview;
    }


    @Override
    public void initUI() {
        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new SalesmanListAdapter(context, list);
        recycleView.setAdapter(adapter);
        adapter.setOnClickImgListener(new SalesmanListAdapter.OnClickImgListener() {
            @Override
            public void OnClick(String tittle, int position, int childPosition) {
                PermissionsBean permissionsBean = adapter.getItem(position).getAppHomeMenuBeans().get(childPosition).getAppPermissionsBeans();
                Intent intent;
                switch (tittle) {
                    case "ArchivesApply"://客户档案
                        intent = new Intent(getActivity(), CustomerFileActivity.class);
                        startActivity(intent);
                        break;

                    case "SaleForecast"://销售预测
                        intent = new Intent(getActivity(), SaleForecastActivity.class);
                        intent.putExtra("permissions", permissionsBean);
                        intent.putExtra("signList", (Serializable) signList);
                        startActivity(intent);
                        break;
                    case "SaleTask"://任务分派
                        startDispatchFragment(permissionsBean);
                        break;
                    case "SaleDataSum"://销量统计
                        startSaleDataFragment(permissionsBean);
                        break;
                    case "SaleDataContrast"://销量分析
                        startAnalysisFragment(permissionsBean);
                        break;
                    case "PressurePage":
                        startTestPressureFragment(Constants.PRESSURE_RECORD_QIFEI, permissionsBean);
                        break;
                    case "AreaPressurePage":
                        startAreaTestPressureFragment(Constants.PRESSURE_RECORD_QIFEI, permissionsBean);
                        break;
                    case "BigAreaPressurePage":
                        startBigTestPressureFragment(Constants.PRESSURE_RECORD_QIFEI);
                        break;
                    case "ProfitReport"://利润重算
                        startProfitReportFragment(permissionsBean);
                        break;


                    case "New_PressurePage":    //  试压记录
                        startTestPressureFragment(Constants.PRESSURE_RECORD_DEFAULT, permissionsBean);
                        break;
                    case "New_AreaPressurePage":    //  区域试压排行
                        startAreaTestPressureFragment(Constants.PRESSURE_RECORD_DEFAULT, permissionsBean);
                        break;
                    case "New_BigPressurePage": //  大区试压排行
                        startBigTestPressureFragment(Constants.PRESSURE_RECORD_DEFAULT);
                        break;

                    default:
                        intent = new Intent(getActivity(), DoubleContentActivity.class);
                        intent.putExtra("permissions", permissionsBean);
                        intent.putExtra("signList", (Serializable) signList);
                        intent.putExtra(Constants.KEY_FRAGMENT, tittle);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        getPresenter().getStaffSignList(userBean.getStaff_token(), map);
        map.put("menu_group", "1");
        getPresenter().getAppHomeMenuTreeByStaff(userBean.getStaff_token(), map);
    }

    @Override
    public void onGetAppHomeMenuTreeByStaff(List<AppHomeMenuTreeBean> data) {
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGetBannerListt(List<BannerBean> data) {

    }

    @Override
    public void OnGetNoticeList(List<NoticeBean> data) {

    }

    @Override
    public void OnGetNotReadNoticeCount(String data) {

    }

    @Override
    public void OnGetStaffSignList(List<LableBean> data) {
        signList.clear();
        LableBean lableBean = new LableBean();
        lableBean.setSign_id("");
        lableBean.setSign_name("全部");
        List<StaffSignListBean> childList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            childList.addAll(data.get(i).getStaffSignList());
        }
        lableBean.setStaffSignList(childList);
        signList.add(lableBean);
        signList.addAll(data);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }


    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(getApp());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
