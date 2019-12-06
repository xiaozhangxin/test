package com.akan.qf.mvp.fragment.fsales;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.ClassList;
import com.akan.qf.bean.DepartmentBean;
import com.akan.qf.bean.DepartmentEvent;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.SaleDataBean;
import com.akan.qf.bean.SaleDataContrastBean;
import com.akan.qf.bean.TaskBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.home.SaleDataListAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.DispatchPresenter;
import com.akan.qf.mvp.view.home.IDispatchView;
import com.akan.qf.util.DynamicTimeFormat;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/11/20.
 */

public class SaleDataFragment extends BaseFragment<IDispatchView, DispatchPresenter> implements IDispatchView {

    Unbinder unbinder;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvFour)
    TextView tvFour;
    @BindView(R.id.tvFive)
    TextView tvFive;


    private List<SaleDataBean> list;
    private SaleDataListAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private Map<String, String> mapClass = new HashMap<>();
    private UserBean userBean;
    private int page = 1;
    private ClassicsHeader mClassicsHeader;
    private PermissionsBean permissionsBean;

    public static SaleDataFragment newInstance(PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        SaleDataFragment fragment = new SaleDataFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_dispatch_list_two;
    }

    @Override
    public void initUI() {
        tvTitle.setText("销量统计记录");
        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new SaleDataListAdapter(context, list);
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startSaleDataDetailFragment(adapter.getItem(position));
            }
        });
        int delta = new Random().nextInt(7 * 24 * 60 * 60 * 1000);
        mClassicsHeader = (ClassicsHeader) refreshLayout.getRefreshHeader();
        mClassicsHeader.setLastUpdateTime(new Date(System.currentTimeMillis() - delta));
        mClassicsHeader.setTimeFormat(new SimpleDateFormat("更新于 MM-dd HH:mm", Locale.CHINA));
        mClassicsHeader.setTimeFormat(new DynamicTimeFormat("更新于 %s"));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                page = 1;
                refresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                page++;
                refresh();
            }
        });
    }

    private void refresh() {
        String one = tvOne.getText().toString();
        String three = tvThree.getText().toString();
        if ("单个产品".equals(tvTwo.getText().toString())) {
            switch (one) {
                case "区域销量统计":
                    map.put("search_type", "area");
                    break;
                case "大区销量统计":
                    map.put("search_type", "big_area");
                    break;
                case "南北方销量统计":
                    map.put("search_type", "region");
                    break;
            }
            if ("全部".equals(three)) {
                map.put("class_name", "");
            } else {
                map.put("class_name", three);
            }
        } else {
            switch (one) {
                case "区域销量统计":
                    map.put("search_type", "area_all");
                    break;
                case "大区销量统计":
                    map.put("search_type", "big_area_all");
                    break;
                case "南北方销量统计":
                    map.put("search_type", "region_all");
                    break;
            }
        }
        String four = tvFour.getText().toString();
        if ("爱康企业集团".equals(four)) {
            map.put("area", "");
        } else {
            map.put("area", four);
        }
        map.put("year", tvFive.getText().toString());
        map.put("page", page + "");
        getPresenter().getSaleDataSumList(userBean.getStaff_token(), map);
    }

    @Override
    public void initData() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tvFive.setText(str);
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        getPresenter().getSaleTaskClassList(userBean.getStaff_token(), mapClass);
        tvOne.setText("区域销量统计");
        tvTwo.setText("单个产品");
        tvThree.setText("全部");
        tvFour.setText(userBean.getSimple_department_name());
        refreshLayout.autoRefresh();
        refresh();
    }

    @Override
    public void OnGetSaleTaskList(List<TaskBean> data) {

    }

    @Override
    public void OnGetSaleDataSumList(List<SaleDataBean> data) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        if (page == 1) {
            adapter.clear();
            if (data.size() <= 0) {
                tvEmpty.setVisibility(View.VISIBLE);
            } else {
                tvEmpty.setVisibility(View.GONE);
            }
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnGetSaleDataContrastList(List<SaleDataContrastBean> data) {

    }


    private void showTwoList(final List<ClassList> data) {

        List<String> sigList = new ArrayList<>();
        for (ClassList mm : data) {

            sigList.add(mm.getClass_name());
        }
        final String[] item = new String[data.size()];
        sigList.toArray(item);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("请选择");
        builder.setItems(item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                tvThree.setText(item[which]);
                refreshLayout.autoRefresh();
                page = 1;
                refresh();
            }
        });
        // 取消可以不添加
        //builder.setNegativeButton("取消",null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    List<ClassList> classList = new ArrayList<>();

    @Override
    public void OnGetSaleTaskClassList(List<ClassList> data) {
        classList.add(new ClassList("", "全部", "", ""));
        classList.addAll(data);
    }

    @OnClick({R.id.ivLeft, R.id.tvOne, R.id.tvTwo, R.id.tvThree, R.id.tvFour, R.id.tvFive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvOne:
                showOneList(new String[]{"区域销量统计", "大区销量统计", "南北方销量统计"}, 1);
                break;
            case R.id.tvTwo:
                showOneList(new String[]{"单个产品", "所有产品合计",}, 2);
                break;
            case R.id.tvThree:
                if (classList.size() <= 0) {
                    getPresenter().getSaleTaskClassList(userBean.getStaff_token(), mapClass);
                    return;
                }
                showTwoList(classList);
                break;
            case R.id.tvFour:

                switch (permissionsBean.getApp_data()) {
                    case "0":
                        ToastUtil.showToast(context.getApplicationContext(), "没有权限");
                        break;
                    case "1":
                        StartChooseDepartmentFragment("saleDate");
                        break;
                    case "2":
                        startDepartmentPermissionFragment(permissionsBean.getMenu_id(), "filter");
                        break;
                }

                break;
            case R.id.tvFive:
                showStartTime();
                break;

        }
    }

    private void showOneList(final String[] item, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("请选择");
        builder.setItems(item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (position) {
                    case 1:
                        tvOne.setText(item[which]);
                        break;
                    case 2:
                        tvTwo.setText(item[which]);
                        if ("单个产品".equals(item[which])) {
                            tvThree.setVisibility(View.VISIBLE);
                        } else {
                            tvThree.setVisibility(View.GONE);
                            map.put("class_name", "");
                        }

                        break;
                    case 3:
                        tvThree.setText(item[which]);
                        break;
                }
                refreshLayout.autoRefresh();
                page = 1;
                refresh();
            }
        });
        // 取消可以不添加
        //builder.setNegativeButton("取消",null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showStartTime() {
        TimePickerView build = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
                String format = formatter.format(date);
                tvFive.setText(format);
                refreshLayout.autoRefresh();
                page = 1;
                refresh();
            }
        })
                .setType(new boolean[]{true, false, false, false, false, false})
                .setCancelText("取消")
                .setSubmitText("确认")
                .setTitleSize(15)
                .setSubCalSize(14)
                .setTitleText("选择时间")
                .setOutSideCancelable(false)
                .isCyclic(true)
                .setTitleColor(Color.WHITE)
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)
                .setTitleBgColor(0xFF4DA9EB)
                .setBgColor(0xFFFFFFFF)
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(false)
                .isDialog(true)
                .build();
        build.show();

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
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public DispatchPresenter createPresenter() {
        return new DispatchPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(DepartmentEvent event) {
        switch (event.getmMsg()) {
            case "1":
                DepartmentBean bean = event.getmBean();
                tvFour.setText(bean.getName());
                page = 1;
                refreshLayout.autoRefresh();
                break;

        }
    }


}