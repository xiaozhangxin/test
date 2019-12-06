package com.akan.qf.mvp.fragment.bapproval;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.FirstEventNew;
import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.ReprotedBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.ReportPresenter;
import com.akan.qf.mvp.view.home.IReportView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.DialogLoadding;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2018/11/15.
 */

public class ReportAddFragment extends BaseFragment<IReportView, ReportPresenter> implements IReportView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvDepartment)
    EditText tvDepartment;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvSaleName)
    EditText tvSaleName;
    @BindView(R.id.tvProject)
    EditText tvProject;
    @BindView(R.id.tvAddress)
    EditText tvAddress;
    @BindView(R.id.tvAName)
    EditText tvAName;
    @BindView(R.id.tvAPhone)
    EditText tvAPhone;
    @BindView(R.id.tvBName)
    EditText tvBName;
    @BindView(R.id.tvBPhone)
    EditText tvBPhone;
    @BindView(R.id.tvArea)
    EditText tvArea;
    @BindView(R.id.tvDealerName)
    EditText tvDealerName;
    @BindView(R.id.tvDealerRelationship)
    EditText tvDealerRelationship;
    @BindView(R.id.tvNum)
    EditText tvNum;
    @BindView(R.id.tvCompetition)
    EditText tvCompetition;
    @BindView(R.id.tvAreaSuggest)
    EditText tvAreaSuggest;
    @BindView(R.id.tvDealerSuggest)
    EditText tvDealerSuggest;
    @BindView(R.id.tvCompanySuggest)
    EditText tvCompanySuggest;
    @BindView(R.id.tvRemark)
    EditText tvRemark;
    @BindView(R.id.tvCheck)
    TextView tvCheck;
    @BindView(R.id.circleImageVIew)
    CircleImageView circleImageVIew;
    @BindView(R.id.ivCheckDelete)
    ImageView ivCheckDelete;
    @BindView(R.id.tvCheckPersonName)
    TextView tvCheckPersonName;
    @BindView(R.id.ok)
    TextView ok;
    Unbinder unbinder;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mNext_audit_id = "";
    private ReprotedBean data;
    private String type;
    private DialogLoadding dialogLoadding;

    private PermissionsBean permissionsBean;
    public static ReportAddFragment newInstance(ReprotedBean bean, String type,PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        ReportAddFragment fragment = new ReportAddFragment();
        fragment.type = type;
        fragment.data = bean;
        fragment.permissionsBean=permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_report;
    }

    @Override
    public void initUI() {
        if ("0".equals(type)) {
            getData();
            tvTitle.setText("新增工程报备申请");
            ok.setText("确认申请");
        } else {
            tvTitle.setText("修改工程报备申请");
            ok.setText("确认修改");
            tvName.setText(data.getApply_name());
            tvTime.setText(data.getApply_create_time());
            tvDepartment.setText(data.getStaff_department());
            tvType.setText(data.getApply_category());
            tvSaleName.setText(data.getApply_name());
            tvProject.setText(data.getApply_title());
            tvAddress.setText(data.getApply_address());
            tvAName.setText(data.getApply_first());
            tvBName.setText(data.getApply_second());
            tvAPhone.setText(data.getApply_first_tel());
            tvBPhone.setText(data.getApply_second_tel());
            tvArea.setText(data.getApply_size());

            tvDealerName.setText(data.getApply_shop());
            tvDealerRelationship.setText(data.getApply_join());
            tvNum.setText(data.getApply_number());
            tvCompetition.setText(data.getApply_compete());
            tvAreaSuggest.setText(data.getApply_area());
            tvDealerSuggest.setText(data.getApply_suggest());
            tvCompanySuggest.setText(data.getApply_company());
            tvRemark.setText(data.getApply_remark());
        }

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        tvName.setText(userBean.getStaff_name());
        tvDepartment.setText(userBean.getSimple_department_name());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tvTime.setText(str);

        if ("0".equals(type)) {
            if (!TextUtils.isEmpty(userBean.getParent_id())) {
                mNext_audit_id = userBean.getParent_id();
                Glide.with(context)
                        .load(Constants.BASE_URL + userBean.getParent_head_img())
                        .error(R.drawable.error_img)
                        .into(circleImageVIew);
                tvCheckPersonName.setText(userBean.getParent_staff_name());
            }
            map.clear();
            map.put("group_id", userBean.getDepartment_id());
            map.put("staff_id", userBean.getStaff_id());
            getPresenter().getCheckPerson(userBean.getStaff_token(), map);
        } else {

            mNext_audit_id = data.getNext_staff_id();
            Glide.with(context)
                    .load(Constants.BASE_URL + data.getNext_audit_staff_head_img())
                    .error(R.drawable.error_img)
                    .into(circleImageVIew);
            tvCheckPersonName.setText(data.getNext_audit_staff_name());
        }

    }

    @Override
    public void OnInsertProjectApply(String data) {
        dialogLoadding.closeDialog();
        ToastUtil.showToast(context.getApplicationContext(), data);
        tvSaleName.setText("");
        tvProject.setText("");
        tvAddress.setText("");
        tvAName.setText("");
        tvBName.setText("");
        tvAPhone.setText("");
        tvBPhone.setText("");
        tvArea.setText("");
        tvDealerName.setText("");
        tvDealerRelationship.setText("");
        tvNum.setText("");
        tvCompetition.setText("");
        tvAreaSuggest.setText("");
        tvDealerSuggest.setText("");
        tvCompanySuggest.setText("");
        tvRemark.setText("");
        finish();
    }


    @OnClick({R.id.ivLeft, R.id.tvType, R.id.ok, R.id.circleImageVIew, R.id.ivCheckDelete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.circleImageVIew:
                startChooseCheckPersonFragment("1");
                break;
            case R.id.ivCheckDelete:
                ivCheckDelete.setVisibility(View.GONE);
                tvCheckPersonName.setText("请选择");
                mNext_audit_id = "";
                Glide.with(context).load("").error(R.drawable.check_img).into(circleImageVIew);
                break;
            case R.id.tvType:
                showSingleAlertDialog("类型", new String[]{"同区", "跨区"});
                break;
            case R.id.ok:
                String mtvDepartment = tvDepartment.getText().toString().trim();
                if (TextUtils.isEmpty(mtvDepartment)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入部门");
                    return;
                }
                String mtvType = tvType.getText().toString().trim();
                if (TextUtils.isEmpty(mtvType)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择类型");
                    return;
                }
                String mtvSaleName = tvSaleName.getText().toString().trim();
                if (TextUtils.isEmpty(mtvSaleName)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入业务员姓名");
                    return;
                }
                if (TextUtils.isEmpty(mNext_audit_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择审批人");
                    return;
                }
                ok.setEnabled(false);
                dialogLoadding = new DialogLoadding(context);
                dialogLoadding.showDialog();
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("apply_department", mtvDepartment);
                map.put("apply_category", mtvType);
                map.put("apply_name", mtvSaleName);
                map.put("apply_title", tvProject.getText().toString());
                map.put("apply_address", tvAddress.getText().toString());
                map.put("apply_first", tvAName.getText().toString());
                map.put("apply_second", tvBName.getText().toString());
                map.put("apply_first_tel", tvAPhone.getText().toString());
                map.put("apply_second_tel", tvBPhone.getText().toString());
                map.put("apply_size", tvArea.getText().toString());
                map.put("apply_shop", tvDealerName.getText().toString());
                map.put("apply_join", tvDealerRelationship.getText().toString());
                map.put("apply_number", tvNum.getText().toString());
                map.put("apply_compete", tvCompetition.getText().toString());
                map.put("apply_area", tvAreaSuggest.getText().toString());
                map.put("apply_suggest", tvDealerSuggest.getText().toString());
                map.put("apply_company", tvCompanySuggest.getText().toString());
                map.put("apply_remark", tvRemark.getText().toString());
                map.put("next_staff_id", mNext_audit_id);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());

                if ("0".equals(type)) {
                    map.put("operation", "0");
                    getPresenter().insertProjectApply(userBean.getStaff_token(), map);
                } else {
                    map.put("operation", "1");
                    map.put("apply_id", data.getApply_id());
                    getPresenter().updateProjectApply(userBean.getStaff_token(), map);
                }
                break;
        }
    }

    private AlertDialog alertDialog2;
    private int choose = 0;

    public void showSingleAlertDialog(final String tittle, final String[] items) {
        choose = 0;
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle(tittle);
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose = i;
            }
        });
        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvType.setText(items[choose]);
                alertDialog2.dismiss();
            }
        });

        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog2.dismiss();
            }
        });
        alertDialog2 = alertBuilder.create();
        alertDialog2.show();
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
        saveData();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    private void saveData() {
        ReprotedBean bean = new ReprotedBean();





        bean.setApply_name(tvSaleName.getText().toString());
        bean.setApply_title(tvProject.getText().toString());
        bean.setApply_address(tvAddress.getText().toString());
        bean.setApply_first(tvAName.getText().toString());
        bean.setApply_second(tvBName.getText().toString());
        bean.setApply_first_tel(tvAPhone.getText().toString());
        bean.setApply_second_tel(tvBPhone.getText().toString());
        bean.setApply_size(tvArea.getText().toString());
        bean.setApply_shop(tvDealerName.getText().toString());
        bean.setApply_join(tvDealerRelationship.getText().toString());
        bean.setApply_number(tvNum.getText().toString());
        bean.setApply_compete(tvCompetition.getText().toString());
        bean.setApply_area(tvAreaSuggest.getText().toString());
        bean.setApply_suggest(tvDealerSuggest.getText().toString());
        bean.setApply_company(tvCompanySuggest.getText().toString());
        bean.setApply_remark(tvRemark.getText().toString());
        String s = new Gson().toJson(bean);
        SharedPreferences sharedPre = context.getSharedPreferences("beanData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("report", s);
        editor.commit();
    }
    private void getData() {
        SharedPreferences sp = context.getSharedPreferences("beanData", MODE_PRIVATE);
        String s = sp.getString("report", "");
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject object = new JSONObject(s);
                tvSaleName.setText(object.getString("apply_name"));
                tvProject.setText(object.getString("apply_title"));
                tvAddress.setText(object.getString("apply_address"));
                tvAName.setText(object.getString("apply_first"));
                tvBName.setText(object.getString("apply_second"));
                tvAPhone.setText(object.getString("apply_first_tel"));
                tvBPhone.setText(object.getString("apply_second_tel"));
                tvArea.setText(object.getString("apply_size"));
                tvDealerName.setText(object.getString("apply_shop"));
                tvDealerRelationship.setText(object.getString("apply_join"));
                tvNum.setText(object.getString("apply_number"));
                tvCompetition.setText(object.getString("apply_compete"));
                tvAreaSuggest.setText(object.getString("apply_area"));
                tvDealerSuggest.setText(object.getString("apply_suggest"));
                tvCompanySuggest.setText(object.getString("apply_company"));
                tvRemark.setText(object.getString("apply_remark"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
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
                mNext_audit_id = bean.getStaff_id();
                break;
        }

    }


    @Override
    public ReportPresenter createPresenter() {
        return new ReportPresenter(getApp());
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
        ok.setEnabled(true);
    }


    @Override
    public void OnGetProjectApplyList(List<ReprotedBean> data, String total) {

    }

    @Override
    public void OnGetProjectApply(ReprotedBean data) {

    }

    @Override
    public void OnAuditProjectApply(String data) {

    }

    @Override
    public void OnDeleteProjectApply(String data) {

    }

    @Override
    public void OnupdateProjectApply(String data) {
        dialogLoadding.closeDialog();
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();

    }

    @Override
    public void onGetCheckPerson(List<MeParentBean> data) {
        for (int i = 0; i < data.size(); i++) {
            String module = data.get(i).getStaff_module();
            if (module.contains("ProjectApply")) {
                String staff_give = data.get(i).getStaff_give();
                if ("1".equals(staff_give)) {
                    MeParentBean checkBean = data.get(i);
                    mNext_audit_id = checkBean.getStaff_id();
                    Glide.with(context)
                            .load(Constants.BASE_URL + checkBean.getHead_img())
                            .error(R.drawable.error_img)
                            .into(circleImageVIew);
                    tvCheckPersonName.setText(checkBean.getStaff_name());
                }

            }
        }
    }
}
