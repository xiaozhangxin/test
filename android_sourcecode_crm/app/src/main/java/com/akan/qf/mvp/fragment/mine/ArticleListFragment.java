package com.akan.qf.mvp.fragment.mine;

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
import com.akan.qf.bean.ContributeBean;
import com.akan.qf.bean.DepartmentEvent;
import com.akan.qf.bean.FilterBean;
import com.akan.qf.bean.FirstEventFilter;
import com.akan.qf.bean.LableBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.mine.ArticleListAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.fragment.FilterFragment;
import com.akan.qf.mvp.presenter.mine.ArticlePresenter;
import com.akan.qf.mvp.view.mine.IArticleView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
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
 * Created by admin on 2019/2/18.
 */

public class ArticleListFragment extends BaseFragment<IArticleView, ArticlePresenter> implements IArticleView {


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
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.tvNew)
    TextView tvNew;
    @BindView(R.id.tableLayout)
    TabLayout tableLayout;

    private List<ContributeBean> list;
    private ArticleListAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int page = 1;
    private int deletePosition;
    private PermissionsBean permissionsBean;
    private LableBean mlBean = new LableBean();
    private List<LableBean> signList;

    public static ArticleListFragment newInstance(PermissionsBean permissionsBean, List<LableBean> signList) {
        Bundle args = new Bundle();
        ArticleListFragment fragment = new ArticleListFragment();
        fragment.setArguments(args);
        fragment.permissionsBean = permissionsBean;
        fragment.signList = signList;
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_all_article_new;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.area_posting);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(R.string.filter);
        initSignTable();
        initFilter();
        initList();
        initSerach();
        //是否有新增权限
        if (isHave("0", permissionsBean.getApp_operation().split(","))) {
            tvNew.setVisibility(View.VISIBLE);
        } else {
            tvNew.setVisibility(View.GONE);
        }


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


    //数组钟是否包含某元素
    public boolean isHave(String index, String[] split) {
        for (int i = 0; i < split.length; i++) {
            if (index.equals(split[i])) {
                return true;
            }
        }
        return false;
    }

    private void initList() {
        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ArticleListAdapter(context, list, "all");
        recycleView.setAdapterWithProgress(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                deletePosition = position;
                startArticleDetailFragment(adapter.getItem(position).getId(), permissionsBean);
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
        bundle.putString("stateType", "5");
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
        map.put("title", etName.getText().toString());
        map.put("page", page + "");
        map.put("staff_id", userBean.getStaff_id());
        map.put("is_all_data", userBean.getIs_all_data());
        map.put("is_select", "1");
        map.put("is_app", "1");
        map.put("operation", "1000");
        map.put("module_id", permissionsBean.getMenu_id());
        getPresenter().getAreaContributeList(userBean.getStaff_token(), map);
    }


    @Override
    public void OnGetAreaContributeList(List<ContributeBean> data, String total) {
        tvNum.setText(total + "篇文章");
        tvTitle.setText(String.format(Locale.CHINA, "%s(%s)", getResources().getString(R.string.area_posting), total));
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.ivSearch, R.id.tvNew})
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
            case R.id.tvNew:
                StartChooseTableFragment("1", permissionsBean);
                break;
        }
    }


    @Override
    public void onUploadFiles(String[] data) {

    }

    @Override
    public void OnInsertOrUpdateAreaContribute(String data) {

    }

    @Override
    public void OnGetAreaContributeDetail(ContributeBean data) {

    }

    @Override
    public void OnInsertContributeComment(String data) {

    }

    @Override
    public void OnDeleteAreaContribute(String data) {

    }

    @Override
    public void OnAauditAreaContribute(String data) {

    }


    @Override
    public ArticlePresenter createPresenter() {
        return new ArticlePresenter(getApp());
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
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEventFilter event) {
        drawerLayout.closeDrawer(drawerContent);
        switch (event.getMsg()) {
            case "1":
                FilterBean filterBean = event.getFilterBean();
                map.put("start_time", filterBean.getStartTime());
                map.put("end_time", filterBean.getEndTime());
                map.put("job_name", filterBean.getPostNames());//职位
                map.put("state", filterBean.getOrderState());//单据状态
                map.put("class_id", filterBean.getTableId());//目录id
                map.put("staff_sign", filterBean.getSignId());
                map.put("group_parent_uuid", filterBean.getUuid());
                page = 1;
                refresh();
                recycleView.setRefreshing(true);
                break;
            case "article_delete":
                adapter.remove(deletePosition);
                break;
        }

    }


}
