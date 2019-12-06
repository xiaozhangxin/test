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
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.ClassList;
import com.akan.qf.bean.CostTypeBean;
import com.akan.qf.bean.DepartmentBean;
import com.akan.qf.bean.DepartmentEvent;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.ProfitBean;
import com.akan.qf.bean.SaleDataContrastBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.ProfitListAdapter;
import com.akan.qf.mvp.adapter.home.AnalysisListAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.sale.ProfitReportPresenter;
import com.akan.qf.mvp.view.sale.IProfitReportView;
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
import java.util.Calendar;
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

public class ProfitReportFragment extends BaseFragment<IProfitReportView, ProfitReportPresenter> implements IProfitReportView {

    Unbinder unbinder;
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
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;



    private List<ProfitBean> list;
    private ProfitListAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int page = 1;
    private ClassicsHeader mClassicsHeader;
    List<CostTypeBean> classList = new ArrayList<>();
    private PermissionsBean permissionsBean;

    public static ProfitReportFragment newInstance(PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        ProfitReportFragment fragment = new ProfitReportFragment();
        fragment.setArguments(args);
        fragment.permissionsBean=permissionsBean;
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_profir_report;
    }

    @Override
    public void initUI() {
        tvTitle.setText("利润表");
        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ProfitListAdapter(context, list);
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startProfitReportDetailFragment(adapter.getItem(position));
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
        switch (one) {
            case "区域利润表":
                map.put("area_type", "area");
                break;
            case "大区利润表":
                map.put("area_type", "big_area");
                break;
            case "南北方利润表":
                map.put("area_type", "sale_center");
                break;
        }

        map.put("year", tvFour.getText().toString());
        map.put("is_select", "1");
        map.put("is_app", "1");
        map.put("operation", "1000");
        map.put("module_id", permissionsBean.getMenu_id());
        map.put("page", page + "");
        getPresenter().queryCostTypeProfitStatisticList(userBean.getStaff_token(), map);


    }

    @Override
    public void initData() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Calendar c = Calendar.getInstance();
        int month = new Date().getMonth();
        if (month == 0) {
            c.add(Calendar.YEAR, -1);
        }
        Date y = c.getTime();
        String str = formatter.format(y);
        tvFour.setText(str);
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        getPresenter().queryCostTypeList(userBean.getStaff_token(), map);
        tvOne.setText("区域利润表");
        tvThree.setText(userBean.getSimple_department_name());
        map.put("group_uid",userBean.getGroup_parent_uuid_new());
        refreshLayout.autoRefresh();
        refresh();


    }




    @OnClick({R.id.ivLeft, R.id.tvOne, R.id.tvTwo, R.id.tvThree, R.id.tvFour})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvOne:
                showOneList(new String[]{"区域利润表", "大区利润表", "南北方利润表"}, 1);
                break;
            case R.id.tvTwo:
                showTwoList(classList);
                break;
            case R.id.tvThree:

                switch (permissionsBean.getApp_data()) {
                    case "0":
                        ToastUtil.showToast(context.getApplicationContext(), "没有权限");
                        break;
                    case "1":
                        StartChooseDepartmentFragment("profit");
                        break;
                    case "2":
                        startDepartmentPermissionFragment(permissionsBean.getMenu_id(),"filter");
                        break;
                }
                break;
            case R.id.tvFour:
                showStartTime();
                break;


        }
    }

    private void showTwoList(final List<CostTypeBean> data) {

        List<String> sigList = new ArrayList<>();
        for (CostTypeBean mm : data) {

            sigList.add(mm.getName());
        }
        final String[] item = new String[data.size()];
        sigList.toArray(item);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvTwo.setText(item[which]);
                map.put("cost_type_id", data.get(which).getId());
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


    private void showOneList(final String[] item, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (position) {
                    case 1:
                        tvOne.setText(item[which]);
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
                tvFour.setText(format);
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
    public ProfitReportPresenter createPresenter() {
        return new ProfitReportPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(DepartmentEvent event) {
        switch (event.getmMsg()){
            case "1":
                DepartmentBean bean = event.getmBean();
                tvThree.setText(bean.getName());
                map.put("group_uid",bean.getUuid());
                page = 1;
                refreshLayout.autoRefresh();
                break;
        }


    }


    @Override
    public void OnQueryCostTypeList(List<CostTypeBean> data) {
        if (data.size() > 0) {
            tvTwo.setText(data.get(0).getName());
            map.put("cost_type_id", data.get(0).getId());
        }
        classList.addAll(data);
    }

    @Override
    public void OnQueryCostTypeProfitStatisticList(List<ProfitBean> data) {
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
}