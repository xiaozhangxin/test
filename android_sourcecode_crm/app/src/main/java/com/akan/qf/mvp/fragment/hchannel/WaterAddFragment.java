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
import com.akan.qf.bean.UserBean;
import com.akan.qf.bean.WaterBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.channel.WaterPresenter;
import com.akan.qf.mvp.view.channel.IWaterView;
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
 * Created by admin on 2018/11/7.
 */

public class WaterAddFragment extends BaseFragment<IWaterView, WaterPresenter> implements IWaterView {


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
    @BindView(R.id.textView28)
    TextView textView28;
    @BindView(R.id.tvFour)
    EditText tvFour;
    @BindView(R.id.tvFive)
    EditText tvFive;
    @BindView(R.id.tvSix)
    EditText tvSix;
    @BindView(R.id.tvPee)
    TextView tvPee;
    @BindView(R.id.tvSeven)
    EditText tvSeven;
    @BindView(R.id.tvEight)
    EditText tvEight;
    @BindView(R.id.textView29)
    TextView textView29;
    @BindView(R.id.tvNine)
    EditText tvNine;
    @BindView(R.id.tvTwelve)
    EditText tvTwelve;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.tvCity)
    EditText tvCity;
    @BindView(R.id.tvNature)
    TextView tvNature;
    @BindView(R.id.tvCustomer)
    EditText tvCustomer;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String type;
    private WaterBean data;

    private PermissionsBean permissionsBean;
    public static WaterAddFragment newInstance(WaterBean bean, String type,PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        WaterAddFragment fragment = new WaterAddFragment();
        fragment.data = bean;
        fragment.permissionsBean=permissionsBean;
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_water_add;
    }

    @Override
    public void initUI() {
        switch (type) {
            case "0":
                getData();
                ok.setText("提交");
                tvTitle.setText("工长信息");
                break;
            case "1":
                setData();
                break;

        }
    }

    private void setData() {
        tvTitle.setText("修改工长信息");
        ok.setText("确认修改");
        tvOne.setText(data.getApply_department());
        tvTwo.setText(data.getApply_name());
        tvThree.setText(data.getApply_tel());
        tvFour.setText(data.getApply_native());
        tvFive.setText(data.getApply_time());
        tvSix.setText(data.getApply_num());
        tvSeven.setText(data.getApply_brand());
        tvEight.setText(data.getApply_shop());
        tvNine.setText(data.getApply_reward());
       // tvTwelve.setText(data.getApply_remark());
        tvCity.setText(data.getApply_city());
        tvCustomer.setText(data.getApply_customer());
        tvNature.setText(data.getApply_quality());
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
                    ToastUtil.showToast(context.getApplicationContext(), "请输入姓名");
                    return;
                }
                String mtvThree = tvThree.getText().toString().trim();
                if (TextUtils.isEmpty(mtvThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选输入电话");
                    return;
                }

                ok.setEnabled(false);
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("apply_department", mtvOne);
                map.put("apply_name", mtvTwo);
                map.put("apply_tel", mtvThree);
                map.put("apply_native", tvFour.getText().toString());
                map.put("apply_time", tvFive.getText().toString());
                map.put("apply_num", tvSix.getText().toString());
                map.put("apply_brand", tvSeven.getText().toString());
                map.put("apply_shop", tvEight.getText().toString());
                map.put("apply_reward", tvNine.getText().toString());
                map.put("track_remark", tvTwelve.getText().toString());
                map.put("apply_city", tvCity.getText().toString());//城市
                map.put("apply_customer", tvCustomer.getText().toString());//归属客户
                map.put("apply_quality", tvNature.getText().toString());//性质
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "0");
                map.put("module_id", permissionsBean.getMenu_id());
                if (!"0".equals(type)) {
                    map.put("operation", "1");
                    map.put("apply_id", data.getApply_id());
                }
                getPresenter().insertOrUpdateForemanApply(userBean.getStaff_token(), map);
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
    public WaterPresenter createPresenter() {
        return new WaterPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        saveData();
        unbinder.unbind();
    }

    private void saveData() {
        WaterBean bean = new WaterBean();


        bean.setApply_department(tvOne.getText().toString());
        bean.setApply_name(tvTwo.getText().toString());
        bean.setApply_tel(tvThree.getText().toString());
        bean.setApply_native(tvFour.getText().toString());
        bean.setApply_time(tvFive.getText().toString());
        bean.setApply_num(tvSix.getText().toString());
        bean.setApply_brand(tvSeven.getText().toString());
        bean.setApply_shop(tvEight.getText().toString());
        bean.setApply_reward(tvNine.getText().toString());
        bean.setApply_remark(tvTwelve.getText().toString());
        bean.setApply_city(tvCity.getText().toString());
        bean.setApply_customer(tvCustomer.getText().toString());
        bean.setApply_quality(tvNature.getText().toString());
        String s = new Gson().toJson(bean);
        SharedPreferences sharedPre = context.getSharedPreferences("beanData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("company", s);
        editor.commit();
    }

    private void getData() {
        SharedPreferences sp = context.getSharedPreferences("beanData", MODE_PRIVATE);
        String s = sp.getString("company", "");
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject object = new JSONObject(s);

                tvOne.setText(object.getString("apply_department"));
                tvTwo.setText(object.getString("apply_name"));
                tvThree.setText(object.getString("apply_tel"));
                tvFour.setText(object.getString("apply_native"));
                tvFive.setText(object.getString("apply_time"));
                tvSix.setText(object.getString("apply_num"));
                tvSeven.setText(object.getString("apply_brand"));
                tvEight.setText(object.getString("apply_shop"));
                tvNine.setText(object.getString("apply_reward"));
                tvTwelve.setText(object.getString("apply_remark"));
                tvCity.setText(object.getString("apply_city"));
                tvCustomer.setText(object.getString("apply_customer"));
                tvNature.setText(object.getString("apply_quality"));

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
    public void OnInsertOrUpdateForemanApply(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        tvOne.setText("");
        tvTwo.setText("");
        tvThree.setText("");
        tvFour.setText("");
        tvFive.setText("");
        tvSix.setText("");
        tvSeven.setText("");
        tvEight.setText("");
        tvNine.setText("");
        tvTwelve.setText("");
        tvCity.setText("");
        tvCustomer.setText("");
        tvNature.setText("");

        finish();
    }

    @Override
    public void OnGetForemanApplyList(List<WaterBean> data, String total) {

    }

    @Override
    public void OnGetForemanApply(WaterBean data) {

    }

    @Override
    public void OnAuditForemanApply(String data) {

    }

    @Override
    public void OnDeleteForemanApply(String data) {

    }
}