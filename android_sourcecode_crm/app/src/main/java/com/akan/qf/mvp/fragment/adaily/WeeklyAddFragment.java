package com.akan.qf.mvp.fragment.adaily;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.LableBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.DailyBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.DailyPresenter;
import com.akan.qf.mvp.view.home.IDailyView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.DialogLoadding;
import com.google.gson.Gson;
import com.king.base.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;

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
    private DialogLoadding dialogLoadding;

    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String type;
    private DailyBean data;

    private PermissionsBean permissionsBean;
    public static WeeklyAddFragment newInstance(DailyBean bean, String type,PermissionsBean permissionsBean) {
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initUI() {

        if ("0".equals(type)) {
            ok.setText("提交");
            tvTitle.setText("新增周报");
            getData();

        } else {
            tvTitle.setText("修改周报");
            ok.setText("确认修改");
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
        dialogLoadding.closeDialog();
        ToastUtil.showToast(context.getApplicationContext(), data);
        tvToday.setText("");
        tvTomorrow.setText("");
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
        dialogLoadding.closeDialog();
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onDeleteDaily(String data) {

    }

    @Override
    public void OnGetStaffSignList(List<LableBean> data) {

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

                dialogLoadding = new DialogLoadding(context);
                dialogLoadding.showDialog();
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("today_work", today);
                map.put("tomorrow_work", tomorrow);
                map.put("daily_type", "1");
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());
                map.put("operation", "0");
                if ("1".equals(type)) {
                    map.put("daily_id", data.getDaily_id());
                    getPresenter().updateDaily(userBean.getStaff_token(), map);
                } else {
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
        saveData();
        unbinder.unbind();
    }
    private void saveData() {
        DailyBean dailyBean = new DailyBean();
        dailyBean.setToday_work(tvToday.getText().toString());
        dailyBean.setTomorrow_work(tvTomorrow.getText().toString());
        String s = new Gson().toJson(dailyBean);
        SharedPreferences sharedPre = context.getSharedPreferences("beanData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("week", s);
        editor.commit();
    }

    private void getData() {
        SharedPreferences sp = context.getSharedPreferences("beanData", MODE_PRIVATE);
        String s = sp.getString("week", "");
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject object = new JSONObject(s);
                tvToday.setText(object.getString("today_work"));
                tvTomorrow.setText(  object.getString("tomorrow_work"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
