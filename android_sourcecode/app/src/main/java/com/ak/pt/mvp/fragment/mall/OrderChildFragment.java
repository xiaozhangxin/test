package com.ak.pt.mvp.fragment.mall;

import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ak.pt.R;
import com.ak.pt.bean.OrderBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.OrderMallAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.OrderMallPresenter;
import com.ak.pt.mvp.view.IOrderMallView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/5/7.
 */

public class OrderChildFragment extends BaseFragment<IOrderMallView, OrderMallPresenter> implements IOrderMallView {


    Unbinder unbinder;
    @BindView(R.id.recycleView)
    EasyRecyclerView recycleView;

    private List<OrderBean> list;
    private OrderMallAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int page = 1;

    private String type;

    public static OrderChildFragment newInstance(String type) {
        Bundle args = new Bundle();
        OrderChildFragment fragment = new OrderChildFragment();
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
        adapter = new OrderMallAdapter(context, list);
        recycleView.setAdapterWithProgress(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startOrderDetailFragment(adapter.getItem(position).getOrder_id());
            }
        });
        adapter.setNoMore(R.layout.view_nomore);
        //下拉刷新
        recycleView.setRefreshingColorResources(R.color.colorPrimaryNew);
        recycleView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                refresh();
            }
        });
        //上拉加载
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                refresh();
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
        page = 1;
        refresh();
    }

    //cancel：取消  wait_pay:待付款  wait_send:待发货  wait_receive：待确认收货 wait_assessment：待评价 end：已结束
    private void refresh() {
        switch (type) {
            case "0":
                map.put("order_state", "");
                break;
            case "1":
                map.put("order_state", "wait_send");
                break;
            case "2":
                map.put("order_state", "wait_receive");
                break;
            case "3":
                map.put("order_state", "end");
                break;
        }
        map.put("page", page + "");
        map.put("limit", "20");
        map.put("staff_id", userBean.getStaff_id());
        getPresenter().queryMyOrderList(userBean.getStaff_token(), map);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public OrderMallPresenter createPresenter() {
        return new OrderMallPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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


    @Override
    public void OnQueryMyOrderList(List<OrderBean> data) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGetOrderDetail(OrderBean data) {

    }

    @Override
    public void onConfirmOrder(String data) {

    }
}
