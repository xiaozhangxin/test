package com.akan.qf.mvp.fragment.adaily;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.LableBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.DailyBean;
import com.akan.qf.bean.FirstEventFilter;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.ImageShowAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.fragment.ReviewFragment;
import com.akan.qf.mvp.presenter.home.DailyPresenter;
import com.akan.qf.mvp.view.home.IDailyView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.CustomDialog;
import com.akan.qf.view.img.ShowPictureActivity;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/7/11.
 */

public class MonthDetailFragment extends BaseFragment<IDailyView, DailyPresenter> implements IDailyView {

    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvTop)
    TextView tvTop;
    @BindView(R.id.tvNoTittle)
    TextView tvNoTittle;
    @BindView(R.id.tvNo)
    TextView tvNo;
    @BindView(R.id.tvNameTittle)
    TextView tvNameTittle;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvJobTittle)
    TextView tvJobTittle;
    @BindView(R.id.tvjob)
    TextView tvjob;
    @BindView(R.id.tvDepartmnetTittle)
    TextView tvDepartmnetTittle;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvStateTittle)
    TextView tvStateTittle;
    @BindView(R.id.tvState)
    TextView tvState;
    @BindView(R.id.tvTimeTittle)
    TextView tvTimeTittle;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.lineOne)
    View lineOne;
    @BindView(R.id.tvTodayTittle)
    TextView tvTodayTittle;
    @BindView(R.id.tvToday)
    TextView tvToday;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.tvTomorrowTittle)
    TextView tvTomorrowTittle;
    @BindView(R.id.tvTomorrow)
    TextView tvTomorrow;
    @BindView(R.id.lineTwo)
    View lineTwo;
    @BindView(R.id.tvAssessTittle)
    TextView tvAssessTittle;
    @BindView(R.id.tvAssess)
    TextView tvAssess;

    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String daily_id;
    private DailyBean bean;
    private List<String> imgList;
    private ImageShowAdapter imgAdapter;
    private PermissionsBean permissionsBean;

    public static MonthDetailFragment newInstance(String daily_id, PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        MonthDetailFragment fragment = new MonthDetailFragment();
        fragment.daily_id = daily_id;
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_month_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.monthly_details);
        imgList = new ArrayList<>();
        recycleView.setLayoutManager(new GridLayoutManager(context, 3));
        recycleView.setNestedScrollingEnabled(false);
        imgAdapter = new ImageShowAdapter(context, imgList);
        recycleView.setAdapter(imgAdapter);

        imgAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), ShowPictureActivity.class);
                intent.putExtra("imagelist", (Serializable) imgAdapter.getAllData());
                intent.putExtra("position", position);
                getActivity().startActivity(intent);
            }
        });

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    //请求单据详情
    private void refresh() {
        map.clear();
        map.put("daily_id", daily_id);
        getPresenter().getDailyDetail(userBean.getStaff_token(), map);
    }

    private String appOperation;
    private String orderStaffId;//制单人id

    @Override
    public void onGetDailyDetail(DailyBean data) {
        if (TextUtils.isEmpty(data.getDaily_id())) {
            showEmptyDialog();
            return;
        }
        orderStaffId = data.getStaff_id();
        //appOperation 0新增 1编辑 2删除 3审核
        appOperation = permissionsBean.getApp_operation();
        String[] strings = appOperation.split(",");
        switch (data.getDaily_state()) {
            case "wait_audit":
                if (data.getStaff_id().equals(userBean.getStaff_id())) {
                    if (isHave("2",strings)) {
                        tvRight.setVisibility(View.VISIBLE);
                        tvRight.setText(R.string.more);
                    } else {
                        tvRight.setVisibility(View.VISIBLE);
                        tvRight.setText(R.string.edit);
                    }
                } else {
                    showRight(strings);
                }
                tvState.setTextColor(getResources().getColor(R.color.audit_one));
                break;
            case "accept":
                if (data.getStaff_id().equals(userBean.getStaff_id())) {
                    tvRight.setVisibility(View.VISIBLE);
                    tvRight.setText(R.string.review_replay);
                } else {
                    if (isHave("3",strings)) {
                        tvRight.setVisibility(View.VISIBLE);
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
        tvjob.setText(data.getJob_name());
        tvToday.setText(data.getToday_work());
        tvTomorrow.setText(data.getTomorrow_work());
        if (data.getDailyImageBeans().size() <= 0) {
            recycleView.setVisibility(View.GONE);
        } else {
            recycleView.setVisibility(View.VISIBLE);
            imgAdapter.clear();
            imgAdapter.addAll(data.getDailyImageBeans());
            imgAdapter.notifyDataSetChanged();
        }
        List<DailyBean.DailyAuditBeansBean> dailyAuditBeans = data.getDailyAuditBeans();
        String audit = "";
        if (dailyAuditBeans.size() > 0) {
            for (int i = 0; i < dailyAuditBeans.size(); i++) {
                audit = audit + dailyAuditBeans.get(i).getStaff_name() + "：" +
                        dailyAuditBeans.get(i).getAudit_content() + "  "
                        + dailyAuditBeans.get(i).getCreate_time() + "\n\n";
            }
            tvAssess.setText(audit);
        } else {
            tvAssess.setText(R.string.no_comment);
        }


    }


    //右上角显示
    private void showRight(String[] strings) {
        if (isHave("1", strings) && isHave("2", strings) && isHave("3", strings)) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText(R.string.more);
        } else if (isHave("1", strings) && isHave("2", strings) && !isHave("3", strings)) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText(R.string.more);
        } else if (isHave("1", strings) && isHave("3", strings) && !isHave("2", strings)) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText(R.string.more);
        } else if (isHave("2", strings) && isHave("3", strings) && !isHave("1", strings)) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText(R.string.more);
        } else if (isHave("1", strings) && !isHave("2", strings) && !isHave("3", strings)) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText(R.string.edit);
        } else if (isHave("2", strings) && !isHave("1", strings) && !isHave("3", strings)) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText(R.string.delete);
        } else if (isHave("3", strings) && !isHave("1", strings) && !isHave("2", strings)) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText(R.string.review);
        } else if (!isHave("1", strings) && !isHave("2", strings) && !isHave("3", strings)) {
            tvRight.setVisibility(View.GONE);
        }

    }

    //数组钟是否包含某元素
    public boolean isHave(String index, String[] split) {
        for (int i = 0; i < split.length; i++) {
            if (index.equals(split[i])) {
                return true;
            }
        }
        return false;
    }


    //显示空单据弹框
    private void showEmptyDialog() {
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage(getString(R.string.deleted));
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
                dialog.dismiss();
            }
        });
        builder.onCreate().show();
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
                    case "编辑":
                        startMonthAddFragment(bean, "1", permissionsBean);
                        break;
                    case "删除":
                        toDelete();
                        break;
                    case "审阅":
                        startDayReviewFragment("0");
                        break;
                    case "审阅回复":
                        startDayReviewFragment("1");
                        break;
                    case "更多":
                        if (TextUtils.isEmpty(orderStaffId) || TextUtils.isEmpty(appOperation)) {
                            return;
                        }
                        moreOperation(orderStaffId, appOperation);
                        break;
                }
                break;
        }
    }


    //更多显示的操作  //appOperation  1编辑 2删除 3审核
    private void moreOperation(String orderStaffId, String appOperation) {
        if (orderStaffId.equals(userBean.getStaff_id())) {//自己的单据
            showList(new String[]{"编辑", "删除"});
        } else {
            String[] strings = appOperation.split(",");
            if (isHave("1", strings) && isHave("2", strings) && isHave("3", strings)) {
                showList(new String[]{"编辑", "删除", "审阅"});
            } else if (isHave("1", strings) && isHave("2", strings) && !isHave("3", strings)) {
                showList(new String[]{"编辑", "删除"});
            } else if (isHave("1", strings) && isHave("3", strings) && !isHave("2", strings)) {
                showList(new String[]{"编辑", "审阅"});
            } else if (isHave("2", strings) && isHave("3", strings) && !isHave("1", strings)) {
                showList(new String[]{"删除", "审阅"});
            }
        }
    }


    private AlertDialog alertDialog1;

    public void showList(final String[] items) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (items[i]) {
                    case "编辑":
                        startMonthAddFragment(bean, "1", permissionsBean);
                        break;
                    case "删除":
                        toDelete();
                        break;
                    case "审阅":
                        startDayReviewFragment("0");
                        break;
                }
                alertDialog1.dismiss();
            }


        });
        alertDialog1 = alertBuilder.create();
        alertDialog1.show();
    }

    //删除单据
    private void toDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.sure_delete_monthly);
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
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
    public void auditDaily(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        refresh();
    }

    @Override
    public void onDeleteDaily(String data) {
        EventBus.getDefault().post(new FirstEventFilter("month_delete"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void OnGetStaffSignList(List<LableBean> data) {

    }

    @Override
    public void onUpdateDaily(String data) {

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
