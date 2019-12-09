package com.ak.pt.mvp.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.DepartmentBean;
import com.ak.pt.bean.FirstEvent;
import com.ak.pt.bean.FirstEventWorker;
import com.ak.pt.mvp.fragment.statistics.MemoryBean;
import com.ak.pt.mvp.fragment.statistics.PressurePageBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.OrderPresenter;
import com.ak.pt.mvp.view.IOrderView;
import com.ak.pt.util.DialogUtil;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/5/6.
 */

public class OrderAddWorkerFragment extends BaseFragment<IOrderView, OrderPresenter> implements IOrderView {

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
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    EditText tvTwo;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvFour)
    TextView tvFour;
    @BindView(R.id.tvFive)
    TextView tvFive;
    @BindView(R.id.tvSix)
    TextView tvSix;
    @BindView(R.id.tvRemark)
    EditText tvRemark;
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.tvAddDepartment)
    TextView tvAddDepartment;
    private String group_id = "";//所属区域id
    private AppPermissionsBean permissionsBean;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    public static OrderAddWorkerFragment newInstance(AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        OrderAddWorkerFragment fragment = new OrderAddWorkerFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_order_add_worker;
    }

    @Override
    public void initUI() {
        tvTitle.setText("新增试压报单");
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        group_id = userBean.getDepartment_id();
        tvAddDepartment.setText(userBean.getSimple_department_name());
    }

    @Override
    public void onInsertOrUpdateTestPressure(String data) {

    }

    @Override
    public void OnGetAppTestPressureList(List<PressurePageBean> data,String total) {

    }

    @Override
    public void onInsertOrUpdateAppTestPressure(String data) {
        EventBus.getDefault().post(new FirstEvent("refresh"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onGetMemoryList(String type, List<MemoryBean> data) {

    }

    private void showSingleAlertDialog(TextView textView, String title, String[] items) {
        DialogUtil.showSingleChoiceDialog(textView, title, items);
    }

    @OnClick({R.id.ivLeft, R.id.tvAddDepartment, R.id.tvOne, R.id.tvThree, R.id.tvFour, R.id.tvFive, R.id.tvSix, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvAddDepartment://选择所属区域
                startDepartmentPermissionFragment(permissionsBean.getMenu_id(), "order_add");
                break;
            case R.id.tvOne:
                showSingleAlertDialog(tvOne,"服务类型", new String[]{"管路试压", "地暖验收"});
                break;
            case R.id.tvThree:
                chooseTime(tvThree, "预约试压时间");
                break;
            case R.id.tvFour:
                showSingleAlertDialog(tvFour, "业主是否在场", new String[]{"是", "否"});
                break;
            case R.id.tvFive:
                showSingleAlertDialog(tvFive, "试压类型", new String[]{"初次试压", "二次试压", "三次试压", "事故试压", "整改试压"});
                break;
            case R.id.tvSix:
                showSingleAlertDialog(tvSix, "安装方式", new String[]{"装饰公司", "水电工全包", "水电半包", "自装", "项目经理私单", "其他"});
                break;

            case R.id.ok:
                String mTvOne = tvOne.getText().toString();
                if (TextUtils.isEmpty(mTvOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择服务类型");
                    return;
                }
                String mtvTwo = tvTwo.getText().toString();
                if (TextUtils.isEmpty(mtvTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入试压地址");
                    return;
                }
                String mtvThree = tvThree.getText().toString();
                if (TextUtils.isEmpty(mtvThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择预约试压时间");
                    return;
                }
                String mtvFour= tvFour.getText().toString();
                if (TextUtils.isEmpty(mtvFour)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择业主是否在场");
                    return;
                }
                String mtvFive = tvFive.getText().toString();
                if (TextUtils.isEmpty(mtvFive)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择试压类型");
                    return;
                }
                String mtvSix = tvSix.getText().toString();
                if (TextUtils.isEmpty(mtvTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择安装方式");
                    return;
                }


                ok.setEnabled(false);
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("is_weixin", "0");
                map.put("pressure_type", mTvOne);
                map.put("address", mtvTwo);
                map.put("book_time", mtvThree);
                map.put("is_presence", mtvFour);
                map.put("serve_type", mtvFive);
                map.put("install_type", mtvSix);
                map.put("description", tvRemark.getText().toString());
                map.put("is_app", "1");
                map.put("area_id", group_id);
                getPresenter().insertOrUpdateAppTestPressure(userBean.getStaff_token(), map);

                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEventWorker bean) {
        String msg = bean.getMsg();
        if ("orderAdd".equals(msg)) {
            DepartmentBean departmentBean = bean.getmDepartmentBean();
            tvAddDepartment.setText(departmentBean.getName());
            group_id = departmentBean.getUuid();
        }
    }

    private void chooseTime(TextView textView, String title) {
        DialogUtil.showTimePickerDialog(textView, title);
    }


    @Override
    public OrderPresenter createPresenter() {
        return new OrderPresenter(getApp());
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
        ok.setEnabled(true);
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());

    }

    @Override
    public void OnGetTestPressureList(List<PressurePageBean> data,String  total) {

    }


}
