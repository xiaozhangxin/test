package com.akan.wms.mvp.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.FirstEventTwo;
import com.akan.wms.bean.UserBean;
import com.akan.wms.bean.WareHouseBean;
import com.akan.wms.mvp.adapter.home.ChooseDeportAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.ChooseDeportPresenter;
import com.akan.wms.mvp.view.IChooseDeportView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChooseDeportFragment extends BaseFragment<IChooseDeportView, ChooseDeportPresenter> implements IChooseDeportView {
    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.etSearch)
    EditText etSearch;


    private List<WareHouseBean> list;
    private ChooseDeportAdapter adapter;
    private int page = 1;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String detail_id;
    private String orgName;

    public static ChooseDeportFragment newInstance(String detail_id, String orgName) {
        Bundle args = new Bundle();
        ChooseDeportFragment fragment = new ChooseDeportFragment();
        fragment.detail_id = detail_id;
        fragment.orgName = orgName;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_choose_deport;
    }

    @Override
    public void initUI() {
        tvTitle.setText("选择仓库");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ChooseDeportAdapter(context, list);
        recyclerView.setAdapterWithProgress(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                EventBus.getDefault().post(new FirstEvent("6", adapter.getItem(position)));
                EventBus.getDefault().post(new FirstEventTwo("6", adapter.getItem(position)));
                finish();
            }
        });

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

        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    page=1;
                    refresh();
                    recyclerView.setRefreshing(true);
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
        map.put("org_id", userBean.getOrg_id());
        map.put("warehouse_name", etSearch.getText().toString());
        map.put("page", page + "");
        getPresenter().getWareHouseList(userBean.getStaff_token(), map);
    }

    @Override
    public void getWareHouseList(List<WareHouseBean> data) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void getWareHouseById(List<WareHouseBean> data) {

    }


    @OnClick({R.id.ivLeft})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;

        }
    }


    @Override
    public ChooseDeportPresenter createPresenter() {
        return new ChooseDeportPresenter(getApp());
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
