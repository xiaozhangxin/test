package com.ak.pt.mvp.fragment.statistics;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.AreaPressureBean;
import com.ak.pt.bean.BigAreaBean;
import com.ak.pt.bean.DepartmentBean;
import com.ak.pt.bean.DepartmentEvent;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.ak.pt.view.TopMiddlePopup;
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
 * Created by admin on 2018/11/23.
 */

public class OrderCompleteFragment extends BaseFragment<IPressureView, PressurePresenter> implements IPressureView {

    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRightTwo)
    ImageView ivRightTwo;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.bgTime)
    TextView bgTime;
    @BindView(R.id.tvStartTime)
    TextView tvStartTime;
    @BindView(R.id.tvEndTime)
    TextView tvEndTime;
    @BindView(R.id.tvLine)
    View tvLine;
    @BindView(R.id.etName)
    TextView etName;
    @BindView(R.id.tvDepartment)
    EditText tvDepartment;
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.recycleView)
    EasyRecyclerView recycleView;


    private List<PressurePageBean> list;
    private ArePressureListAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int page = 1;
    private String tvGroupuuid = "";

    private AppPermissionsBean permissionsBean;

    public static OrderCompleteFragment newInstance(AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        OrderCompleteFragment fragment = new OrderCompleteFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_test_list;
    }

    @Override
    public void initUI() {
        tvTitle.setText("试压记录");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("筛选");

        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ArePressureListAdapter(context, list);
        recycleView.setAdapterWithProgress(adapter);

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startOrderCompleteDetailFragment(adapter.getItem(position).getDoc_no(), permissionsBean);
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

    private void refresh() {
        map.put("doc_no", tvDepartment.getText().toString());
        map.put("start_time", tvStartTime.getText().toString());
        map.put("end_time", tvEndTime.getText().toString());
        map.put("group_parent_uuid", tvGroupuuid);
        map.put("is_select", "1");
        map.put("is_app", "1");
        map.put("operation", "1000");
        map.put("module_id", permissionsBean.getMenu_id());
        map.put("page", page + "");
        getPresenter().getTestPressureList(userBean.getStaff_token(), map);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        if ("0".equals(permissionsBean.getApp_data())) {
            map.put("staff_id", userBean.getStaff_id());
            map.put("job_name", userBean.getJob_name());
            etName.setVisibility(View.GONE);
        }
        refresh();
    }

    @Override
    public void OnQueryAreaCountPressurePage(List<AreaPressureBean> data) {

    }

    @Override
    public void OnQueryPressurePage(List<PressurePageBean> data) {

    }

    @Override
    public void OnQueryDetail(PressurePageBean data) {

    }

    @Override
    public void OnQueryBigAreaCountPressurePage(List<BigAreaBean> data) {

    }

    @Override
    public void OnGetTestPressureList(List<PressurePageBean> data, String total) {
        tvTitle.setText("试压记录(" + total + ")");
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAuditTestPressure(String data) {

    }

    @Override
    public void onSendWaterMessage(String data) {

    }

    @Override
    public void onDeleteTestPressure(String data) {

    }

    @Override
    public void onAuditAppTestPressure(String data) {

    }


    @OnClick({R.id.ivLeft, R.id.tvStartTime, R.id.tvEndTime, R.id.etName, R.id.ok, R.id.tvRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                getScreenPixels();
                setPopup(2);
                middlePopup.show(tvRight);
                break;
            case R.id.etName:
                startDepartmentPermissionFragment(permissionsBean.getMenu_id(), "complete");
                break;
            case R.id.tvStartTime:
                showStartTime();
                break;
            case R.id.tvEndTime:
                showEndTime();
                break;
            case R.id.ok:
                page = 1;
                refresh();
                recycleView.setRefreshing(true);
                break;
        }
    }

    private TopMiddlePopup middlePopup;
    public static int screenW, screenH;

    /**
     * 获取屏幕的宽和高
     */
    public void getScreenPixels() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenW = metrics.widthPixels;
        screenH = metrics.heightPixels;
    }

    private ArrayList<String> getItemsName() {
        ArrayList<String> items = new ArrayList<String>();
        items.add("全部");
        items.add("未发出");
        items.add("已发出");
        items.add("已接单");
        items.add("待审核");
        items.add("已审核");
        return items;
    }

    private void setPopup(int type) {
        middlePopup = new TopMiddlePopup(getActivity(), screenW, screenH,
                onItemClickListener, getItemsName(), type);
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String s = getItemsName().get(position);
            tvRight.setText(s);
            switch (s) {
                case "全部":
                    map.put("flow_state", "");
                    break;
                case "未发出":
                    map.put("flow_state", "order");
                    break;
                case "已发出":
                    map.put("flow_state", "accept");
                    break;
                case "已接单":
                    map.put("flow_state", "plan");
                    break;
                case "待审核":
                    map.put("flow_state", "success");
                    break;
                case "已审核":
                    map.put("flow_state", "audit");
                    break;
            }
            middlePopup.dismiss();
            page = 1;
            refresh();
            recycleView.setRefreshing(true);
        }
    };


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
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public PressurePresenter createPresenter() {
        return new PressurePresenter(getApp());
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
                DepartmentBean departmentBean = event.getmBean();
                etName.setText(departmentBean.getName());
                tvGroupuuid = departmentBean.getUuid();
                page = 1;
                refresh();
                recycleView.setRefreshing(true);
                break;

        }
    }
}