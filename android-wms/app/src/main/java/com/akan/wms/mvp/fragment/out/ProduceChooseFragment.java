package com.akan.wms.mvp.fragment.out;

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
import com.akan.wms.bean.DefineValueBean;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.MiscShipDocTypeBean;
import com.akan.wms.bean.OperatorBean;
import com.akan.wms.bean.StaffGroupBean;
import com.akan.wms.bean.StaffOrgsBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.home.ProduceFiveAdapter;
import com.akan.wms.mvp.adapter.home.ProduceFourAdapter;
import com.akan.wms.mvp.adapter.home.ProduceOneAdapter;
import com.akan.wms.mvp.adapter.home.ProduceThreeAdapter;
import com.akan.wms.mvp.adapter.home.ProduceTwoAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.ProduceChoosePresenter;
import com.akan.wms.mvp.view.home.IProduceChooseView;
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

public class ProduceChooseFragment extends BaseFragment<IProduceChooseView, ProduceChoosePresenter> implements IProduceChooseView {
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

    private List<MiscShipDocTypeBean> listOne;
    private ProduceOneAdapter adapterOne;
    private List<OperatorBean> listTwo;
    private ProduceTwoAdapter adapterTwo;
    private List<StaffOrgsBean> listThree;
    private ProduceThreeAdapter adapterThree;
    private List<DefineValueBean> listFour;
    private ProduceFourAdapter adapterFour;
    private List<StaffGroupBean> listFive;
    private ProduceFiveAdapter adapterFive;

    private int page = 1;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mType;

