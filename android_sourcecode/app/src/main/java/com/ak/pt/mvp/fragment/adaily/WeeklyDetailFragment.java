package com.ak.pt.mvp.fragment.adaily;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.DailyBean;
import com.ak.pt.bean.FirstEventFilter;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.fragment.ReviewFragment;
import com.ak.pt.mvp.presenter.DailyPresenter;
import com.ak.pt.mvp.view.IDailyView;
import com.ak.pt.util.CustomDialog;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;


import org.greenrobot.eventbus.EventBus;

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

public class WeeklyDetailFragment extends BaseFragment<IDailyView, DailyPresenter> implements IDailyView {

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
    @BindView(R.id.tvTop)
    TextView tvTop;
    @BindView(R.id.tvNo)
    TextView tvNo;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvjob)
    TextView tvjob;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvState)
    TextView tvState;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvToday)
    TextView tvToday;
    @BindView(R.id.tvTomorrow)
    TextView tvTomorrow;
    @BindView(R.id.tvAssess)
    TextView tvAssess;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String daily_id;
    private DailyBean bean;
    private AppPermissionsBean permissionsBean;

    public static WeeklyDetailFragment newInstance(String daily_id, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        WeeklyDetailFragment fragment = new WeeklyDetailFragment();
        fragment.daily_id = daily_id;
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_weekiy_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.week_detail);
        tvRight.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }

    @Override
    public void onResume() {
        super.onResume();
        map.put("daily_id", daily_id);
        getPresenter().getDailyDetail(userBean.getStaff_token(), map);
    }

    @Override
    public void onInsertDaily(String data) {

    }

    @Override
    public void onUploadFiles(String[] data) {

    }

    @Override
    public void onGetDailyList(List<DailyBean> data, String total) {

    }

    @Override
    public void onGetDailyDetail(DailyBean data) {
        if (TextUtils.isEmpty(data.getDaily_id())) {
            final CustomDialog.Builder builder1 = new CustomDialog.Builder(context);
            builder1.setMessage(getString(R.string.deleted));
            builder1.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    dialog.dismiss();
                }
            });
            builder1.onCreate().show();
            return;
        }
        //appOperation 0新增 1编辑 2删除 3审核
        String appOperation = permissionsBean.getApp_operation();
        switch (data.getDaily_state()) {
            case "wait_audit":
                if (data.getStaff_id().equals(userBean.getStaff_id())) {
                    if (appOperation.contains("2")) {
                        tvRight.setText(R.string.more);
                    } else {
                        tvRight.setText(R.string.edit);
                    }

                } else {
                    if (appOperation.contains("3")) {
                        tvRight.setText(R.string.review);
                    } else {
                        tvRight.setVisibility(View.GONE);
                    }
                }
                tvState.setTextColor(getResources().getColor(R.color.audit_one));
                break;
            case "accept":
                if (data.getStaff_id().equals(userBean.getStaff_id())) {
                    tvRight.setText(R.string.review_replay);
                } else {
                    if (appOperation.contains("3")) {
                        tvRight.setText(R.string.review);
                    } else {
                        tvRight.setVisibility(View.GONE);
                    }
                }

                tvState.setTextColor(getResources().getColor(R.color.audit_two));
                break;
        }
        bean = data;
        tvNo.setText(data.getDaily_no());
        tvState.setText(data.getDaily_state_show());
        tvName.setText(data.getStaff_name());
        tvTime.setText(data.getCreate_time());
        tvDepartment.setText(data.getDepartment_name());
        tvjob.setText(data.getJob());
        tvToday.setText(data.getToday_work());
        tvTomorrow.setText(data.getTomorrow_work());
        List<DailyBean.DailyAuditBeansBean> dailyAuditBeans = data.getDailyAuditBeans();
        if (dailyAuditBeans.size() > 0) {
            StringBuilder audit = new StringBuilder();
            for (int i = 0; i < dailyAuditBeans.size(); i++) {
                DailyBean.DailyAuditBeansBean bean = dailyAuditBeans.get(i);
                audit.append(bean.getStaff_name() + "：<font color=\"#333333\">"
                        + bean.getAudit_content() + "</font>&nbsp&nbsp&nbsp<font color=\"#999999\"><small>"
                        + bean.getCreate_time().substring(0, 16) + "</small></font><br><br>");
            }
            tvAssess.setText(Html.fromHtml(audit.toString()));
        } else {
            tvAssess.setText(R.string.no_comment);
        }
    }

    @Override
    public void auditDaily(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        map.clear();
        map.put("daily_id", daily_id);
        getPresenter().getDailyDetail(userBean.getStaff_token(), map);
    }

    @Override
    public void onUpdateDaily(String data) {


    }

    @Override
    public void onDeleteDaily(String data) {
        EventBus.getDefault().post(new FirstEventFilter("week_delete"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                String s = tvRight.getText().toString();
                switch (s) {
                    case "删除":
                        onDelete();
                        break;
                    case "编辑":
                        startWeeklyAddFragment(bean, "1", permissionsBean);
                        break;
                    case "审阅":
                        startDayReviewFragment("0");
                        break;
                    case "审阅回复":
                        startDayReviewFragment("1");
                        break;
                    case "更多":
                        showList(new String[]{"编辑", "删除"});
                        break;
                }

                break;
        }
    }


    //删除周报
    private void onDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.sure_delete_week);
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("daily_id", daily_id);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "2");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().deleteDaily(userBean.getStaff_token(), map);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private AlertDialog alertDialog1;

    public void showList(final String[] items) {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (items[i]) {
                    case "编辑":
                        startWeeklyAddFragment(bean, "1", permissionsBean);
                        break;
                    case "删除":
                        onDelete();
                        break;
                }

                alertDialog1.dismiss();
            }
        });
        alertDialog1 = alertBuilder.create();
        alertDialog1.show();
    }

    private void startDayReviewFragment(String type) {
        ReviewFragment fragment = ReviewFragment.newInstance(type);
        fragment.setOnReviewClickListener(new ReviewFragment.OnReviewClickListener() {
            @Override
            public void ok(String content) {
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("daily_id", daily_id);
                map.put("audit_content", content);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "3");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().auditDaily(userBean.getStaff_token(), map);
            }
        });

        fragment.show(getFragmentManager(), DailyDetailFragment.class.getSimpleName());
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
