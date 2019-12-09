package com.ak.pt.mvp.fragment;

import android.os.Bundle;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.FirstEventFilter;
import com.ak.pt.mvp.fragment.home.NoticeFragment;
import com.ak.pt.mvp.fragment.statistics.MemoryBean;
import com.ak.pt.mvp.fragment.statistics.PressurePageBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.OrderAcceptAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.OrderPresenter;
import com.ak.pt.mvp.view.IOrderView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/1/17.
 */

public class OrderManagerChildFragment extends BaseFragment<IOrderView, OrderPresenter> implements IOrderView {


    Unbinder unbinder;
    @BindView(R.id.recycleView)
    EasyRecyclerView recycleView;


    private List<PressurePageBean> list;
    private OrderAcceptAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int page = 1;
    private int deletePosition;
    private String type;
    private AppPermissionsBean permissionsBean;

    public static OrderManagerChildFragment newInstance(String type, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        OrderManagerChildFragment fragment = new OrderManagerChildFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_order_accept_child;
    }

    @Override
    public void initUI() {
        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new OrderAcceptAdapter(context, list);
        recycleView.setAdapterWithProgress(adapter);
        adapter.setOnItemClickListener(position -> {
            deletePosition = position;
            PressurePageBean item = adapter.getItem(position);
            //待接单已接单并且指派给自己
            if (("accept".equals(item.getFlow_state()) || "plan".equals(item.getFlow_state())) && userBean.getStaff_id().equals(item.getPlumber_id())) {
                startTestPressureDetailFragment(adapter.getItem(position).getDoc_no(), permissionsBean);
            } else {
                startOrderManagerDetailFragment(adapter.getItem(position).getDoc_no(), permissionsBean);
            }

        });
        adapter.setNoMore(R.layout.view_nomore);
        //下拉刷新
        recycleView.setRefreshingColorResources(R.color.colorPrimaryNew);
        recycleView.setRefreshListener(() -> {
            page = 1;
            refresh();
        });
        //上拉加载
        adapter.setMore(R.layout.view_more, () -> {
            page++;
            refresh();
        });

    }

    @Override
    public void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("is_select", "1");
        map.put("is_app", "1");
        map.put("operation", "1000");
        map.put("module_id", permissionsBean.getMenu_id());
        map.put("start_time", getTodayLastYear());
        page = 1;
        refresh();
    }


    public String getTodayLastYear() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        //过去一年
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Date y = c.getTime();
        return  format.format(y);
    }

    private void refresh() {
        switch (type) {
            case "0":
                map.put("flow_state", "order");
                break;
            case "1":
                map.put("flow_state", "accept");
                break;
            case "2":
                map.put("flow_state", "plan");
                break;
            case "3":
                map.put("flow_state", "success");
                break;
            case "4":
                map.put("flow_state", "result");
                break;
        }


        map.put("page", page + "");
        map.put("limit", "20");
        map.put("staff_id", userBean.getStaff_id());
        map.put("job_name", userBean.getJob_name());


        getPresenter().getTestPressureList(userBean.getStaff_token(), map);
    }

    @Override
    public void OnGetTestPressureList(List<PressurePageBean> data, String total) {

        String tittle = "待发出";
        int index = 0;
        switch (type) {
            case "0":
                tittle = "待发出";
                index = 0;
                break;
            case "1":
                index = 1;
                tittle = "待接单";
                break;
            case "2":
                index = 2;
                tittle = "已接单";
                break;
            case "3":
                index = 3;
                tittle = "待审核";
                break;
            case "4":
                index = 4;
                tittle = "已拒绝";
                break;

        }
        ((OrderManagerFragment) getParentFragment()).setTitleTotal(index, String.format(Locale.CHINA, tittle + "(%s)", total));

        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onInsertOrUpdateTestPressure(String data) {

    }

    @Override
    public void OnGetAppTestPressureList(List<PressurePageBean> data, String total) {

    }

    @Override
    public void onInsertOrUpdateAppTestPressure(String data) {

    }

    @Override
    public void onGetMemoryList(String type, List<MemoryBean> data) {

    }


    @Override
    public OrderPresenter createPresenter() {
        return new OrderPresenter(getApp());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEventFilter event) {
        switch (event.getMsg()) {
            case "mpt_delete":
                adapter.remove(deletePosition);
                break;
        }

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());

    }


}

