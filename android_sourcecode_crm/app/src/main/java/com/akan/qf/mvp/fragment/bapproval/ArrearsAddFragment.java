package com.akan.qf.mvp.fragment.bapproval;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.DebtApplyBean;
import com.akan.qf.bean.FirstEventNew;
import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.ArrearsPresenter;
import com.akan.qf.mvp.view.home.IArrearsView;
import com.akan.qf.util.CashierInputFilter;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.util.VerificationUtil;
import com.akan.qf.view.DialogLoadding;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.king.base.util.ToastUtils;

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

public class ArrearsAddFragment extends BaseFragment<IArrearsView, ArrearsPresenter> implements IArrearsView {

    Unbinder unbinder;
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
    TextView tvDepartment;
    @BindView(R.id.tvOne)
    EditText tvOne;
    @BindView(R.id.tvTwo)
    EditText tvTwo;
    @BindView(R.id.tvThree)
    EditText tvThree;
    @BindView(R.id.tvFour)
    EditText tvFour;
    @BindView(R.id.tvFive)
    EditText tvFive;
    @BindView(R.id.tvSix)
    EditText tvSix;
    @BindView(R.id.tvSeven)
    EditText tvSeven;
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

    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String next_audit_id = "";
    private DebtApplyBean data;
    private String type;
    private PermissionsBean permissionsBean;

    public static ArrearsAddFragment newInstance(DebtApplyBean bean, String type,PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        ArrearsAddFragment fragment = new ArrearsAddFragment();
        fragment.type = type;
        fragment.data = bean;
        fragment.permissionsBean=permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_arrears;
    }

    @Override
    public void initUI() {
        InputFilter[] filters = {new CashierInputFilter(2)};
        tvSeven.setFilters(filters);
        if ("0".equals(type)) {
            getData();
            tvTitle.setText("新增欠款申请");
            ok.setText("确认申请");
        } else {
            tvTitle.setText("修改欠款申请");
            ok.setText("确认修改");
            tvName.setText(data.getApply_name());
            tvTime.setText(data.getApply_create_time());
            tvDepartment.setText(data.getStaff_department());
            tvOne.setText(data.getCustomer_no());
            tvTwo.setText(data.getApply_name());
            tvThree.setText(data.getApply_number());
            tvFour.setText(data.getApply_assure());
            tvFive.setText(data.getAssure_no());
            tvSix.setText(data.getApply_goods());
            tvSeven.setText(data.getApply_money());
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
                next_audit_id = userBean.getParent_id();
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

            next_audit_id = data.getNext_staff_id();
            Glide.with(context)
                    .load(Constants.BASE_URL + data.getNext_audit_staff_head_img())
                    .error(R.drawable.error_img)
                    .into(circleImageVIew);
            tvCheckPersonName.setText(data.getNext_audit_staff_name());
        }

    }


    @Override
    public void OnInsertOrUpdateDebtApply(String data) {
        dialogLoadding.closeDialog();
        ToastUtil.showToast(context.getApplicationContext(), data);
        tvOne.setText("");
        tvTwo.setText("");
        tvThree.setText("");
        tvFour.setText("");
        tvFive.setText("");
        tvSix.setText("");
        tvSeven.setText("");
        finish();
    }

    @Override
    public void OnDeleteDebtApply(String data) {

    }

    @Override
    public void onGetCheckPerson(List<MeParentBean> data) {
        for (int i = 0; i < data.size(); i++) {
            String module = data.get(i).getStaff_module();
            if (module.contains("DebtApply")) {
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

    @OnClick({R.id.ivLeft, R.id.ok, R.id.circleImageVIew, R.id.ivCheckDelete})
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
                next_audit_id = "";
                Glide.with(context).load("").error(R.drawable.check_img).into(circleImageVIew);
                break;
            case R.id.ok:

                String mtvOne = tvOne.getText().toString().trim();
                if (TextUtils.isEmpty(mtvOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入客户抬头");
                    return;
                }
                String mtvTwo = tvTwo.getText().toString().trim();
                if (TextUtils.isEmpty(mtvTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入姓名");
                    return;
                }
                String mtvThree = tvThree.getText().toString().trim();
                if (TextUtils.isEmpty(mtvThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入身份证号");
                    return;
                }
                if (!VerificationUtil.isIDNumber(mtvThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入正确的身份证号");
                    return;
                }
                String mtvFour = tvFour.getText().toString().trim();
                if (TextUtils.isEmpty(mtvFour)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入担保人姓名");
                    return;
                }
                String mtvFive = tvFive.getText().toString().trim();
                if (TextUtils.isEmpty(mtvFive)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入担保人身份证号");
                    return;
                }
                if (!VerificationUtil.isIDNumber(mtvFive)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入正确的担保人身份证号");
                    return;
                }
                String mtvSix = tvSix.getText().toString().trim();
                if (TextUtils.isEmpty(mtvSix)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入抵押物");
                    return;
                }
                String mtvSeven = tvSeven.getText().toString().trim();
                if (TextUtils.isEmpty(mtvSeven)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入金额");
                    return;
                }
                if (TextUtils.isEmpty(next_audit_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择审批人");
                    return;
                }
                ok.setEnabled(false);
                dialogLoadding = new DialogLoadding(context);
                dialogLoadding.showDialog();

                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("staff_department", userBean.getSimple_department_name());
                map.put("staff_name", userBean.getStaff_name());

                map.put("customer_no", mtvOne);
                map.put("apply_name", mtvTwo);
                map.put("apply_number", mtvThree);
                map.put("apply_assure", mtvFour);
                map.put("assure_no", mtvFive);
                map.put("apply_goods", mtvSix);
                map.put("apply_money", mtvSeven);
                map.put("next_staff_id", next_audit_id);//负责人评定

                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());
                map.put("operation", "0");
                if ("1".equals(type)) {
                    map.put("operation", "1");
                    map.put("apply_id", data.getApply_id());
                }
                getPresenter().insertOrUpdateDebtApply(userBean.getStaff_token(), map);

                break;
        }
    }

    private DialogLoadding dialogLoadding;

    @Override
    public ArrearsPresenter createPresenter() {
        return new ArrearsPresenter(getApp());
    }



    private void saveData() {
        DebtApplyBean bean = new DebtApplyBean();
        bean.setCustomer_no(tvOne.getText().toString());
        bean.setApply_name(tvTwo.getText().toString());
        bean.setApply_number(tvThree.getText().toString());
        bean.setApply_assure(tvFour.getText().toString());
        bean.setAssure_no(tvFive.getText().toString());
        bean.setApply_goods(tvSix.getText().toString());
        bean.setApply_money(tvSeven.getText().toString());
        String s = new Gson().toJson(bean);
        SharedPreferences sharedPre = context.getSharedPreferences("beanData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("arrears", s);
        editor.commit();
    }

    private void getData() {
        SharedPreferences sp = context.getSharedPreferences("beanData", MODE_PRIVATE);
        String s = sp.getString("arrears", "");
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject object = new JSONObject(s);
                tvOne.setText(object.getString("customer_no"));
                tvTwo.setText(object.getString("apply_name"));
                tvThree.setText(object.getString("apply_number"));
                tvFour.setText(object.getString("apply_assure"));
                tvFive.setText(object.getString("assure_no"));
                tvSix.setText(object.getString("apply_goods"));
                tvSeven.setText(object.getString("apply_money"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
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
    public void OnAuditDebtApply(String data) {

    }

    @Override
    public void OnGetDebtApply(DebtApplyBean data) {

    }

    @Override
    public void OnGetDebtApplyList(List<DebtApplyBean> data, String total) {

    }


}
