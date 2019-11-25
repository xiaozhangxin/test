package com.ak.pt.mvp.fragment.people;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import com.ak.pt.bean.ClerkInfoListBean;
import com.ak.pt.bean.FirstEventFilter;
import com.ak.pt.bean.FirstEventNew;
import com.ak.pt.bean.MeParentBean;
import com.ak.pt.bean.RecordListBean;
import com.ak.pt.bean.StaffApplyBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.people.EntryAduitAdapter;
import com.ak.pt.mvp.adapter.people.EntryPeopleInfoAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.people.EntryPresenter;
import com.ak.pt.mvp.view.people.IEntryView;
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

public class EntryDetailFragment extends BaseFragment<IEntryView, EntryPresenter> implements IEntryView {


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
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvAddOne)
    TextView tvAddOne;
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
    @BindView(R.id.recycleViewPeople)
    RecyclerView recycleViewPeople;
    @BindView(R.id.tvTopTwo)
    TextView tvTopTwo;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.lineCheck)
    View lineCheck;
    @BindView(R.id.tvCheck)
    TextView tvCheck;
    @BindView(R.id.circleImageVIew)
    CornerImageView circleImageVIew;
    @BindView(R.id.ivCheckDelete)
    ImageView ivCheckDelete;
    @BindView(R.id.tvCheckPersonName)
    TextView tvCheckPersonName;
    @BindView(R.id.tvAgree)
    TextView tvAgree;
    @BindView(R.id.tvRefuse)
    TextView tvRefuse;
    @BindView(R.id.llBottom)
    LinearLayout llBottom;
    @BindView(R.id.llchooseckeck)
    ConstraintLayout llchooseckeck;
    @BindView(R.id.tvAddTwo)
    TextView tvAddTwo;
    @BindView(R.id.tvAddThree)
    TextView tvAddThree;

    private Map<String, String> map = new HashMap<>();
    private Map<String, String> map2 = new HashMap<>();
    private UserBean userBean;
    private String detail_id;//单据id
    private String next_audit_id;//下一位审核人id

    private List<RecordListBean> auditList;
    private EntryAduitAdapter auditAdapter;
    private List<ClerkInfoListBean> peopleList;
    private EntryPeopleInfoAdapter peopleAdapter;
    private StaffApplyBean bean;

    private AppPermissionsBean permissionsBean;

    public static EntryDetailFragment newInstance(String detail_id, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        EntryDetailFragment fragment = new EntryDetailFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.detail_id = detail_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_detail_entry;
    }

    @Override
    public void initUI() {
        tvTitle.setText("入职申请详情");
        auditList = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        recycleView.setNestedScrollingEnabled(false);
        auditAdapter = new EntryAduitAdapter(context, auditList);
        recycleView.setAdapter(auditAdapter);

        peopleList = new ArrayList<>();
        recycleViewPeople.setLayoutManager(new LinearLayoutManager(context));
        recycleViewPeople.setNestedScrollingEnabled(false);
        peopleAdapter = new EntryPeopleInfoAdapter(context, peopleList);
        recycleViewPeople.setAdapter(peopleAdapter);

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        //is_second_group_level 1是南北方大区 0不是
        if ("1".equals(userBean.getIs_second_group_level())) {
            llchooseckeck.setVisibility(View.GONE);
        } else {
            llchooseckeck.setVisibility(View.VISIBLE);
            next_audit_id = userBean.getParent_id();
            Glide.with(context)
                    .load(Constants.BASE_URL + userBean.getParent_head_img())
                    .error(R.drawable.error_img)
                    .into(circleImageVIew);
            tvCheckPersonName.setText(userBean.getParent_staff_name());
            map2.clear();
            map2.put("group_id", userBean.getDepartment_id());
            map2.put("staff_id", userBean.getStaff_id());
            getPresenter().getParentDepartmentStaffList(userBean.getStaff_token(), map2);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();

    }

    //请求单据详情
    private void refresh() {
        map.clear();
        map.put("apply_id", detail_id);
        getPresenter().getStaffApplyDetail(userBean.getStaff_token(), map);
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
    public void onGgetParentDepartmentStaffList(List<MeParentBean> data) {
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
                        startEntryAddFragment(bean, "1", permissionsBean);
                        break;
                    case "更多":
                        showList(new String[]{"编辑", "删除"});
                        break;
                }
                break;
            case R.id.circleImageVIew:
                startChooseCheckFragment("1");
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
                        ToastUtil.showToast(context.getApplicationContext(), "请选择下一位审核人");
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

    //同意单据
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
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "2");
                map.put("module_id", permissionsBean.getMenu_id());
                map.put("apply_id", detail_id);
                map.put("is_audit", "1");
                if (!TextUtils.isEmpty(mAudit)) {
                    map.put("remark", mAudit);
                }
                if (!TextUtils.isEmpty(next_audit_id)) {
                    map.put("next_audit_id", next_audit_id);
                }
                getPresenter().auditStaffApply(userBean.getStaff_token(), map);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    //拒绝申请
    private void toRefuse() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_edittext_two, null);
        final EditText edit = (EditText) inflate.findViewById(R.id.editText);
        builder.setView(inflate);
        builder.setNegativeButton(getString(R.string.cancel), null);
        builder.setPositiveButton(getString(R.string.sure), null);
        final AlertDialog refuseDialog = builder.create();
        refuseDialog.setCancelable(false);
        refuseDialog.show();
        refuseDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edit.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入拒绝原因");
                    return;
                }
                map.clear();
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "2");
                map.put("module_id", permissionsBean.getMenu_id());
                map.put("apply_id", detail_id);
                map.put("is_audit", "0");
                map.put("remark", s);
                getPresenter().auditStaffApply(userBean.getStaff_token(), map);
                refuseDialog.cancel();
            }
        });
    }

    //删除单据
    private void onDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.sure_delete_apply);
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("apply_id", detail_id);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "2");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().deleteStaffApply(userBean.getStaff_token(), map);
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
                         startEntryAddFragment(bean, "1", permissionsBean);
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
    public EntryPresenter createPresenter() {
        return new EntryPresenter(getApp());
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
        }
    }

    @Override
    public void insertStaffApply(String data) {

    }

    @Override
    public void deleteStaffApply(String data) {
        EventBus.getDefault().post(new FirstEventFilter("entry_delete"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void updateStaffApply(String data) {

    }

    @Override
    public void auditStaffApply(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        refresh();
    }

    @Override
    public void getStaffApplyList(List<StaffApplyBean> data) {

    }

    @Override
    public void getStaffApplyDetail(StaffApplyBean data) {
        if (TextUtils.isEmpty(data.getApply_id())) {
            showEmptyView();

            return;
        }
        bean = data;
        String appOperation = permissionsBean.getApp_operation();
        //wait_audit:未审核 auditing:审核中 accept:接受 refuse:拒绝
        //0新增1编辑2删除3审核
        tvRight.setVisibility(View.GONE);
        switch (data.getApply_state()) {
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


        tvNo.setText(data.getApply_no());
        tvName.setText(data.getStaff_name());
        tvJobName.setText(data.getJob_name());
        tvTime.setText(data.getCreate_time());
        tvState.setText(data.getApply_state_show());
        tvDepartment.setText(data.getDepartment_name());

        tvAddOne.setText(data.getLarge_area_name());
        tvAddTwo.setText(data.getRegion_name());
        tvAddThree.setText(data.getShop_name());


        tvOne.setText(data.getContact_name());
        tvTwo.setText(data.getContact_tel());
        tvThree.setText(data.getPerson_num());
        tvFour.setText(data.getPressure_num());
        tvFive.setText(data.getPhoto_tool());
        tvSix.setText(data.getVehicle());

        auditAdapter.clear();
        auditAdapter.addAll(data.getRecordList());
        auditAdapter.notifyDataSetChanged();
        peopleAdapter.clear();


        peopleAdapter.addAll(data.getClerkInfoList());
        peopleAdapter.addAll(data.getPressureInfoList());
        peopleAdapter.notifyDataSetChanged();
    }

    //显示单据不存在窗口
    private void showEmptyView() {
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage(getString(R.string.deleted));
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
                dialog.dismiss();
            }
        });
        builder.onCreate().show();
    }



}