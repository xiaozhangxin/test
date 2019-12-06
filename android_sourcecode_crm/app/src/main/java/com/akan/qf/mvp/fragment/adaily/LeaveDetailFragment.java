package com.akan.qf.mvp.fragment.adaily;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
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
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.FileListBean;
import com.akan.qf.bean.FirstEventFilter;
import com.akan.qf.bean.FirstEventNew;
import com.akan.qf.bean.LeaveBean;
import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.activity.TbsFileActivity;
import com.akan.qf.mvp.adapter.CopyAdapter;
import com.akan.qf.mvp.adapter.home.ImageLeaveAdapter;
import com.akan.qf.mvp.adapter.home.LeaveAduitAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.LeavePresenter;
import com.akan.qf.mvp.view.home.ILeaveView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.CustomDialog;
import com.akan.qf.view.DialogLoadding;
import com.akan.qf.view.img.ShowPictureActivity;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
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
 * Created by admin on 2018/7/13.
 */

public class LeaveDetailFragment extends BaseFragment<ILeaveView, LeavePresenter> implements ILeaveView {


    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;

    @BindView(R.id.tvAgree)
    TextView tvAgree;

    @BindView(R.id.tvRefuse)
    TextView tvRefuse;

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
    @BindView(R.id.tvImgTittle)
    TextView tvImgTittle;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.llImg)
    LinearLayout llImg;
    @BindView(R.id.tvTopTwo)
    TextView tvTopTwo;
    @BindView(R.id.recycleViewTwo)
    RecyclerView recycleViewTwo;
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
    @BindView(R.id.llBottom)
    LinearLayout llBottom;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String detail_id;
    private String next_audit_id;
    private int auditSize = 0;
    private List<FileListBean> imgList;
    private ImageLeaveAdapter imgAdapter;
    private List<LeaveBean.AskLeaveAuditBeansBean> auditList;
    private LeaveAduitAdapter auditAdapter;
    private LeaveBean bean;
    private List<MeParentBean> copyList;
    private CopyAdapter copyAdapter;
    private DialogLoadding dialogLoadding;

    private PermissionsBean permissionsBean;

    public static LeaveDetailFragment newInstance(String detail_id, PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        LeaveDetailFragment fragment = new LeaveDetailFragment();
        fragment.detail_id = detail_id;
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_leave_detail_new;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.leave_details);
        initImgList();
        initAuditList();
        initCopyList();

    }

    //初始化抄送人
    private void initCopyList() {
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

    //初始化审批流程
    private void initAuditList() {
        auditList = new ArrayList<>();
        recycleViewTwo.setLayoutManager(new LinearLayoutManager(context));
        recycleViewTwo.setNestedScrollingEnabled(false);
        auditAdapter = new LeaveAduitAdapter(context, auditList);
        recycleViewTwo.setAdapter(auditAdapter);
    }

    //初始化请假单附件
    private void initImgList() {
        imgList = new ArrayList<>();
        recycleView.setLayoutManager(new GridLayoutManager(context, 3));
        recycleView.setNestedScrollingEnabled(false);
        imgAdapter = new ImageLeaveAdapter(context, imgList);
        recycleView.setAdapter(imgAdapter);
        imgAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String upUrl = imgAdapter.getItem(position).getFile_path();
                if (upUrl.endsWith(".png") | upUrl.endsWith(".jpg") | upUrl.endsWith(".jpeg")) {
                    ArrayList<String> list = new ArrayList<>();
                    int mPosition = 0;
                    for (int i = 0; i < imgAdapter.getAllData().size(); i++) {
                        String file_path = imgAdapter.getItem(i).getFile_path();
                        if (file_path.equals(upUrl)) {
                            mPosition = list.size();
                        }
                        if (file_path.endsWith(".png") | file_path.endsWith(".jpg") | file_path.endsWith(".jpeg")) {
                            list.add(file_path);
                        }
                    }
                    Intent intent = new Intent(getActivity(), ShowPictureActivity.class);
                    intent.putExtra("imagelist", (Serializable) list);
                    intent.putExtra("position", mPosition);
                    getActivity().startActivity(intent);
                } else {
                    Intent intentVisit = new Intent(getActivity(), TbsFileActivity.class);
                    intentVisit.putExtra("file_url", upUrl);
                    intentVisit.putExtra("file_name", imgAdapter.getItem(position).getFile_name());
                    startActivity(intentVisit);
                }
            }
        });
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        //is_second_group_level 1南北方大区 0不是 权限高层不需要再指定审核人
        if ("0".equals(userBean.getIs_second_group_level())) {
            llCheck.setVisibility(View.VISIBLE);
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

    //请求单据详情
    private void refresh() {
        map.clear();
        map.put("ask_id", detail_id);
        getPresenter().getLeaveDetail(userBean.getStaff_token(), map);
    }

    @Override
    public void onGetLeaveDetail(LeaveBean data) {
        if (TextUtils.isEmpty(data.getAsk_id())) {
            orderEmpty();
            return;
        }
        bean = data;
        //wait_audit:未审核 auditing:审核中 accept:接受 refuse:拒绝


        String appOperation = permissionsBean.getApp_operation();
        //appOperation  0新增 1编辑 2删除 3审核 4导出
        String[] strings = appOperation.split(",");
        switch (data.getAsk_state()) {
            case "wait_audit":

                if (userBean.getStaff_id().equals(data.getStaff_id())) {//是自己的单据 只控制删除权限
                    llCheck.setVisibility(View.GONE);
                    if (isHave("2", strings)) {
                        tvRight.setVisibility(View.VISIBLE);
                        tvRight.setText("更多");
                    } else {
                        tvRight.setVisibility(View.VISIBLE);
                        tvRight.setText("编辑");
                    }
                } else {//不是自己的单据 控制删除编辑权限
                    if (isHave("1", strings) && isHave("2", strings)) {
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

                    isShowCheck(data.getNext_audit_staff_id(), data.getAskLeaveAuditBeans().size());
                }
                tvState.setTextColor(getResources().getColor(R.color.audit_one));
                break;
            case "auditing":
                isShowCheck(data.getNext_audit_staff_id(), data.getAskLeaveAuditBeans().size());
                tvState.setTextColor(getResources().getColor(R.color.audit_one));
                break;
            case "accept":
                auditGone();
                tvState.setTextColor(getResources().getColor(R.color.audit_two));
                break;
            case "refuse":
                auditGone();
                tvState.setTextColor(getResources().getColor(R.color.red));
                break;
        }

        tvNo.setText(data.getAsk_no());
        tvName.setText(data.getStaff_name());
        tvJobName.setText(data.getJob());
        tvTime.setText(data.getCreate_time());
        tvState.setText(data.getAsk_state_show());
        tvDepartment.setText(data.getDepartment_name());
        tvOne.setText(data.getAsk_type());
        tvTwo.setText(data.getStart_time());
        tvThree.setText(data.getEnd_time());
        tvFour.setText(data.getAll_time());
        tvFive.setText(data.getAsk_why());

        if (data.getAskLeaveFileBeans().size() <= 0) {
            llImg.setVisibility(View.GONE);
        } else {
            llImg.setVisibility(View.VISIBLE);
            imgAdapter.clear();
            imgAdapter.addAll(data.getAskLeaveFileBeans());
            imgAdapter.notifyDataSetChanged();
        }

        auditAdapter.clear();
        auditAdapter.addAll(data.getAskLeaveAuditBeans());
        auditAdapter.notifyDataSetChanged();
        auditSize = data.getAskLeaveAuditBeans().size();
        //审批三级时不选审批人
        if (auditSize > 2) {
            llCheck.setVisibility(View.GONE);
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


    //是否显示选择审核人
    private void isShowCheck(String next_id, int auditSize) {
        if (userBean.getStaff_id().equals(next_id)) {

            if (auditSize > 2) {
                llCheck.setVisibility(View.GONE);
                llCopy.setVisibility(View.GONE);
            } else {
                llCheck.setVisibility(View.VISIBLE);
                llCopy.setVisibility(View.VISIBLE);
            }
            llBottom.setVisibility(View.VISIBLE);
        } else {
            auditGone();
        }
    }


    //隐藏选择审核人和抄送以及审核部分
    private void auditGone() {
        llCheck.setVisibility(View.GONE);
        llCopy.setVisibility(View.GONE);
        llBottom.setVisibility(View.GONE);
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


    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.circleImageVIew, R.id.ivCheckDelete,
            R.id.tvAgree, R.id.tvRefuse})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                switch (tvRight.getText().toString()) {
                    case "编辑":
                        startLeaveAddFragment(bean, "1", permissionsBean);
                        break;
                    case "删除":
                        toDelete();
                        break;
                    case "更多":
                        showList();
                        break;
                }

                break;
            case R.id.circleImageVIew:
                startChooseCheckPersonFragment("1");
                break;
            case R.id.ivCheckDelete:
                checkClear();
                break;
            case R.id.tvAgree:
                if (auditSize < 3) {//审批最少三级
                    if (TextUtils.isEmpty(next_audit_id)) {
                        ToastUtil.showToast(context.getApplicationContext(), getString(R.string.please_select_next_reviewer));
                        return;
                    }
                }
                toAgree();
                break;
            case R.id.tvRefuse:
                toRefush();
                break;
        }
    }

    //删除编辑选择框
    private AlertDialog alertDialog;

    public void showList() {
        final String[] items = {"编辑", "删除"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        startLeaveAddFragment(bean, "1", permissionsBean);
                        break;
                    case 1:
                        toDelete();
                        break;
                }
                alertDialog.dismiss();
            }
        });
        alertDialog = alertBuilder.create();
        alertDialog.show();
    }

    //删除单据
    private void toDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.sure_delete_request);
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("ask_id", detail_id);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "2");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().deleteAskLeave(userBean.getStaff_token(), map);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    //审批拒绝
    private void toRefush() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View inflate1 = LayoutInflater.from(context).inflate(R.layout.dialog_edittext_two, null);
        final EditText edit1 = (EditText) inflate1.findViewById(R.id.editText);
        builder.setView(inflate1);
        builder.setNegativeButton(getString(R.string.cancel), null);
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String s = edit1.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.enter_reason_rejection));
                    return;
                }
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("ask_id", detail_id);
                map.put("audit_content", s);
                map.put("audit_state", "not_pass");
                getPresenter().auditAskLeave(userBean.getStaff_token(), map);
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

    }

    //审批同意
    private void toAgree() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_edittext, null);
        final EditText edit = (EditText) inflate.findViewById(R.id.editText);
        builder.setView(inflate);
        builder.setNegativeButton(getString(R.string.cancel), null);
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialogLoadding != null) {
                    dialogLoadding = new DialogLoadding(context);
                    dialogLoadding.showDialog();
                }
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
                map.put("ask_id", detail_id);
                map.put("audit_state", "pass");
                map.put("cc_person", cc_person);
                map.put("cc_person_name", cc_person_name);
                map.put("audit_content", edit.getText().toString());
                if (!TextUtils.isEmpty(next_audit_id)) {
                    map.put("next_audit_staff_id", next_audit_id);
                }
                getPresenter().auditAskLeave(userBean.getStaff_token(), map);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //清空审批人
    private void checkClear() {
        ivCheckDelete.setVisibility(View.GONE);
        next_audit_id = "";
        tvCheckPersonName.setText("");
        Glide.with(context).load(Constants.BASE_URL)
                .error(R.drawable.check_img)
                .into(circleImageVIew);

    }

    @Override
    public void auditAskLeave(String data) {
        if (dialogLoadding != null) {
            dialogLoadding.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        refresh();
    }

    @Override
    public void onDeleteAskLeave(String data) {
        EventBus.getDefault().post(new FirstEventFilter("leave_delete"));
        ToastUtil.showToast(context.getApplicationContext(), "删除成功");
        finish();
    }

    @Override
    public void onGetCheckPerson(List<MeParentBean> data) {
        for (int i = 0; i < data.size(); i++) {
            String module = data.get(i).getStaff_module();
            if (module.contains("AskLeave")) {
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
    public void onUploadFiles(String[] data) {

    }

    @Override
    public void onInsertAskLeave(String data) {

    }


    @Override
    public void onGetAskLeaveList(List<LeaveBean> data, String total) {

    }

    @Override
    public void onDeleteAskLeaveFile(String data) {

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
    public LeavePresenter createPresenter() {
        return new LeavePresenter(getApp());
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


}