    public static ProduceChooseFragment newInstance(String type) {
        Bundle args = new Bundle();
        ProduceChooseFragment fragment = new ProduceChooseFragment();
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
            case "1":
            case "8":
                tvTitle.setText("选择单据类型");
                listOne = new ArrayList<>();
                adapterOne = new ProduceOneAdapter(context, listOne);
                recyclerView.setAdapterWithProgress(adapterOne);
                adapterOne.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        EventBus.getDefault().post(new FirstEvent("8", adapterOne.getItem(position)));
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
            case "2":
                etSearch.setVisibility(View.VISIBLE);
                SpannableString s1 = new SpannableString("请输入姓名搜索");
                etSearch.setHint(s1);
                etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                            page = 1;
                            map.put("operator_name", etSearch.getText().toString());
                            refresh();
                        }
                        return false;
                    }
                });

                tvTitle.setText("选择库管员");
                listTwo = new ArrayList<>();
                adapterTwo = new ProduceTwoAdapter(context, listTwo);
                recyclerView.setAdapterWithProgress(adapterTwo);
                adapterTwo.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        EventBus.getDefault().post(new FirstEvent("9", adapterTwo.getItem(position)));
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
            case "3":

                tvTitle.setText("选择受益组织");
                listThree = new ArrayList<>();
                adapterThree = new ProduceThreeAdapter(context, listThree);
                recyclerView.setAdapterWithProgress(adapterThree);
                adapterThree.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        EventBus.getDefault().post(new FirstEvent("1", adapterThree.getItem(position)));
                        finish();
                    }
                });
                adapterThree.setNoMore(R.layout.view_nomore);
                etSearch.setVisibility(View.VISIBLE);
                SpannableString s4 = new SpannableString("请输入名称搜索");
                etSearch.setHint(s4);
                etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                            page = 1;
                            map.put("name", etSearch.getText().toString());
                            refresh();
                        }
                        return false;
                    }
                });
                break;

            case "4":
                tvTitle.setText("选择使用中心");
                listFour = new ArrayList<>();
                adapterFour = new ProduceFourAdapter(context, listFour);
                recyclerView.setAdapterWithProgress(adapterFour);
                adapterFour.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        EventBus.getDefault().post(new FirstEvent("10", adapterFour.getItem(position)));
                        finish();
                    }
                });
                adapterFour.setNoMore(R.layout.view_nomore);
                recyclerView.setRefreshingColorResources(R.color.colorPrimary);
                recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        page = 1;
                        refresh();

                    }
                });

                adapterFour.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        page++;
                        refresh();
                    }
                });
                break;
            case "5":
                tvTitle.setText("选择负责部门");
                listFive = new ArrayList<>();
                adapterFive = new ProduceFiveAdapter(context, listFive);
                recyclerView.setAdapterWithProgress(adapterFive);
                adapterFive.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        EventBus.getDefault().post(new FirstEvent("11", adapterFive.getItem(position)));
                        finish();
                    }
                });
                adapterFive.setNoMore(R.layout.view_nomore);
                recyclerView.setRefreshingColorResources(R.color.colorPrimary);
                recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        page = 1;
                        refresh();

                    }
                });

                adapterFive.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        page++;
                        refresh();
                    }
                });
                etSearch.setVisibility(View.VISIBLE);
                SpannableString s = new SpannableString("请输入名称搜索");
                etSearch.setHint(s);
                etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                            page = 1;
                            map.put("group_name", etSearch.getText().toString());
                            refresh();
                        }
                        return false;
                    }
                });

                break;
            case "6":
                tvTitle.setText("选择受益部门");
                listFive = new ArrayList<>();
                adapterFive = new ProduceFiveAdapter(context, listFive);
                recyclerView.setAdapterWithProgress(adapterFive);
                adapterFive.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        EventBus.getDefault().post(new FirstEvent("12", adapterFive.getItem(position)));
                        finish();
                    }
                });
                adapterFive.setNoMore(R.layout.view_nomore);
                recyclerView.setRefreshingColorResources(R.color.colorPrimary);
                recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        page = 1;
                        refresh();

                    }
                });

                adapterFive.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        page++;
                        refresh();
                    }
                });

                etSearch.setVisibility(View.VISIBLE);
                SpannableString s3 = new SpannableString("请输入名称搜索");
                etSearch.setHint(s3);
                etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                            map.put("group_name", etSearch.getText().toString());
                            page = 1;
                            refresh();
                        }
                        return false;
                    }
                });
                break;
            case "7":
                tvTitle.setText("选择负责中心");
                listFour = new ArrayList<>();
                adapterFour = new ProduceFourAdapter(context, listFour);
                recyclerView.setAdapterWithProgress(adapterFour);
                adapterFour.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        EventBus.getDefault().post(new FirstEvent("13", adapterFour.getItem(position)));
                        finish();
                    }
                });
                adapterFour.setNoMore(R.layout.view_nomore);
                recyclerView.setRefreshingColorResources(R.color.colorPrimary);
                recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        page = 1;
                        refresh();

                    }
                });

                adapterFour.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        page++;
                        refresh();
                    }
                });
                break;

            case "11":
                tvTitle.setText("选择组织");
                listThree = new ArrayList<>();
                adapterThree = new ProduceThreeAdapter(context, listThree);
                recyclerView.setAdapterWithProgress(adapterThree);
                adapterThree.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        EventBus.getDefault().post(new FirstEvent("11", adapterThree.getItem(position)));
                        finish();
                    }
                });
                adapterThree.setNoMore(R.layout.view_nomore);
                break;
        }


    }

    //刷新请求数据
    private void refresh() {
        map.put("page", page + "");
        switch (mType) {
            case "1":
                map.put("org_id", userBean.getOrg_id());
                getPresenter().getMiscShipDocTypeList(userBean.getStaff_token(), map);
                break;
            case "8":
                map.put("org_id", userBean.getOrg_id());
                getPresenter().getMiscRcvDocTypeList(userBean.getStaff_token(), map);
                break;
            case "2":
                map.put("org_id", userBean.getOrg_id());
                getPresenter().getOperatorList(userBean.getStaff_token(), map);
                break;
            case "3":
            case "11":
                map.put("staff_account", userBean.getStaff_account());
                getPresenter().queryStaffOrgs(map);
                break;
            case "4":
            case "7":
                map.put("org_id", userBean.getOrg_id());
                getPresenter().getDefineValueList(userBean.getStaff_token(), map);
                break;
            case "5":
            case "6":
                map.put("org_id", userBean.getOrg_id());
                getPresenter().getStaffGroupByOrgId(userBean.getStaff_token(), map);
                break;

        }

    }


    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        refresh();
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
    public ProduceChoosePresenter createPresenter() {
        return new ProduceChoosePresenter(getApp());
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
    public void onGetMiscShipDocTypeList(List<MiscShipDocTypeBean> data) {
        if (page == 1) {
            adapterOne.clear();
        }
        adapterOne.addAll(data);
        adapterOne.notifyDataSetChanged();

    }

    @Override
    public void onGetOperatorList(List<OperatorBean> data) {
        if (page == 1) {
            adapterTwo.clear();
        }
        adapterTwo.addAll(data);
        adapterTwo.notifyDataSetChanged();
    }

    @Override
    public void queryStaffOrgs(List<StaffOrgsBean> data) {
            adapterThree.clear();
        adapterThree.addAll(data);
        adapterThree.notifyDataSetChanged();
    }

    @Override
    public void onGetMiscRcvDocTypeList(List<MiscShipDocTypeBean> data) {
        if (page == 1) {
            adapterOne.clear();
        }
        adapterOne.addAll(data);
        adapterOne.notifyDataSetChanged();
    }


    @Override
    public void onGetDefineValueList(List<DefineValueBean> data) {
        if (page == 1) {
            adapterFour.clear();
        }
        adapterFour.addAll(data);
        adapterFour.notifyDataSetChanged();
    }


    @Override
    public void onGetStaffGroupByOrgId(List<StaffGroupBean> data) {
        if (page == 1) {
            adapterFive.clear();
        }
        adapterFive.addAll(data);
        adapterFive.notifyDataSetChanged();
    }


}
