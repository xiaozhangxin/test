package com.akan.qf.mvp.fragment.hchannel;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.CompanyBean;
import com.akan.qf.bean.DepartmentEvent;
import com.akan.qf.bean.FilterBean;
import com.akan.qf.bean.FirstEventFilter;
import com.akan.qf.bean.LableBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.channel.CompanyFamilyAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.fragment.FilterFragment;
import com.akan.qf.mvp.presenter.home.CompanyPresenter;
import com.akan.qf.mvp.view.home.ICompanyView;
import com.akan.qf.util.SpSingleInstance;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/11/7.
 */

public class CompanyListFragment extends BaseFragment<ICompanyView, CompanyPresenter> implements ICompanyView {
    View loadMore;
    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.ivSearch)
    ImageView ivSearch;
    @BindView(R.id.recycleView)
    EasyRecyclerView recycleView;
    @BindView(R.id.drawer_content)
    FrameLayout drawerContent;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.tableLayout)
    TabLayout tableLayout;
    private List<CompanyBean> list;
    private CompanyFamilyAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int page = 1;
    private int deletePosition;
    private PermissionsBean permissionsBean;
    private LableBean mlBean = new LableBean();
    private List<LableBean> signList;

    public static CompanyListFragment newInstance(PermissionsBean permissionsBean, List<LableBean> signList) {
        Bundle args = new Bundle();
        CompanyListFragment fragment = new CompanyListFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.signList = signList;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_list_public;
    }

    @Override
    public void initUI() {
        tvTitle.setText("家装公司记录");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(R.string.filter);
        initSignTable();
        initFilter();
        initList();
        initSerach();

    }

    //初始化table标签
    private void initSignTable() {
        if (signList.size() > 0) {
            mlBean = signList.get(0);
        }
        if (signList.size() > 5) {
            tableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        } else {
            tableLayout.setTabMode(TabLayout.MODE_FIXED);
        }
        for (int j = 0; j < signList.size(); j++) {
            tableLayout.addTab(tableLayout.newTab().setText(signList.get(j).getSign_name()));
        }

        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                LableBean lableBean = signList.get(position);
                EventBus.getDefault().post(new DepartmentEvent("sign_child", lableBean));
                map.put("staff_sign", lableBean.getSign_id());
                page = 1;
                recycleView.setRefreshing(true);
                refresh();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initList() {

        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new CompanyFamilyAdapter(context, list);
        recycleView.setAdapterWithProgress(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                deletePosition = position;
                staretCompanyDetailFragment(adapter.getItem(position).getApply_id(), permissionsBean);
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

    //初始化筛选界面
    private void initFilter() {
        Fragment fragment = new FilterFragment();
        FragmentManager fragmentManager = getFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putString("stateType", "2");
        bundle.putString("app_data", permissionsBean.getApp_data());
        bundle.putString("module_id", permissionsBean.getMenu_id());
        bundle.putSerializable("sign_list", (Serializable) mlBean);
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.drawer_content, fragment).commit();
    }

    //设置键盘搜索按钮
    private void initSerach() {
        etName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    page = 1;
                    refresh();
                    recycleView.setRefreshing(true);
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
        map.put("staff_name", etName.getText().toString());
        map.put("company_sign", "JZGS");
        map.put("is_select", "1");
        map.put("is_app", "1");
        map.put("operation", "1000");
        map.put("module_id", permissionsBean.getMenu_id());
        map.put("page", page + "");
        getPresenter().getCompanyApplyList(userBean.getStaff_token(), map);

    }

    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.ivSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                drawerLayout.openDrawer(drawerContent);
                break;
            case R.id.ivSearch:
                page = 1;
                refresh();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEventFilter event) {
        drawerLayout.closeDrawer(drawerContent);
        switch (event.getMsg()) {
            case "1":
                FilterBean filterBean = event.getFilterBean();
                map.put("start_time", filterBean.getStartTime());
                map.put("end_time", filterBean.getEndTime());
                map.put("job_name", filterBean.getPostNames());
                map.put("apply_state", filterBean.getOrderState());
                map.put("staff_sign", filterBean.getSignId());
                map.put("group_parent_uuid", filterBean.getUuid());
                page = 1;
                refresh();
                recycleView.setRefreshing(true);
                break;
            case "company_delete":
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

    }


    @Override
    public CompanyPresenter createPresenter() {
        return new CompanyPresenter(getApp());
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


    @Override
    public void OnInsertOrUpdateCompanyApply(String data) {

    }

    @Override
    public void OnGetCompanyApplyList(List<CompanyBean> data, String total) {
        tvTitle.setText(String.format(Locale.CHINA, "家装公司记录(%s)", total));
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnGetCompanyApply(CompanyBean data) {

    }

    @Override
    public void OnAuditCompanyApply(String data) {

    }

    @Override
    public void OnDeleteCompanyApply(String data) {

    }
}