package com.ak.pt.mvp.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.DepartmentBean;
import com.ak.pt.bean.DepartmentEvent;
import com.ak.pt.bean.FilterBean;
import com.ak.pt.bean.FirstEventFilter;
import com.ak.pt.bean.PostBean;
import com.ak.pt.bean.StateBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.PostAdapter;
import com.ak.pt.mvp.adapter.StateAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.FilterPresenter;
import com.ak.pt.mvp.view.IFilterView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
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
    @BindView(R.id.tvChoosePartTittle)
    TextView tvChoosePartTittle;
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

    private List<PostBean> list;
    private PostAdapter adapter;
    private List<StateBean> statelist;
    private StateAdapter adapterTwo;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String uuid = "";
    private String state = "";
    private String postName = "";
    private String stateType;
    private String app_data;
    private String permission;
    private String moduleId;

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


        statelist = new ArrayList<>();
        stateType = getArguments().getString("stateType");
        app_data = getArguments().getString("app_data");
        permission = getArguments().getString("permission");
        moduleId = getArguments().getString("module_id");

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
        // tvChoosePart.setText(userBean.getSimple_department_name());
        map.put("group_parent_uuid", userBean.getGroup_parent_uuid_new());
        getPresenter().queryJobNames(userBean.getStaff_token(), map);
    }

    @Override
    public void onResume() {
        super.onResume();

       /*
       userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
       if (bean != null) {
            tvChoosePart.setText(bean.getName());
            map.put("group_parent_uuid", bean.getUuid());
        } else {
            tvChoosePart.setText(userBean.getSimple_department_name());
            map.put("group_parent_uuid", userBean.getGroup_parent_uuid_new());
        }
        getPresenter().queryJobNames(userBean.getStaff_token(), map);*/
    }

    @OnClick({R.id.tvStartTime, R.id.tvEndTime, R.id.tvChoosePart, R.id.btn_reset, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvStartTime:
                showStartTime();
                break;
            case R.id.tvEndTime:
                showEndTime();
                break;
            case R.id.tvChoosePart:
                startDepartmentPermissionFragment(moduleId, "filter");
                break;
            case R.id.btn_reset:
                tvStartTime.setText("");
                tvEndTime.setText("");
                tvChoosePart.setText("");
                uuid = "";
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
                EventBus.getDefault().post(new FirstEventFilter("1", new FilterBean(tvStartTime.getText().toString(), tvEndTime.getText().toString(), uuid, postName, state)));
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
    public FilterPresenter createPresenter() {
        return new FilterPresenter(getApp());
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
    public void onEventMainThread(DepartmentEvent event) {
        switch (event.getmMsg()) {
            case "1":
                //userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
                DepartmentBean bean = event.getmBean();
                tvChoosePart.setText(bean.getName());
                uuid = bean.getUuid();
                map.put("group_parent_uuid", uuid);
                getPresenter().queryJobNames(userBean.getStaff_token(), map);
                break;
        }
    }


    //将职位是 总监 主管 经理 助理的提前
    private ArrayList<PostBean> listA;

    @Override
    public void OnQueryJobNames(List<String> data) {
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
}
