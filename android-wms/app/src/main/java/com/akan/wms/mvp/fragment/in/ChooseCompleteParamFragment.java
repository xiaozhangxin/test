package com.akan.wms.mvp.fragment.in;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableString;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.ClassTeamBean;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.ModNoBean;
import com.akan.wms.bean.OperatorStaffBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.home.ClassTeamAdapter;
import com.akan.wms.mvp.adapter.home.ModNoAdapter;
import com.akan.wms.mvp.adapter.home.OperatorAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.ChooseCompleteParamPresenter;
import com.akan.wms.mvp.view.home.IChooseCompleteParamView;
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

public class ChooseCompleteParamFragment extends BaseFragment<IChooseCompleteParamView, ChooseCompleteParamPresenter> implements IChooseCompleteParamView {
    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;

    private List<OperatorStaffBean> listOne;
    private OperatorAdapter adapterOne;
    private List<ClassTeamBean> listTwo;
    private ClassTeamAdapter adapterTwo;
    private List<ModNoBean> listThree;
    private ModNoAdapter adapterThree;

    private int page = 1;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mType;

    public static ChooseCompleteParamFragment newInstance(String type) {
        Bundle args = new Bundle();
        ChooseCompleteParamFragment fragment = new ChooseCompleteParamFragment();
        fragment.mType = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_list_search;
    }

    @Override
    public void initUI() {
        etSearch.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        switch (mType) {
            case "1"://选择操作工
                tvTitle.setText(R.string.choose_operator);

                etSearch.setVisibility(View.VISIBLE);
                SpannableString s = new SpannableString("请输入名称搜索");
                etSearch.setHint(s);
                etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                            map.put("staff_name",etSearch.getText().toString());
                            page=1;
                            refresh();
                            hideInputMethod();
                        }
                        return false;
                    }
                });
                listOne = new ArrayList<>();
                adapterOne = new OperatorAdapter(context, listOne);
                recyclerView.setAdapterWithProgress(adapterOne);
                adapterOne.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        EventBus.getDefault().post(new FirstEvent("19", adapterOne.getItem(position)));
                        finish();
                    }
                });
                adapterOne.setNoMore(R.layout.view_nomore);
                recyclerView.setRefreshingColorResources(R.color.colorPrimary);
                recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        page = 1;
                        refresh();

                    }
                });

                adapterOne.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        page++;
                        refresh();
                    }
                });
                break;
            case "2"://选择班组
                tvTitle.setText(R.string.choose_group);
                listTwo = new ArrayList<>();
                adapterTwo = new ClassTeamAdapter(context, listTwo);
                recyclerView.setAdapterWithProgress(adapterTwo);
                adapterTwo.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        EventBus.getDefault().post(new FirstEvent("20", adapterTwo.getItem(position)));
                        finish();
                    }
                });
                adapterTwo.setNoMore(R.layout.view_nomore);
                recyclerView.setRefreshingColorResources(R.color.colorPrimary);
                recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        page = 1;
                        refresh();

                    }
                });

                adapterTwo.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        page++;
                        refresh();
                    }
                });
                break;
            case "3"://选择模具编号
               etSearch.setVisibility(View.VISIBLE);
                SpannableString s1 = new SpannableString("请输入名称搜索");
                etSearch.setHint(s1);
                etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                            page=1;
                            refresh();
                            hideInputMethod();
                        }
                        return false;
                    }
                });
                tvTitle.setText(R.string.choose_mold_num);
                listThree = new ArrayList<>();
                adapterThree = new ModNoAdapter(context, listThree);
                recyclerView.setAdapterWithProgress(adapterThree);
                adapterThree.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        EventBus.getDefault().post(new FirstEvent("21", adapterThree.getItem(position)));
                        finish();
                    }
                });
                adapterThree.setNoMore(R.layout.view_nomore);
                recyclerView.setRefreshingColorResources(R.color.colorPrimary);
                recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        page = 1;
                        refresh();

                    }
                });
                adapterThree.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        page++;
                        refresh();
                    }
                });
                break;


        }}



    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        refresh();
    }

    //刷新请求数据
    private void refresh() {
        map.put("page", page + "");
        map.put("org_id", userBean.getOrg_id());
        switch (mType) {
            case "1":
                getPresenter().queryOperatorStaffList(userBean.getStaff_token(), map);
                break;
            case "2":
                getPresenter().queryClassTeam(userBean.getStaff_token(), map);
                break;
            case "3":
                map.put("name", etSearch.getText().toString());
                getPresenter().queryModNo(userBean.getStaff_token(), map);
                break;


        }

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
    public ChooseCompleteParamPresenter createPresenter() {
        return new ChooseCompleteParamPresenter(getApp());
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




    @Override
    public void onQueryOperatorStaffList(List<OperatorStaffBean> data) {
        if (page == 1) {
            adapterOne.clear();
        }
        adapterOne.addAll(data);
        adapterOne.notifyDataSetChanged();
    }

    @Override
    public void onQueryClassTeam(List<ClassTeamBean> data) {
        if (page == 1) {
            adapterTwo.clear();
        }
        adapterTwo.addAll(data);
        adapterTwo.notifyDataSetChanged();
    }

    @Override
    public void onQueryModNo(List<ModNoBean> data) {
        if (page == 1) {
            adapterThree.clear();
        }
        adapterThree.addAll(data);
        adapterThree.notifyDataSetChanged();
    }
}
