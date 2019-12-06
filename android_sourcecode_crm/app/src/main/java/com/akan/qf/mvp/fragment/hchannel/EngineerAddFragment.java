package com.akan.qf.mvp.fragment.hchannel;

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

import com.akan.qf.R;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.StoreApplyBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.ProjectPresenter;
import com.akan.qf.mvp.view.home.IProjectView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.google.gson.Gson;

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

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2019/4/4.
 */

public class EngineerAddFragment extends BaseFragment<IProjectView, ProjectPresenter> implements IProjectView {


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
    @BindView(R.id.tvCompanyName)
    TextView tvCompanyName;
    @BindView(R.id.tvTwo)
    EditText tvTwo;
    @BindView(R.id.tvThree)
    EditText tvThree;
    @BindView(R.id.tvFour)
    EditText tvFour;
    @BindView(R.id.tvFive)
    EditText tvFive;
    @BindView(R.id.tvCity)
    EditText tvCity;
    @BindView(R.id.tvNature)
    TextView tvNature;
    @BindView(R.id.tvCustomer)
    EditText tvCustomer;
    @BindView(R.id.tvTwelve)
    EditText tvTwelve;
    @BindView(R.id.ok)
    TextView ok;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String type;
    private StoreApplyBean data;
    private PermissionsBean permissionsBean;

    public static EngineerAddFragment newInstance(StoreApplyBean bean, String type,PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        EngineerAddFragment fragment = new EngineerAddFragment();
        fragment.data = bean;
        fragment.type = type;
        fragment.permissionsBean=permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_engineer_add;
    }

    @Override
    public void initUI() {

        switch (type) {
            case "0":
                getData();
                ok.setText("提交");
                tvTitle.setText("工程项目");
                break;
            case "1":
                setData();
                break;
        }
    }

    private void setData() {
        tvTitle.setText("修改工程项目");
        ok.setText("确认修改");
        tvOne.setText(data.getApply_department());
        tvTwo.setText(data.getApply_name());
        tvThree.setText(data.getApply_address());
        tvFour.setText(data.getApply_product());
        tvFive.setText(data.getApply_number());

        tvCity.setText(data.getApply_city());
        // tvCustomer.setText(data.getApply_customer());
        // tvNature.setText(data.getApply_quality());
    }


    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tvTime.setText(str);
        tvName.setText(userBean.getStaff_name());
        tvOne.setText(userBean.getSimple_department_name());
    }


    @OnClick({R.id.ivLeft, R.id.ok, R.id.tvNature})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvNature:
                showSingleAlertDialog("选择性质", new String[]{"已合作", "意向"});
                break;
            case R.id.ok:
                String mtvOne = tvOne.getText().toString().trim();
                if (TextUtils.isEmpty(mtvOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入部门");
                    return;
                }
                String mtvTwo = tvTwo.getText().toString().trim();
                if (TextUtils.isEmpty(mtvTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入项目名称");
                    return;
                }
                String mtvThree = tvThree.getText().toString().trim();
                if (TextUtils.isEmpty(mtvThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入项目地址");
                    return;
                }
                String mtvFour = tvFour.getText().toString().trim();
                if (TextUtils.isEmpty(mtvFour)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入使用产品");
                    return;
                }
                String mtvFive = tvFive.getText().toString().trim();
                if (TextUtils.isEmpty(mtvFive)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入大概用量");
                    return;
                }
                String mtvCity = tvCity.getText().toString().trim();
                if (TextUtils.isEmpty(mtvCity)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入所在城市");
                    return;
                }

                ok.setEnabled(false);
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("apply_department", mtvOne);
                map.put("apply_name", mtvTwo);
                map.put("apply_address", mtvThree);
                map.put("apply_product", mtvFour);
                map.put("apply_number", mtvFive);
                map.put("apply_city", mtvCity);//城市
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "0");
                map.put("module_id", permissionsBean.getMenu_id());
                // map.put("apply_customer", tvCustomer.getText().toString());//归属客户
                //  map.put("apply_quality", tvNature.getText().toString());//性质
                if (!"0".equals(type)) {
                    map.put("operation", "1");
                    map.put("apply_id", data.getApply_id());
                }
                getPresenter().insertOrUpdateStoreApply(userBean.getStaff_token(), map);
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
                tvNature.setText(items[choose]);
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
        return rootView;
    }

    @Override
    public ProjectPresenter createPresenter() {
        return new ProjectPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        saveData();
        unbinder.unbind();
    }


    //退出时保存数据
    private void saveData() {
        StoreApplyBean bean = new StoreApplyBean();
        bean.setApply_department(tvOne.getText().toString());
        bean.setApply_name(tvTwo.getText().toString());
        bean.setApply_address(tvThree.getText().toString());
        bean.setApply_product(tvFour.getText().toString());
        bean.setApply_number(tvFive.getText().toString());
        bean.setApply_city(tvCity.getText().toString());
        // bean.setApply_customer(tvCustomer.getText().toString());
        // bean.setApply_quality(tvNature.getText().toString());
        String s = new Gson().toJson(bean);
        SharedPreferences sharedPre = context.getSharedPreferences("beanData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("engineer", s);
        editor.commit();
    }

    //进入时恢复数据
    private void getData() {
        SharedPreferences sp = context.getSharedPreferences("beanData", MODE_PRIVATE);
        String s = sp.getString("engineer", "");
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject object = new JSONObject(s);
                tvOne.setText(object.getString("apply_department"));
                tvTwo.setText(object.getString("apply_name"));
                tvThree.setText(object.getString("apply_address"));
                tvFour.setText(object.getString("apply_product"));
                tvFive.setText(object.getString("apply_number"));
                tvCity.setText(object.getString("apply_city"));
                // tvCustomer.setText(object.getString("apply_customer"));
                //  tvNature.setText(object.getString("apply_quality"));

            } catch (JSONException e) {
                e.printStackTrace();
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
        ok.setEnabled(true);
    }


    @Override
    public void OnInsertOrUpdateStoreApply(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        tvOne.setText("");
        tvTwo.setText("");
        tvThree.setText("");
        tvFour.setText("");
        tvFive.setText("");
        tvCity.setText("");
        // tvCustomer.setText("");
        // tvNature.setText("");
        tvTwelve.setText("");

        finish();
    }

    @Override
    public void OnGetStoreApplyList(List<StoreApplyBean> data, String total) {

    }

    @Override
    public void OnGetStoreApply(StoreApplyBean data) {

    }

    @Override
    public void OnAuditStoreApply(String data) {

    }

    @Override
    public void OndeleteStoreApply(String data) {

    }
}
