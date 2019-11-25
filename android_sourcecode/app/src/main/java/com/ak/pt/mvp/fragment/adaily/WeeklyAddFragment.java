package com.ak.pt.mvp.fragment.adaily;

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
import com.ak.pt.bean.DailyBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.DailyPresenter;
import com.ak.pt.mvp.view.IDailyView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.ak.pt.view.DialogLoading;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/6/28.
 */

public class WeeklyAddFragment extends BaseFragment<IDailyView, DailyPresenter> implements IDailyView {

    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvTodayTittle)
    TextView tvTodayTittle;
    @BindView(R.id.tvToday)
    EditText tvToday;
    @BindView(R.id.tvTomorrowTittle)
    TextView tvTomorrowTittle;
    @BindView(R.id.dtvTomorrow)
    EditText tvTomorrow;
    @BindView(R.id.ok)
    Button ok;
    private DialogLoading dialogLoading;

    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String type;
    private DailyBean data;
    private AppPermissionsBean permissionsBean;

    public static WeeklyAddFragment newInstance(DailyBean bean, String type,AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        WeeklyAddFragment fragment = new WeeklyAddFragment();
        fragment.data = bean;
        fragment.type = type;
        fragment.permissionsBean=permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_weekly;
    }

    @Override
    public void initUI() {

        if ("0".equals(type)) {
            ok.setText(R.string.commit);
            tvTitle.setText(R.string.week_add);
        } else {
            tvTitle.setText(R.string.week_update);
            ok.setText(R.string.update_sure);
            tvToday.setText(data.getToday_work());
            tvTomorrow.setText(data.getTomorrow_work());
        }
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }

    @Override
    public void onInsertDaily(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onUploadFiles(String[] data) {

    }

    @Override
    public void onGetDailyList(List<DailyBean> data,String total) {

    }

    @Override
    public void onGetDailyDetail(DailyBean data) {

    }

    @Override
    public void auditDaily(String data) {

    }

    @Override
    public void onUpdateDaily(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onDeleteDaily(String data) {

    }


    @OnClick({R.id.ivLeft, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ok:
                String today = tvToday.getText().toString().trim();
                if (TextUtils.isEmpty(today)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请填写本周小结");
                    return;
                }
                String tomorrow = tvTomorrow.getText().toString();
                if (TextUtils.isEmpty(tomorrow)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请填写下周计划");
                    return;
                }
                ok.setEnabled(false);

                dialogLoading = new DialogLoading(context);
                dialogLoading.showDialog();
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("today_work", today);
                map.put("tomorrow_work", tomorrow);
                map.put("daily_type", "1");
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());
                if ("1".equals(type)) {
                    map.put("operation", "1");
                    map.put("daily_id", data.getDaily_id());
                    getPresenter().updateDaily(userBean.getStaff_token(), map);
                } else {
                    map.put("operation", "0");
                    getPresenter().insertDaily(userBean.getStaff_token(), map);
                }
                break;
        }
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
    public DailyPresenter createPresenter() {
        return new DailyPresenter(getApp());
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


}
