package com.akan.qf.mvp.fragment.bapproval;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.FilterBean;
import com.akan.qf.bean.FirstEventFilter;
import com.akan.qf.bean.FirstEventNew;
import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.RecordListBean;
import com.akan.qf.bean.ReprotedBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.CopyAdapter;
import com.akan.qf.mvp.adapter.home.PayAduitAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.fragment.ReviewFragment;
import com.akan.qf.mvp.fragment.adaily.DailyDetailFragment;
import com.akan.qf.mvp.presenter.home.ReportPresenter;
import com.akan.qf.mvp.view.home.IReportView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.CustomDialog;
import com.akan.qf.view.DialogLoadding;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admin on 2018/11/19.
 */

public class ReportedDetailFragment extends BaseFragment<IReportView, ReportPresenter> implements IReportView {


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
    @BindView(R.id.tvNo)
    TextView tvNo;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvJobName)
    TextView tvJobName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvState)
    TextView tvState;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvBuyTime)
    TextView tvBuyTime;
    @BindView(R.id.tvUnit)
    TextView tvUnit;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvDepartmentCustome)
    TextView tvDepartmentCustome;
    @BindView(R.id.tvReturnNum)
    TextView tvReturnNum;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvCurrent)
    TextView tvCurrent;
    @BindView(R.id.tvDescribe)
    TextView tvDescribe;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @BindView(R.id.tvPackageNum)
    TextView tvPackageNum;
    @BindView(R.id.tvBagNum)
    TextView tvBagNum;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvManagerAudit)
    TextView tvManagerAudit;
    @BindView(R.id.tvManager)
    TextView tvManager;
    @BindView(R.id.tvTopTwo)
    TextView tvTopTwo;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.lineTop)
    TextView lineTop;
    @BindView(R.id.tvCheck)
    TextView tvCheck;
    @BindView(R.id.circleImageVIew)
    CircleImageView circleImageVIew;
    @BindView(R.id.ivCheckDelete)
    ImageView ivCheckDelete;
    @BindView(R.id.tvCheckPersonName)
    TextView tvCheckPersonName;
    @BindView(R.id.llCheck)
    ConstraintLayout llCheck;
    @BindView(R.id.tvCopyTittle)
    TextView tvCopyTittle;
    @BindView(R.id.copyRecycleView)
    RecyclerView copyRecycleView;
    @BindView(R.id.llCopy)
    LinearLayout llCopy;
    @BindView(R.id.tvAgree)
    TextView tvAgree;
    @BindView(R.id.tvRefuse)
    TextView tvRefuse;
    @BindView(R.id.llBottom)
    LinearLayout llBottom;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String ask_id;
    private String next_audit_id;
    private List<RecordListBean> auditList;
    private PayAduitAdapter auditAdapter;
    private List<MeParentBean> copyList;
    private CopyAdapter copyAdapter;
    private ReprotedBean bean;
    private DialogLoadding dialogLoadding;

    private PermissionsBean permissionsBean;

    public static ReportedDetailFragment newInstance(String ask_id, PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        ReportedDetailFragment fragment = new ReportedDetailFragment();
        fragment.ask_id = ask_id;
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_reported_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("工程报备详情");
        auditList = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        recycleView.setNestedScrollingEnabled(false);
        auditAdapter = new PayAduitAdapter(context, auditList);
        recycleView.setAdapter(auditAdapter);


        copyList = new ArrayList<>();
        copyList.add(new MeParentBean("", "", "", ""));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        copyRecycleView.setLayoutManager(linearLayoutManager);
        copyRecycleView.setNestedScrollingEnabled(false);
        copyAdapter = new CopyAdapter(context, copyList);
        copyRecycleView.setAdapter(copyAdapter);
        copyAdapter.setOnDeleteClickListener(new CopyAdapter.OnDeleteClickListener() {
            @Override
            public void delete(int position) {
                copyAdapter.remove(position);
            }
        });
        copyAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startChooseCheckPersonFragment("2");
            }
        });
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        //is_second_group_level 1是南北方大区 0不是
        if ("0".equals(userBean.getIs_second_group_level())) {
            next_audit_id = userBean.getParent_id();
            Glide.with(context)
                    .load(Constants.BASE_URL + userBean.getParent_head_img())
                    .error(R.drawable.error_img)
                    .into(circleImageVIew);
            tvCheckPersonName.setText(userBean.getParent_staff_name());
            map.clear();
            map.put("group_id", userBean.getDepartment_id());
            map.put("staff_id", userBean.getStaff_id());
            getPresenter().getCheckPerson(userBean.getStaff_token(), map);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();

    }

    private void refresh() {
        map.clear();
        map.put("apply_id", ask_id);
        getPresenter().getProjectApply(userBean.getStaff_token(), map);
    }


    //隐藏选择审核人和抄送审核部分
    private void auditGone() {
        llCheck.setVisibility(View.GONE);
        llCopy.setVisibility(View.GONE);
        llBottom.setVisibility(View.GONE);
    }


    @Override
    public void OnGetProjectApply(ReprotedBean data) {
        if (TextUtils.isEmpty(data.getApply_id())) {
            orderEmpty();
            return;
        }
        //appOperation  0新增 1编辑 2删除 3审核 4导出
        String appOperation = permissionsBean.getApp_operation();
        //wait_audit:未审核 auditing:审核中 accept:接受 refuse:拒绝
        String[] strings = appOperation.split(",");
        switch (data.getApply_state()) {
            case "wait_audit":
                if (userBean.getStaff_id().equals(data.getStaff_id())) {
                    llCheck.setVisibility(View.GONE);
                    if (isHave("2", strings)) {
                        tvRight.setVisibility(View.VISIBLE);
                        tvRight.setText(R.string.more);
                    } else {
                        tvRight.setVisibility(View.VISIBLE);
                        tvRight.setText(R.string.edit);
                    }
                } else {
                    if (isHave("1", strings)&&isHave("2", strings)) {
                        tvRight.setVisibility(View.VISIBLE);
                        tvRight.setText(R.string.more);

                    } else if (isHave("1", strings)) {
                        tvRight.setVisibility(View.VISIBLE);
                        tvRight.setText(R.string.edit);

                    } else if (isHave("2", strings)) {
                        tvRight.setVisibility(View.VISIBLE);
                        tvRight.setText(R.string.delete);
                    } else {
                        tvRight.setVisibility(View.GONE);
                    }
                    isShowCheck(data.getNext_staff_id());
                }

                tvState.setTextColor(getResources().getColor(R.color.audit_one));
                break;
            case "auditing":
                isShowCheck(data.getNext_staff_id());
                tvState.setTextColor(getResources().getColor(R.color.yuan_one));
                break;
            case "accept":
                auditGone();
                tvRight.setVisibility(View.GONE);
                tvState.setTextColor(getResources().getColor(R.color.audit_two));
                break;
            case "refuse":
                auditGone();
                tvRight.setVisibility(View.GONE);
                tvState.setTextColor(getResources().getColor(R.color.red));
                break;
        }
        bean = data;
        tvJobName.setText(data.getJob_name());
        tvName.setText(data.getStaff_name());
        tvNo.setText(data.getApply_no());
        tvState.setText(data.getApply_state_show());
        tvTime.setText(data.getApply_create_time());
        tvDepartment.setText(data.getStaff_department());

        tvBuyTime.setText(data.getApply_category());
        tvUnit.setText(data.getApply_name());
        tvPhone.setText(data.getApply_department());


        tvDepartmentCustome.setText(data.getApply_title());
        tvReturnNum.setText(data.getApply_address());
        tvType.setText(data.getApply_first() + " " + data.getApply_first_tel());
        tvCurrent.setText(data.getApply_second() + " " + data.getApply_second_tel());
        tvDescribe.setText(data.getApply_size());
        tvTotal.setText(data.getApply_shop());
        tvPackageNum.setText(data.getApply_join());
        tvBagNum.setText(data.getApply_number());
        tvOne.setText(data.getApply_compete());
        tvTwo.setText(data.getApply_area());
        tvThree.setText(data.getApply_suggest());
        tvManagerAudit.setText(data.getApply_company());
        tvManager.setText(data.getApply_remark());

        auditAdapter.clear();
        auditAdapter.addAll(data.getRecordList());
        auditAdapter.notifyDataSetChanged();

    }

    //是否显示选择审核人
    private void isShowCheck(String next_id) {
        if (userBean.getStaff_id().equals(next_id)) {
            if ("1".equals(userBean.getIs_second_group_level())) {
                llCheck.setVisibility(View.GONE);
            } else {
                llCheck.setVisibility(View.VISIBLE);
            }
            llCopy.setVisibility(View.VISIBLE);
            llBottom.setVisibility(View.VISIBLE);
        } else {
            auditGone();
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



    //单据被删除
    private void orderEmpty() {
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


    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.tvAgree, R.id.tvRefuse, R.id.circleImageVIew, R.id.ivCheckDelete})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                switch (tvRight.getText().toString()) {
                    case "更多":
                        showList();
                        break;
                    case "编辑":
                        startReportedAddFragment(bean, "1", permissionsBean);
                        break;
                    case "删除":
                        toDelete();
                        break;
                }
                break;
            case R.id.circleImageVIew:
                startChooseCheckPersonFragment("1");
                break;
            case R.id.ivCheckDelete:
                ivCheckDelete.setVisibility(View.GONE);
                tvCheckPersonName.setText("请选择");
                Glide.with(context).load(Constants.BASE_URL)
                        .error(R.drawable.check_img)
                        .into(circleImageVIew);
                next_audit_id = "";
                break;
            case R.id.tvAgree:
                if ("0".equals(userBean.getIs_second_group_level())) {
                    if (TextUtils.isEmpty(next_audit_id)) {
                        ToastUtil.showToast(context.getApplicationContext(),
                                getString(R.string.please_select_next_reviewer));
                        return;
                    }
                }
                toAgree();
                break;
            case R.id.tvRefuse:
                toRefuse();
                break;
        }
    }

    //审核同意
    private void toAgree() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_edittext, null);
        final EditText edit = (EditText) inflate.findViewById(R.id.editText);//获得输入框对象
        builder.setView(inflate);
        builder.setNegativeButton(getString(R.string.cancel), null);
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogLoadding = new DialogLoadding(context);
                dialogLoadding.showDialog();
                String cc_person = "";
                String cc_person_name = "";
                List<MeParentBean> allData = copyAdapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    String staffId = allData.get(i).getStaff_id();
                    if (!TextUtils.isEmpty(staffId)) {
                        cc_person = cc_person + staffId + ",";
                        if (0 == i) {
                            cc_person_name = allData.get(i).getStaff_name();
                        } else {
                            cc_person_name = cc_person_name + "," + allData.get(i).getStaff_name();
                        }
                    }
                }
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("apply_id", ask_id);
                map.put("is_egis", "1");
                map.put("cc_person", cc_person);
                map.put("cc_person_name", cc_person_name);
                map.put("apply_remark", edit.getText().toString());
                if (!TextUtils.isEmpty(next_audit_id)) {
                    map.put("next_staff_id", next_audit_id);
                }
                getPresenter().auditProjectApply(userBean.getStaff_token(), map);

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //审核拒绝
    private void toRefuse() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        final View inflate1 = LayoutInflater.from(context).inflate(R.layout.dialog_edittext_two, null);
        final EditText edit1 = (EditText) inflate1.findViewById(R.id.editText);
        builder1.setView(inflate1);
        builder1.setNegativeButton(getString(R.string.cancel), null);
        builder1.setPositiveButton(getString(R.string.ok), null);
        final AlertDialog alertDialog1 = builder1.create();
        alertDialog1.setCancelable(false);
        alertDialog1.show();
        alertDialog1.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edit1.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    ToastUtil.showToast(context.getApplicationContext(),
                            getString(R.string.enter_reason_rejection));
                    return;
                }
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("apply_id", ask_id);
                map.put("apply_remark", s);
                map.put("is_egis", "0");
                getPresenter().auditProjectApply(userBean.getStaff_token(), map);
                alertDialog1.cancel();
            }
        });
    }

    private AlertDialog alertDialog1;

    public void showList() {
        final String[] items = {"编辑", "删除"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        startReportedAddFragment(bean, "1", permissionsBean);
                        break;
                    case 1:
                        toDelete();
                        break;
                }

                alertDialog1.dismiss();
            }
        });
        alertDialog1 = alertBuilder.create();
        alertDialog1.show();
    }

    //单据删除
    private void toDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(getString(R.string.delete_order));
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("apply_id", ask_id);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "2");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().deleteProjectApply(userBean.getStaff_token(), map);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public void OnAuditProjectApply(String data) {
        if (dialogLoadding != null) {
            dialogLoadding.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        refresh();
    }

    @Override
    public void OnDeleteProjectApply(String data) {
        EventBus.getDefault().post(new FirstEventFilter("report_delete"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void OnupdateProjectApply(String data) {

    }

    @Override
    public void onGetCheckPerson(List<MeParentBean> data) {
        for (int i = 0; i < data.size(); i++) {
            String module = data.get(i).getStaff_module();
            if (module.contains("ProjectApply")) {
                String staff_give = data.get(i).getStaff_give();
                if ("1".equals(staff_give)) {
                    MeParentBean checkBean = data.get(i);
                    next_audit_id = checkBean.getStaff_id();
                    Glide.with(context)
                            .load(Constants.BASE_URL + checkBean.getHead_img())
                            .error(R.drawable.error_img)
                            .into(circleImageVIew);
                    tvCheckPersonName.setText(checkBean.getStaff_name());
                }

            }
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public ReportPresenter createPresenter() {
        return new ReportPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEventNew event) {
        switch (event.getMsg()) {
            case "1":
                MeParentBean bean = event.getmBean();
                Glide.with(context)
                        .load(Constants.BASE_URL + bean.getHead_img())
                        .error(R.drawable.error_img)
                        .into(circleImageVIew);
                tvCheckPersonName.setText(bean.getStaff_name());
                ivCheckDelete.setVisibility(View.VISIBLE);
                next_audit_id = bean.getStaff_id();
                break;
            case "2":
                List<MeParentBean> allData = copyAdapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    if (event.getmBean().getStaff_id().equals(allData.get(i).getStaff_id())) {
                        ToastUtil.showToast(context.getApplicationContext(), getString(R.string.cope_already_choose));
                        return;
                    }
                }
                copyAdapter.remove(copyAdapter.getAllData().size() - 1);
                copyAdapter.add(event.getmBean());
                copyAdapter.add(new MeParentBean("", "", "", ""));
                copyAdapter.notifyDataSetChanged();
                break;
        }
    }


    @Override
    public void OnInsertProjectApply(String data) {

    }

    @Override
    public void OnGetProjectApplyList(List<ReprotedBean> data, String total) {

    }


}