package com.akan.qf.mvp.fragment.fsales;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.akan.qf.bean.ArchivesApplyBean;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.home.CustomerFileAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.CustomerFilePresenter;
import com.akan.qf.mvp.view.home.ICustomerFileView;
import com.akan.qf.util.DynamicTimeFormat;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/11/27.
 */

public class CustomerFileListFragment extends BaseFragment<ICustomerFileView, CustomerFilePresenter> implements ICustomerFileView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
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
    EditText etName;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.clean)
    Button clean;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;
    Unbinder unbinder;
    private List<ArchivesApplyBean> list;
    private CustomerFileAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int page = 1;
    private ClassicsHeader mClassicsHeader;
    private String tvGroupuuid;

    public static CustomerFileListFragment newInstance() {
        Bundle args = new Bundle();
        CustomerFileListFragment fragment = new CustomerFileListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_list_new;
    }

    @Override
    public void initUI() {
        tvTitle.setText("客户档案记录");
        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new CustomerFileAdapter(context, list);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                deletePosition=position;
                startCustomerFileDetailFragment(adapter.getItem(position));
            }
        });
        recycleView.setAdapter(adapter);

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

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        if ("0".equals(userBean.getIs_all_data())) {
            map.put("staff_uuid", userBean.getStaff_uuid());
        } else {
            map.put("group_parent_uuid", userBean.getGroup_parent_uuid_new());
        }
        map.put("page", page + "");
        getPresenter().getArchivesApplyList(userBean.getStaff_token(), map);
    }

    private void refresh() {

        if (TextUtils.isEmpty(etName.getText().toString())) {
            map.put("staff_name","");
        }else {
            map.put("staff_name", etName.getText().toString());
        }
        if (!TextUtils.isEmpty(tvStartTime.getText().toString())) {
            map.put("start_time", tvStartTime.getText().toString());
        }
        if (!TextUtils.isEmpty(tvEndTime.getText().toString())) {
            map.put("end_time", tvEndTime.getText().toString());
        }
        if ("0".equals(userBean.getIs_all_data())) {//有没有数据权限
            map.put("staff_uuid", userBean.getStaff_uuid());
        } else {
            if (!TextUtils.isEmpty(tvGroupuuid)) {//有数据权限后判断是否选择部门
                map.put("group_parent_uuid", tvGroupuuid);
            } else {
                map.put("group_parent_uuid", userBean.getGroup_parent_uuid_new());
            }
        }
        map.put("page", page + "");
        getPresenter().getArchivesApplyList(userBean.getStaff_token(), map);
    }




    @OnClick({R.id.ivLeft, R.id.tvStartTime, R.id.tvEndTime, R.id.tvDepartment, R.id.ok})
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
            case R.id.tvDepartment:
                if ("0".equals(userBean.getIs_all_data())) {
                    ToastUtil.showToast(context.getApplicationContext(), "没有权限");
                    return;
                }
                StartChooseDepartmentFragment("company");
                break;
            case R.id.ok:
                refreshLayout.autoRefresh();
                page=1;
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

    }


    @Override
    public CustomerFilePresenter createPresenter() {
        return new CustomerFilePresenter(getApp());
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
        if (msg.contains("uuid_compa")) {
            String s = event.getMsg().toString();
            tvDepartment.setText(s.substring(11, s.indexOf("+")));
            tvGroupuuid = s.substring((s.indexOf("+") + 1), s.length());
            page=1;
            refreshLayout.autoRefresh();
            refresh();
        }else if (msg.equals("leave_arrears")){
            adapter.remove(deletePosition);
        }

    }
    private int deletePosition;


    @Override
    public void OnInsertArchivesApply(String data) {

    }

    @Override
    public void OnGetArchivesApplyList(List<ArchivesApplyBean> data) {
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
    public void OngetArchivesApply(ArchivesApplyBean data) {

    }


    @Override
    public void OndeleteArchivesApply(String data) {

    }

    @Override
    public void OnupdateArchivesApply(String data) {

    }

    @Override
    public void onUploadFiles(String[] data) {

    }


}