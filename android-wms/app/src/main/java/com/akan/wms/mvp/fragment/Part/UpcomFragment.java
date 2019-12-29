package com.akan.wms.mvp.fragment.part;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.ItemInfoBean;
import com.akan.wms.bean.ItemWhQohBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.upcom.UpcomListAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.ChooseItemPresenter;
import com.akan.wms.mvp.view.home.IChooseItemView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UpcomFragment extends BaseFragment<IChooseItemView, ChooseItemPresenter> implements IChooseItemView {


    Unbinder unbinder;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;


    private List<ItemInfoBean> list;
    private UpcomListAdapter adapter;
    private int page = 1;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;


    public static UpcomFragment newInstance() {
        Bundle args = new Bundle();
        UpcomFragment fragment = new UpcomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_upcom;
    }

    @Override
    public void initUI() {

        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new UpcomListAdapter(context, list);
        recyclerView.setAdapterWithProgress(adapter);

        adapter.setNoMore(R.layout.view_nomore);
        recyclerView.setRefreshingColorResources(R.color.colorPrimary);
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                refresh();

            }
        });

        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                refresh();
            }
        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    page = 1;
                    refresh();
                    recyclerView.setRefreshing(true);
                    hideInputMethod();
                }
                return false;
            }
        });

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        refresh();

    }

    private void refresh() {
        String mAllSelect = etSearch.getText().toString();
        map.put("all_select", mAllSelect);
        map.put("org_id", userBean.getOrg_id());
        map.put("page", page + "");
        getPresenter().getItemInfoList(userBean.getStaff_token(), map);

    }

    @Override
    public ChooseItemPresenter createPresenter() {
        return new ChooseItemPresenter(getApp());
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
    public void onGetItemWhQohByWhList(List<ItemWhQohBean> data) {

    }

    @Override
    public void onGetItemInfoList(List<ItemInfoBean> data) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
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
    public void onEventMainThread(FirstEvent event) {
        String msg = event.getMsg();
        switch (msg) {
            case "org_change":
                page = 1;
                initData();
                break;

        }

    }
}
