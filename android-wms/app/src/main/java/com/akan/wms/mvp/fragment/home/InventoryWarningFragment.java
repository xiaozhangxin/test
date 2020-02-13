package com.akan.wms.mvp.fragment.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.UserBean;
import com.akan.wms.bean.WareHouseBean;
import com.akan.wms.bean.WarnTwoBean;
import com.akan.wms.mvp.adapter.home.WarningAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.InventoryWarningPresenter;
import com.akan.wms.mvp.view.home.IInventoryWarningView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.akan.wms.view.DropDownView.DropDownMenu;
import com.akan.wms.view.DropDownView.GirdDropDownAdapter;
import com.bilibili.boxing_impl.view.SpacesItemDecoration;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class InventoryWarningFragment extends BaseFragment<IInventoryWarningView, InventoryWarningPresenter> implements IInventoryWarningView, View.OnClickListener {

    Unbinder unbinder;

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;

    private List<WarnTwoBean> list;
    private WarningAdapter adapter;
    private int page = 1;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    private String headers[] = {"全部状态", "条件筛选"};
    private String mOne[] = {"全部", "过低", "过高"};
    private List<View> popupViews = new ArrayList<>();
    private GirdDropDownAdapter oneAdapter;
    private EditText tvOne;
    private TextView tvTwo;
    private TextView tvThree;
    private TextView tvClear;
    private TextView tvOk;
    private List<String> mTypeList = new ArrayList<>();

    public static InventoryWarningFragment newInstance() {
        Bundle args = new Bundle();
        InventoryWarningFragment fragment = new InventoryWarningFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_list_public_new;
    }

    @Override
    public void initUI() {
        tvTitle.setText("库存警告");
        initMenu();
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new WarningAdapter(context, list);
        recyclerView.addItemDecoration(new SpacesItemDecoration(1));
        recyclerView.setAdapterWithProgress(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startStockFindDetailFragment(adapter.getItem(position));
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
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        refresh();
        map.put("status", "0");
    }

    private void refresh() {
        map.put("page", page + "");
        getPresenter().queryWarningTwoList(userBean.getStaff_token(), map);
    }


    //初始化选择栏
    private void initMenu() {
        final ListView oneView = new ListView(context);
        oneView.setDividerHeight(0);
        oneAdapter = new GirdDropDownAdapter(context, Arrays.asList(mOne));
        oneView.setAdapter(oneAdapter);
        View twoView = LayoutInflater.from(context).inflate(R.layout.top_select_stock, null);
        popupViews.add(oneView);
        popupViews.add(twoView);
        oneView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                oneAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[0] : mOne[position]);
                dropDownMenu.closeMenu();
                switch (mOne[position]) {
                    case "全部":
                        map.put("status", "0");
                        break;
                    case "过低":
                        map.put("status", "1");
                        break;
                    case "过高":
                        map.put("status", "2");
                        break;

                }
                page = 1;
                refresh();
            }
        });

        tvOne = twoView.findViewById(R.id.tvOne);
        tvTwo = twoView.findViewById(R.id.tvTwo);
        tvThree = twoView.findViewById(R.id.tvThree);
        tvClear = twoView.findViewById(R.id.tvClear);
        tvOk = twoView.findViewById(R.id.tvOk);
        tvClear.setOnClickListener(this);
        tvOk.setOnClickListener(this);
        tvTwo.setOnClickListener(this);
        tvThree.setOnClickListener(this);
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvTwo:
                startChooseDeportFragment();
                break;
            case R.id.tvClear:
                tvOne.setText("");
                tvTwo.setText("");
                tvThree.setText("");
                dropDownMenu.closeMenu();
                map.put("name", "");
                map.put("wh_id", "");
                page = 1;
                refresh();
                recyclerView.setRefreshing(true);
                break;
            case R.id.tvOk:
                dropDownMenu.closeMenu();
                map.put("name", tvOne.getText().toString());
                page = 1;
                refresh();
                recyclerView.setRefreshing(true);
                break;
        }

    }


    @OnClick({R.id.ivLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
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


    @Override
    public InventoryWarningPresenter createPresenter() {
        return new InventoryWarningPresenter(getApp());
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
            case "6"://选择仓库
                WareHouseBean houseBean = event.getmWareHouseBean();
                tvTwo.setText(houseBean.getWarehouse_name());
                map.put("wh_id", houseBean.getWarehouse_id());
                break;

        }

    }


    @Override
    public void onQueryWarningTwoList(List<WarnTwoBean> data) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }
}

