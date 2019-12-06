package com.akan.qf.mvp.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.DepartmentBean;
import com.akan.qf.bean.DepartmentEvent;
import com.akan.qf.bean.FilterBean;
import com.akan.qf.bean.FirstEventFilter;
import com.akan.qf.bean.LableBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.PostBean;
import com.akan.qf.bean.StaffSignListBean;
import com.akan.qf.bean.StateBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.PostAdapter;
import com.akan.qf.mvp.adapter.StateAdapter;
import com.akan.qf.mvp.adapter.home.LableAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.FilterPresenter;
import com.akan.qf.mvp.view.IFilterView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
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
 * Created by admin on 2019/4/1.
 */

public class FilterFragment extends BaseFragment<IFilterView, FilterPresenter> implements IFilterView {


    Unbinder unbinder;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.tvStartTime)
    TextView tvStartTime;
    @BindView(R.id.tvEndTime)
    TextView tvEndTime;
    @BindView(R.id.tvChoosePart)
    TextView tvChoosePart;
    @BindView(R.id.postRecycleView)
    RecyclerView postRecycleView;
    @BindView(R.id.statusRecycleView)
    RecyclerView statusRecycleView;
    @BindView(R.id.btn_reset)
    Button btnReset;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.lineOne)
    View lineOne;
    @BindView(R.id.lineTwo)
    View lineTwo;
    @BindView(R.id.tvStateTittle)
    TextView tvStateTittle;
    @BindView(R.id.tvTableTittle)
    TextView tvTableTittle;
    @BindView(R.id.tvChooseTable)
    TextView tvChooseTable;
    @BindView(R.id.tvChoosePartTittle)
    TextView tvChoosePartTittle;
    @BindView(R.id.labelRecycleView)
    RecyclerView labelRecycleView;
    @BindView(R.id.lableTittle)
    TextView lableTittle;
    @BindView(R.id.postTittle)
    TextView postTittle;

    private List<PostBean> list;
    private PostAdapter adapter;
    private List<LableBean> lableList;
    private LableAdapter lableAdapter;
    private List<StateBean> statelist;
    private StateAdapter adapterTwo;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String uuid = "";//部门id
    private String tableUuid = "";//区域投稿目录id
    private String state = "";//单据状态
    private String lableId = "";//标签id
    private String postName = "";//
    private String stateType;
    private String app_data;
    private String moduleId;
    private  LableBean mLableBean;

    public static AboutFragment newInstance() {
        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_filter;
    }

    @Override
    public void initUI() {

        statelist = new ArrayList<>();
        stateType = getArguments().getString("stateType");
        app_data = getArguments().getString("app_data");
        moduleId = getArguments().getString("module_id");
        mLableBean = (LableBean) getArguments().getSerializable("sign_list");

        lableId=mLableBean.getSign_id();

        //职位列表

        list = new ArrayList<>();
        postRecycleView.setNestedScrollingEnabled(false);
        postRecycleView.setLayoutManager(new GridLayoutManager(context, 3));
        adapter = new PostAdapter(context, list);
        postRecycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                boolean b = adapter.getItem(position).isCheck();
                adapter.getItem(position).setCheck(!b);
                adapter.notifyItemChanged(position);

            }
        });



        if (mLableBean.getStaffSignList().size() <= 0) {
            lableTittle.setVisibility(View.GONE);
            labelRecycleView.setVisibility(View.GONE);
        } else {
            lableTittle.setVisibility(View.VISIBLE);
            labelRecycleView.setVisibility(View.VISIBLE);
        }


        //标签列表
        lableList = new ArrayList<>();
        labelRecycleView.setNestedScrollingEnabled(false);
        labelRecycleView.setLayoutManager(new GridLayoutManager(context, 3));
        lableAdapter = new LableAdapter(context, mLableBean.getStaffSignList());
        labelRecycleView.setAdapter(lableAdapter);
        lableAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                List<StaffSignListBean> allData = lableAdapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    if (allData.get(i).isCheck()) {
                        allData.get(i).setCheck(false);
                    }
                }
                lableAdapter.getItem(position).setCheck(true);
                lableAdapter.notifyDataSetChanged();
                lableId = lableAdapter.getItem(position).getSign_id();
                requestJobName(uuid, lableId);

            }
        });


        //app_data 0仅自己 1本级和下级部门 2选择部门
        if ("0".equals(app_data)) {
            tvChoosePartTittle.setVisibility(View.GONE);
            tvChoosePart.setVisibility(View.GONE);
        }


        switch (stateType) {
            case "1":
                statelist.add(new StateBean("全部", "", false));
                statelist.add(new StateBean("未审核", "wait_audit", false));
                statelist.add(new StateBean("审核中", "auditing", false));
                statelist.add(new StateBean("已审核", "accept", false));
                statelist.add(new StateBean("拒绝", "refuse", false));
                break;
            case "2":
                statelist.add(new StateBean("全部", "", false));
                statelist.add(new StateBean("未审阅", "wait_audit", false));
                statelist.add(new StateBean("已审阅", "accept", false));
                break;
            case "3"://没有选择单据状态
                //隐藏状态选择
                lineOne.setVisibility(View.GONE);
                tvStateTittle.setVisibility(View.GONE);
                statusRecycleView.setVisibility(View.GONE);
                lineTwo.setVisibility(View.VISIBLE);
                break;
            case "4"://人员信息
                lineTwo.setVisibility(View.GONE);
                tvStateTittle.setText("在职状态");
                statelist.add(new StateBean("全部", "", false));
                statelist.add(new StateBean("在职", "0", false));
                statelist.add(new StateBean("离职", "1", false));

                break;
            case "5"://区域投稿
                lineTwo.setVisibility(View.GONE);
                tvTableTittle.setVisibility(View.VISIBLE);
                tvChooseTable.setVisibility(View.VISIBLE);
                statelist.add(new StateBean("全部", "", false));
                statelist.add(new StateBean("未审阅", "wait_audit", false));
                statelist.add(new StateBean("已审阅", "accept", false));
                break;
        }

        statusRecycleView.setNestedScrollingEnabled(false);
        statusRecycleView.setLayoutManager(new GridLayoutManager(context, 3));
        adapterTwo = new StateAdapter(context, statelist);
        statusRecycleView.setAdapter(adapterTwo);
        adapterTwo.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                List<StateBean> allData = adapterTwo.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    if (allData.get(i).isCheck()) {
                        allData.get(i).setCheck(false);
                    }
                }
                adapterTwo.getItem(position).setCheck(true);
                adapterTwo.notifyDataSetChanged();

            }
        });

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        // getPresenter().getStaffSignList(userBean.getStaff_token(), map);
        uuid = userBean.getGroup_parent_uuid_new();
        //tvChoosePart.setText(userBean.getDepartment_name());
        requestJobName(uuid, lableId);


    }

    //请求职位
    private void requestJobName(String uuid, String signId) {
        map.put("group_parent_uuid", uuid);
        map.put("staff_sign", signId);
        getPresenter().queryJobNames(userBean.getStaff_token(), map);
    }


    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
