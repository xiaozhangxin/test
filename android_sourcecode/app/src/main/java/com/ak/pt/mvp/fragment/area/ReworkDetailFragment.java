package com.ak.pt.mvp.fragment.area;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.FirstEventFilter;
import com.ak.pt.bean.FirstEventNew;
import com.ak.pt.bean.MeParentBean;
import com.ak.pt.bean.PeopleRecordListBean;
import com.ak.pt.bean.PressureBackBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.area.PayAduitAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.area.ReworkPresenter;
import com.ak.pt.mvp.view.area.IReworkView;
import com.ak.pt.util.CustomDialog;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.bumptech.glide.Glide;


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
import com.uniquext.android.widget.view.CornerImageView;

/**
 * Created by admin on 2019/5/30.
 */

public class ReworkDetailFragment extends BaseFragment<IReworkView, ReworkPresenter> implements IReworkView {


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
    @BindView(R.id.tvJobName)
    TextView tvJobName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvState)
    TextView tvState;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvFour)
    TextView tvFour;
    @BindView(R.id.tvFive)
    TextView tvFive;
    @BindView(R.id.tvSix)
    TextView tvSix;
    @BindView(R.id.tvSeven)
    TextView tvSeven;
    @BindView(R.id.tvEight)
    TextView tvEight;
    @BindView(R.id.tvNine)
    TextView tvNine;
    @BindView(R.id.tvTen)
    TextView tvTen;
    @BindView(R.id.tvEleven)
    TextView tvEleven;
    @BindView(R.id.tvTopTwo)
    TextView tvTopTwo;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.lineTwo)
    View lineTwo;
    @BindView(R.id.tvCheck)
    TextView tvCheck;
    @BindView(R.id.circleImageVIew)
    CornerImageView circleImageVIew;
    @BindView(R.id.ivCheckDelete)
    ImageView ivCheckDelete;
    @BindView(R.id.tvCheckPersonName)
    TextView tvCheckPersonName;
    @BindView(R.id.llchooseckeck)
    LinearLayout llchooseckeck;
    @BindView(R.id.tvAgree)
    TextView tvAgree;
    @BindView(R.id.tvRefuse)
    TextView tvRefuse;
    @BindView(R.id.llBottom)
    LinearLayout llBottom;

    private Map<String, String> map = new HashMap<>();
    private Map<String, String> map2 = new HashMap<>();
    private UserBean userBean;
    private String detail_id;
    private String next_audit_id = "";
    private PressureBackBean bean;

    private List<PeopleRecordListBean> auditList;
    private PayAduitAdapter auditAdapter;
    private AppPermissionsBean permissionsBean;

    public static ReworkDetailFragment newInstance(String detail_id, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        ReworkDetailFragment fragment = new ReworkDetailFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.detail_id = detail_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_rework_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("试压仪返修详情");
        auditList = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        recycleView.setNestedScrollingEnabled(false);
        auditAdapter = new PayAduitAdapter(context, auditList);
        recycleView.setAdapter(auditAdapter);

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        llchooseckeck.setVisibility(View.GONE);
        //is_second_group_level 1是南北方大区 0不是
        if ("1".equals(userBean.getIs_second_group_level())) {
            llchooseckeck.setVisibility(View.GONE);
        } else {
            llchooseckeck.setVisibility(View.VISIBLE);
        }
        map2.clear();
        getPresenter().getLargeStaffList(userBean.getStaff_token(), map2);
    }

    @Override
    public void onResume() {
        super.onResume();
        map.clear();
        map.put("back_id", detail_id);
        getPresenter().getPressureBackDetail(userBean.getStaff_token(), map);
    }


    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.tvAgree, R.id.tvRefuse, R.id.circleImageVIew, R.id.ivCheckDelete})
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
                        startreworkAddFragment(bean, "1", permissionsBean);
                        break;
                    case "更多":
                        showList(new String[]{"编辑", "删除"});
                        break;
                }
                break;
            case R.id.circleImageVIew:
                startChooseCheckFragment("2");
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
                        ToastUtil.showToast(context.getApplicationContext(), getString(R.string.please_choose_next_audit));
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

    //审核拒绝
    private void toRefuse() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        final View inflate1 = LayoutInflater.from(context).inflate(R.layout.dialog_edittext_two, null);
        final EditText edit1 = (EditText) inflate1.findViewById(R.id.editText);//获得输入框对象
        builder1.setView(inflate1);
        builder1.setNegativeButton(getString(R.string.cancel), null);
        builder1.setPositiveButton(getString(R.string.sure), null);
        final AlertDialog alertDialog1 = builder1.create();
        alertDialog1.setCancelable(false);
        alertDialog1.show();
        alertDialog1.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edit1.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.please_input_refuse_reason));
                    return;
                }
                map.clear();
                map.put("back_id", detail_id);
                map.put("is_audit", "0");
                map.put("remark", s);
                getPresenter().auditPressureBack(userBean.getStaff_token(), map);
                alertDialog1.cancel();
            }
        });
    }

    //审核同意
    private void toAgree() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_edittext, null);
        final EditText edit = (EditText) inflate.findViewById(R.id.editText);
        builder.setView(inflate);
        builder.setNegativeButton(getString(R.string.cancel), null);
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String mAudit = edit.getText().toString();
                map.clear();
                map.put("back_id", detail_id);
                map.put("is_audit", "1");
                if (!TextUtils.isEmpty(mAudit)) {
                    map.put("remark", mAudit);
                }
                if (!TextUtils.isEmpty(next_audit_id)) {
                    map.put("next_audit_id", next_audit_id);
                }
                getPresenter().auditPressureBack(userBean.getStaff_token(), map);
            }
        });
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
                        startreworkAddFragment(bean, "1", permissionsBean);
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

    //删除单据
    private void onDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(getString(R.string.sure_delete_apply));
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("back_id", detail_id);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "2");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().deletePressureBack(userBean.getStaff_token(), map);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
    public ReworkPresenter createPresenter() {
        return new ReworkPresenter(getApp());
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
            case "2":
                MeParentBean bean = event.getmBean();
                Glide.with(context)
                        .load(Constants.BASE_URL + bean.getHead_img())
                        .error(R.drawable.error_img)
                        .into(circleImageVIew);
                tvCheckPersonName.setText(bean.getStaff_name());
                ivCheckDelete.setVisibility(View.VISIBLE);
                next_audit_id = bean.getStaff_id();
                break;
        }
    }

    @Override
    public void oninsertPressureBack(String data) {

    }

    @Override
    public void ondeletePressureBack(String data) {
        EventBus.getDefault().post(new FirstEventFilter("filter_delete"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onupdatePressureBack(String data) {

    }

    @Override
    public void onauditPressureBack(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        map.clear();
        map.put("back_id", detail_id);
        getPresenter().getPressureBackDetail(userBean.getStaff_token(), map);
    }

    @Override
    public void OngetPressureBackList(List<PressureBackBean> data) {

    }

    private void isShowCheck(String next_id) {
        if (userBean.getStaff_id().equals(next_id)) {
            if ("1".equals(userBean.getIs_second_group_level())) {
                llchooseckeck.setVisibility(View.GONE);
            } else {
                llchooseckeck.setVisibility(View.VISIBLE);
            }
            llBottom.setVisibility(View.VISIBLE);
        } else {
            llchooseckeck.setVisibility(View.GONE);
            llBottom.setVisibility(View.GONE);
        }
    }

    @Override
    public void ongetPressureBackDetail(PressureBackBean data) {
        if (TextUtils.isEmpty(data.getBack_id())) {
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
        bean = data;
        String appOperation = permissionsBean.getApp_operation();
        //wait_audit:未审核 auditing:审核中 accept:接受 refuse:拒绝
        switch (data.getBack_state()) {
            case "wait_audit":
                if (userBean.getStaff_id().equals(data.getStaff_id())) {
                    if (appOperation.contains("2")) {
                        tvRight.setVisibility(View.VISIBLE);
                        tvRight.setText("更多");
                    } else {
                        tvRight.setVisibility(View.VISIBLE);
                        tvRight.setText("编辑");
                    }
                } else {
                    tvRight.setVisibility(View.VISIBLE);
                    if (appOperation.contains("2") && appOperation.contains("3")){
                        tvRight.setText("更多");
                    } else if (appOperation.contains("2")) {
                        tvRight.setText("编辑");
                    } else if (appOperation.contains("3")) {
                        tvRight.setText("删除");
                    } else {
                        tvRight.setVisibility(View.GONE);
                    }
                }
                isShowCheck(data.getNext_audit_id());
                tvState.setTextColor(getResources().getColor(R.color.audit_one));
                break;
            case "auditing":
                isShowCheck(data.getNext_audit_id());
                tvState.setTextColor(getResources().getColor(R.color.audit_one));
                break;
            case "accept":
                llchooseckeck.setVisibility(View.GONE);
                llBottom.setVisibility(View.GONE);
                tvState.setTextColor(getResources().getColor(R.color.audit_two));
                break;
            case "refuse":
                llchooseckeck.setVisibility(View.GONE);
                llBottom.setVisibility(View.GONE);
                tvState.setTextColor(getResources().getColor(R.color.red));
                break;
        }
        tvJobName.setText(data.getJob_name());
        tvName.setText(data.getStaff_name());
        tvNo.setText(data.getBack_no());
        tvState.setText(data.getBack_state_show());
        tvTime.setText(data.getCreate_time());
        tvDepartment.setText(data.getDepartment_name());

        tvOne.setText(data.getSend_name());
        tvTwo.setText(data.getSend_address());
        tvThree.setText(data.getSend_tel());
        tvFour.setText(data.getTool_name());
        tvFive.setText(data.getOrder_no());
        tvSix.setText(data.getShop_name());
        tvSeven.setText(data.getBack_remark());
        tvEight.setText(data.getReceipt_name());
        tvNine.setText(data.getReceipt_tel());
        tvTen.setText(data.getReceipt_address());
        tvEleven.setText(data.getRemark());
        auditAdapter.clear();
        auditAdapter.addAll(data.getRecordList());
        auditAdapter.notifyDataSetChanged();

    }

    @Override
    public void ongetLargeStaffList(List<MeParentBean> data) {
        for (int i = 0; i < data.size(); i++) {
            MeParentBean bean = data.get(i);
            if ("1".equals(bean.getStaff_give())) {
                Glide.with(context)
                        .load(Constants.BASE_URL + bean.getHead_img())
                        .error(R.drawable.error_img)
                        .into(circleImageVIew);
                tvCheckPersonName.setText(bean.getStaff_name());
                ivCheckDelete.setVisibility(View.VISIBLE);
                next_audit_id = bean.getStaff_id();
            }


        }
    }
}