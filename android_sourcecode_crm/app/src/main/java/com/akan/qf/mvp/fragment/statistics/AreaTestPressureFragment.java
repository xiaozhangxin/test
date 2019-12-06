package com.akan.qf.mvp.fragment.statistics;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.DepartmentBean;
import com.akan.qf.bean.DepartmentEvent;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.fragment.qifei.AreListAdapter;
import com.akan.qf.mvp.fragment.qifei.AreaPressureBean;
import com.akan.qf.mvp.fragment.qifei.BigAreaBean;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/12/3.
 */

public class AreaTestPressureFragment extends BaseFragment<IPtRankView, PtRankPresenter> implements IPtRankView {
    Unbinder unbinder;

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvStartTime)
    TextView tvStartTime;
    @BindView(R.id.tvEndTime)
    TextView tvEndTime;
    @BindView(R.id.tvDepartment)
    EditText tvDepartment;
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.recycleView)
    EasyRecyclerView recycleView;

    private List<BigAreaBean> list;
    private AreListAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int page = 1;
    private String tvGroupuuid = "";
    private PermissionsBean permissionsBean;

    public static AreaTestPressureFragment newInstance(PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        AreaTestPressureFragment fragment = new AreaTestPressureFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_pressuer_big_list_new;
    }

    @Override
    public void initUI() {
        tvTitle.setText("区域试压量排行");
        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new AreListAdapter<>(context, list);
        recycleView.setAdapterWithProgress(adapter);
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
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                refresh();
            }
        });
    }

    private void refresh() {
        String start = tvStartTime.getText().toString();
        if (TextUtils.isEmpty(start)) {
            map.put("start_time", "");

        } else {
            map.put("start_time", start);
        }
        String end = tvEndTime.getText().toString();
        if (TextUtils.isEmpty(end)) {
            map.put("end_time", "");
        } else {
            map.put("end_time", end);
        }
//        map.put("group_parent_uuid", tvGroupuuid);
        map.put("group_name", tvDepartment.getText().toString());
        map.put("page", page + "");
        map.put("type", "2");
        getPresenter().getAreaTestPressure(userBean.getStaff_token(), map);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);
        Date starDate = calendar.getTime();
        String str = formatter.format(starDate);
        tvStartTime.setText(str);
        refresh();
    }

    @Override
    public void OnGetAreaTestPressure(List<BigAreaBean> data) {
        if (page == 1) {
            adapter.clear();

        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }


    @OnClick({R.id.ivLeft, R.id.tvStartTime, R.id.tvEndTime, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvStartTime:
                showStartTime();
                break;
            case R.id.tvEndTime:
                showEndTime();
                break;
            case R.id.ok:
                page = 1;
                recycleView.setRefreshing(true);
                refresh();

                break;
        }
    }

    private void showStartTime() {
        //获取当前年月日
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);//当前年
        int month = calendar.get(Calendar.MONTH);//当前月
        int day = calendar.get(Calendar.DAY_OF_MONTH);//当前日
        //new一个日期选择对话框的对象,并设置默认显示时间为当前的年月日时间.
        DatePickerDialog dialog = new DatePickerDialog(getContext(), mdateListener, year, month, day);
        dialog.show();
    }

    private void showEndTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getContext(), mdateListenerTwo, year, month, day);
        dialog.show();
    }

    /**
     * 日期选择的回调监听
     */
    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int years, int monthOfYear, int dayOfMonth) {
            String time = years + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
            String s = formatDate(time);
            tvStartTime.setText(s);
        }


    };
    private DatePickerDialog.OnDateSetListener mdateListenerTwo = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int years, int monthOfYear, int dayOfMonth) {
            String time = years + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
            String s = formatDate(time);
            tvEndTime.setText(s);
        }
    };

    //将日历选择的日期日起进行格式转换
    private String formatDate(String date) {
        Date parse = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            parse = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return simpleDateFormat.format(parse);
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
//        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public PtRankPresenter createPresenter() {
        return new PtRankPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
//        EventBus.getDefault().unregister(this);
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEventMainThread(DepartmentEvent event) {
//        switch (event.getmMsg()) {
//            case "1":
//                DepartmentBean departmentBean = event.getmBean();
//                tvDepartment.setText(departmentBean.getName());
//                tvGroupuuid = departmentBean.getUuid();
//                page = 1;
//                recycleView.setRefreshing(true);
//                refresh();
//                break;
//
//        }
//    }
}