/*
        if (bean != null) {
            tvChoosePart.setText(bean.getName());
            map.put("group_parent_uuid", bean.getUuid());
        } else {
            tvChoosePart.setText(userBean.getSimple_department_name());
            map.put("group_parent_uuid", userBean.getGroup_parent_uuid_new());
        }
        getPresenter().queryJobNames(userBean.getStaff_token(), map);*/
    }

    @OnClick({R.id.tvStartTime, R.id.tvEndTime, R.id.tvChoosePart, R.id.tvChooseTable, R.id.btn_reset, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvStartTime:
                showStartTime();
                break;
            case R.id.tvEndTime:
                showEndTime();
                break;
            case R.id.tvChooseTable:
                StartChooseTableFragment("filter", new PermissionsBean());
                break;
            case R.id.tvChoosePart:
                switch (app_data) {
                    case "1":
                        StartChooseDepartmentFragment("filter");
                        break;
                    case "2":
                        startDepartmentPermissionFragment(moduleId, "filter");
                        break;
                }


                break;
            case R.id.btn_reset:
                tvStartTime.setText("");
                tvEndTime.setText("");
                tvChoosePart.setText("");
                tvChooseTable.setText("");
                uuid = "";
                tableUuid = "";
                lableId=mLableBean.getSign_id();
                List<StateBean> allData = adapterTwo.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    if (allData.get(i).isCheck()) {
                        allData.get(i).setCheck(false);
                    }
                }
                adapterTwo.notifyDataSetChanged();
                List<PostBean> postBeanList = adapter.getAllData();
                for (int i = 0; i < postBeanList.size(); i++) {
                    if (postBeanList.get(i).isCheck()) {
                        postBeanList.get(i).setCheck(false);
                    }
                }
                adapter.notifyDataSetChanged();

                List<StaffSignListBean> lableBeanList = lableAdapter.getAllData();
                for (int i = 0; i < lableBeanList.size(); i++) {
                    if (lableBeanList.get(i).isCheck()) {
                        lableBeanList.get(i).setCheck(false);
                    }
                }
                lableAdapter.notifyDataSetChanged();

                requestJobName(userBean.getGroup_parent_uuid_new(), lableId);
                break;
            case R.id.btn_confirm:
                //单据状态
                state = "";
                List<StateBean> allData1 = adapterTwo.getAllData();
                for (int i = 0; i < allData1.size(); i++) {
                    if (allData1.get(i).isCheck()) {
                        state = allData1.get(i).getState();
                    }
                }
                //职位
                postName = "";
                List<PostBean> allData2 = adapter.getAllData();
                for (int j = 0; j < allData2.size(); j++) {
                    if (allData2.get(j).isCheck()) {
                        postName = postName + allData2.get(j).getName() + ",";
                    }
                }

                EventBus.getDefault().post(new FirstEventFilter("1", new FilterBean(tvStartTime.getText().toString(), tvEndTime.getText().toString(), uuid, postName, state, tableUuid, lableId)));
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public FilterPresenter createPresenter() {
        return new FilterPresenter(getApp());
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

    DepartmentBean bean;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(DepartmentEvent event) {
        switch (event.getmMsg()) {
            case "1"://部门选择
                userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
                bean = event.getmBean();
                tvChoosePart.setText(bean.getName());
                uuid = bean.getUuid();
                requestJobName(uuid, lableId);
                break;
            case "table"://区域投稿标签选择
                DepartmentBean beanOne = event.getmBean();
                tvChooseTable.setText(beanOne.getName());
                tableUuid = beanOne.getUuid();
                break;
            case "expired"://登陆过期 关闭界面
                finish();
                break;
            case "sign_child":
                mLableBean = event.getLableBean();
                lableId=  mLableBean.getSign_id();
                List<StaffSignListBean> staffSignList = mLableBean.getStaffSignList();
                lableAdapter.clear();
                lableAdapter.addAll(staffSignList);
                lableAdapter.notifyDataSetChanged();

                for (int i=0;i<staffSignList.size();i++){
                    if (staffSignList.get(i).isCheck()){
                        lableId=staffSignList.get(i).getSign_id();
                    }
                }
                requestJobName(uuid, lableId);
                break;
        }
    }

    private ArrayList<PostBean> listA;

    @Override
    public void OnQueryJobNames(List<String> data) {
        if (data.size() <= 0) {
            postTittle.setVisibility(View.GONE);
            postRecycleView.setVisibility(View.GONE);
        } else {
            postTittle.setVisibility(View.VISIBLE);
            postRecycleView.setVisibility(View.VISIBLE);
        }


        if (listA == null) {
            listA = new ArrayList<>();
        }
        listA.clear();
        adapter.clear();
        for (int i = 0; i < data.size(); i++) {
            String sName = data.get(i);
            if (sName.contains("总监") | sName.contains("经理") | sName.contains("主管") | sName.contains("助理")) {
                adapter.add(new PostBean(sName, false));
            } else {
                listA.add(new PostBean(sName, false));
            }
        }
        adapter.addAll(listA);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnGetStaffSignList(List<LableBean> data) {
/*        if (data.size() <= 0) {
            lableTittle.setVisibility(View.GONE);
            labelRecycleView.setVisibility(View.GONE);
        } else {
            lableTittle.setVisibility(View.VISIBLE);
            labelRecycleView.setVisibility(View.VISIBLE);
        }

        lableAdapter.clear();
        lableAdapter.addAll(data);
        lableAdapter.notifyDataSetChanged();*/
    }
}
